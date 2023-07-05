package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.entities.TypeAliment;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.exceptions.PermissionException;
import dev.rlaffi.spring.patatedouce.exceptions.ResourceNotFoundException;
import dev.rlaffi.spring.patatedouce.repositories.AlimentRepository;
import dev.rlaffi.spring.patatedouce.repositories.MaraicherRepository;
import dev.rlaffi.spring.patatedouce.repositories.TypeAlimentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlimentService {
	@Autowired
	private AlimentRepository alimentRepository;
	@Autowired
	private LiaisonUtilisateurService liaisonUtilisateurService;
	@Autowired
	private MaraicherRepository maraicherRepository;
	@Autowired
	private TypeAlimentRepository typeAlimentRepository;
	private static final Logger logger = LoggerFactory.getLogger(AlimentService.class);
	public Aliment get(Integer id) throws ResourceNotFoundException {
		return alimentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Aliment.class, id));
	}
	public List<Aliment> getAll() {
		return alimentRepository.findAll();
	}
	public void deleteById(Integer alimentId) throws ResourceNotFoundException, PermissionException {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwt = (Jwt) authentication.getPrincipal();
			String userId = jwt.getSubject();
			if (authentication != null && userId != null) {
				Utilisateur utilisateur = liaisonUtilisateurService.getById(userId);
				Maraicher maraicher = maraicherRepository.findByEmail(utilisateur.getEmail());
				Aliment aliment = alimentRepository.findById(alimentId).orElseThrow(() -> new ResourceNotFoundException(Aliment.class, alimentId));
				if(aliment.getMaraicher() == maraicher){
					aliment.setTypeAliment(null);
//					aliment.setMaraicher(null);
					alimentRepository.delete(aliment);
				}else{
					throw new PermissionException("supprimer l'aliment.");
				}
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(Aliment.class, alimentId);
		}
	}
	public Aliment create(Aliment aliment) {
		if (aliment.getTypeAliment() != null) {
			TypeAliment typeAliment = aliment.getTypeAliment();
			if (typeAliment.getId() != null) {
				TypeAliment typeAlimentFound = typeAlimentRepository.findById(typeAliment.getId()).orElse(null);
				if (typeAlimentFound == null) {
					typeAliment = typeAlimentRepository.save(typeAliment);
					aliment.setTypeAliment(typeAliment);
				} else {
					aliment.setTypeAliment(typeAlimentFound);
				}
			}
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authentication.getPrincipal();
		String userId = jwt.getSubject();
		if (authentication != null && userId != null) {
			Utilisateur utilisateur = liaisonUtilisateurService.getById(userId);
			Maraicher maraicher = maraicherRepository.findByEmail(utilisateur.getEmail());
			aliment.setMaraicher(maraicher);
			return alimentRepository.save(aliment);
		}
		return null;
	}

}
