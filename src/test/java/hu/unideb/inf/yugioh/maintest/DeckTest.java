package hu.unideb.inf.yugioh.maintest;


import static org.junit.Assert.*;

import java.util.Vector;

import hu.unideb.inf.yugioh.main.Card;
import hu.unideb.inf.yugioh.main.Deck;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;
import hu.unideb.inf.yugioh.main.SpellCard;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Egy Deck osztályt tesztelő osztály.
 * 
 * @author Rácz Roland
 */
public class DeckTest {
	
	/**
	 * Tesztelendő pakli.
	 */
	private Deck deck;
	
	/**
	 * Kártyák listája.
	 */
	private Vector<Card> cards;
	
	/**
	 * Első teszt játékos.
	 */
	private Player player1;
	
	/**
	 * Második teszt játékos.
	 */
	private Player player2;
	
	/**
	 * Tesztkörnyezet kialakítása.
	 */
	@BeforeClass
	public static void setupTest() {
		Game.getInstance();
		Game.newMatch();
	}
	
	/**
	 * Értékek beállítása tesztek előtt.
	 */
	@Before
	public void setup() {
		player1 = new Player("Teszt játékos");
		cards = new Vector<Card>();
		cards.add( new MonsterCard("Teszt1", "Teszt1", true, "sötét", 3000, 2000, 7, false, player1) );
		cards.add( new MonsterCard("Teszt2", "Teszt2", false, "fény", 500, 500, 3, false, player1) );
		cards.add( new MonsterCard("Teszt3", "Teszt3", true, "sötét", 1000, 2000, 5, true, player1) );
		deck = new Deck(cards);
	}
	
	/**
	 * Konstruktor tesztelése.
	 */
	@Test
	public void testDeck() {
		Deck deck = new Deck(cards);
		assertTrue(deck!=null && deck instanceof Deck);
	}

	/**
	 * A size() metódus tesztelése.
	 */
	@Test
	public void testSize() {
		assertEquals(3, deck.size());
	}

	/**
	 * Húzások tesztelése.
	 */
	@Test
	public void testDraw() {
		assertEquals(cards.get(0), deck.draw());
		assertArrayEquals(new Card[]{ cards.get(0), cards.get(1) }, deck.draw(2) );
		assertEquals(null, deck.draw());
		deck.removeAll();
		assertArrayEquals(null, deck.draw(5));
	}

	/**
	 * Hozzáadások tesztelése.
	 */
	@Test
	public void testAdd() {
		SpellCard sc1 = new SpellCard("Teszt4", "Teszt4", true, player1, null);
		SpellCard sc2 = new SpellCard("Teszt5", "Teszt5", false, player1, null);
		deck.addBottom(sc1);
		assertEquals(sc1, deck.getCards().get(3));
		deck.addBottom(new Card[]{ sc1, sc2});
		assertArrayEquals(new Card[]{ sc1, sc2}, new Card[]{ deck.getCards().get(4), deck.getCards().get(5) });
		deck.addTop(sc1);
		assertEquals(sc1, deck.getCards().get(0));
		deck.addTop(new Card[]{ sc1, sc2});
		assertArrayEquals(new Card[]{ sc2, sc1}, new Card[]{ deck.getCards().get(0), deck.getCards().get(1) });
	}
	
	/**
	 * A containsCardWithName() metódus tesztelése.
	 */
	@Test
	public void testContainsCardWithName() {
		assertTrue(deck.containsCardWithName("Teszt1"));
		assertFalse(deck.containsCardWithName("NemLétezőTeszt"));
	}
	
	/**
	 * A getCards() metódus tesztelése.
	 */
	@Test
	public void testGetCards() {
		assertEquals(cards, deck.getCards());
	}
	
	/**
	 * A removeCard() metódus tesztelése.
	 */
	@Test
	public void testRemoveCard() {
		Card card = deck.getCards().get(0);
		deck.removeCard(card);
		assertFalse(deck.getCards().contains(card));
	}
	
	/**
	 * A removeAll() metódus tesztelése.
	 */
	@Test
	public void testRemoveAll() {
		deck.removeAll();
		assertEquals(0, deck.size());
	}
	
	/**
	 * A setOwner() metódus tesztelése.
	 */
	@Test
	public void testSetOwner() {
		deck.setOwner(player2);
		boolean bl = true;
		for (Card card : deck.getCards()) {
			if (card.getOwner()!=player2) {
				bl = false;
			}
		}
		assertTrue(bl);
	}
	
	/**
	 * A setAllCardFaceUp() metódus tesztelése.
	 */
	@Test
	public void testSetAllCardFaceup() {
		deck.setAllCardToFaceUp();
		boolean bl = true;
		for (Card card : deck.getCards()) {
			if (!card.isFaceup()) {
				bl = false;
			}
		}
		assertTrue(bl);
	}


}
