package it.prova.pokeronline.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.StatoUtente;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.UtenteRepository;
import it.prova.pokeronline.web.api.exception.UtenteNotFoundException;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Utente findByUsername(String username) {
		// TODO Auto-generated method stub
		return utenteRepository.findByUsername(username).orElse(null);
	}

	@Override
	@Transactional
	public Utente inserisciNuovo(Utente utenteInstance) {
		// TODO Auto-generated method stub
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteInstance.setDataRegistrazione(LocalDate.now());
		
		return utenteRepository.save(utenteInstance);
	}

	@Override
	@Transactional
	public void changeUserAbilitation(Long idUtente) {
		// TODO Auto-generated method stub
		Utente utenteInstance = caricaSingoloUtente(idUtente);
		if (utenteInstance == null)
			throw new RuntimeException("Elemento non trovato.");

		if (utenteInstance.getStato() == null || utenteInstance.getStato().equals(StatoUtente.CREATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
		else if (utenteInstance.getStato().equals(StatoUtente.ATTIVO))
			utenteInstance.setStato(StatoUtente.DISABILITATO);
		else if (utenteInstance.getStato().equals(StatoUtente.DISABILITATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
	}

	@Override
	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long idUtente) {
		// TODO Auto-generated method stub
		return utenteRepository.findById(idUtente).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Utente> findAllUtenti() {
		// TODO Auto-generated method stub
		return (List<Utente>) utenteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Utente> findByExample(Utente example) {
		// TODO Auto-generated method stub
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("SELECT u FROM Utente u WHERE u.id = u.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" u.nome  LIKE :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" u.cognome LIKE :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getUsername())) {
			whereClauses.add(" u.username LIKE :username ");
			paramaterMap.put("username", "%" + example.getUsername() + "%");
		}
		if (example.getStato() != null) {
			whereClauses.add(" u.stato =:stato ");
			paramaterMap.put("stato", example.getStato());
		}
		if (example.getCreditoAccumulato() != null && example.getCreditoAccumulato() > 0) {
			whereClauses.add(" u.creditoAccumulato >= :creditoAccumulato ");
			paramaterMap.put("creditoAccumulato", example.getCreditoAccumulato());
		}
		if (example.getEsperienzaAccumulata() != null && example.getEsperienzaAccumulata() > 0) {
			whereClauses.add(" u.esperienzaAccumulata >= :esperienzaAccumulata ");
			paramaterMap.put("esperienzaAccumulata", example.getEsperienzaAccumulata());
		}
		if (example.getDataRegistrazione() != null) {
			whereClauses.add("u.dataRegistrazione >= :dataRegistrazione ");
			paramaterMap.put("dataRegistrazione", example.getDataRegistrazione());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Utente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Utente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	@Transactional
	public void rimuovi(Utente utenteInstance) {
		// TODO Auto-generated method stub
		utenteInstance.setStato(StatoUtente.DISABILITATO);
		utenteRepository.save(utenteInstance);
	}

	@Override
	@Transactional
	public Utente aggiorna(Utente utenteInstance) {
		// TODO Auto-generated method stub
		Utente utenteApp = utenteRepository.findById(utenteInstance.getId()).orElse(null);
		if(utenteApp == null)
			throw new UtenteNotFoundException("Utente non trovato");
		
		utenteApp.setNome(utenteInstance.getNome());
		utenteApp.setCognome(utenteInstance.getCognome());
		utenteApp.setUsername(utenteInstance.getUsername());
		utenteApp.setEsperienzaAccumulata(utenteInstance.getEsperienzaAccumulata());
		utenteApp.setCreditoAccumulato(utenteInstance.getCreditoAccumulato());
		utenteApp.setRuoli(utenteInstance.getRuoli());
		
		return utenteRepository.save(utenteApp);
	}

}
