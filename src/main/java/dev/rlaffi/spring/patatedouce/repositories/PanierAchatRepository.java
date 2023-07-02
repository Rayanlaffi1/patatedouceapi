package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.LiaisonUtilisateur;
import dev.rlaffi.spring.patatedouce.entities.PanierAchat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierAchatRepository extends JpaRepository<PanierAchat, Long>{
}
