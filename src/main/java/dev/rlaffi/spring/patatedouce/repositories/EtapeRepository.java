package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.Etape;
import dev.rlaffi.spring.patatedouce.entities.Recette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtapeRepository extends JpaRepository<Etape, Integer>{
}
