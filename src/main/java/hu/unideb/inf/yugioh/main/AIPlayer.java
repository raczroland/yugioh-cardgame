package hu.unideb.inf.yugioh.main;

import java.util.Collections;

/**
 * Számítógépes játékost reprezentáló osztály.
 * Tartalmazza a számítógépes játékos mesterséges intelligenciáját.
 * 
 * @author Rácz Roland
 */
public class AIPlayer extends Player {

	/**
	 * Konstruktor az osztályhoz.
	 * 
	 * @param name a játékos neve
	 */
	public AIPlayer(String name) {
		super(name);
	}

	/**
	 * A húzási fázist végrehajtó metódus.
	 * A mesterséges intelligencia lapját lefordítva húzza fel.
	 */
	@Override
	public void drawPhase() {
		logger.info(this + ": húzási fázis");
		Game.showMessage(getName() + ": húzási fázis");
		Card card = getDeck().draw();
		getHand().addTop(card);
	}
	
	/**
	 * Előkészítő fázist végrehajtó metódus.
	 * A mesterséges intelligencia átugorja ezt a fázist.
	 */
	@Override
	public void standbyPhase() {
		logger.info(this + ": előkészítő fázis");
		Game.showMessage(getName() + ": előkészítő fázis");
	}

	/**
	 * A fő fázist végrehajtó metódus.
	 * A mesterséges intelligencia megpróbál megidézni egy szörnykártyát.
	 */
	@Override
	public void mainPhase() {
		logger.info(this + ": fő fázis");
		Game.showMessage(getName() + ": fő fázis");
		Game._wait();
		Hand hand = getHand();
		Collections.sort(hand.getCards());
		Collections.reverse(hand.getCards());
		for (Card card : hand.getCards()) {
			if (card instanceof MonsterCard) {
				MonsterCard mcard = (MonsterCard) card;
				if (mcard.getLevel()>=7 && getMonsterCardZone().size()>=2) {
					if (hand.summonMonsterCard(
						mcard, mcard.getDef()>mcard.getAtk(),
						(MonsterCard) getMonsterCardZone().getCards().elementAt(0), (MonsterCard) getMonsterCardZone().getCards().elementAt(1)
					)) {
						break;
					}
				} else if (mcard.getLevel()>=5 && mcard.getLevel()<=6 && getMonsterCardZone().size()>=1) {
					if (hand.summonMonsterCard(
						mcard, mcard.getDef()>mcard.getAtk(), (MonsterCard) getMonsterCardZone().getCards().elementAt(0)
					)) {
						break;
					}
				} else if (mcard.getLevel()<5) {
					if (hand.summonMonsterCard(
						mcard, mcard.getDef()>mcard.getAtk()
					)) {
						break;
					}
				}
			}
		}
	}
	
	/**
	 * A harci fázist végrehajtó metódus.
	 * A mesterséges intelligencia végrehajtja az összes lehetséges támadást.
	 */
	@Override
	public void battlePhase() {
		logger.info(this + ": harci fázis");
		Game.showMessage(getName() + ": harci fázis");
		Game._wait();
		for (Card card : getMonsterCardZone().getCards()) {
			MonsterCard monster = (MonsterCard) card;
			if (!monster.isDefensePosition()) {
				if (Game.getMatch().getEnemy(this).getMonsterCardZone().size()==0) {
					monster.attack(Game.getMatch().getEnemy(this));
				} else {
					for (Card ecard : Game.getMatch().getEnemy(this).getMonsterCardZone().getCards()) {
						MonsterCard enemy = (MonsterCard) ecard;
						if (!enemy.isDefensePosition() && enemy.getAtk()<monster.getAtk()) {
							monster.attack(enemy);
							break;
						} else if (enemy.isDefensePosition() && enemy.isFaceup() && enemy.getDef()<monster.getAtk()) {
							monster.attack(enemy);
							break;
						} else if (enemy.isDefensePosition() && !enemy.isFaceup()) {
							monster.attack(enemy);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * A vég fázist végrehajtó metódus.
	 * Ha a kézben 6-nál több kártyalap van, a mesterséges intelligencia eldob annyi lapot, hogy 6 maradjon.
	 */
	@Override
	public void endPhase() {
		logger.info(this + ": vég fázis");
		Game.showMessage(getName() + ": kör vége");
		Game._wait();
		while ( getHand().size() > Game.MAX_CARD_IN_HAND ) {
			Card card = getHand().draw();
			getGraveyard().addTop(card);
			Game.getGUI().removeCardFromHand(card, card.getOwner());
			Game.getGUI().addCardToGraveyard(card, card.getOwner());
		}
	}
	
}
