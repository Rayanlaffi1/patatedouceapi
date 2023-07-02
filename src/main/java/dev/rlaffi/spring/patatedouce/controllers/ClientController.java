package dev.rlaffi.spring.patatedouce.controllers;

import java.util.List;

import dev.rlaffi.spring.patatedouce.entities.Client;
import dev.rlaffi.spring.patatedouce.entities.Panier;
import dev.rlaffi.spring.patatedouce.entities.PanierAchat;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.services.ClientService;
import dev.rlaffi.spring.patatedouce.services.UtilisateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name="Clients")
@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService clientService;
	@GetMapping("/{id}")
	public Client get(@PathVariable Long id) {
		return clientService.getById(id);
	}
	@GetMapping("/all")
	public List<Client> getAll() {
		return clientService.getClients();
	}

	@PostMapping("/ajouterpanierachat/")
	public Client ajouterAliment(Integer alimentId, Integer qte) {
		return clientService.ajouterAliment(alimentId,qte);
	}


}
