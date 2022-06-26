package it.prova.pokeronline.web.api.exception;

public class CreditoInsufficientePerGiocareException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CreditoInsufficientePerGiocareException (String message) {
		super(message);
	}
	
	
}
