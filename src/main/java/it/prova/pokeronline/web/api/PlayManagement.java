package it.prova.pokeronline.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exception.UtenteNotFoundException;

@RestController
@RequestMapping("api/playManagement")
public class PlayManagement {

	@Autowired
	private UtenteService utenteService;

	@GetMapping("/compraCredito/{aggiuntaCredito}")
	public UtenteDTO compraCredito(@PathVariable(value = "aggiuntaCredito", required = true) Integer aggiuntaCredito) {

		if (aggiuntaCredito < 1) {
			throw new RuntimeException("Il credito da aggiungere deve essere superiore a 1");
		}

		Utente giocatoreInstance = utenteService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if(giocatoreInstance == null || giocatoreInstance.getId() < 1)
			throw new UtenteNotFoundException("Giocatore non trovato");
		
		giocatoreInstance.setCreditoAccumulato(utenteService.compraCredito(giocatoreInstance, aggiuntaCredito));

		return UtenteDTO.buildUtenteDTOFromModel(giocatoreInstance);

	}

}
