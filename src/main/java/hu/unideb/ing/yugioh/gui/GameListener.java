package hu.unideb.ing.yugioh.gui;

import hu.unideb.inf.yugioh.main.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameListener implements ActionListener {

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
				GUI.btnNew.setEnabled(true);
				GUI.btnGiveup.setEnabled(false);
				break;
		}
		
	}

}
