package hu.unideb.inf.yugioh.maintest;
import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.Player;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Egy Player osztályt tesztelő osztály.
 * 
 * @author Rácz Roland
 */
public class PlayerTest {
	
	/**
	 * Tesztelendő játékos.
	 */
	private static Player player;
	
	/**
	 * Tesztkönyezet inicializálása.
	 */
	@BeforeClass
	public static void setupTest() {
		Game.getInstance();
		Game.newMatch();
		player = new Player("Teszt", Game.getLoadedDeck());
	}

	/**
	 * A getName() metódus tesztelése.
	 */
	@Test
	public void testPlayer() {
		assertEquals("Teszt", player.getName());
	}

	/**
	 * Az életpontot módosító metódusok tesztelése.
	 */
	@Test
	public void testLifepoint() {
		player.setLifepoints(8000);
		player.subLifepoints(500);
		assertEquals(7500, player.getLifepoints());
		player.addLifepoints(1000);
		assertEquals(8500, player.getLifepoints());
		player.subLifepoints(9000);
		assertEquals(0, player.getLifepoints());
	}

	/**
	 * A temetőt tesztelő metódus.
	 */
	@Test
	public void testGetGraveyard() {
		assertEquals(0, player.getGraveyard().size());
	}

	/**
	 * A húzási fázist tesztelő metódus.
	 */
	@Test
	public void testDrawPhase() {
		int n = Game.getHuman().getHand().size();
		Game.getHuman().drawPhase();
		assertEquals(n+1, Game.getHuman().getHand().size());		
	}

}
