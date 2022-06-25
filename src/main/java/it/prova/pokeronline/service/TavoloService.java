package it.prova.pokeronline.service;

import java.util.List;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloService {

	List<Tavolo> listAllElements();

	List<Tavolo> listAllElementsEager();

	Tavolo caricaSingoloElemento(Long id);

	Tavolo caricaSingoloElementoConGiocatori(Long id);
	
	Tavolo findByDenominazione(String denominazione);
	
	Tavolo inserisciNuovo(Tavolo tavoloInstance);
	
	List<Tavolo> trovaTramiteUtenteCreazione(Long idUtenteCreazione);

}
