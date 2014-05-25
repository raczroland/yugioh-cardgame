package hu.unideb.inf.yugioh.data;

import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Effect;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;
import hu.unideb.inf.yugioh.main.SpellCard;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Egy varázslap osztályának adatait reprezentáló osztály tesztelése.
 * 
 * @author Rácz Roland
 */
public class SpellCardItemTest {
	
	/**
	 * Tesztelendő objektum.
	 */
	private static SpellCardItem sci;
	
	/**
	 * Tesztelendő objektum létrehozása.
	 */
	@BeforeClass
	public static void setupTest() {
		sci = new SpellCardItem();
	}

	/**
	 * Hatás típust lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetEffectType() {
		sci.setEffectType("incAtk");
		assertEquals("incAtk", sci.getEffectType());
	}

	/**
	 * Hatás célpontja osztálya nevét lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetEffectTargetClass() {
		sci.setEffectTargetClass("MonsterCard");
		assertEquals("MonsterCard", sci.getEffectTargetClass());
	}

	/**
	 * Az első segédváltozót lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetEffectVal1() {
		sci.setEffectVal1(200);
		assertEquals(200, sci.getEffectVal1());
	}

	/**
	 * A második segédváltozót lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetEffectVal2() {
		sci.setEffectVal2(500);
		assertEquals(500, sci.getEffectVal2());
	}
	
	/**
	 * Előállít egy varázslap objektumot, majd leteszteli, hogy megfelelő objektumot hozott-e létre.
	 */
	@Test
	public void testAsSpellCard() {
		sci.setName("Teszt név");
		sci.setEffectTargetClass("SpellCard");
		sci.setEffectType("decAtk");
		sci.setEffectVal1(200);
		sci.setEffectVal2(0);
		SpellCard sc = sci.asSpellCard();
		assertEquals("Teszt név", sc.getName());
		assertEquals("SpellCard", sc.getEffect().getTargetClass());
		assertEquals("decAtk", sc.getEffect().getType());
		assertEquals(200, sc.getEffect().getVal1());
		assertEquals(0, sc.getEffect().getVal2());
	}
	
	/**
	 * Teszteli a konstruktort.
	 */	
	@Test
	public void testSpellCardItem() {
		Player player = new Player("Teszt játékos");
		Effect effect = new Effect("incDef", "MonsterCard", 300, 500);
		SpellCard sc = new SpellCard("Teszt név", "Teszt leírás.", true, player, effect);
		SpellCardItem sci = new SpellCardItem(sc);
		assertEquals("Teszt név", sci.getName());
		assertEquals("Teszt leírás.", sci.getDescription());
		assertEquals("incDef", sci.getEffectType());
		assertEquals("MonsterCard", sci.getEffectTargetClass());
		assertEquals(300, sci.getEffectVal1());
		assertEquals(500, sci.getEffectVal2());
	}

}
