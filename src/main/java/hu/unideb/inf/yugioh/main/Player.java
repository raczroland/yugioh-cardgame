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
	private int lifepoints;
	
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
	public int getLifepoints() {
		return lifepoints;
	}
	
	/**
	 * A játékos életpontjainak számát növeli a megadott értékkel.
	 * 
	 * @param lp a hozzáadandó életpontok száma
	 */
	public void addLifepoints(int lp) {
		lifepoints += lp;
	}
	
	/**
	 * A játékos életpontjainak számát csökkenti a megadott értékkel.
	 * Ha az életpontok 0-ig vagy az alá csökkenek, beállítja a másik játékost győztesnek.
	 * 
	 * @param lp a kivonandó életpontok száma
	 */
	public void subLifepoints(int lp) {
		lifepoints -= lp;
		if (lifepoints<0) {
			lifepoints = 0;
		}
		Game.getGUI().showLifepoints(this);
		if (lifepoints==0) {
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
	 * A játékos pakliját állítja be.
	 * 
	 * @param deck a játékos beállítandó paklija
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
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
		this.lifepoints = 8000;
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
		this.lifepoints = 8000;
		this.deck = deck;
		this.graveyard = new Deck(new Vector<Card>());
		this.hand = new Hand(new Vector<Card>());
		this.monsterCardZone = new MonsterCardZone();
		this.spellCardZone = new SpellCardZone();
		logger.info("Játékos létrehozva megadott paklival: " + this);
		// TODO megírni jól
	}
	
	/**
	 * Egy kiválasztott kártyalapot vár a felhasználó felülettől.
	 * 
	 * @param handEvent várakozzon-e kézben lévő lapra
	 * @param humanMonsterEvent várakozzon-e az emberi játékos megidézett szörnylapjaira
	 * @param computerMonsterEvent várakozzon-e a számítógépes játékos megidézett szörnylapjaira
	 * @return a kiválasztott kártyalap
	 */
	private Card requestCard(boolean handEvent, boolean humanMonsterEvent, boolean computerMonsterEvent) {
		Game.getGUI().setHandEventEnabled(handEvent);
		Game.getGUI().setHumanMonsterEventEnabled(humanMonsterEvent);
		Game.getGUI().setComputerMonsterEventEnabled(computerMonsterEvent);
		while (Game.getGUI().getEventObject()==null) {
			Game._wait();
		}
		Game.getGUI().setHandEventEnabled(false);
		Game.getGUI().setHumanMonsterEventEnabled(false);
		Game.getGUI().setComputerMonsterEventEnabled(false);
		Card card = (Card) Game.getGUI().getEventObject();
		Game.getGUI().setEventObject(null);
		return card;
	}
	
	/**
	 * Húzási fázist végrehajtó metódus.
	 */
	public void drawPhase() {
		logger.info(this + ": húzási fázis");
		Game.showMessage(getName() + ": húzási fázis");
		Card card = getDeck().draw();
		card.setFaceup(true);
		getHand().addTop(card);
	}
	
	/**
	 * A fő fázist végrehajtó metódus.
	 * Várakozik a játékos lépésére.
	 */
	public void mainPhase() {
		logger.info(this + ": fő fázis");
		Game.showMessage(getName() + ": fő fázis");
		
		Card card = requestCard(true, true, false);
		
		if (hand.getCards().contains(card) && card instanceof MonsterCard) {
			hand.summonMonsterCard((MonsterCard)card, false);
			// TODO áldozás...
		} else if (monsterCardZone.getCards().contains(card)) {
			MonsterCard mc = (MonsterCard) card;
			if (mc.isDefensePosition()) {
				mc.setDefensePosition(false);
			} else {
				mc.setDefensePosition(true);
			}
			Game.getGUI().removeCardFromField(mc, mc.getOwner());
			Game.getGUI().addCardToField(mc, mc.getOwner());
		}
		
		// TODO varázslaphoz is!!!
		//System.out.println(card);
	}
	
	/**
	 * A harci fázist végrehajtó metódus.
	 */
	public void battlePhase() {
		logger.info(this + ": harci fázis");
		Game.showMessage(getName() + ": harci fázis");
		
		if (monsterCardZone.size()>0) {
		
			MonsterCard mc = (MonsterCard) requestCard(false, true, false);
			
			if (!mc.isDefensePosition()) {
			
				if (Game.getComputer().getMonsterCardZone().size()>0) {
					
					MonsterCard emc = (MonsterCard) requestCard(false, false, true);
					
					mc.attack(emc);
					
				} else {
					mc.attack(Game.getComputer());
				}
				
			}
		
		} else {
			Game._wait();
		}
		
	}
	
	/**
	 * Vég fázist végrehajtó metódus.
	 */
	public void endPhase() {
		logger.info(this + ": vég fázis");
		Game.showMessage(getName() + ": kör vége");

		while ( getHand().size() > Game.MAX_CARD_IN_HAND ) {
			
			Game.showMessage("Túl sok kártyalap van a kezedben. Dobj el egyet.");
			
			Card card = requestCard(true, false, false);
			
			getHand().removeCard(card);
			getGraveyard().addTop(card);
			
			Game.getGUI().removeCardFromHand(card, card.getOwner());
			Game.getGUI().addCardToGraveyard(card, card.getOwner());
			
		}
		
	}

	@Override
	public String toString() {
		return "[" + name + "]";
	}
	
	

}
