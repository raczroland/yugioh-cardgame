

import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * MonsterCard osztályt tesztelő osztály.
 * 
 * @author Rácz Roland
 */
public class MonsterCardTest {
	
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
	 * Ellenséges szörny a teszteléshez.
	 */
	private MonsterCard enemy;
	
	/**
	 * Tesztkönyezet inicializálása.
	 */
	@Before
	public void init() {
		card = new MonsterCard("Sötét varázsló", "minta leírás", true, "sötét", 2500, 2100, 7, true, Game.getHuman());
	}
	
	@Test
	public void testFaceup() {
		card.setFaceup(false);
		assertEquals(false, card.isFaceup());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Sötét varázsló", card.getName());
	}
	
	@Test
	public void testDescription() {
		assertEquals("minta leírás", card.getDescription());
	}
	
	@Test
	public void testSetGetOwner() {
		card.setOwner(Game.getComputer());
		assertEquals(Game.getComputer(), card.getOwner());
	}
	
	@Test
	public void testSetAtk() {
		card.setAtk(500);
		assertEquals(500, card.getAtk());
	}
	
	@Test
	public void testAddAtk() {
		card.addAtk(300);
		assertEquals(2800, card.getAtk());
	}
	
	@Test
	public void testSubAtk() {
		card.subAtk(400);
		assertEquals(2100, card.getAtk());
		card.subAtk(5000);
		assertEquals(0, card.getAtk());
	}
	
	@Test
	public void testSetDef() {
		card.setDef(500);
		assertEquals(500, card.getDef());
	}
	
	@Test
	public void testAddDef() {
		card.addDef(300);
		assertEquals(2400, card.getDef());
	}
	
	@Test
	public void testSubDef() {
		card.subDef(400);
		assertEquals(1700, card.getDef());
		card.subDef(5000);
		assertEquals(0, card.getDef());
	}
	
	@Test
	public void testGetType() {
		assertEquals("sötét", card.getType());
	}
	
	@Test
	public void testLevel() {
		assertEquals(7, card.getLevel());
	}
	
	@Test
	public void testDefensePosition() {
		card.setDefensePosition(false);
		assertEquals(false, card.isDefensePosition());
	}
	
	/*@Test
	public void testAttack() {
		enemy = new MonsterCard("Kékszemű hósárkány", "minta leírás", true, "szél", 3000, 2500, 8, false, Game.getComputer());
		assertNotNull(enemy);
		// TODO megírni
		//card.attack(enemy);
	}*/

}
