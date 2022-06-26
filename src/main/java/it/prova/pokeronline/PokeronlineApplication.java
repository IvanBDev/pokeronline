package it.prova.pokeronline;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;

@SpringBootApplication
public class PokeronlineApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;

	@Autowired
	private UtenteService utenteServiceInstance;

	@Autowired
	private TavoloService tavoloServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PokeronlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if (ruoloServiceInstance.trovaTramiteDescrizioneERuolo("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.trovaTramiteDescrizioneERuolo("Special Player", Ruolo.ROLE_SPECIAL_PLAYER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
		}

		if (ruoloServiceInstance.trovaTramiteDescrizioneERuolo("Player", Ruolo.ROLE_PLAYER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Player", Ruolo.ROLE_PLAYER));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Ivan", "Bendotti", 1000, 1000);
			admin.getRuoli().add(ruoloServiceInstance.trovaTramiteDescrizioneERuolo("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("sPlayer") == null) {
			Utente specialPlayer = new Utente("sPlayer", "sPlayer", "Luigi", "Verdi", 100, 100);
			specialPlayer.getRuoli().add(
					ruoloServiceInstance.trovaTramiteDescrizioneERuolo("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
			utenteServiceInstance.inserisciNuovo(specialPlayer);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(specialPlayer.getId());
		}

		if (utenteServiceInstance.findByUsername("player") == null) {
			Utente player = new Utente("player", "player", "Mery", "Rose", 100, 100);
			player.getRuoli().add(ruoloServiceInstance.trovaTramiteDescrizioneERuolo("Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(player);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(player.getId());
		}

		if (utenteServiceInstance.findByUsername("jackPot") == null) {
			Utente player2 = new Utente("jackPot", "jackPot", "Maryanne", "LOrence", 250, 250);
			player2.getRuoli().add(ruoloServiceInstance.trovaTramiteDescrizioneERuolo("Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(player2);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(player2.getId());
		}

		Set<Utente> giocatoriTavolo1 = new HashSet<Utente>();
		giocatoriTavolo1.add(utenteServiceInstance.findByUsername("sPlayer"));
		giocatoriTavolo1.add(utenteServiceInstance.findByUsername("player"));

		Utente utenteCreazione1 = utenteServiceInstance.findByUsername("sPlayer");
		String denominazione1 = "A001";
		Tavolo tavolo1 = tavoloServiceInstance.findByDenominazione(denominazione1);

		if (tavolo1 == null) {
			tavolo1 = new Tavolo(1L, denominazione1, LocalDate.now(), 50, 50, giocatoriTavolo1, utenteCreazione1);
			tavoloServiceInstance.inserisciNuovo(tavolo1);
		}

		// TAVOLO2##########################################
		Set<Utente> giocatoriTavolo2 = new HashSet<Utente>();
		// giocatoriTavolo2.add(utenteServiceInstance.findByUsername("player"));
		giocatoriTavolo2.add(utenteServiceInstance.findByUsername("jackPot"));

		Utente utenteCreazione2 = utenteServiceInstance.findByUsername("sPlayer");
		String denominazione2 = "A002";
		Tavolo tavolo2 = tavoloServiceInstance.findByDenominazione(denominazione2);

		if (tavolo2 == null) {
			tavolo2 = new Tavolo(2L, denominazione2, LocalDate.now(), 100, 100, giocatoriTavolo2, utenteCreazione2);
			tavoloServiceInstance.inserisciNuovo(tavolo2);
		}

		// TAVOLO2##########################################
		Set<Utente> giocatoriTavolo3 = new HashSet<Utente>();
		//giocatoriTavolo3.add(utenteServiceInstance.findByUsername("player"));
		//giocatoriTavolo3.add(utenteServiceInstance.findByUsername("jackPot"));

		Utente utenteCreazione3 = utenteServiceInstance.findByUsername("sPlayer");
		String denominazione3 = "A003";
		Tavolo tavolo3 = tavoloServiceInstance.findByDenominazione(denominazione3);

		if (tavolo3 == null) {
			tavolo3 = new Tavolo(3L, denominazione2, LocalDate.now(), 100, 100, giocatoriTavolo3, utenteCreazione3);
			tavoloServiceInstance.inserisciNuovo(tavolo3);
		}

	}

}
