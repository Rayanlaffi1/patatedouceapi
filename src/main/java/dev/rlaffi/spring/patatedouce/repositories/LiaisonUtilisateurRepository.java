package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.KeycloakUser;
import dev.rlaffi.spring.patatedouce.entities.LiaisonUtilisateur;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiaisonUtilisateurRepository extends JpaRepository<LiaisonUtilisateur, Long>{
    LiaisonUtilisateur findBykeycloakuser_id(String keycloakUserId);
}
