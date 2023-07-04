package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Client;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.services.ClientService;
import dev.rlaffi.spring.patatedouce.services.MaraicherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Maraichers")
@RestController
@RequestMapping("/maraicher")
public class MaraicherController {
	@Autowired
	private MaraicherService maraicherService;
	@GetMapping("/{id}")
	public Maraicher get(@PathVariable Long id) {
		return maraicherService.getById(id);
	}
	@GetMapping("/all")
	public List<Maraicher> getAll() {
		return maraicherService.getMaraichers();
	}
	@PostMapping("/ajouterstock/")
	public Maraicher ajouterArticle(Integer alimentId, Integer quantite, Float prixut) {
		return maraicherService.ajouterArticle( alimentId,  quantite,  prixut);
	}
}
