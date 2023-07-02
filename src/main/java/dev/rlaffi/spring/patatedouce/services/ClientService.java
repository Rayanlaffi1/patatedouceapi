package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.*;
import dev.rlaffi.spring.patatedouce.repositories.AlimentRepository;
import dev.rlaffi.spring.patatedouce.repositories.ClientRepository;
import dev.rlaffi.spring.patatedouce.repositories.PanierAchatRepository;
import dev.rlaffi.spring.patatedouce.repositories.UtilisateurRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
	@Autowired
	private PanierAchatRepository panierAchatRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AlimentRepository alimentRepository;
	@Autowired
	private LiaisonUtilisateurService liaisonUtilisateurService;
	public Client getById(Long id) {
		return clientRepository.findById(id).orElseThrow();
	}
	public List<Client> getClients() {
		List<Client> clients = clientRepository.findAll();
		return clients;
	}
	public Client ajouterAliment(Integer alimentId, Integer qte) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authentication.getPrincipal();
		String userId = jwt.getSubject();
		if (authentication != null && userId != null) {
			Utilisateur utilisateur = liaisonUtilisateurService.getById(userId);
			Client client = clientRepository.findByEmail(utilisateur.getEmail());
			if(client != null){
				Aliment aliment = alimentRepository.findById(alimentId).orElseThrow();
				List<PanierAchat> panierAchats = client.getPanierAchats();
				for (PanierAchat panierAchat : panierAchats) {
					if (panierAchat.getAliment().equals(aliment)) {
						panierAchat.setQte(panierAchat.getQte() + qte);
						return clientRepository.save(client);
					}
				}

				PanierAchat panierAchat = new PanierAchat(aliment, client, qte);
				panierAchat = panierAchatRepository.save(panierAchat);
				panierAchats.add(panierAchat);
				client.setPanierAchats(panierAchats);
			}
			return clientRepository.save(client);
		} else {
			return null;
		}
	}

}
