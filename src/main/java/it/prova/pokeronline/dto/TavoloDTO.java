package it.prova.pokeronline.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.pokeronline.model.Tavolo;

public class TavoloDTO {

	public Long id;

	@NotBlank(message = "{denominazione.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	public String denominazione;

	@NotNull(message = "{dataCreazione.notnull}")
	public LocalDate dataCreazione;

	@NotNull(message = "{esperienzaMinima.notnull}")
	@Min(0)
	public Integer esperienzaMinima;

	@NotNull(message = "{cifraMinima.notnull}")
	@Min(0)
	public Integer cifraMinima;

	@JsonIgnoreProperties(value = { "tavolo" })
	public Set<UtenteDTO> giocatori = new HashSet<UtenteDTO>(0);

	@NotNull(message = "{utenteCreazione.notnull}")
	public UtenteDTO utenteCreazione;

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(Long id,
			@NotBlank(message = "{denominazione.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String denominazione,
			@NotNull(message = "{dataCreazione.notnull}") LocalDate dataCreazione,
			@NotNull(message = "{esperienzaMinima.notnull}") Integer esperienzaMinima,
			@NotNull(message = "{cifraMinima.notnull}") Integer cifraMinima, Set<UtenteDTO> giocatori,
			@NotNull(message = "{utenteCreazione.notnull}") UtenteDTO utenteCreazione) {
		super();
		this.id = id;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.esperienzaMinima = esperienzaMinima;
		this.cifraMinima = cifraMinima;
		this.giocatori = giocatori;
		this.utenteCreazione = utenteCreazione;
	}

	public TavoloDTO(
			@NotBlank(message = "{denominazione.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String denominazione,
			@NotNull(message = "{dataCreazione.notnull}") LocalDate dataCreazione,
			@NotNull(message = "{esperienzaMinima.notnull}") Integer esperienzaMinima,
			@NotNull(message = "{cifraMinima.notnull}") Integer cifraMinima) {
		super();
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.esperienzaMinima = esperienzaMinima;
		this.cifraMinima = cifraMinima;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}

	public TavoloDTO(Long id, Integer esperienzaMinima, Integer cifraMinima, String denominazione,
			LocalDate dataCreazione) {
		super();
		this.id = id;
		this.esperienzaMinima = esperienzaMinima;
		this.cifraMinima = cifraMinima;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Integer getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Integer esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Integer getCifraMinima() {
		return cifraMinima;
	}

	public void setCifraMinima(Integer cifraMinima) {
		this.cifraMinima = cifraMinima;
	}

	public Set<UtenteDTO> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Set<UtenteDTO> giocatori) {
		this.giocatori = giocatori;
	}

	public UtenteDTO getUtenteCreazione() {
		return utenteCreazione;
	}

	public void setUtenteCreazione(UtenteDTO utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

	public Tavolo buildTavoloModel() {

		Tavolo result = new Tavolo(this.id, this.denominazione, this.dataCreazione, this.esperienzaMinima,
				this.cifraMinima, this.utenteCreazione.buildUtenteModel(false));

		return result;

	}

	public static TavoloDTO buildTavoloDTOFromModel(Tavolo tavoloModel, boolean includeGiocatori) {
		TavoloDTO result = new TavoloDTO(tavoloModel.getId(), tavoloModel.getEsperienzaMinima(),
				tavoloModel.getCifraMinima(), tavoloModel.getDenominazione(), tavoloModel.getDataCreazione());

		if (tavoloModel.getUtenteCreazione() != null && tavoloModel.getUtenteCreazione().getId() != null
				&& tavoloModel.getUtenteCreazione().getId() > 0) {
			result.setUtenteCreazione(UtenteDTO.buildUtenteDTOFromModel(tavoloModel.getUtenteCreazione()));
		}

		if (tavoloModel.getGiocatori() != null && !tavoloModel.getGiocatori().isEmpty()) {
			result.setGiocatori(UtenteDTO.buildUtenteDTOSetFromModelSet(tavoloModel.getGiocatori()));
		}

		return result;
	}

	public static List<TavoloDTO> createTavoloDTOListFromModelList(List<Tavolo> modelListInput,
			boolean includeGiocatori) {
		return modelListInput.stream().map(tavoloEntity -> {
			TavoloDTO result = TavoloDTO.buildTavoloDTOFromModel(tavoloEntity, includeGiocatori);
			
			if (includeGiocatori)
				result.setGiocatori(UtenteDTO.buildUtenteDTOSetFromModelSet(tavoloEntity.getGiocatori()));
			
			return result;
		}).collect(Collectors.toList());
	}

}
