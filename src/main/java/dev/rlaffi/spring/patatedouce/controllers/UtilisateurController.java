package dev.rlaffi.spring.patatedouce.controllers;

import java.util.List;

import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.services.UtilisateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
@Tag(name="Utilisateurs")
@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;
	@GetMapping("/{id}")
	public Utilisateur get(@PathVariable String id) {
		return utilisateurService.getById(id);
	}

	@PutMapping("/{mdp}")
	public Utilisateur creation(@RequestBody Utilisateur user,@PathVariable String mdp) {
		return utilisateurService.creation(user,mdp);
	}

	@GetMapping("/all")
	public List<Utilisateur> getAll() {
		return utilisateurService.getAll();
	}

}
