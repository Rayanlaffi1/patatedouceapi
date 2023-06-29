package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Etape;
import dev.rlaffi.spring.patatedouce.entities.Panier;
import dev.rlaffi.spring.patatedouce.entities.Recette;
import dev.rlaffi.spring.patatedouce.services.PanierService;
import dev.rlaffi.spring.patatedouce.services.RecetteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Recettes")
@RestController
@RequestMapping("/recette")
public class RecetteController {
	@Autowired
	private RecetteService recetteService;

	@GetMapping("{id}")
	public Recette get(@PathVariable Integer id) {
		return recetteService.get(id);
	}
	@GetMapping("/all")
	public List<Recette> getAll() {
		return recetteService.getAll();
	}
	@PutMapping
	public Recette create(@RequestBody Recette recette) {
		return recetteService.create(recette);
	}
	@DeleteMapping("/{recetteId}")
	public ResponseEntity<String> deleteRecette(@PathVariable Integer recetteId) {
		recetteService.deleteRecetteById(recetteId);
		return ResponseEntity.ok("Recette retirée");
	}
	@PostMapping("/ajouter/etape/{etapeId}/recette/{recetteId}")
	public Recette ajouterEtape(@PathVariable Integer etapeId, @PathVariable Integer recetteId, Integer ordre) {
		return recetteService.ajouterEtape(etapeId,recetteId,ordre);
	}
	@PostMapping("/supprimer/etape/{etapeId}/recette/{recetteId}")
	public Recette supprimerEtape(@PathVariable Integer etapeId, @PathVariable Integer recetteId) {
		return recetteService.supprimerEtape(etapeId,recetteId);
	}
}
