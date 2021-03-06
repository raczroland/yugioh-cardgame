package hu.unideb.inf.yugioh.main;

import java.util.Vector;

/**
 * Egy játékos kezében lévő lapokat reprezentáló osztály.
 * 
 * @author Rácz Roland
 */
public class Hand extends Deck {

	/**
	 * Konstruktor az osztályhoz.
	 * 
	 * @param cards a kézben lévő lapok llétrehozás után
	 */
	public Hand(Vector<Card> cards) {
		super(cards);
	}
	
	/**
	 * Megidéz egy adott szörnylapot a játékos kezéből a játékos mezőjére támadó vagy védelmi módban.
	 * 
	 * @param card a megidézendő szörnylap
	 * @param defensePosition védelmi módban legyen-e megidézve
	 * @return sikerült-e megidézni a szörnyet
	 */
	public boolean summonMonsterCard(MonsterCard card, boolean defensePosition) {
		Game._wait();
		if ( card.getOwner().getMonsterCardZone().size() < Game.MAX_CARD_ON_FIELD && removeCard(card) ) {
			if (defensePosition) {
				card.setFaceup(false);
				card.setDefensePosition(true);
			} else {
				card.setFaceup(true);
				card.setDefensePosition(false);
			}
			card.getOwner().getMonsterCardZone().addTop(card);
			Game.getGUI().removeCardFromHand(card, card.getOwner());
			Game.getGUI().addCardToField(card, card.getOwner());
			logger.info("Szörny megidézve " + (defensePosition?"védelmi":"támadó") + " módban. [" + card + "]");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Megidéz egy adott szörnylapot a játékos kezéből a játékos mezőjére támadó vagy védelmi módban,
	 * egy darab, a pályán lévő szörnykártya feláldozásával.
	 * 
	 * @param card a megidézendő szörnylap
	 * @param defensePosition védelmi módban legyen-e megidézve
	 * @param tribute feláldozott szörnylap
	 * @return sikerült-e megidézni a szörnyet
	 */
	public boolean summonMonsterCard(MonsterCard card, boolean defensePosition, MonsterCard tribute) {
		if (card.getLevel()>=5 && card.getLevel()<=6 && card.getOwner().getMonsterCardZone().removeCard(tribute)) {
			card.getOwner().getGraveyard().addTop(tribute);
			Game.getGUI().removeCardFromField(tribute, tribute.getOwner());
			Game.getGUI().addCardToGraveyard(tribute, tribute.getOwner());
			logger.info("Szörnylap feláldozva: " + tribute);
			return summonMonsterCard(card, defensePosition);
		} else {
			return false;
		}
	}

	/**
	 * Megidéz egy adott szörnylapot a játékos kezéből a játékos mezőjére támadó vagy védelmi módban,
	 * két darab, a pályán lévő szörnykártya feláldozásával.
	 * 
	 * @param card a megidézendő szörnylap
	 * @param defensePosition védelmi módban legyen-e megidézve
	 * @param tribute1 az első feláldozott szörnylap
	 * @param tribute2 a második feláldozott szörnylap
	 * @return sikerült-e megidézni a szörnyet
	 */
	public boolean summonMonsterCard(MonsterCard card, boolean defensePosition, MonsterCard tribute1, MonsterCard tribute2) {
		if (card.getLevel()>=7 && card.getOwner().getMonsterCardZone().removeCard(tribute1) && card.getOwner().getMonsterCardZone().removeCard(tribute2)) {
			card.getOwner().getGraveyard().addTop(tribute1);
			card.getOwner().getGraveyard().addTop(tribute2);
			Game.getGUI().removeCardFromField(tribute1, tribute1.getOwner());
			Game.getGUI().addCardToGraveyard(tribute1, tribute1.getOwner());
			Game.getGUI().removeCardFromField(tribute2, tribute2.getOwner());
			Game.getGUI().addCardToGraveyard(tribute2, tribute2.getOwner());
			logger.info("Szörnylapok feláldozva: " + tribute1 + ", " + tribute2);
			return summonMonsterCard(card, defensePosition);
		} else {
			return false;
		}
	}

	@Override
	public void addTop(Card card) {
		super.addTop(card);
		Game.getGUI().addCardToHand(card, card.getOwner());
	}

}
