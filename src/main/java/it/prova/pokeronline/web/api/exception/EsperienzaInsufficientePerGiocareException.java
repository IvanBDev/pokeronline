package it.prova.pokeronline.web.api.exception;

public class EsperienzaInsufficientePerGiocareException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EsperienzaInsufficientePerGiocareException (String message) {
		super(message);
	}

}
