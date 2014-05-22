package hu.unideb.inf.yugioh.data;

import hu.unideb.inf.yugioh.main.Effect;
import hu.unideb.inf.yugioh.main.SpellCard;

/**
 * Egy varázslap osztályának adatait reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
class SpellCardItem extends CardItem {

	/**
	 * A varázslap hatásának típusa.
	 */
	private String effectType;

	/**
	 * A varázslap hatása célpontja osztályának neve.
	 */
	private String effectTargetClass;
	
	/**
	 * A varázslap hatásának első segédváltozója.
	 */
	private int effectVal1 = 0;
	
	/**
	 * A varázslap hatásának második segédváltozója.
	 */
	private int effectVal2 = 0;

	/**
	 * A varázslap hatása megszűnésének típusa.
	 */
	private String uneffectType;

	/**
	 * A varázslap hatása megszűnése célpontja osztályának neve.
	 */
	private String uneffectTargetClass;
	
	/**
	 * A varázslap hatása megszűnésének első segédváltozója.
	 */
	private int uneffectVal1 = 0;
	
	/**
	 * A varázslap hatása megszűnésének második segédváltozója.
	 */
	private int uneffectVal2 = 0;
	
	/**
	 * Visszaadja a varázslap hatásának típusát.
	 * 
	 * @return a varázslap hatásának típusa
	 */
	public String getEffectType() {
		return effectType;
	}
	
	/**
	 * Beállítja a varázslap hatásának típusát.
	 * 
	 * @param effectType a varázslap hatásának típusa
	 */
	public void setEffectType(String effectType) {
		this.effectType = effectType;
	}

	/**
	 * Visszaadja a varázslap hatása célpontja osztályának a nevét.
	 * 
	 * @return a varázslap hatása célpontja osztályának neve
	 */
	public String getEffectTargetClass() {
		return effectTargetClass;
	}

	/**
	 * Beállítja a varázslap hatása célpontja osztályának a nevét.
	 * 
	 * @param effectTargetClass a varázslap hatása célpontja osztályának neve
	 */
	public void setEffectTargetClass(String effectTargetClass) {
		this.effectTargetClass = effectTargetClass;
	}

	/**
	 * Visszaadja a varázslap hatásának első segédváltozóját.
	 * 
	 * @return a hatás első segédváltozója
	 */
	public int getEffectVal1() {
		return effectVal1;
	}

	/**
	 * Beállítja a varázslap hatásának első segédváltozóját.
	 * 
	 * @param effectVal1 a varázslap hatásának első segédváltozója
	 */
	public void setEffectVal1(int effectVal1) {
		this.effectVal1 = effectVal1;
	}

	/**
	 * Visszaadja a varázslap hatásának második segédváltozóját.
	 * 
	 * @return a hatás második segédváltozója
	 */
	public int getEffectVal2() {
		return effectVal2;
	}

	/**
	 * Beállítja a varázslap hatásának második segédváltozóját.
	 * 
	 * @param effectVal2 a varázslap hatásának második segédváltozója
	 */
	public void setEffectVal2(int effectVal2) {
		this.effectVal2 = effectVal2;
	}
	
	/**
	 * Visszaadja a varázslap hatása megszűnésének típusát.
	 * 
	 * @return a varázslap hatása megszűnésének típusa
	 */
	public String getUneffectType() {
		return uneffectType;
	}
	
	/**
	 * Beállítja a varázslap hatása megszűnésének típusát.
	 * 
	 * @param uneffectType a varázslap hatása megszűnésének típusa
	 */
	public void setUneffectType(String uneffectType) {
		this.uneffectType = uneffectType;
	}

	/**
	 * Visszaadja a varázslap hatása megszűnése célpontja osztályának a nevét.
	 * 
	 * @return a varázslap hatása megszűnése célpontja osztályának neve
	 */
	public String getUneffectTargetClass() {
		return uneffectTargetClass;
	}

	/**
	 * Beállítja a varázslap hatása megszűnése célpontja osztályának a nevét.
	 * 
	 * @param uneffectTargetClass a varázslap hatása megszűnése célpontja osztályának neve
	 */
	public void setUneffectTargetClass(String uneffectTargetClass) {
		this.uneffectTargetClass = uneffectTargetClass;
	}

	/**
	 * Visszaadja a varázslap hatása megszűnésének első segédváltozóját.
	 * 
	 * @return a varázslap hatása megszűnésének első segédváltozója
	 */
	public int getUneffectVal1() {
		return uneffectVal1;
	}

	/**
	 * Beállítja a varázslap hatása megszűnésének első segédváltozóját.
	 * 
	 * @param uneffectVal1 a varázslap hatása megszűnésének első segédváltozója
	 */
	public void setUneffectVal1(int uneffectVal1) {
		this.uneffectVal1 = uneffectVal1;
	}

	/**
	 * Visszaadja a varázslap hatása megszűnésének második segédváltozóját.
	 * 
	 * @return a varázslap hatása megszűnésének második segédváltozója
	 */
	public int getUneffectVal2() {
		return uneffectVal2;
	}

	/**
	 * Beállítja a varázslap hatása megszűnésének második segédváltozóját.
	 * 
	 * @param uneffectVal2 a varázslap hatása megszűnésének második segédváltozója
	 */
	public void setUneffectVal2(int uneffectVal2) {
		this.uneffectVal2 = uneffectVal2;
	}
	
	/**
	 * Konstruktor az osztályhoz.
	 */
	public SpellCardItem() { }
	
	/**
	 * Konstruktor az osztályhoz.
	 * Az mezőket feltölti a paraméterként átadott varázslap adataival.
	 * 
	 * @param card a szörnylap
	 */
	public SpellCardItem(SpellCard card) {
		setName(card.getName());
		setDescription(card.getDescription());
		this.effectType = card.getEffect().getType();
		this.effectTargetClass = card.getEffect().getTargetClass();
		this.effectVal1 = card.getEffect().getVal1();
		this.effectVal2 = card.getEffect().getVal2();
		this.uneffectType = card.getUneffect().getType();
		this.uneffectTargetClass = card.getUneffect().getTargetClass();
		this.uneffectVal1 = card.getUneffect().getVal1();
		this.uneffectVal2 = card.getUneffect().getVal2();
	}
	
	/**
	 * Előállít egy varázslap objektumot a meglévő adatok alapján.
	 * 
	 * @return az előállított varázslap
	 */
	public SpellCard asSpellCard() {
		// TODO megírni jól
		Effect effect = new Effect(getEffectType(), getEffectTargetClass(), getEffectVal1(), getEffectVal2());
		Effect uneffect = null;
		return new SpellCard(getName(), getDescription(), false, null, effect, uneffect);
	}
	
}