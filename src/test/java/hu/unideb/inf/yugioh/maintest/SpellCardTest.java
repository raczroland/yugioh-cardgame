package hu.unideb.inf.yugioh.maintest;

import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Effect;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;
import hu.unideb.inf.yugioh.main.SpellCard;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * SpellCard osztályt tesztelő osztály.
 * 
 * @author Rácz Roland
 */
public class SpellCardTest {
	
	/**
	 * Tesztelendő varázslap.
	 */
	private SpellCard card;
	
	/**
	 * Teszt hatás.
	 */
	private Effect effect;
	
	/**
	 * Teszt játékos.
	 */
	private Player player;
	
	/**
	 * Tesztkönyezet inicializálása.
	 */
	@Before
	public void init() {
		effect = new Effect("incAtk", "MonsterCard", 300);
		player = new Player("Teszt játékos");
		card = new SpellCard("Teszt", "Teszt leírás.", true, player, effect);
	}
	
	/**
	 * Tesztkönyezet inicializálása.
	 */
	@BeforeClass
	public static void setupTest() {
		Game.getInstance();
		Game.newMatch();
	}
	
	/**
	 * A compareTo() metódus tesztelése.
	 */
	@Test
	public void testCompareTo() {
		
		SpellCard sc = new SpellCard("Teszt1", "-", false, player, effect);
		assertEquals(0, card.compareTo(sc));
		
		MonsterCard mc = new MonsterCard("Teszt2", "-", true, "szél", 500, 600, 4, true, player);
		assertEquals(-1, card.compareTo(mc));
		
	}

	/**
	 * Az activate() metódus tesztelése.
	 */
	@Test
	public void testActivate() {
		
		assertFalse(card.activate());
		
		Effect effect1 = new Effect("draw", "Player", 3);
		SpellCard sc = new SpellCard("Teszt4", "-", false, player, effect1);
		assertTrue(sc.activate());
		
		MonsterCard mc = new MonsterCard("Teszt5", "-", true, "sötét", 800, 400, 4, true, player);
		assertTrue(card.activate(mc));
		
		assertFalse(sc.activate(mc));
		
	}
	
	/**
	 * A konstruktor tesztelése.
	 */
	@Test
	public void testSpellCard() {
		SpellCard sc = new SpellCard("Teszt6", "-", false, player, effect);
		assertTrue(sc!=null && sc instanceof SpellCard);
	}

}
