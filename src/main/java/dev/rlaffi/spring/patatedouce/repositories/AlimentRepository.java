package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentRepository extends JpaRepository<Aliment, Integer>{
}
