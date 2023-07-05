package Maraicher;

import dev.rlaffi.spring.patatedouce.controllers.UtilisateurController;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class MaraicherTest {
    @Autowired
    private UtilisateurService utilisateurService;

    @BeforeEach
    public void setUp() {
        utilisateurService = new UtilisateurService();
    }

    @Test
    public void testCreateMaraicher() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setNom("Doe");
        utilisateur.setPrenom("John");
        String mdp = "password";

        Maraicher maraicher = utilisateurService.creationMaraicher(utilisateur, mdp);
        assertEquals(utilisateur.getEmail(), maraicher.getEmail());
    }
}
