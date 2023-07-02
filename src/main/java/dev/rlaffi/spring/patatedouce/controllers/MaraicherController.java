package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Client;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.services.ClientService;
import dev.rlaffi.spring.patatedouce.services.MaraicherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
