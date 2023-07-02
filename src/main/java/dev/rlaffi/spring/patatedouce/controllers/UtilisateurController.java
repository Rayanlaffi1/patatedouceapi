package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Client;
import dev.rlaffi.spring.patatedouce.entities.LiaisonUtilisateur;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.services.ClientService;
import dev.rlaffi.spring.patatedouce.services.LiaisonUtilisateurService;
import dev.rlaffi.spring.patatedouce.services.UtilisateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Utilisateurs")
@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private LiaisonUtilisateurService liaisonUtilisateurService;
	@PutMapping("/creation/client/{mdp}")
	public Client creationClient(@RequestBody Utilisateur user, @PathVariable String mdp) {
		return utilisateurService.creationClient(user,mdp);
	}
	@PutMapping("/creation/maraicher/{mdp}")
	public Maraicher creationMaraicher(@RequestBody Utilisateur user, @PathVariable String mdp) {
		return utilisateurService.creationMaraicher(user,mdp);
	}
	@GetMapping("/{id}")
	public Utilisateur get(@PathVariable String id) {
		return liaisonUtilisateurService.getById(id);
	}
}
