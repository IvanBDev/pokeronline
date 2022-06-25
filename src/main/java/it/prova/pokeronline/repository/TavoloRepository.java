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
	
}
