package hu.unideb.inf.yugioh.main;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.SwingWorker;

import hu.unideb.inf.yugioh.gui.GUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játékot reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
public class Game {

	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Card.class);
	
	/**
	 * Konstans. A kör végén maximálisan kézben tarható kártyalapok száma. 
	 */
	public static final int MAX_CARD_IN_HAND = 6;
	
	/**
	 * Konstans. A zónákban maximálisan elhelyezhető kátyalapok száma.
	 */
	public static final int MAX_CARD_ON_FIELD = 5;
	
	/**
	 * Az osztály példánya.
	 */
	private static Game instance = null;

	/**
	 * A program grafikus felülete
	 */
	private static GUI visualizator;
	
	/**
	 * A játék aktuális, betöltödd meccse.
	 */
	private static Match match;
	
	/**
	 * Konstruktor az osztályhoz.
	 */
	protected Game() { }
	
	/**
	 * Az osztály példányának lekérése.
	 * 
	 * @return az osztály példánya
	 */
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
	    }
	    return instance;
	}
	
	/**
	 * A program inicializálása.
	 */
	public static void init() {
		visualizator = new GUI();
	}
	
	public static Match getMatch() {
		return match;
	}
	
	
	
	public static SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

		@Override
		protected Void doInBackground() throws Exception {
			match.run();
			return null;
		}
		
	};
	
	
	/**
	 * Új meccs létrehozása.
	 */
	public static void newMatch() {
		Player human = new Player("Felhasználó");
		Player computer = new AIPlayer("Számítógép");
		match = new Match( human, computer );
		
		visualizator.setPlayers(human, computer);
		visualizator.showLifepoints(human);
		visualizator.showLifepoints(computer);
		
		worker.execute();
		
		//match.run();
	}
	
	/**
	 * Üzenet megjelenítése a grafikus felületen.
	 * 
	 * @param msg a megjelenítendő üzenet
	 */
	public static void showMessage(String msg) {
		visualizator.showMessage(msg);
	}
	
	public static void _wait() {
		try {
		    Thread.sleep(800);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Game game = Game.getInstance();
		Game.init();
		
		/*Player player = new Player("tesztJátékos");
		Deck teszt = Generator.generateRandomDeck(player, 10);
		/*teszt.shuffle();
		for (Card card : teszt.draw(10)) {
			System.out.println(card.toString());
		}*/
		//DataManager.saveDeckToFile(teszt, "tesztPakli.xml");
		
		/*Deck teszt = DataManager.loadDeckFromFile("cards.xml");
		for (Card card : teszt.draw(2)) {
			System.out.println(card.toString());
		}*/
		
		

	}

}
