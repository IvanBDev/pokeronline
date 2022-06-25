package it.prova.pokeronline.security.auth.dto;

import java.time.LocalDate;
import java.util.List;

public class UtenteInfoJWTResponseDTO {

	private String nome;
	private String cognome;
	private String type = "Bearer";
	private String username;
	private LocalDate dataCreazione;
	private List<String> roles;

	public UtenteInfoJWTResponseDTO(String nome, String cognome, String username, List<String> roles) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.roles = roles;
	}

	public UtenteInfoJWTResponseDTO(String nome, String cognome, String username, LocalDate dataCreazione,
			List<String> roles) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.dataCreazione = dataCreazione;
		this.roles = roles;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}