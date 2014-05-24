package hu.unideb.inf.yugioh.main;

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

	public Effect(String type, String targetClass, int val1, int val2) {
		this.type = type;
		this.targetClass = targetClass;
		this.val1 = val1;
		this.val2 = val2;
	}
	
	public Effect(String type, String targetClass, int val1) {
		this.type = type;
		this.targetClass = targetClass;
		this.val1 = val1;
		this.val2 = 0;
	}
	
	public Effect(String type, String targetClass) {
		this.type = type;
		this.targetClass = targetClass;
		this.val1 = 0;
		this.val2 = 0;
	}

	/**
	 * A hatás érvényesítése a célobjektumon a hatás típusának megfelelően.
	 * 
	 * @param obj a célobjektum
	 */
	public void run(Object obj) {
		
		switch (type) {
			case "incAtk":
				((MonsterCard)obj).addAtk(val1);
				break;
			case "decAtk":
				((MonsterCard)obj).subAtk(val1);
				break;
			case "incDef":
				((MonsterCard)obj).addDef(val1);
				break;
			case "decDef":
				((MonsterCard)obj).subDef(val1);
				break;
			case "incAtkDef":
				((MonsterCard)obj).addAtk(val1);
				((MonsterCard)obj).addDef(val2);
				break;
			case "decAtkDef":
				((MonsterCard)obj).subAtk(val1);
				((MonsterCard)obj).subDef(val2);
				break;
			case "draw":
				((Player)obj).getHand().addTop(((Player)obj).getDeck().draw(val1));
				break;
			case "dmg":
				((Player)obj).subLifepoints(val1);
				break;
			case "heal":
				((Player)obj).addLifepoints(val1);
				break;
		}
		
	}
	
}