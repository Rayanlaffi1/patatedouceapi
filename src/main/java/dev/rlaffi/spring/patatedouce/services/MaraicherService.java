package dev.rlaffi.spring.patatedouce.services;

import dev.rlaffi.spring.patatedouce.entities.Client;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.repositories.ClientRepository;
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
	private MaraicherRepository maraicherRepository;
	public Maraicher getById(Long id) {
		return maraicherRepository.findById(id);
	}
	public List<Maraicher> getMaraichers() {
		List<Maraicher> maraichers = maraicherRepository.findAll();
		return maraichers;
	}
}
