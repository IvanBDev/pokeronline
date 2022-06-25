package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Ruolo;

public interface RuoloService {
	
	public Ruolo trovaTramiteDescrizioneERuolo(String descrizione, String codice);
	
	public void inserisciNuovo(Ruolo ruoloInstance);
	
}
