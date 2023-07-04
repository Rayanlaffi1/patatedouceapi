package dev.rlaffi.spring.patatedouce.repositories;

import dev.rlaffi.spring.patatedouce.entities.PanierAchat;
import dev.rlaffi.spring.patatedouce.entities.StockMaraicher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMaraicherRepository extends JpaRepository<StockMaraicher, Long>{
}
