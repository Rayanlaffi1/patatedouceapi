package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.Etape;
import dev.rlaffi.spring.patatedouce.entities.TypeAliment;
import dev.rlaffi.spring.patatedouce.repositories.EtapeRepository;
import dev.rlaffi.spring.patatedouce.repositories.TypeAlimentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapeService {
	@Autowired
	private EtapeRepository etapeRepository;
	private static final Logger logger = LoggerFactory.getLogger(EtapeService.class);

	public Etape get(Integer Id) {
		return etapeRepository.findById(Id).orElseThrow();
	}
	public List<Etape> getAll() {
		return etapeRepository.findAll();
	}
	public Etape create(Etape etape) {
		return etapeRepository.save(etape);
	}
	public void deleteById(Integer etapeId) {
		etapeRepository.deleteById(etapeId);
	}
}
