package it.prova.pokeronline.service;

import java.util.List;

import it.prova.pokeronline.model.Utente;

public interface UtenteService {
	
	public Utente findByUsername(String username);
	
	public Utente inserisciNuovo(Utente utenteInstance);
	
	public void changeUserAbilitation(Long idUtente);
	
	public Utente caricaSingoloUtente(Long idUtente);
	
	public List<Utente> findAllUtenti();
	
	public List<Utente> findByExample(Utente example);
	
}
