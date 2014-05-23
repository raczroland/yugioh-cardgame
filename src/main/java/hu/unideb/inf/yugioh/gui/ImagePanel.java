package hu.unideb.inf.yugioh.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Kép megjelenítését megvalósító panel.
 * 
 * @author Rácz Roland
 */
public class ImagePanel extends JPanel {
	
	/**
	 * A panel által megjelenített kép.
	 */
	private BufferedImage image;
	
	/**
	 * A panelhez rendelt objektum.
	 */
	private Object linkedObject;

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
	public ImagePanel(String imageName, Object linkedObject) {
		try {
			this.image = ImageIO.read(ImagePanel.class.getClassLoader().getResourceAsStream(imageName + ".jpg"));
			this.linkedObject = linkedObject;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(image, 0, 0, null);
	}

}
