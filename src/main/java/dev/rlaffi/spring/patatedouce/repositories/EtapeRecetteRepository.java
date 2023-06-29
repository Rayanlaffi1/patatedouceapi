package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.Etape;
import dev.rlaffi.spring.patatedouce.entities.EtapeRecette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtapeRecetteRepository extends JpaRepository<EtapeRecette, Integer>{
}
