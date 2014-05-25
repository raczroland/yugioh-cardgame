package hu.unideb.inf.yugioh.main;

/**
 * Egy darab szörnylapot reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
public class MonsterCard extends Card {
	
	/**
	 * A szörnylap típusa.
	 */
	private String type;
	
	/**
	 * A szörnylap támadópontjainak száma.
	 */
	private int atk;
	
	/**
	 * A szörnylap védelmi pontjainak száma.
	 */
	private int def;
	
	/**
	 * A szörnylap szintje. 
	 */
	private int level;
	
	/**
	 * Védelmi módban van-e a szörny.
	 */
	private boolean defensePosition;
	
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
	 * Hozzáadja a megadott értéket a szörnylap támadópontjaihoz.
	 * 
	 * @param atk hozzáadandó támadópontok
	 */
	public void addAtk(int atk) {
		this.atk += atk;
	}
	
	/**
	 * Kivonja a megadott értéket a szörnylap támadópontjaiból.
	 * 
	 * @param atk kivonandó támadópontok
	 */
	public void subAtk(int atk) {
		this.atk -= atk;
		if (this.atk<0) {
			this.atk = 0;
		}
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
	 * Hozzáadja a megadott értéket a szörnylap védelmi pontjaihoz.
	 * 
	 * @param def hozzáadandó védelmi pontok
	 */
	public void addDef(int def) {
		this.def += def;
	}
	
	/**
	 * Kivonja a megadott értéket a szörnylap védelmi pontjaiból.
	 * 
	 * @param def kivonandó védelmi pontok
	 */
	public void subDef(int def) {
		this.def -= def;
		if (this.def<0) {
			this.def = 0;
		}
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
	 * Visszaadja a szörnylap szintjét.
	 * 
	 * @return a szörnylap szintje
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Visszaadja, hogy a szörny védelmi állásban van-e.
	 * 
	 * @return védelmi állásban van-e
	 */
	public boolean isDefensePosition() {
		return defensePosition;
	}

	/**
	 * Beállítja, hogy a szörny védelmi állásban legyen-e.
	 * 
	 * @param defensePosition védelmi állásban legyen-e
	 */
	public void setDefensePosition(boolean defensePosition) {
		this.defensePosition = defensePosition;
	}

	/**
	 * Konstruktor a szörnylap létrehozásához.
	 * 
	 * @param name a szörnylap neve
	 * @param description a szörnylap leírása
	 * @param faceup a szörnylap felfordítva van-e
	 * @param type a szörnylap típusa
	 * @param atk a szörnylap támadópontjainak száma
	 * @param def a szörnylap védelmi pontjainak száma
	 * @param level a szörnylap szintje
	 * @param defensePosition védelmi állásban van-e a szörny
	 * @param owner a szörny tulajdonosa
	 */
	public MonsterCard(String name, String description,
			boolean faceup, String type, int atk, int def,
			int level, boolean defensePosition, Player owner) {
		super(name, description, faceup, owner);
		this.type = type;
		this.atk = atk;
		this.def = def;
		this.level = level;
		this.defensePosition = defensePosition;
		logger.info("Szörnylap létrehozva. [" + this + "]");
	}
	
	/**
	 * Egy támadást hajt végre a szörnnyel a paraméterként átadott szörnyön.
	 * 
	 * @param enemy a megtámadott szörny
	 */
	public void attack(MonsterCard enemy) {
		if (!enemy.isFaceup()) {
			enemy.setFaceup(true);
		}
		if (enemy.isDefensePosition()) {
			if (enemy.getDef() > this.getAtk()) {
				this.getOwner().subLifepoints(enemy.getDef()-this.getAtk());
			} else if (enemy.getDef() < this.getAtk()) {
				enemy.destroy();
				Game.getGUI().removeCardFromField(enemy, enemy.getOwner());
				Game.getGUI().addCardToGraveyard(enemy, enemy.getOwner());
			}
		} else {
			if (enemy.getAtk() > this.getAtk()) {
				this.getOwner().subLifepoints(enemy.getAtk()-this.getAtk());
				this.destroy();
				Game.getGUI().removeCardFromField(this, this.getOwner());
				Game.getGUI().addCardToGraveyard(this, this.getOwner());
			} else if(enemy.getAtk() < this.getAtk()) {
				enemy.getOwner().subLifepoints(this.getAtk()-enemy.getAtk());
				enemy.destroy();
				Game.getGUI().removeCardFromField(enemy, enemy.getOwner());
				Game.getGUI().addCardToGraveyard(enemy, enemy.getOwner());
			} else {
				enemy.destroy();
				Game.getGUI().removeCardFromField(enemy, enemy.getOwner());
				Game.getGUI().addCardToGraveyard(enemy, enemy.getOwner());
				this.destroy();
				Game.getGUI().removeCardFromField(this, this.getOwner());
				Game.getGUI().addCardToGraveyard(this, this.getOwner());
			}
		}
	}
	
	/**
	 * Egy támadást hajt végre a szörnnyel a paraméterként átadott játékos életpontjain.
	 * 
	 * @param player a megtámadott játékos
	 */
	public void attack(Player player) {
		player.subLifepoints(getAtk());
	}

	@Override
	public void destroy() {
		this.getOwner().getMonsterCardZone().removeCard(this);
		this.getOwner().getGraveyard().addTop(this);
		logger.info("Szörnylap elpusztítva. [" + this + "]");
	}

	@Override
	public String toString() {
		return "(Szörnylap) " + getName() + ": {" + getDescription() + "}, " + getAtk() + ", " + getDef() + ", " + getType() + ", " + getLevel();
	}

	@Override
	public int compareTo(Card card) {
		if (card instanceof SpellCard) {
			return 1;
		} else if (card instanceof MonsterCard) {
			return getLevel()-((MonsterCard)card).getLevel();
		}
		return 1;
	}
	
	
	
}
