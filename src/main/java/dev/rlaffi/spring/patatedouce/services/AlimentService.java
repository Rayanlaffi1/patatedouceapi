package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.TypeAliment;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.repositories.AlimentRepository;
import dev.rlaffi.spring.patatedouce.repositories.TypeAlimentRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AlimentService {
	@Autowired
	private AlimentRepository alimentRepository;
	@Autowired
	private TypeAlimentRepository typeAlimentRepository;
	private static final Logger logger = LoggerFactory.getLogger(AlimentService.class);
	public Aliment get(Integer Id) {
		return alimentRepository.findById(Id).orElseThrow();
	}
	public List<Aliment> getAll() {
		return alimentRepository.findAll();
	}

	public Aliment create(Aliment aliment) {
		if (aliment.getTypeAliment().getNom() != null) {
			TypeAliment typeAliment = typeAlimentRepository.findByNom(aliment.getTypeAliment().getNom());
			aliment.setTypeAliment(typeAliment);
		}
		return alimentRepository.save(aliment);
	}
}
