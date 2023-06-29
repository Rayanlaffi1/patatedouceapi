package dev.rlaffi.spring.patatedouce.controllers;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.TypeAliment;
import dev.rlaffi.spring.patatedouce.services.AlimentService;
import dev.rlaffi.spring.patatedouce.services.TypeAlimentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Types d'aliment")
@RestController
@RequestMapping("/typealiment")
public class TypeAlimentController {
	@Autowired
	private TypeAlimentService typeAlimentService;

	@GetMapping("{id}")
	public TypeAliment get(@PathVariable Integer id) {
		return typeAlimentService.get(id);
	}
	@GetMapping("/all")
	public List<TypeAliment> getAll() {
		return typeAlimentService.getAll();
	}
	@PutMapping
	public TypeAliment create(@RequestBody TypeAliment typeAliment) {
		return typeAlimentService.create(typeAliment);
	}
	
}
