package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.TypeAliment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeAlimentRepository extends JpaRepository<TypeAliment, Integer>{
    TypeAliment findByNom(String nom);
}
