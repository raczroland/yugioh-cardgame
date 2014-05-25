package hu.unideb.inf.yugioh.gui;

import hu.unideb.inf.yugioh.data.DataManager;
import hu.unideb.inf.yugioh.main.Card;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.Generator;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

/**
 * A játék felhasználói interakcióit kezelő osztálya.
 * 
 * @author Rácz Roland
 */
public class GameListener implements ActionListener, MouseListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		
			// Kilépés:
			case "exit":
				System.exit(0);
				break;
				
			// Új meccs kezdése:
			case "new":
				Game.newMatch();
				GUI.btnNew.setEnabled(false);
				GUI.btnGiveup.setEnabled(true);
				break;
				
			// Aktuális meccs feladása:
			case "giveup":
				Game.getMatch().setGiveup(true);
				Game.getMatch().setWinner(Game.getComputer());
				break;
				
			// Aktuálisan betöltött pakli mentése fájlba:
			case "deckSave":
				DataManager.saveDeckToFile(Game.getLoadedDeck());
				Game.getGUI().getDeckList().setListData( DataManager.getSavedDecks() );
				break;
			
			// Kiválasztott pakli betöltése:
			case "deckLoaded":
				Game.setLoadedDeck(DataManager.loadDeckFromFile( (String)Game.getGUI().getDeckList().getSelectedValue() ));
				Game.getGUI().getDeckList().setListData( DataManager.getSavedDecks() );
				break;
				
			// Kiválasztott pakli törlése:
			case "deckDelete":
				DataManager.deleteXML( (String)Game.getGUI().getDeckList().getSelectedValue() );
				Game.getGUI().getDeckList().setListData( DataManager.getSavedDecks() );
				break;
			
			// Véletlenszerű pakli generálása és betöltése:
			case "deckRandom":
				Game.setLoadedDeck( Generator.generateRandomDeck(Game.getHuman(), 40) );
				Game.getGUI().showMessage("Új pakli generálva.");
				break;
				
			// Ugrás a következő fázisra:
			case "nextPhase":
				Game.getGUI().setNextFlag(true);
				Game.getGUI().setHandEventEnabled(false);
				Game.getGUI().setHumanMonsterEventEnabled(false);
				Game.getGUI().setComputerMonsterEventEnabled(false);
				break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	
		if (e.getSource() instanceof CardPanel) {
			
			CardPanel cp = (CardPanel) e.getSource();
			
			if (Game.getGUI().isHandEventEnabled()) {
				
				// Kártyalap kiválasztása a kézből:
				if (Game.getHuman().getHand().getCards().contains((Card)cp.getLinkedObject())) {
					Game.getGUI().setEventObject((Card)cp.getLinkedObject());
					Game.getGUI().setHandEventEnabled(false);
					Game.getGUI().setRightClick(SwingUtilities.isRightMouseButton(e));
				} 
				
			}
			if (Game.getGUI().isHumanMonsterEventEnabled()) {

				// Kártyalap kiválasztása a felhasználó mezőjéről:
				if (Game.getHuman().getMonsterCardZone().getCards().contains((Card)cp.getLinkedObject())) {
					Game.getGUI().setEventObject((Card)cp.getLinkedObject());
					Game.getGUI().setHumanMonsterEventEnabled(false);
				}
				
			}
			if (Game.getGUI().isComputerMonsterEventEnabled()) {

				// Kártyalap kiválastása az ellenfél mezőjéről:
				if (Game.getComputer().getMonsterCardZone().getCards().contains((Card)cp.getLinkedObject())) {
					Game.getGUI().setEventObject((Card)cp.getLinkedObject());
					Game.getGUI().setComputerMonsterEventEnabled(false);
				}
				
			}
			
		}
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource() instanceof CardPanel) {
			
			// Kártyalap adatainak megjelenítése:
			Card card = (Card) ((CardPanel)e.getSource()).getLinkedObject();
			if (card.isFaceup()) {
				Game.getGUI().showCard( card );
			}
			
			// Kurzor állítása:
			CardPanel cp = (CardPanel) e.getSource();
			if (Game.getGUI().isHandEventEnabled() && Game.getHuman().getHand().getCards().contains((Card)cp.getLinkedObject())) {
				cp.setCursor(new Cursor(Cursor.HAND_CURSOR));				
			}
			if (Game.getGUI().isHumanMonsterEventEnabled() && Game.getHuman().getMonsterCardZone().getCards().contains((Card)cp.getLinkedObject())) {
				cp.setCursor(new Cursor(Cursor.HAND_CURSOR));				
			}
			if (Game.getGUI().isComputerMonsterEventEnabled() && Game.getComputer().getMonsterCardZone().getCards().contains((Card)cp.getLinkedObject())) {
				cp.setCursor(new Cursor(Cursor.HAND_CURSOR));				
			}
			
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		// Kurzor állítása:
		if (e.getSource() instanceof CardPanel) {
			
			CardPanel cp = (CardPanel) e.getSource();
			cp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

}
