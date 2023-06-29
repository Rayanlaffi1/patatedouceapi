package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.services.AlimentService;
import dev.rlaffi.spring.patatedouce.services.UtilisateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
@Tag(name="Aliments")
@RestController
@RequestMapping("/aliment")
public class AlimentController {
	@Autowired
	private AlimentService alimentService;

	@GetMapping("{id}")
	public Aliment get(@PathVariable Integer id) {
		return alimentService.get(id);
	}
	@GetMapping("/all")
	public List<Aliment> getAll() {
		return alimentService.getAll();
	}
	@PutMapping
	public Aliment create(@RequestBody Aliment aliment) {
		return alimentService.create(aliment);
	}


}
