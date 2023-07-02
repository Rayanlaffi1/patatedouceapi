package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.*;
import dev.rlaffi.spring.patatedouce.repositories.*;
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
	private RecettesFavorisRepository recettesFavorisRepository;
	@Autowired
	private RecetteRepository recetteRepository;
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
	public Client supprimerAliment(Integer alimentId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authentication.getPrincipal();
		String userId = jwt.getSubject();
		if (authentication != null && userId != null) {
			Utilisateur utilisateur = liaisonUtilisateurService.getById(userId);
			Client client = clientRepository.findByEmail(utilisateur.getEmail());
			if(client != null){
				Aliment aliment = alimentRepository.findById(alimentId).orElseThrow();
				List<PanierAchat> panierAchats = client.getPanierAchats();
				PanierAchat panierAchatToFind = null;
				for (PanierAchat panierAchat : panierAchats) {
					if (panierAchat.getAliment().equals(aliment)) {
						panierAchatToFind = panierAchat;
						break;
					}
				}
				if(panierAchatToFind != null){
					client.getPanierAchats().remove(panierAchatToFind);
				}
			}
			return clientRepository.save(client);
		} else {
			return null;
		}
	}

	public Client ajouterRecetteFavoris(Integer recette_id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authentication.getPrincipal();
		String userId = jwt.getSubject();
		if (authentication != null && userId != null) {
			Utilisateur utilisateur = liaisonUtilisateurService.getById(userId);
			Client client = clientRepository.findByEmail(utilisateur.getEmail());
			if(client != null){
				Recette recette = recetteRepository.findById(recette_id).orElseThrow();
				List<RecetteFavoris> recetteFavorites = client.getListeRecettesFavoris();
				for (RecetteFavoris recetteFavoris : recetteFavorites) {
					if (recetteFavoris.getRecette().equals(recette)) {
						return clientRepository.save(client);
					}
				}
				RecetteFavoris recetteFavoris = new RecetteFavoris(recette,client);
				recettesFavorisRepository.save(recetteFavoris);
				client.getListeRecettesFavoris().add(recetteFavoris);
			}
			return clientRepository.save(client);
		} else {
			return null;
		}
	}
	public Client supprimerRecetteFavoris(Integer recette_id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authentication.getPrincipal();
		String userId = jwt.getSubject();
		if (authentication != null && userId != null) {
			Utilisateur utilisateur = liaisonUtilisateurService.getById(userId);
			Client client = clientRepository.findByEmail(utilisateur.getEmail());
			if(client != null){
				Recette recette = recetteRepository.findById(recette_id).orElseThrow();
				List<RecetteFavoris> recetteFavorites = client.getListeRecettesFavoris();
				RecetteFavoris favorisToFind = null;
				for (RecetteFavoris favoris : recetteFavorites) {
					if (favoris.getRecette().equals(recette)) {
						favorisToFind = favoris;
						break;
					}
				}
				if(favorisToFind != null){
					client.getListeRecettesFavoris().remove(favorisToFind);
				}
			}
			return clientRepository.save(client);
		} else {
			return null;
		}
	}
}
