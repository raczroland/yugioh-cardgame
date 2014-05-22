package hu.unideb.inf.yugioh.data;

import hu.unideb.inf.yugioh.main.MonsterCard;

/**
 * Egy szörnylap osztályának adatait reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
class MonsterCardItem extends CardItem {
	
	/**
	 * A szörnylap támadópontjainak száma.
	 */
	private int atk;
	
	/**
	 * A szörnylap védelmi pontjainak száma.
	 */
	private int def;
	
	/**
	 * A szörnylap típusa.
	 */
	private String type;
	
	/**
	 * A szörnylap szintje. 
	 */
	private int level;
	
	/**
	 * Visszaadja a szörnylap támadópontjainak számát.
	 * 
	 * @return a szörnylap támadópontjainak száma
	 */
	public int getAtk() {
		return atk;
	}
	
	/**
	 * Beállítja a szörnylap támadópontjainak számát.
	 * 
	 * @param atk a szörnylap támadópontjainak száma
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	/**
	 * Visszaadja a szörnylap védelmi pontjainak számát.
	 * 
	 * @return a szörnylap védelmi pontjainak száma
	 */
	public int getDef() {
		return def;
	}
	
	/**
	 * Beállítja a szörnylap védelmi pontjainak számát.
	 * 
	 * @param def a szörnylap védelmi pontjainak száma
	 */
	public void setDef(int def) {
		this.def = def;
	}
	
	/**
	 * Visszaadja a szörnylap típusát.
	 * 
	 * @return a szörnylap típusa
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Beállítja a szörnylap típusát.
	 * 
	 * @param type a szörnylap típusa
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Visszaadja a szörnylap szintjét.
	 * 
	 * @return a szörnylap szintje
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Beállítja a szörnylap szintjét.
	 * 
	 * @param level a szörnylap szintje
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Konstruktor az osztályhoz.
	 */
	public MonsterCardItem() { }
	
	/**
	 * Konstruktor az osztályhoz.
	 * Az mezőket feltölti a paraméterként átadott szörnylap adataival.
	 * 
	 * @param card a szörnylap
	 */
	public MonsterCardItem(MonsterCard card) {
		setName(card.getName());
		setDescription(card.getDescription());
		this.atk = card.getAtk();
		this.def = card.getDef();
		this.type = card.getType();
		this.level = card.getLevel();
	}
	
	/**
	 * Előállít egy szörnylap objektumot a meglévő adatok alapján.
	 * 
	 * @return az előállított szörnylap
	 */
	public MonsterCard asMonsterCard() {
		return new MonsterCard(getName(), getDescription(), false, getType(), getAtk(), getDef(), getLevel(), false, null);
	}
	
}