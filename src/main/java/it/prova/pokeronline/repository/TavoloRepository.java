package it.prova.pokeronline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>{
	
	@Query("SELECT DISTINCT t FROM Tavolo t LEFT JOIN FETCH t.giocatori ")
	List<Tavolo> findAllEager();

	@Query("FROM Tavolo t LEFT JOIN FETCH t.giocatori WHERE t.id=?1")
	Tavolo findByIdEager(Long idTavolo);
	
	Tavolo findByDenominazione(String denominazione);
	
	@Query("FROM Tavolo t LEFT JOIN t.utenteCreazione u WHERE u.id = ?1")
	List<Tavolo> findByUtenteCreazione(Long idUtenteCreazione);
	
	@Query("FROM Tavolo t LEFT JOIN t.giocatori g WHERE g.id = ?1")
	List<Tavolo> findLastGameDiUnGiocatore(Long id);
	
}
