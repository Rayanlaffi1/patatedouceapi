package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.*;
import dev.rlaffi.spring.patatedouce.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class RecetteService {
	@Autowired
	private RecetteRepository recetteRepository;
	@Autowired
	private EtapeRepository etapeRepository;
	@Autowired
	private EtapeRecetteRepository etapeRecetteRepository;

	public Recette get(Integer Id) {
		return recetteRepository.findById(Id).orElseThrow();
	}
	public List<Recette> getAll() {
		return recetteRepository.findAll();
	}
	public void deleteRecetteById(Integer recetteId) {
		recetteRepository.deleteById(recetteId);
	}
	public Recette create(Recette recette) {
		if(recette.getId() == null){
			List<EtapeRecette> lesEtapesRecette = recette.getEtapes();
			recette.setEtapes(null);
			Recette recetteSaved = recetteRepository.save(recette);
			for (EtapeRecette etapeRecette : lesEtapesRecette) {
				etapeRecette.setRecette(recetteSaved);
				if(etapeRecette.getEtape().getId() != null){
					Etape etape = etapeRepository.findById(etapeRecette.getEtape().getId()).orElseThrow();
					if(etape != null){
						etapeRepository.save(etapeRecette.getEtape());
						etapeRecetteRepository.save(etapeRecette);
					}
				}else{
					Etape etape = etapeRecette.getEtape();
					etapeRepository.save(etape);
					etapeRecetteRepository.save(etapeRecette);
				}
			}
		}
		return recetteRepository.save(recette);
	}
	public Recette ajouterEtape(Integer etapeId, Integer recetteId, Integer ordre) {
		Recette recette = recetteRepository.findById(recetteId).orElseThrow();
		Etape etape = etapeRepository.findById(etapeId).orElseThrow();
		if(etape != null){
			recette.getEtapes().add(new EtapeRecette(etape,recette,ordre));
		}
		return recetteRepository.save(recette);
	}
	public Recette supprimerEtape(Integer etapeId, Integer recetteId) {
		Recette recette = recetteRepository.findById(recetteId).orElseThrow();
		List<EtapeRecette> etapes = recette.getEtapes();
		EtapeRecette etapeToRemove = null;
		for (EtapeRecette etape : etapes) {
			if (etape.getEtape().getId().equals(etapeId)) {
				etapeToRemove = etape;
				break;
			}
		}
		if (etapeToRemove != null) {
			etapes.remove(etapeToRemove);
		} else {
			throw new IllegalArgumentException("Étape non trouvé avec l'id: " + etapeId);
		}
		return recetteRepository.save(recette);
	}

}
