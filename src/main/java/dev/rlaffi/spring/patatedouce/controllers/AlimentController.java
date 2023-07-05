package dev.rlaffi.spring.patatedouce.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.exceptions.PermissionException;
import dev.rlaffi.spring.patatedouce.exceptions.ResourceNotFoundException;
import dev.rlaffi.spring.patatedouce.services.AlimentService;
import dev.rlaffi.spring.patatedouce.services.UtilisateurService;
import dev.rlaffi.spring.patatedouce.views.Views;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
@Tag(name="Aliments", description = "Gestion des aliments")
@RestController
@RequestMapping("/aliment")
public class AlimentController {
	@Autowired
	private AlimentService alimentService;

	@GetMapping("{id}")
	public Aliment get(@PathVariable Integer id) throws ResourceNotFoundException {
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
	@DeleteMapping("/{alimentId}")
	public void deletePanierById(@PathVariable Integer alimentId) throws ResourceNotFoundException, PermissionException {
		alimentService.deleteById(alimentId);
	}
}
