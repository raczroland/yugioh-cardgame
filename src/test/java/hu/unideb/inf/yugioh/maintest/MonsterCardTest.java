package hu.unideb.inf.yugioh.maintest;


import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Effect;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.SpellCard;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * MonsterCard osztályt tesztelő osztály.
 * 
 * @author Rácz Roland
 */
public class MonsterCardTest {
	
	/**
	 * Tesztkörnyezet beállítása.
	 */
	@BeforeClass
	public static void setupTest() {
		Game.getInstance();
		Game.newMatch();
	}
	
	/**
	 * Tesztelendő szörnylap.
	 */
	private MonsterCard card;
	
	/**
	 * Tesztkönyezet inicializálása.
	 */
	@Before
	public void init() {
		card = new MonsterCard("Sötét varázsló", "minta leírás", true, "sötét", 2500, 2100, 7, true, Game.getHuman());
	}
	
	/**
	 * A setFaceup() metódus tesztelése.
	 */
	@Test
	public void testSetFaceup() {
		card.setFaceup(false);
		assertEquals(false, card.isFaceup());
	}
	
	/**
	 * A getName() metódus tesztelése.
	 */
	@Test
	public void testGetName() {
		assertEquals("Sötét varázsló", card.getName());
	}
	
	/**
	 * A getDescription() metódus tesztelése.
	 */
	@Test
	public void testGetDescription() {
		assertEquals("minta leírás", card.getDescription());
	}
	
	/**
	 * A setOwner() és a getOwner() metódus tesztelése.
	 */
	@Test
	public void testSetGetOwner() {
		card.setOwner(Game.getComputer());
		assertEquals(Game.getComputer(), card.getOwner());
	}
	
	/**
	 * A setAtk() metódus tesztelése.
	 */
	@Test
	public void testSetAtk() {
		card.setAtk(500);
		assertEquals(500, card.getAtk());
	}
	
	/**
	 * Az addAtk() metódus tesztelése.
	 */
	@Test
	public void testAddAtk() {
		card.addAtk(300);
		assertEquals(2800, card.getAtk());
	}
	
	/**
	 * A subAtk() metódus tesztelése.
	 */
	@Test
	public void testSubAtk() {
		card.subAtk(400);
		assertEquals(2100, card.getAtk());
		card.subAtk(5000);
		assertEquals(0, card.getAtk());
	}
	
	/**
	 * A setDef() metódus tesztelése.
	 */
	@Test
	public void testSetDef() {
		card.setDef(500);
		assertEquals(500, card.getDef());
	}
	
	/**
	 * Az addDef() metódus tesztelése.
	 */
	@Test
	public void testAddDef() {
		card.addDef(300);
		assertEquals(2400, card.getDef());
	}
	
	/**
	 * A subDef() metódus tesztelése.
	 */
	@Test
	public void testSubDef() {
		card.subDef(400);
		assertEquals(1700, card.getDef());
		card.subDef(5000);
		assertEquals(0, card.getDef());
	}
	
	/**
	 * A getType() metódus tesztelése.
	 */
	@Test
	public void testGetType() {
		assertEquals("sötét", card.getType());
	}
	
	/**
	 * A getLevel() metódus tesztelése.
	 */
	@Test
	public void testGetLevel() {
		assertEquals(7, card.getLevel());
	}
	
	/**
	 * A setDefensePosition() metódus tesztelése.
	 */
	@Test
	public void testSetDefensePosition() {
		card.setDefensePosition(false);
		assertEquals(false, card.isDefensePosition());
	}
	
	/**
	 * A compareTo() metódus tesztelése.
	 */
	@Test
	public void testCompareTo() {

		Effect effect = new Effect("draw", "Player", 3);
		SpellCard sc = new SpellCard("Teszt1", "-", false, Game.getHuman(), effect);
		assertEquals(1, card.compareTo(sc));
		
		MonsterCard mc = new MonsterCard("Teszt2", "-", true, "szél", 500, 600, 4, true, Game.getHuman());
		assertTrue(card.compareTo(mc)>0);
		
	}
	
	/**
	 * A konstruktor tesztelése.
	 */
	@Test
	public void testMonsterCard() {
		MonsterCard mc = new MonsterCard("Teszt2", "-", true, "szél", 500, 600, 4, true, Game.getHuman());
		assertTrue(mc!=null && mc instanceof MonsterCard);
	}

}
