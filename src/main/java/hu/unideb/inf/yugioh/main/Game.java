package hu.unideb.inf.yugioh.main;

import hu.unideb.inf.yugioh.gui.GUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játékot reprezentáló osztály.
 * 
 * A játék grafikus felülete innen érhető el a következő módon: <code>Game.getGUI()</code>.
 * 
 * A játék meccsét futtató szála a következő módon érhető el: <code>Game.GetWorker()</code>.
 * 
 * A program várakoztatása is innen érhető el: <code>Game._wait()</code>.
 * 
 * @author Rácz Roland
 */
public class Game {

	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Game.class);
	
	/**
	 * Konstans. A kör végén maximálisan kézben tarható kártyalapok száma. 
	 */
	public static final int MAX_CARD_IN_HAND = 6;
	
	/**
	 * Konstans. A zónákban maximálisan elhelyezhető kátyalapok száma.
	 */
	public static final int MAX_CARD_ON_FIELD = 5;
	
	/**
	 * Konstans. Kezdéskor a kézben lévő lapok száma.
	 */
	public static final int START_CARD_IN_HAND = 5;
	
	/**
	 * Konstans. Kezdéskor a játékosok életpontjainak száma.
	 */
	public static final int START_LIFEPOINTS = 8000;
	
	/**
	 * Az osztály példánya.
	 */
	private static Game instance = null;

	/**
	 * A program grafikus felülete.
	 */
	private static GUI visualizator;
	
	/**
	 * A játék aktuális, betöltödd meccse.
	 */
	private static Match match;
	
	/**
	 * Az emberi játékos.
	 */
	private static Player human;
	
	/**
	 * A számítógépes játékos.
	 */
	private static Player computer;
	
	/**
	 * Aktuálisan betöltött pakli az emberi játékos számára.
	 */
	private static Deck loadedDeck;
	
    /**
     * Az aktuális meccset "futtató" szál.
     */
    public static GameWorker worker;
	
	/**
	 * Konstruktor az osztályhoz.
	 */
	protected Game() {
		init();
	}
	
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
	 * Visszaadja a program grafikus felületét.
	 * 
	 * @return program grafikus felülete
	 */
	public static GUI getGUI() {
		return visualizator;
	}
	
	/**
	 * Visszaadja az aktuális játékot "futtató" szálat.
	 * 
	 * @return az aktuális játékot "futtató" szál
	 */
	public static GameWorker getWorker() {
		return worker;
	}

	/**
	 * A program inicializálása.
	 */
	public static void init() {
		human = new Player("Felhasználó");
		computer = new AIPlayer("Számítógép");
		loadedDeck = Generator.generateRandomDeck(human, 40);
		visualizator = new GUI();
		logger.info("Játék inicializálva.");
	}
	
	/**
	 * Visszaadja az aktuális meccset.
	 * 
	 * @return az aktuális meccs
	 */
	public static Match getMatch() {
		return match;
	}
	
	/**
	 * Visszaadja a betöltött paklit.
	 * 
	 * @return az aktuálisan betöltött pakli
	 */
	public static Deck getLoadedDeck() {
		return loadedDeck;
	}
	
	/**
	 * Beállítja a betöltött paklit.
	 * 
	 * @param deck a betöltött pakli
	 */
	public static void setLoadedDeck(Deck deck) {
		loadedDeck = deck;
	}
	
	/**
	 * Visszaadja az emberi játékost.
	 * 
	 * @return az emberi játékos
	 */
	public static Player getHuman() {
		return human;
	}

	/**
	 * Visszaadja a számítógépes játékot.
	 * 
	 * @return a számítógépes játékos
	 */
	public static Player getComputer() {
		return computer;
	}

	/**
	 * Új meccs létrehozása.
	 */
	public static void newMatch() {
		
		logger.info("Új meccs létrehozása.");

		human.setDeck((Deck)loadedDeck.clone());
		computer.setDeck(Generator.generateRandomDeck(computer, 40));
		
		match = new Match( human, computer );
		
		visualizator.setPlayers(human, computer);
		visualizator.showLifepoints(human);
		visualizator.showLifepoints(computer);
		
		human.getDeck().shuffle();
		computer.getDeck().shuffle();
		
		worker = new GameWorker();		
		worker.execute();
		
		logger.info("Új meccs létrehozva és elindítva.");
		
	}
	
	/**
	 * Üzenet megjelenítése a grafikus felületen.
	 * 
	 * @deprecated helyette a GUI saját üzenetmegjelenítője használható
	 * @param msg a megjelenítendő üzenet
	 */
	@Deprecated
	public static void showMessage(String msg) {
		visualizator.showMessage(msg);
	}
	
	/**
	 * Várakoztató metódus.
	 * 700 milliszekundumig várakoztatja a program futását. 
	 */
	public static void _wait() {
		try {
		    Thread.sleep(700);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * A játék főmetódusa.
	 * 
	 * @param args parancssori argumentumok
	 */
	public static void main(String[] args) {
		
		Game.getInstance();

	}

}
