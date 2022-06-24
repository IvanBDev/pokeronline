package it.prova.pokeronline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public Utente findByUsername(String username) {
		// TODO Auto-generated method stub
		return utenteRepository.findByUsername(username).orElse(null);
	}

}
