package hu.unideb.inf.yugioh.main;

import java.util.List;

import javax.swing.SwingWorker;


/**
 * Egy aktuális meccset "futtató" szál osztálya.
 * 
 * @author Rácz Roland
 */
public class GameWorker extends SwingWorker<Void, Void> {

	@Override
	protected Void doInBackground() throws Exception {
		Game.getMatch().run();
		return null;
	}

	@Override
	protected void process(List<Void> chunks) {
		Game.getGUI().revalidate();
		Game.getGUI().repaint();
		Game.getGUI().setVisible(true);
	}
	
	/**
	 * Frissíti a program ablakát.
	 */
	public void refreshGUI() {
		process(null);
	}
	
};