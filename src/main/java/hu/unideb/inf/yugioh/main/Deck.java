package hu.unideb.inf.yugioh.main;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;

/**
 * Egy paklit reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
public class Deck {

	/**
	 * A pakli osztályának loggere.
	 */
	protected static Logger	logger = LoggerFactory.getLogger(Deck.class);
	
	/**
	 * A pakli lapjait tartalmazó vektor.
	 */
	private Vector<Card> cards = new Vector<Card>();

	/**
	 * Konstruktor a pakli létrehozásához.
	 * 
	 * @param cards kátyalapokat tartalmazó vektor
	 */
	public Deck(Vector<Card> cards) {
		// TODO jól megírni
		super();
		this.cards = cards;
		logger.info("Pakli létrehozva. [" + this + "]");
	}
	
	/**
	 * Visszaadja a pakliban lévő kártyalapok számát.
	 * 
	 * @return a kártyalapok száma
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * Megkeveri a pakliban lévő kártyákat.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
		logger.info("Pakli megkeverve. [" + this + "]");
	}
	
	/**
	 * Kihúzza a pakli legfelső kártyalapját.
	 * Ha nincs több kihúzható lap a pakliban, beállítja a másik játékost győztesnek.
	 * 
	 * @return a pakli legfelső kártyalapja
	 */
	public Card draw() {
		Game._wait();
		if (size()>0) {
			logger.info("Kártyalap kihúzva: " + cards.get(0) + ", új méret: " + (cards.size()-1));
			return cards.remove(0);
		} else {
			Game.getMatch().setWinner(
				Game.getMatch().getNextPlayer()==Game.getMatch().getPlayer1() ? Game.getMatch().getPlayer2() : Game.getMatch().getPlayer1()
			);
		}
		return null;
	}
	
	/**
	 * Visszaadja a pakli legfelső adott számú kártyalapját.
	 * 
	 * @param n a kártyalapok száma
	 * @return a pakli legfelső adott számú kártyalapjának tömbje
	 */
	public Card[] draw(int n) {
		Card[] drawnCards = new Card[n];
		Card card;
		for (int i=0; i<n; i++) {
			if ( (card=draw()) != null ) {
				drawnCards[i] = card;
			} else {
				return null;
			}
		}
		return drawnCards;
	}
	
	/**
	 * A pakli aljára helyez egy kártyalapot.
	 * 
	 * @param card a pakli aljára helyezendő kártyalap
	 */
	public void addBottom(Card card) {
		cards.add(card);
	}

	/**
	 * A pakli aljára helyezi a tömbként átadott kártyalapokat.
	 * 
	 * @param cards a pakli aljára helyezendő kártyalapok tömbje
	 */
	public void addBottom(Card[] cards) {
		for (Card card : cards) {
			addBottom(card);
		}
	}
	
	/**
	 * A pakli tetejére helyez egy kártyalapot.
	 * 
	 * @param card a pakli tetejére helyezendő kártyalap
	 */
	public void addTop(Card card) {
		cards.add(0, card);
	}
	
	/**
	 * A pakli tetejére helyezi a tömbként átadott kártyalapokat.
	 * 
	 * @param cards a pakli tetejére helyezendő kártyalapok tömbje
	 */
	public void addTop(Card[] cards) {
		for (Card card : cards) {
			addTop(card);
		}
	}
	
	/**
	 * Megmondja, a pakli tartalmaz-e megadott nevű kártyalapot.
	 * 
	 * @param name a keresendő kártyalap neve
	 * @return igazat ad vissza, ha a pakli tartalmazza az adott nevű kártyalapot, különben hamisat
	 */
	public boolean containsCardWithName(String name) {
		for (Card card : cards) {
			if (name.equals(card.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Visszaadja a pakli összes kártyáját egy vektorban.
	 * 
	 * @return a pakli összes kártyáját tartalmazó vektor
	 */
	public Vector<Card> getCards() {
		return cards;
	}
	
	/**
	 * Visszaad a pakliból egy megadott nevű kártyalapot, ha talál.
	 * 
	 * @param name a keresendő kártyalap neve
	 * @return adott nevű kártyalap, különben null
	 */
	public Card getCardWithName(String name) {
		for (Card card : cards) {
			if (name.equals(card.getName())) {
				cards.remove(card);
			}
		}
		return null;
	}
	
	/**
	 * Eltávolít egy megadott lapot a pakliból.
	 * 
	 * @param card az eltávolítandó lap
	 * @return sikerült-e eltávolítani a lapot
	 */
	public boolean removeCard(Card card) {
		return cards.remove(card);
	}
	
	/**
	 * Beállítja a pakli összes kártyalapjának a tulajdonosát.
	 * 
	 * @param player a kártyalapok tulajdonosa
	 */
	public void setOwner(Player player) {
		for (Card card : cards) {
			card.setOwner(player);
		}
	}

	@Override
	public String toString() {
		return "(Pakli) " + cards.size();
	}
	
	

}
