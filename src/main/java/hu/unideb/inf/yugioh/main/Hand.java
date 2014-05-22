package hu.unideb.inf.yugioh.main;

import java.util.Vector;

public class Hand extends Deck {

	public Hand(Vector<Card> cards) {
		super(cards);
		// TODO Auto-generated constructor stub
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
			logger.info("Szörny megidézve. [" + card + "]");
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
			return summonMonsterCard(card, defensePosition);
		} else {
			return false;
		}
	}

	/**
	 * Lerakja és aktiválja a játékos egy adott varázslapját.
	 * 
	 * @param card a lerakandó és aktiválandó varázslap
	 */
	public void activateSpellCard(SpellCard card) {
		removeCard(card);
		card.setFaceup(true);
		card.getOwner().getSpellCardZone().addTop(card);
		// TODO kijavítani:
		//card.getEffect().run();
	}

}
