package dev.rlaffi.spring.patatedouce.services;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.entities.Role;
import dev.rlaffi.spring.patatedouce.entities.RoleMapping;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.repositories.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

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
	@PostConstruct
	public void init() {
		serverUrl = env.getProperty("app.keycloak.auth.url");
		keycloak = KeycloakBuilder.builder()
				.serverUrl(serverUrl)
				.realm("master")
				.clientId("admin-cli")
				.username("user")
				.password("bitnami")
				.build();
	}
	private List<String> getUserRoles(String userId) {
		List<String> roles = new ArrayList<>();
		RealmResource realmResource = keycloak.realm("realm-patatedouce");
		UsersResource usersResource = realmResource.users();
		UserResource userResource = usersResource.get(userId);
		List<RoleRepresentation> roleMappings = userResource.roles().realmLevel().listEffective();
		for (RoleRepresentation role : roleMappings) {
			roles.add(role.getName());
		}
		return roles;
	}
	public Utilisateur getById(String id) {
		return utilisateurRepository.findById(id).orElseThrow();
	}
	public List<Utilisateur> getAll() {
		List<Utilisateur> lesUtilisateurs = new ArrayList<>();
		List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
		for (Utilisateur utilisateur : utilisateurs) {
			List<RoleMapping> roleMappings = utilisateur.getRoleMappings();
			List<String> lesRoles = new ArrayList<>();
			for (RoleMapping roleMapping : roleMappings) {
				Role role = roleMapping.getRole();
				lesRoles.add(role.getNom());
			}
			if(lesRoles.contains("CLIENT")){
				lesUtilisateurs.add(utilisateur);
			}
		}
		return lesUtilisateurs;
	}
	public Utilisateur creation(Utilisateur utilisateur, String mdp) {
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

		RealmResource realmResource = keycloak.realm("realm-patatedouce");
		UsersResource usersResource = realmResource.users();

		Response response = usersResource.create(user);
		if (response.getStatus() != 201) {
			// Handle error
		} else {
			String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
			UserResource userResource = usersResource.get(userId);

			RoleRepresentation role = realmResource.roles().get("client").toRepresentation();
			userResource.roles().realmLevel().add(Arrays.asList(role));
		}
		return utilisateur;
	}
//	public String connexion(Utilisateur utilisateur) {
//		Keycloak keycloaku = KeycloakBuilder.builder()
//			.serverUrl("http://localhost:9009/auth")
//			.realm("realm-patatedouce")
//			.clientId("patatedouce")
//			.username(utilisateur.getMail())
//			.password(utilisateur.getMdp())
//			.build();
//		AccessTokenResponse response = keycloaku.tokenManager().getAccessToken();
//
//		if(response != null){
//			return response.getToken();
//		}else{
//			return "Erreur";
//		}
//		return null;
//	}
}
