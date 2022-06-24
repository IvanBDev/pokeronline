package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Utente;

public interface UtenteService {
	
	public Utente findByUsername(String username);
	
}
