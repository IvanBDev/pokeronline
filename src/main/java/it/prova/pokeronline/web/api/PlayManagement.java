package it.prova.pokeronline.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;
import it.prova.pokeronline.web.api.exception.UtenteNotFoundException;

@RestController
@RequestMapping("api/playManagement")
public class PlayManagement {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private TavoloService tavoloService;

	@GetMapping("/compraCredito/{aggiuntaCredito}")
	public UtenteDTO compraCredito(@PathVariable(value = "aggiuntaCredito", required = true) Integer aggiuntaCredito) {

		if (aggiuntaCredito < 1) {
			throw new RuntimeException("Il credito da aggiungere deve essere superiore a 1");
		}

		Utente giocatoreInstance = utenteService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		if (giocatoreInstance == null || giocatoreInstance.getId() < 1)
			throw new UtenteNotFoundException("Giocatore non trovato");

		giocatoreInstance.setCreditoAccumulato(utenteService.compraCredito(giocatoreInstance, aggiuntaCredito));

		return UtenteDTO.buildUtenteDTOFromModel(giocatoreInstance);

	}

	@GetMapping("/lastGame")
	public List<TavoloDTO> lastGame() {

		Utente giocatore = utenteService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if (giocatore == null || giocatore.getId() < 1)
			throw new UtenteNotFoundException("Giocatore non trovato");

		return TavoloDTO.createTavoloDTOListFromModelList(tavoloService.trovaTavoloConGiocatore(giocatore.getId()),
				true);

	}
	
	@GetMapping("/abbandonaPartita/{idTavolo}")
	public void abbandonaPartita(@PathVariable(value = "idTavolo", required = true) long idTavolo) {
		
		TavoloDTO tavoloInstance = TavoloDTO.buildTavoloDTOFromModel(tavoloService.caricaSingoloElementoConGiocatori(idTavolo), false);
		if(tavoloInstance == null)
			throw new TavoloNotFoundException("Tavolo non trovato");
		
		Utente giocatore = utenteService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if (giocatore == null || giocatore.getId() < 1)
			throw new UtenteNotFoundException("Giocatore non trovato");
		
		tavoloService.abbandonaPartita(tavoloInstance.buildTavoloModel(), giocatore);	
		
	}

}
