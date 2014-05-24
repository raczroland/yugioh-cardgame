package hu.unideb.inf.yugioh.gui;

import hu.unideb.inf.yugioh.data.DataManager;
import hu.unideb.inf.yugioh.main.Card;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.Generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A játék felhasználói interakcióit kezelő osztálya.
 * 
 * @author Rácz Roland
 */
public class GameListener implements ActionListener, MouseListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
			case "exit":
				System.exit(0);
				break;
			case "new":
				Game.newMatch();
				GUI.btnNew.setEnabled(false);
				GUI.btnGiveup.setEnabled(true);
				break;
			case "giveup":
				// TODO megírni
				//GUI.btnNew.setEnabled(true);
				//GUI.btnGiveup.setEnabled(false);
				Game.getGUI().revalidate();
				Game.getGUI().repaint();
				break;
			case "deckSave":
				DataManager.saveDeckToFile(Game.getLoadedDeck());
				Game.getGUI().getDeckList().setListData( DataManager.getSavedDecks() );
				break;
			case "deckLoaded":
				Game.setLoadedDeck(DataManager.loadDeckFromFile( (String)Game.getGUI().getDeckList().getSelectedValue() ));
				Game.getGUI().getDeckList().setListData( DataManager.getSavedDecks() );
				break;
			case "deckDelete":
				DataManager.deleteXML( (String)Game.getGUI().getDeckList().getSelectedValue() );
				Game.getGUI().getDeckList().setListData( DataManager.getSavedDecks() );
				break;
			case "deckRandom":
				Game.setLoadedDeck( Generator.generateRandomDeck(Game.getHuman(), 40) );
				Game.getGUI().showMessage("Új pakli generálva.");
				break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		if (e.getSource() instanceof CardPanel) {
			
			CardPanel cp = (CardPanel) e.getSource();
			
			if (Game.getGUI().isMPEventEnabled()) {
				
				if (Game.getHuman().getHand().getCards().contains((Card)cp.getLinkedObject())) {
					Game.getGUI().setEventObject((Card)cp.getLinkedObject());
					Game.getGUI().setMPEventEnabled(false);
				} else if (Game.getHuman().getMonsterCardZone().getCards().contains((Card)cp.getLinkedObject())) {
					Game.getGUI().setEventObject((Card)cp.getLinkedObject());
					Game.getGUI().setMPEventEnabled(false);
				}
				
			}
			
		}
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource() instanceof CardPanel) {
			Card card = (Card) ((CardPanel)e.getSource()).getLinkedObject();
			if (card.isFaceup()) {
				Game.getGUI().showCard( card );
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

}
