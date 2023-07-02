package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.Client;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaraicherRepository extends JpaRepository<Maraicher, Id>{
    Maraicher findById(Long id);
}
