package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.TypeAliment;
import dev.rlaffi.spring.patatedouce.repositories.AlimentRepository;
import dev.rlaffi.spring.patatedouce.repositories.TypeAlimentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAlimentService {
	@Autowired
	private TypeAlimentRepository typeAlimentRepository;
	private static final Logger logger = LoggerFactory.getLogger(TypeAlimentService.class);

	public TypeAliment get(Integer Id) {
		return typeAlimentRepository.findById(Id).orElseThrow();
	}
	public List<TypeAliment> getAll() {
		return typeAlimentRepository.findAll();
	}
	public TypeAliment create(TypeAliment typeAliment) {
		if (typeAliment != null) {
			TypeAliment typeAlimentTrouve = typeAlimentRepository.findByNom(typeAliment.getNom());
			if(typeAlimentTrouve != null){
				typeAliment = typeAlimentRepository.findByNom(typeAliment.getNom());
			}
		}
		return typeAlimentRepository.save(typeAliment);
	}
}
