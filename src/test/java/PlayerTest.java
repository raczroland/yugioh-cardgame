import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.Player;

import org.junit.BeforeClass;
import org.junit.Test;


public class PlayerTest {
	
	private static Player player;
	
	@BeforeClass
	public static void setupTest() {
		Game.getInstance();
		Game.newMatch();
		player = new Player("Teszt", Game.getLoadedDeck());
	}

	@Test
	public void testPlayer() {
		assertEquals("Teszt", player.getName());
	}

	@Test
	public void testLifepoint() {
		player.setLifepoints(8000);
		player.subLifepoints(500);
		assertEquals(7500, player.getLifepoints());
		player.addLifepoints(1000);
		assertEquals(8500, player.getLifepoints());
	}

	@Test
	public void testGetGraveyard() {
		assertEquals(0, player.getGraveyard().size());
	}

	@Test
	public void testDrawPhase() {
		int n = Game.getHuman().getHand().size();
		Game.getHuman().drawPhase();
		assertEquals(n+1, Game.getHuman().getHand().size());		
	}

}
