package hu.unideb.inf.yugioh.gui;

import hu.unideb.inf.yugioh.main.MonsterCard;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kártyalap megjelenítését megvalósító panel.
 * 
 * @author Rácz Roland
 */
public class CardPanel extends JPanel {

	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(CardPanel.class);
	
	/**
	 * A panel által megjelenített kép.
	 */
	private BufferedImage image;
	
	/**
	 * A panelhez rendelt objektum.
	 */
	private Object linkedObject;
	
	/**
	 * Támadópontokat megjelenítő címke.
	 */
	private JLabel atkLabel;
	
	/**
	 * Védelmi pontokat megjelenítő címke.
	 */
	private JLabel defLabel;

	/**
	 * Visszaadja a panelhez rendelt objektumot.
	 * 
	 * @return a panelhez rendelt objektum
	 */
	public Object getLinkedObject() {
		return linkedObject;
	}

	/**
	 * Konstruktor az osztályhoz.
	 * Beállítja a paraméterként átadott nevű képet és hozzácsatolja a panelhez az adott objektumot.
	 * 
	 * @param imageName a beállítandó kép neve
	 * @param linkedObject a panelhez rendelt objektum
	 */
	public CardPanel(String imageName, Object linkedObject) {
		try {
			this.image = ImageIO.read(CardPanel.class.getClassLoader().getResourceAsStream(imageName + ".jpg"));
			this.linkedObject = linkedObject;
			setLayout(null);
			if (linkedObject instanceof MonsterCard) {
				atkLabel = new JLabel(""+((MonsterCard)linkedObject).getAtk());
				atkLabel.setBounds(5, 2, 60, 20);
				add(atkLabel);
				defLabel = new JLabel(""+((MonsterCard)linkedObject).getDef());
				defLabel.setBounds(5, 20, 60, 20);
				add(defLabel);
			}
		} catch (IOException e) {
			logger.error("Nem tölthető be a kép.");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(image, 0, 0, null);
	}

}
