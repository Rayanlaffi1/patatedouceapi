package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.Panier;
import dev.rlaffi.spring.patatedouce.services.PanierService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Integer>{
}
