package hu.unideb.inf.yugioh.main;

import java.util.Vector;

/**
 * A szörnylapok mezőit tartalmazó tároló.
 * 
 * @author Rácz Roland
 */
public class MonsterCardZone extends Deck {

	/**
	 * Konstruktor az osztályhoz.
	 */
	public MonsterCardZone() {
		super(new Vector<Card>(5));
	}

}
