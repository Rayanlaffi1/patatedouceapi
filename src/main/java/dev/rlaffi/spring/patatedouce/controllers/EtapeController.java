package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Etape;
import dev.rlaffi.spring.patatedouce.entities.TypeAliment;
import dev.rlaffi.spring.patatedouce.services.EtapeService;
import dev.rlaffi.spring.patatedouce.services.TypeAlimentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Etape")
@RestController
@RequestMapping("/etape")
public class EtapeController {
	@Autowired
	private EtapeService etapeService;

	@GetMapping("{id}")
	public Etape get(@PathVariable Integer id) {
		return etapeService.get(id);
	}

	@GetMapping("/all")
	public List<Etape> getAll() {
		return etapeService.getAll();
	}

	@PutMapping
	public Etape create(@RequestBody Etape etape) {
		return etapeService.create(etape);
	}
	@DeleteMapping("/{etapeId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer etapeId) {
		etapeService.deleteById(etapeId);
		return ResponseEntity.ok("Etape retirée");
	}
}
