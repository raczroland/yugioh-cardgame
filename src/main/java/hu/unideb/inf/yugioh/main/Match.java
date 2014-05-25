package hu.unideb.inf.yugioh.main;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy meccset reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
public class Match {

	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Match.class);
	
	/**
	 * Véletlen számokat előállító objektum.
	 */
	private static Random rand = new Random();

	/**
	 * Az első játékos.
	 */
	private Player player1;
	
	/**
	 * A második játékos.
	 */
	private Player player2;
	
	/**
	 * A következő játékos.
	 */
	private Player nextPlayer;
	
	/**
	 * A győztes játékos.
	 */
	private Player winner;
	
	/**
	 * Fel van-e adva a játék.
	 */
	private boolean giveup;

	/**
	 * Visszaadja az első játékost.
	 * 
	 * @return az első játékos
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Visszaadja a második játékost.
	 * 
	 * @return a második játékos
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * Visszaadja a következő játékost.
	 * 
	 * @return a következő játékos
	 */
	public Player getNextPlayer() {
		return nextPlayer;
	}

	/**
	 * Átváltja a következő játékost, és visszatér a következő játékossal.
	 * 
	 * @return a következő játékos
	 */
	public Player switchNextPlayer() {
		nextPlayer = nextPlayer==player1 ? player2 : player1;
		return nextPlayer;
	}

	/**
	 * Visszatér a győztes játékossal.
	 * 
	 * @return a győztes játékos
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * Beállítja a győztes játékost.
	 * 
	 * @param winner a győztes játékos
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	/**
	 * Visszaadja, hogy fel van-e adva a meccs.
	 * 
	 * @return fel van-e adva a meccs
	 */
	public boolean isGiveup() {
		return giveup;
	}

	/**
	 * Beállítja, hogy fel van-e adva a meccs.
	 * 
	 * @param giveup fel van-e adva a meccs
	 */
	public void setGiveup(boolean giveup) {
		this.giveup = giveup;
	}

	/**
	 * Visszatér a paraméterként átadott játékos ellenfelével.
	 * 
	 * @param player az adott játékos
	 * @return az ellenfél
	 */
	public Player getEnemy(Player player) {
		return player==player1 ? player2 : player1;
	}

	/**
	 * Konstruktor az osztályhoz. Létrehoz egy új meccset.
	 * 
	 * @param player1 az első játékos
	 * @param player2 a második játékos
	 */
	public Match(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		if (rand.nextInt(2)==0) {
			this.nextPlayer = player1;
		} else {
			this.nextPlayer = player2;
		}
		this.winner = null;
		this.player1.setLifepoints(Game.START_LIFEPOINTS);
		this.player1.getHand().removeAll();
		this.player1.getMonsterCardZone().removeAll();
		this.player1.getSpellCardZone().removeAll();
		this.player2.setLifepoints(Game.START_LIFEPOINTS);
		this.player2.getHand().removeAll();
		this.player2.getMonsterCardZone().removeAll();
		this.player2.getSpellCardZone().removeAll();
		logger.info("Meccs létrehozva. [" + this + "]");
	}
	
	/**
	 * Elindítja és működteti a meccset.
	 */
	public void run() {

		logger.info("A játék elindítva.");
		Game.getGUI().showMessage("A játék megkezdődött. A kezdő játékos: " + getNextPlayer());
		
		// Inicializálás:
		getPlayer1().getDeck().setAllCardToFaceUp();
		getPlayer1().getHand().addTop(	getPlayer1().getDeck().draw(Game.START_CARD_IN_HAND) );
		getPlayer2().getHand().addTop(	getPlayer2().getDeck().draw(Game.START_CARD_IN_HAND) );
		
		// Játékvezérlő ciklus:
		while ( winner == null && !giveup ) {
			
			getNextPlayer().drawPhase();
			getNextPlayer().standbyPhase();
			getNextPlayer().mainPhase();
			getNextPlayer().battlePhase();
			getNextPlayer().endPhase();
			
			switchNextPlayer();
			
		}
		
		// GUI visszaállítása:
		Game.getGUI().resetGUI();
		Game.getWorker().refreshGUI();
		
		// Győztes kiírása:
		Game.getGUI().showMessage("Játék vége. A győztes játékos: " + getWinner());
		logger.info("Játék vége. Győztes: " + winner);
		
	}

	@Override
	public String toString() {
		return player1 + " vs " + player2;
	}
	
}
