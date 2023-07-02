package dev.rlaffi.spring.patatedouce.services;

import java.util.*;

import dev.rlaffi.spring.patatedouce.entities.*;
import dev.rlaffi.spring.patatedouce.repositories.*;
import jakarta.annotation.PostConstruct;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@Service
public class UtilisateurService {
	private static final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);
	private Keycloak keycloak;
	private String serverUrl;
	@Autowired
	private Environment env;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private MaraicherRepository maraicherRepository;
	@Autowired
	private KeycloakUserRepository keycloakUserRepository;
	@Autowired
	private LiaisonUtilisateurRepository liaisonUtilisateurRepository;
	@PostConstruct
	public void init() {
		serverUrl = env.getProperty("app.keycloak.auth.url");
		try {
			keycloak = KeycloakBuilder.builder()
					.serverUrl(serverUrl)
					.realm("Master")
					.clientId("admin-cli")
					.username("user")
					.password("bitnami")
					.build();

			// Keycloak est connecté avec succès
			System.out.println("Keycloak est connecté !");
		} catch (Exception e) {
			// Erreur lors de la connexion à Keycloak
			System.out.println("Erreur lors de la connexion à Keycloak : " + e.getMessage());
		}
	}
	public Client creationClient(Utilisateur utilisateur, String mdp) {
		Client client = new Client();
		UserRepresentation user = new UserRepresentation();
		user.setUsername(utilisateur.getEmail());
		user.setFirstName(utilisateur.getPrenom());
		user.setLastName(utilisateur.getNom());
		user.setEmail(utilisateur.getEmail());
		user.setEnabled(true);
		/*Map<String, List<String>> attributes = new HashMap<>();
		attributes.put("a", Arrays.asList("bar"));
		attributes.put("b", Arrays.asList("baz", "qux"));
		user.setAttributes(attributes);*/

		CredentialRepresentation password = new CredentialRepresentation();
		password.setTemporary(false);
		password.setType(CredentialRepresentation.PASSWORD);
		password.setValue(mdp);

		user.setCredentials(Arrays.asList(password));

		RealmResource realmResource = keycloak.realm("Realm-patatedouce");
		UsersResource usersResource = realmResource.users();

		Response response = usersResource.create(user);
		if (response.getStatus() != 201) {
			String errorMessage = response.readEntity(String.class);
			System.out.println("Error creating user: " + response.getStatus());
			System.out.println("Error message: " + errorMessage);
		} else {
			String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
			UserResource userResource = usersResource.get(userId);

			RoleRepresentation role = realmResource.roles().get("client").toRepresentation();
			userResource.roles().realmLevel().add(Arrays.asList(role));

			KeycloakUser keycloakUser = keycloakUserRepository.findById(userId).orElseThrow();

			Utilisateur utilisateurCreer = utilisateurRepository.save(utilisateur);
			LiaisonUtilisateur liaisonUtilisateur = new LiaisonUtilisateur();
			liaisonUtilisateur.setUtilisateur(utilisateurCreer);
			liaisonUtilisateur.setKeycloakuser(keycloakUser);
			liaisonUtilisateurRepository.save(liaisonUtilisateur);

			client.setEmail( utilisateurCreer.getEmail() );
			client.setNom( utilisateurCreer.getNom() );
			client.setPrenom( utilisateurCreer.getPrenom() );
			clientRepository.save(client);
		}
		return client;
	}

	public Maraicher creationMaraicher(Utilisateur utilisateur, String mdp) {
		Maraicher client = new Maraicher();
		UserRepresentation user = new UserRepresentation();
		user.setUsername(utilisateur.getEmail());
		user.setFirstName(utilisateur.getPrenom());
		user.setLastName(utilisateur.getNom());
		user.setEmail(utilisateur.getEmail());
		user.setEnabled(true);
		/*Map<String, List<String>> attributes = new HashMap<>();
		attributes.put("a", Arrays.asList("bar"));
		attributes.put("b", Arrays.asList("baz", "qux"));
		user.setAttributes(attributes);*/

		CredentialRepresentation password = new CredentialRepresentation();
		password.setTemporary(false);
		password.setType(CredentialRepresentation.PASSWORD);
		password.setValue(mdp);

		user.setCredentials(Arrays.asList(password));

		RealmResource realmResource = keycloak.realm("Realm-patatedouce");
		UsersResource usersResource = realmResource.users();

		Response response = usersResource.create(user);
		if (response.getStatus() != 201) {
			String errorMessage = response.readEntity(String.class);
			System.out.println("Error creating user: " + response.getStatus());
			System.out.println("Error message: " + errorMessage);
		} else {
			String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
			UserResource userResource = usersResource.get(userId);

			RoleRepresentation role = realmResource.roles().get("maraicher").toRepresentation();
			userResource.roles().realmLevel().add(Arrays.asList(role));

			KeycloakUser keycloakUser = keycloakUserRepository.findById(userId).orElseThrow();

			Utilisateur utilisateurCreer = utilisateurRepository.save(utilisateur);
			LiaisonUtilisateur liaisonUtilisateur = new LiaisonUtilisateur();
			liaisonUtilisateur.setUtilisateur(utilisateurCreer);
			liaisonUtilisateur.setKeycloakuser(keycloakUser);
			liaisonUtilisateurRepository.save(liaisonUtilisateur);

			client.setEmail( utilisateurCreer.getEmail() );
			client.setNom( utilisateurCreer.getNom() );
			client.setPrenom( utilisateurCreer.getPrenom() );
			maraicherRepository.save(client);
		}
		return client;
	}

}
