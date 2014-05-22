package hu.unideb.inf.yugioh.main;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy játékost reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
/**
 * @author Roland
 *
 */
public class Player {

	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Card.class);
	
	/**
	 * A játékos neve.
	 */
	private String name;
	
	/**
	 * A játékos életpontjainak száma.
	 */
	private int lifepoint;
	
	/**
	 * A játékos paklija.
	 */
	private Deck deck;
	
	/**
	 * A játékos kezében lévő lapok.
	 */
	private Hand hand;
	
	/**
	 * A játékoss temetője.
	 */
	private Deck graveyard;
	
	/**
	 * A megidézett szörnykártyákat tartalmazó zóna.
	 */
	private MonsterCardZone monsterCardZone;
	
	/**
	 * A lerakott varázskártyákat tartalmazó zóna.
	 */
	private SpellCardZone spellCardZone;
	
	/**
	 * A játékos nevét adja vissza.
	 * 
	 * @return a játékos neve
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A játékos életpontjainak számát adja vissza.
	 * 
	 * @return a játékos életpontjainak száma 
	 */
	public int getLifepoint() {
		return lifepoint;
	}
	
	/**
	 * A játékos életpontjainak számát növeli a megadott értékkel.
	 * 
	 * @param lp a hozzáadandó életpontok száma
	 */
	public void addLifepoint(int lp) {
		lifepoint += lp;
	}
	
	/**
	 * A játékos életpontjainak számát csökkenti a megadott értékkel.
	 * Ha az életpontok 0-ig vagy az alá csökkenek, beállítja a másik játékost győztesnek.
	 * 
	 * @param lp a kivonandó életpontok száma
	 */
	public void subLifepoint(int lp) {
		lifepoint -= lp;
		if (lifepoint<0) {
			lifepoint = 0;
		}
		if (lifepoint==0) {
			Game.getMatch().setWinner( this==Game.getMatch().getPlayer1() ? Game.getMatch().getPlayer2() : Game.getMatch().getPlayer1() );
		}
	}
	
	/**
	 * A játékos pakliját adja vissza.
	 * 
	 * @return a játékos paklija
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * A játékos kezében lévő lapokat adja vissza.
	 * 
	 * @return a játékos kezében lévő lapok
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * A játékos temetőjét adja vissza.
	 * 
	 * @return a játékos temetője
	 */
	public Deck getGraveyard() {
		return graveyard;
	}

	/**
	 * A játékos megidézett szörnylapjait tartalmazó zónát adja vissza.
	 * 
	 * @return a játékos megidézett szörnylapjait tartalmazó zóna
	 */
	public MonsterCardZone getMonsterCardZone() {
		return monsterCardZone;
	}

	/**
	 * A játékos lerakott varázslapjait tartalmazó zónát adja vissza.
	 * 
	 * @return a játékos lerakott varázslapjait tartalmazó zóna
	 */
	public SpellCardZone getSpellCardZone() {
		return spellCardZone;
	}

	/**
	 * Konstruktor az osztályhoz.
	 * Példányosít egy játékost a megadott névvel.
	 * 
	 * @param name a játékos neve
	 */
	public Player(String name) {
		this.name = name;
		this.lifepoint = 8000;
		this.deck = Generator.generateRandomDeck(this, 40);
		this.graveyard = new Deck(new Vector<Card>());
		this.hand = new Hand(new Vector<Card>());
		this.monsterCardZone = new MonsterCardZone();
		this.spellCardZone = new SpellCardZone();
		logger.info("Játékos létrehozva: " + this);
		// TODO megírni jól
	}

	/**
	 * Konstruktor az osztályhoz.
	 * Példányosít egy játékost a megadott névvel és a megadott paklival.
	 * 
	 * @param name a játékos neve
	 * @param deck a játékos paklija
	 */
	public Player(String name, Deck deck) {
		this.name = name;
		this.lifepoint = 8000;
		this.deck = deck;
		this.graveyard = new Deck(new Vector<Card>());
		this.hand = new Hand(new Vector<Card>());
		this.monsterCardZone = new MonsterCardZone();
		this.spellCardZone = new SpellCardZone();
		logger.info("Játékos létrehozva megadott paklival: " + this);
		// TODO megírni jól
	}
	
	/**
	 * Húzási fázist végrehajtó metódus.
	 */
	public void drawPhase() {
		logger.info(this + ": húzási fázis");
		Game.showMessage(getName() + ": húzási fázis");
		getHand().addTop(getDeck().draw());
	}
	
	/**
	 * Az első fő fázist végrehajtó metódus.
	 */
	public void mainPhase1() {
		logger.info(this + ": első fő fázis");
		Game.showMessage(getName() + ": 1. fő fázis");
		
	}
	
	/**
	 * A harci fázist végrehajtó metódus.
	 */
	public void battlePhase() {
		logger.info(this + ": harci fázis");
		Game.showMessage(getName() + ": harci fázis");
		
	}
	
	/**
	 * A második fő fázist végrehajtó metódus.
	 */
	public void mainPhase2() {
		logger.info(this + ": második fő fázis");
		Game.showMessage(getName() + ": 2. fő fázis");
		
	}
	
	/**
	 * Vég fázist végrehajtó metódus.
	 */
	public void endPhase() {
		logger.info(this + ": vég fázis");
		Game.showMessage(getName() + ": kör vége");
	}

	@Override
	public String toString() {
		return "[" + name + "]";
	}
	
	

}
