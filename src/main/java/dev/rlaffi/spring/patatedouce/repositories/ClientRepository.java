package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.Client;
import dev.rlaffi.spring.patatedouce.entities.LiaisonUtilisateur;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long>{
    Client findByEmail(String email);

}
