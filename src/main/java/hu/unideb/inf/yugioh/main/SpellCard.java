package hu.unideb.inf.yugioh.main;

import java.util.Arrays;

/**
 * Egy darab varázslapot reprezentáló osztály.
 * 
 * @author Roland
 */
public class SpellCard extends Card {
	
	/**
	 * A varázslap hatása.
	 */
	private Effect effect;

	/**
	 * Visszaadja a varázslap hatását.
	 * 
	 * @return a varázslap hatása
	 */
	public Effect getEffect() {
		return effect;
	}

	/**
	 * Konstruktor az osztályhoz.
	 * 
	 * @param name a varázslap neve
	 * @param description a varázslap leírása
	 * @param faceup felfordítva van-e a kártya
	 * @param owner a varázslap tulajdonosa
	 * @param effect a varázslap hatása
	 */
	public SpellCard(String name, String description, boolean faceup, Player owner, Effect effect) {
		super(name, description, faceup, owner);
		this.effect = effect;
		logger.info("Varázslap létrehozva. [" + this + "]");
	}
	
	/**
	 * Aktiválja a varázskártya hatását.
	 * 
	 * @return sikeres volt-e az aktiválás
	 */
	public boolean activate() {
		logger.info("Varázslap aktiválása: " + this);
		if (!Arrays.asList(Effect.MONSTER_EFFECTS).contains(getEffect().getType())) {
			getEffect().run(getOwner());
			return true;
		} else {
			logger.error("Nem megfelelő active() metódus van meghívva!");
			return false;
		}
	}
	
	/**
	 * Aktiválja a varázskártya hatását egy adott célponton.
	 * 
	 * @param mc a célpont szörnylap
	 * @return sikeres volt-e az aktiválás
	 */
	public boolean activate(MonsterCard mc) {
		logger.info("Varázslap aktiválása: " + this + " >>> " + mc);
		if (Arrays.asList(Effect.MONSTER_EFFECTS).contains(getEffect().getType())) {
			getEffect().run(mc);
			return true;
		} else {
			logger.error("Nem megfelelő active() metódus van meghívva!");
			return false;
		}
	}

	@Override
	public String toString() {
		return "(Varázslap) " + getName() + ": {" + getDescription() + "}, {" + getEffect() + "}";
	}

	@Override
	public int compareTo(Card card) {
		if (card instanceof MonsterCard) {
			return -1;
		} else if (card instanceof SpellCard) {
			return 0;
		}
		return 1;
	}
	
}
