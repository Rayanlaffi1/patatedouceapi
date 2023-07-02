package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.*;
import dev.rlaffi.spring.patatedouce.repositories.ClientRepository;
import dev.rlaffi.spring.patatedouce.repositories.LiaisonUtilisateurRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiaisonUtilisateurService {
	private static final Logger logger = LoggerFactory.getLogger(LiaisonUtilisateurService.class);
	@Autowired
	private LiaisonUtilisateurRepository liaisonUtilisateurRepository;
	public Utilisateur getById(String id) {
		return liaisonUtilisateurRepository.findBykeycloakuser_id(id).getUtilisateur();
	}

}
