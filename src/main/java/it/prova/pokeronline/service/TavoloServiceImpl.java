package it.prova.pokeronline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.repository.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService{
	
	@Autowired
	private TavoloRepository tavoloRepository;

	@Override
	public List<Tavolo> listAllElements() {
		// TODO Auto-generated method stub
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Override
	public List<Tavolo> listAllElementsEager() {
		// TODO Auto-generated method stub
		return tavoloRepository.findAllEager();
	}

	@Override
	public Tavolo caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return tavoloRepository.findById(id).orElse(null);
	}

	@Override
	public Tavolo caricaSingoloElementoConGiocatori(Long id) {
		// TODO Auto-generated method stub
		return tavoloRepository.findByIdEager(id);
	}

}
