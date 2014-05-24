package hu.unideb.inf.yugioh.main;

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
	 * A varázslap elpusztulásának hatása.
	 */
	private Effect uneffect;

	/**
	 * Visszaadja a varázslap hatását.
	 * 
	 * @return a varázslap hatása
	 */
	public Effect getEffect() {
		return effect;
	}

	/**
	 * Visszaadja a varázslap elpusztulásának hatását.
	 * 
	 * @return a varázslap elpusztulásának hatása
	 */
	public Effect getUneffect() {
		return uneffect;
	}

	/**
	 * Konstruktor az osztályhoz.
	 * 
	 * @param name a varázslap neve
	 * @param description a varázslap leírása
	 * @param faceup felfordítva van-e a kártya
	 * @param owner a varázslap tulajdonosa
	 * @param effect a varázslap hatása
	 * @param uneffect a varázslap megszűnésének hatása
	 */
	public SpellCard(String name, String description, boolean faceup, Player owner, Effect effect, Effect uneffect) {
		super(name, description, faceup, owner);
		this.effect = effect;
		this.uneffect = uneffect;
		logger.info("Varázslap létrehozva. [" + this + "]");
	}

	@Override
	public void destroy() {
		// TODO kijavítani:
		//getUneffect().run(this);
		getOwner().getSpellCardZone().removeCard(this);
		getOwner().getGraveyard().addTop(this);
		logger.info("Card destroyed.");
		logger.info("Varázslap elpusztítva. [" + this + "]");
	}

	@Override
	public String toString() {
		return "(Varázslap) " + getName() + ": {" + getDescription() + "}";
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
