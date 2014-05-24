package hu.unideb.inf.yugioh.main;

import java.util.Vector;

/**
 * A varázslapok mezőit tartalmazó tároló.
 * 
 * @author Rácz Roland
 */
public class SpellCardZone extends Deck {

	/**
	 * Konstruktor az osztályhoz.
	 */
	public SpellCardZone() {
		super(new Vector<Card>(5));
	}

}
