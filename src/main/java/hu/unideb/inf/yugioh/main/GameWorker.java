package hu.unideb.inf.yugioh.main;

import java.util.List;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy aktuális meccset "futtató" szál osztálya.
 * 
 * Egy meccs szálának mindig külön szálon kell futnia.
 * A grafikus felület a <code>refreshGUI()</code> metódussal frissíthető.
 * 
 * @author Rácz Roland
 */
public class GameWorker extends SwingWorker<Void, Void> {
	
	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(GameWorker.class);

	@Override
	protected Void doInBackground() throws Exception {
		Game.getMatch().run();
		logger.info("Meccs szála elindítva.");
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
		logger.info("GUI frissítve.");
	}

	@Override
	protected void done() {
		super.done();
		logger.info("Játék szála befejezve.");
	}
	
};