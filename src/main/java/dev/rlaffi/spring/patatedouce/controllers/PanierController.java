package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.Panier;
import dev.rlaffi.spring.patatedouce.services.AlimentService;
import dev.rlaffi.spring.patatedouce.services.PanierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Paniers")
@RestController
@RequestMapping("/panier")
public class PanierController {
	@Autowired
	private PanierService panierService;

	@GetMapping("{id}")
	public Panier get(@PathVariable Integer id) {
		return panierService.get(id);
	}

	@GetMapping("/all")
	public List<Panier> getAll() {
		return panierService.getAll();
	}

	@PutMapping
	public Panier create(@RequestBody Panier panier) {
		return panierService.create(panier);
	}
	@DeleteMapping("/{panierId}")
	public ResponseEntity<String> deletePanierById(@PathVariable Integer panierId) {
		panierService.deletePanierById(panierId);
		return ResponseEntity.ok("Panier retirée");
	}
	@PostMapping("/ajouter/aliment/{alimentId}/panier/{panierId}")
	public Panier ajouterAliment(@PathVariable Integer alimentId,@PathVariable Integer panierId,Integer qte) {
		return panierService.ajouterAliment(alimentId,panierId,qte);
	}
}
