package hu.unideb.inf.yugioh.gui;

import hu.unideb.inf.yugioh.main.Card;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.SpellCard;

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
 * Megjelenít egy betöltött képet.
 * 
 * @author Rácz Roland
 */
public class CardPanel extends JPanel {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

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
	private Card linkedCard;
	
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
		return linkedCard;
	}

	/**
	 * Konstruktor az osztályhoz.
	 * Létrehoz egy panelt és egy képet a paraméterként átadott kártyalap számára.
	 * 
	 * @param card a panelhez rendelt kártyalap
	 */
	public CardPanel(Card card) {
		try {
			String imageName = "";
			if (card instanceof MonsterCard) {
				MonsterCard mc = (MonsterCard) card;
				if (mc.isFaceup() && !mc.isDefensePosition()) {
					imageName = "monstercard_small";
				} else if (!mc.isFaceup() && !mc.isDefensePosition()) {
					imageName = "cardbackground_small";
				} else if (mc.isFaceup() && mc.isDefensePosition()) {
					imageName = "monstercard2_small";
				} else if (!mc.isFaceup() && mc.isDefensePosition()) {
					imageName = "cardbackground2_small";
				}
			} else if (card instanceof SpellCard) {
				SpellCard sc = (SpellCard) card;
				if (sc.isFaceup()) {
					imageName = "spellcard_small";
				} else {
					imageName = "cardbackground_small";
				}
			}
			this.image = ImageIO.read(CardPanel.class.getClassLoader().getResourceAsStream(imageName + ".jpg"));
			this.linkedCard = card;
			setLayout(null);
			if (linkedCard instanceof MonsterCard && ((MonsterCard)linkedCard).isFaceup()) {
				atkLabel = new JLabel(""+((MonsterCard)linkedCard).getAtk());
				atkLabel.setBounds(5, 2, 60, 20);
				add(atkLabel);
				defLabel = new JLabel(""+((MonsterCard)linkedCard).getDef());
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
