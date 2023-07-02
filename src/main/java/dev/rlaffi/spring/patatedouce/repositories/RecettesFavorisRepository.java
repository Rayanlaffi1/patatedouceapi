package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.PanierAchat;
import dev.rlaffi.spring.patatedouce.entities.RecetteFavoris;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecettesFavorisRepository extends JpaRepository<RecetteFavoris, Long>{
}
