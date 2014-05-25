package hu.unideb.inf.yugioh.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy darab kártyalapot reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
public abstract class Card implements Comparable<Card> {
	
	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Card.class);
	
	/**
	 * A kártyalap neve.
	 */
	private String name;
	
	/**
	 * A kártyalap leírása.
	 */
	private String description;
	
	/**
	 * A kártyalap felfordítva van-e.
	 */
	private boolean faceup;
	
	/**
	 * A kártyalap tulajdonosa.
	 */
	private Player owner;
	
	
	/**
	 * Visszaadja, hogy a kártyalap felfordítva van-e.
	 * 
	 * @return a kártyalap felfordítva van-e
	 */
	public boolean isFaceup() {
		return faceup;
	}
	
	/**
	 * Beállítja, hogy a kártyalap felfordítva legyen-e.
	 * 
	 * @param faceup a kártyalap felfordítva van-e
	 */
	public void setFaceup(boolean faceup) {
		this.faceup = faceup;
	}
	
	/**
	 * Visszaadja a kártyalap nevét.
	 * 
	 * @return a kártyalap neve
	 */
	public String getName() {
		return name;
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
	 * Visszaadja a kártyalap tulajdonosát.
	 * 
	 * @return a kártyalap tulajdonosa
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Beállítja a kártyalap tulajdonosát.
	 * 
	 * @param owner a kártyalap tulajdonosa
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Konstruktor a kártyalap létrehozásához.
	 * 
	 * @param name a kártyalap neve
	 * @param description a kártyalap leírása
	 * @param faceup a kártyalap felfordítva van-e
	 * @param owner a kártyalap tulajdonosa
	 */
	public Card(String name, String description, boolean faceup, Player owner) {
		super();
		this.name = name;
		this.description = description;
		this.faceup = faceup;
		this.owner = owner;
	}

}
