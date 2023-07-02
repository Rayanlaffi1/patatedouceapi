package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.KeycloakUser;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeycloakUserRepository extends JpaRepository<KeycloakUser, String>{
}
