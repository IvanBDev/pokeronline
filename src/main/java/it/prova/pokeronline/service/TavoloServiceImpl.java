package it.prova.pokeronline.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.TavoloRepository;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;

@Service
public class TavoloServiceImpl implements TavoloService{
	
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Autowired
	private EntityManager entityManager; 

	@Override
	@Transactional(readOnly = true)
	public List<Tavolo> listAllElements() {
		// TODO Auto-generated method stub
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tavolo> listAllElementsEager() {
		// TODO Auto-generated method stub
		return tavoloRepository.findAllEager();
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return tavoloRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo caricaSingoloElementoConGiocatori(Long id) {
		// TODO Auto-generated method stub
		return tavoloRepository.findByIdEager(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo findByDenominazione(String denominazione) {
		// TODO Auto-generated method stub
		return tavoloRepository.findByDenominazione(denominazione);
	}

	@Override
	@Transactional
	public Tavolo inserisciNuovo(Tavolo tavoloInstance) {
		// TODO Auto-generated method stub
		return tavoloRepository.save(tavoloInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tavolo> trovaTramiteUtenteCreazione(Long idUtenteCreazione) {
		// TODO Auto-generated method stub
		return tavoloRepository.findByUtenteCreazione(idUtenteCreazione);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tavolo> findByExample(Tavolo example) {
		// TODO Auto-generated method stub
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select t from Tavolo t where t.id = t.id ");

		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" t.denominazione  like :denominazione ");
			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
		}
		if (example.getCifraMinima() != null && example.getCifraMinima() > 0) {
			whereClauses.add(" t.cifraMinima > :cifraMinima ");
			paramaterMap.put("cifraMinima", example.getCifraMinima());
		}
		if (example.getEsperienzaMinima() != null && example.getEsperienzaMinima() > 0) {
			whereClauses.add(" t.esperienzaMinima > :esperienzaMinima ");
			paramaterMap.put("esperienzaMinima", example.getEsperienzaMinima());
		}
		if (example.getDataCreazione() != null) {
			whereClauses.add("t.dataCreazione >= :dataCreazione ");
			paramaterMap.put("dataCreazione", example.getDataCreazione());
		}
		if (example.getUtenteCreazione() != null && example.getUtenteCreazione().getId() > 0) {
			whereClauses.add(" t.utenteCreazione.id = :utenteCreazioneId ");
			paramaterMap.put("utenteCreazioneId",  example.getUtenteCreazione().getId());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	@Transactional
	public void rimuovi(Long idTavolo) {
		// TODO Auto-generated method stub
		tavoloRepository.deleteById(idTavolo);
	}

	@Override
	@Transactional
	public Tavolo aggiorna(Tavolo tavoloInstance) {
		// TODO Auto-generated method stub
		Tavolo tavoloAppoggio = tavoloRepository.findById(tavoloInstance.getId()).orElse(null);
		if(tavoloAppoggio == null)
			throw new TavoloNotFoundException("Tavolo non trovato");
		
		tavoloAppoggio.setDenominazione(tavoloInstance.getDenominazione());
		tavoloAppoggio.setDataCreazione(tavoloInstance.getDataCreazione());
		tavoloAppoggio.setCifraMinima(tavoloInstance.getCifraMinima());
		tavoloAppoggio.setEsperienzaMinima(tavoloInstance.getEsperienzaMinima());
		
		return tavoloRepository.save(tavoloAppoggio);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tavolo> trovaTavoloConGiocatore(Long idUtente) {
		// TODO Auto-generated method stub
		return tavoloRepository.findLastGameDiUnGiocatore(idUtente);
	}

	@Override
	@Transactional
	public void abbandonaPartita(Tavolo tavoloInstance, Utente giocatore) {
		// TODO Auto-generated method stub
		tavoloInstance.getGiocatori().remove(giocatore);
		giocatore.setEsperienzaAccumulata(giocatore.getEsperienzaAccumulata() + 25);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tavolo> ricercaTavoloConEsperienzaMinima(Integer esperienzaAccumulata) {
		// TODO Auto-generated method stub
		return tavoloRepository.findTavoliConEsperienzaMinimaMinoreOUgualeAEsperienzaAccumulata(esperienzaAccumulata);
	}

}
