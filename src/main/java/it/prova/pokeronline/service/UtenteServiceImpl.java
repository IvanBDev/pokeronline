package it.prova.pokeronline.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.StatoUtente;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public Utente findByUsername(String username) {
		// TODO Auto-generated method stub
		return utenteRepository.findByUsername(username).orElse(null);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		// TODO Auto-generated method stub
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteInstance.setDataRegistrazione(LocalDate.now());
		
		utenteRepository.save(utenteInstance);
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

}
