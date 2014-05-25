package hu.unideb.inf.yugioh.gui;

import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Effect;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;
import hu.unideb.inf.yugioh.main.SpellCard;

import org.junit.Test;

/**
 * Egy CardPanel osztályt tesztelő osztály.
 * 
 * @author Rácz Roland
 */
public class CardPanelTest {

	/**
	 * Teszteli a CardPanel konstruktorát minden lehetséges módon.
	 */
	@Test
	public void testCardPanel() {
		
		Player player = new Player("Teszt játékos");
		
		MonsterCard mc = new MonsterCard("Teszt", "-", true, "szél", 500, 500, 5, true, player);
		CardPanel cp = new CardPanel(mc);
		assertTrue(cp!=null && cp instanceof CardPanel);
		
		mc = new MonsterCard("Teszt", "-", true, "szél", 500, 500, 5, false, player);
		cp = new CardPanel(mc);
		assertTrue(cp!=null && cp instanceof CardPanel);
		
		mc = new MonsterCard("Teszt", "-", false, "szél", 500, 500, 5, true, player);
		cp = new CardPanel(mc);
		assertTrue(cp!=null && cp instanceof CardPanel);
		
		mc = new MonsterCard("Teszt", "-", false, "szél", 500, 500, 5, false, player);
		cp = new CardPanel(mc);
		assertTrue(cp!=null && cp instanceof CardPanel);
		
		Effect effect = new Effect("incAtk", "MonsterCard", 400);
		
		SpellCard sc = new SpellCard("Teszt varázslap", "-", true, player, effect);
		cp = new CardPanel(sc);
		assertTrue(cp!=null && cp instanceof CardPanel);
		
		sc = new SpellCard("Teszt varázslap", "-", false, player, effect);
		cp = new CardPanel(sc);
		assertTrue(cp!=null && cp instanceof CardPanel);
		
	}

}
