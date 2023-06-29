package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.Aliment;
import dev.rlaffi.spring.patatedouce.entities.AlimentPanier;
import dev.rlaffi.spring.patatedouce.entities.Panier;
import dev.rlaffi.spring.patatedouce.repositories.AlimentRepository;
import dev.rlaffi.spring.patatedouce.repositories.PanierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {
	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private AlimentRepository alimentRepository;
	private static final Logger logger = LoggerFactory.getLogger(PanierService.class);
	public Panier get(Integer Id) {
		return panierRepository.findById(Id).orElseThrow();
	}
	public List<Panier> getAll() {
		return panierRepository.findAll();
	}

	public Panier create(Panier panier) {
		return panierRepository.save(panier);
	}
	public void deletePanierById(Integer panierId) {
		panierRepository.deleteById(panierId);
	}
	public Panier ajouterAliment(Integer alimentId, Integer panierId, Integer qte) {
		Panier panier = panierRepository.findById(panierId).orElseThrow();
		Aliment aliment = alimentRepository.findById(alimentId).orElseThrow();
		panier.getAliments().add(new AlimentPanier(aliment,panier,qte));
		//panier.ajouterAliment(aliment,qte);
		return panierRepository.save(panier);
	}


}
