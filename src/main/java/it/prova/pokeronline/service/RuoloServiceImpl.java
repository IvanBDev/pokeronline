package it.prova.pokeronline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.repository.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService{
	
	@Autowired
	private RuoloRepository ruoloRepository;

	@Override
	public Ruolo trovaTramiteDescrizioneERuolo(String descrizione, String codice) {
		// TODO Auto-generated method stub
		return ruoloRepository.findByDescrizioneAndCodice(descrizione, codice);
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub
		ruoloRepository.save(ruoloInstance);
	}
}
