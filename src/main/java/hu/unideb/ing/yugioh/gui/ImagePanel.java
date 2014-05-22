package hu.unideb.ing.yugioh.gui;

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
	 * Konstruktor az osztályhoz.
	 * Beállítja a paraméterként átadott nevű képet.
	 * 
	 * @param imageName a beállítandó kép neve
	 */
	public ImagePanel(String imageName) {
		try {
			this.image = ImageIO.read(ImagePanel.class.getClassLoader().getResourceAsStream(imageName + ".jpg"));
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
