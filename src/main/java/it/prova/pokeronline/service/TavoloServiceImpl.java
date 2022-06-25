package it.prova.pokeronline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.repository.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService{
	
	@Autowired
	private TavoloRepository tavoloRepository;

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

}
