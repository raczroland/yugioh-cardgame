package hu.unideb.inf.yugioh.data;

/**
 * Egy kártyalap osztályának adatait reprezentáló osztály.
 * A DataManager használja az XML fájlok létrehozásához és betöltéséhez.
 * 
 * @author Rácz Roland
 */
class CardItem {
	
	/**
	 * A kártyalap neve.
	 */
	private String name;
	
	/**
	 * A kártyalap leírása.
	 */
	private String description;

	/**
	 * Visszaadja a kártyalap nevét.
	 * 
	 * @return a kártyalap neve
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Beállítja a kátyalap nevét.
	 * 
	 * @param name a kártyalap neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Visszaadja a kártyalap leírását.
	 * 
	 * @return a kártyalap leírása
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Beállítja a kártyalap leírását.
	 * 
	 * @param description a kártyalap leírása
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}