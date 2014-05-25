package hu.unideb.inf.yugioh.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy hatást reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
/**
 * @author Roland
 *
 */
public class Effect {
	
	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Effect.class);
	
	/**
	 * Azon hatások típusának tömbje, melyek egy szörnyre vonatkoznak.
	 */
	public static final String[] MONSTER_EFFECTS = { "incAtk", "incDef", "incAtkDef", "decAtk", "decDef", "decAtkDef" };

	/**
	 * A hatás típusa.
	 */
	private String type;
	
	/**
	 * Célpont osztályának neve.
	 */
	private String targetClass;
	
	/**
	 * A hatás első segédváltozója.
	 */
	private int val1;
	
	/**
	 * A hatás második segédváltozója.
	 */
	private int val2;
	
	/**
	 * Visszaadja a hatás típusát.
	 * 
	 * @return a hatás típusa
	 */
	public String getType() {
		return type;
	}

	/**
	 * Visszaadja a hatás célpontja osztályának a nevét.
	 * 
	 * @return a hatás célpontja osztályának neve
	 */
	public String getTargetClass() {
		return targetClass;
	}

	/**
	 * Visszaadja a hatás első segédváltozóját.
	 * 
	 * @return a hatás első segédváltozója
	 */
	public int getVal1() {
		return val1;
	}

	/**
	 * Visszaadja a hatás második segédváltozóját.
	 * 
	 * @return a hatás második segédváltozója
	 */
	public int getVal2() {
		return val2;
	}
	
	/**
	 * Konstruktor az osztályhoz.
	 * Létrehoz egy hatást kettő darab segédváltozóval.
	 * 
	 * @param type a hatás típusa
	 * @param targetClass a célpont osztályának neve
	 * @param val1 első segédváltozó
	 * @param val2 második segédváltozó
	 */
	public Effect(String type, String targetClass, int val1, int val2) {
		this.type = type;
		this.targetClass = targetClass;
		this.val1 = val1;
		this.val2 = val2;
		logger.info("Hatás létrehozva: " + this);
	}
	
	/**
	 * Konstruktor az osztályhoz.
	 * Létrehoz egy hatást egy darab segédváltozóval.
	 * 
	 * @param type a hatás típusa
	 * @param targetClass a célpont osztályának neve
	 * @param val1 segédváltozó
	 */
	public Effect(String type, String targetClass, int val1) {
		this.type = type;
		this.targetClass = targetClass;
		this.val1 = val1;
		this.val2 = 0;
		logger.info("Hatás létrehozva: " + this);
	}
	
	/**
	 * Konstruktor az osztályhoz.
	 * Létrehoz egy hatást segédváltozók nélkül.
	 * 
	 * @param type a hatás típusa
	 * @param targetClass a célpont osztályának neve
	 */
	public Effect(String type, String targetClass) {
		this.type = type;
		this.targetClass = targetClass;
		this.val1 = 0;
		this.val2 = 0;
		logger.info("Hatás létrehozva: " + this);
	}

	/**
	 * A hatás érvényesítése a célobjektumon a hatás típusának megfelelően.
	 * 
	 * @param obj a célobjektum
	 */
	public void run(Object obj) {
		
		logger.info("Hatás elindítása: " + this);
		
		switch (type) {
			case "incAtk":
				((MonsterCard)obj).addAtk(val1);
				Game.getGUI().removeCardFromField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				Game.getGUI().addCardToField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				break;
			case "decAtk":
				((MonsterCard)obj).subAtk(val1);
				Game.getGUI().removeCardFromField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				Game.getGUI().addCardToField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				break;
			case "incDef":
				((MonsterCard)obj).addDef(val1);
				Game.getGUI().removeCardFromField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				Game.getGUI().addCardToField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				break;
			case "decDef":
				((MonsterCard)obj).subDef(val1);
				Game.getGUI().removeCardFromField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				Game.getGUI().addCardToField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				break;
			case "incAtkDef":
				((MonsterCard)obj).addAtk(val1);
				((MonsterCard)obj).addDef(val2);
				Game.getGUI().removeCardFromField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				Game.getGUI().addCardToField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				break;
			case "decAtkDef":
				((MonsterCard)obj).subAtk(val1);
				((MonsterCard)obj).subDef(val2);
				Game.getGUI().removeCardFromField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				Game.getGUI().addCardToField((MonsterCard)obj, ((MonsterCard)obj).getOwner());
				break;
			case "draw":
				Card[] cards = ((Player)obj).getDeck().draw(val1);
				for (Card card : cards) {
					card.setFaceup(true);
				}
				((Player)obj).getHand().addTop(cards);
				break;
			case "heal":
				((Player)obj).addLifepoints(val1);
				Game.getGUI().showLifepoints((Player)obj);
				break;
		}
		
		logger.info("Hatás befejezve.");
		
	}

	@Override
	public String toString() {
		return "(Effect) " + type + ": " + val1 + ", " + val2 + ", " + targetClass;
	}
	
}