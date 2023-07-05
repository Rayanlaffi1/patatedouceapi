package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.*;
import dev.rlaffi.spring.patatedouce.repositories.AlimentRepository;
import dev.rlaffi.spring.patatedouce.repositories.MaraicherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaraicherService {
	private static final Logger logger = LoggerFactory.getLogger(MaraicherService.class);
	@Autowired
	private LiaisonUtilisateurService liaisonUtilisateurService;
	@Autowired
	private MaraicherRepository maraicherRepository;
	@Autowired
	private AlimentRepository alimentRepository;
	public Maraicher getById(Long id) {
		return maraicherRepository.findById(id).orElseThrow();
	}
	public List<Maraicher> getMaraichers() {
		List<Maraicher> maraichers = maraicherRepository.findAll();
		return maraichers;
	}
//	public Maraicher ajouterArticle(Integer alimentId, Integer quantite, Float prixut) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Jwt jwt = (Jwt) authentication.getPrincipal();
//		String userId = jwt.getSubject();
//		if (authentication != null && userId != null) {
//			Utilisateur utilisateur = liaisonUtilisateurService.getById(userId);
//			Maraicher maraicher = maraicherRepository.findByEmail(utilisateur.getEmail());
//			if(maraicher != null){
//				Aliment aliment = alimentRepository.findById(alimentId).orElseThrow();
//				StockMaraicher stockMaraicher = new StockMaraicher(aliment,maraicher, quantite, prixut);
//				for (StockMaraicher stockMaraicherFound : maraicher.getStockMaraichers()) {
//					if (stockMaraicherFound.getAliment().equals(aliment)) {
//						stockMaraicherFound.setQuantite(stockMaraicherFound.getQuantite() + quantite);
//						return maraicherRepository.save(maraicher);
//					}
//				}
//				stockMaraicher = stockMaraicherRepository.save(stockMaraicher);
//				maraicher.getStockMaraichers().add(stockMaraicher);
//			}
//			return maraicherRepository.save(maraicher);
//		} else {
//			return null;
//		}
//	}
}
