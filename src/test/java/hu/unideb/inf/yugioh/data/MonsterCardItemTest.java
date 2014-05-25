package hu.unideb.inf.yugioh.data;

import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Egy szörnylap osztályának adatait reprezentáló osztály tesztelése.
 * 
 * @author Rácz Roland
 */
public class MonsterCardItemTest {
	
	/**
	 * Tesztelendő objektum.
	 */
	private static MonsterCardItem mci;
	
	/**
	 * Tesztelendő objektum létrehozása.
	 */
	@BeforeClass
	public static void setupTest() {
		mci = new MonsterCardItem();
	}

	/**
	 * Támadópontokat lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetAtk() {
		mci.setAtk(500);
		assertEquals(500, mci.getAtk());
	}

	/**
	 * Védelmi pontokat lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetDef() {
		mci.setDef(500);
		assertEquals(500, mci.getDef());
	}

	/**
	 * Típust lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetType() {
		mci.setType("fény");
		assertEquals("fény", mci.getType());
	}

	/**
	 * Szintet lekérdező és beállító metódus tesztelése.
	 */
	@Test
	public void testGetSetLevel() {
		mci.setLevel(6);
		assertEquals(6, mci.getLevel());
	}
	
	/**
	 * Előállít egy szörnylap objektumot, majd leteszteli, hogy megfelelő objektumot hozott-e létre.
	 */
	@Test
	public void testAsMonsterCard() {
		mci.setAtk(2000);
		mci.setDef(800);
		mci.setLevel(7);
		mci.setType("sötét");
		mci.setDescription("Teszt leírás.");
		mci.setName("Teszt név");
		MonsterCard mc = mci.asMonsterCard();
		assertEquals("Teszt név", mc.getName());
		assertEquals("Teszt leírás.", mc.getDescription());
		assertEquals("sötét", mc.getType());
		assertEquals(2000, mc.getAtk());
		assertEquals(800, mc.getDef());
		assertEquals(7, mc.getLevel());
	}
	
	/**
	 * Teszteli a konstruktort.
	 */	
	@Test
	public void testMonsterCardItem() {
		Player player = new Player("Teszt játékos");
		MonsterCard mc = new MonsterCard("Teszt név", "Teszt leírás.", true, "szél", 700, 300, 3, true, player);
		MonsterCardItem mci = new MonsterCardItem(mc);
		assertEquals("Teszt név", mci.getName());
		assertEquals("Teszt leírás.", mci.getDescription());
		assertEquals("szél", mci.getType());
		assertEquals(700, mci.getAtk());
		assertEquals(300, mci.getDef());
		assertEquals(3, mci.getLevel());
	}

}
