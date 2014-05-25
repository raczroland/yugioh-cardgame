import static org.junit.Assert.*;
import hu.unideb.inf.yugioh.main.Effect;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.MonsterCard;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class EffectTest {
	
	private Effect effect;
	private MonsterCard mc;
	
	@BeforeClass
	public static void setupTest() {
		Game.getInstance();
		Game.newMatch();
	}
	
	@Before
	public void setup() {
		mc = new MonsterCard("Teszt", "-", true, "szél", 500, 600, 5, false, Game.getHuman());
	}

	@Test
	public void testIncAtk() {
		effect = new Effect("incAtk", "MonsterCard", 500);
		effect.run(mc);
		assertEquals(1000, mc.getAtk());
	}

	@Test
	public void testDecAtk() {
		effect = new Effect("decAtk", "MonsterCard", 500);
		effect.run(mc);
		assertEquals(0, mc.getAtk());
	}

	@Test
	public void testIncDef() {
		effect = new Effect("incDef", "MonsterCard", 500);
		effect.run(mc);
		assertEquals(1100, mc.getDef());
	}

	@Test
	public void testDecDef() {
		effect = new Effect("decDef", "MonsterCard", 500);
		effect.run(mc);
		assertEquals(100, mc.getDef());
	}

	@Test
	public void testIncAtkDef() {
		effect = new Effect("incAtkDef", "MonsterCard", 200, 200);
		effect.run(mc);
		assertEquals(700, mc.getAtk());
		assertEquals(800, mc.getDef());
	}

	@Test
	public void testDecAtkDef() {
		effect = new Effect("decAtkDef", "MonsterCard", 200, 200);
		effect.run(mc);
		assertEquals(300, mc.getAtk());
		assertEquals(400, mc.getDef());
	}

	@Test
	public void testDraw() {
		int n = Game.getHuman().getHand().size();
		effect = new Effect("draw", "Player", 2);
		effect.run(Game.getHuman());
		assertEquals(n+2, Game.getHuman().getHand().size());
	}

	@Test
	public void testHeal() {
		int n = Game.getHuman().getLifepoints();
		effect = new Effect("heal", "Player", 1000);
		effect.run(Game.getHuman());
		assertEquals(n+1000, Game.getHuman().getLifepoints());
	}

	@Test
	public void testConstructor() {
		effect = new Effect("teszt", null);
		assertTrue(effect!=null && effect instanceof Effect);
	}

}
