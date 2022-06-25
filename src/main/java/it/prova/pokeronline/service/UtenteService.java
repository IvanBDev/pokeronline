package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Utente;

public interface UtenteService {
	
	public Utente findByUsername(String username);
	
	public void inserisciNuovo(Utente utenteInstance);
	
	public void changeUserAbilitation(Long idUtente);
	
	public Utente caricaSingoloUtente(Long idUtente);
	
}
