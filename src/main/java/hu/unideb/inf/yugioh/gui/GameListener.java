package hu.unideb.inf.yugioh.gui;

import hu.unideb.inf.yugioh.main.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
				GUI.btnNew.setEnabled(true);
				GUI.btnGiveup.setEnabled(false);
				break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource() instanceof ImagePanel) {
			//((ImagePanel)e.getSource()).set
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
