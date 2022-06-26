package it.prova.pokeronline.service;

import java.util.List;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

public interface TavoloService {

	List<Tavolo> listAllElements();

	List<Tavolo> listAllElementsEager();

	Tavolo caricaSingoloElemento(Long id);

	Tavolo caricaSingoloElementoConGiocatori(Long id);
	
	Tavolo findByDenominazione(String denominazione);
	
	Tavolo inserisciNuovo(Tavolo tavoloInstance);
	
	List<Tavolo> trovaTramiteUtenteCreazione(Long idUtenteCreazione);
	
	List<Tavolo> findByExample(Tavolo example);
	
	void rimuovi(Long idTavolo);
	
	Tavolo aggiorna(Tavolo tavoloInstance);
	
	List<Tavolo> trovaTavoloConGiocatore(Long idUtente);
	
	void abbandonaPartita(Tavolo tavoloInstance, Utente giocatore);
	
	List<Tavolo> ricercaTavoloConEsperienzaMinima(Integer esperienzaAccumulata);

}
