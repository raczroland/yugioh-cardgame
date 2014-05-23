package hu.unideb.inf.yugioh.gui;

import hu.unideb.inf.yugioh.main.Card;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;
import hu.unideb.inf.yugioh.main.SpellCard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

/**
 * A játék grafikus interfészét megvalósító osztálya.
 * 
 * @author Rácz Roland
 */
public class GUI extends JFrame {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A felhasználói felület behatásait kezelő objektum.
	 */
	private GameListener gameListener;
	
	private Player p1;
	private Player p2;
	
	private JPanel contentPane;
	private JPanel menu1Panel;
	
	static JButton btnNew;
	static JButton btnGiveup;
	static JButton btnExit;
	
	static JButton btnDeckRandom;
	static JButton btnDeckDefault;
	static JButton btnDeckLoaded;
	static JButton btnDeckSave;
	
	static JPanel player2Panel;
	static JPanel player1Panel;
	static JLabel infoLabel;
	static JPanel menu2Panel;
	
	/*private Vector<JPanel> player1MonsterCards;
	private Vector<JPanel> player1SpellCards;
	private Vector<JPanel> player2MonsterCards;
	private Vector<JPanel> player2SpellCards;*/
	
	private JPanel player2SpellCardZonePanel;
	private JPanel player2MonsterCardZonePanel;
	private JPanel player1MonsterCardZonePanel;
	private JPanel player1SpellCardZonePanel;
	
	private JLabel P1Lifepoints;
	private JLabel P2Lifepoints;
	
	private JLabel viewCardTop;
	private JLabel viewCardBottom;
	

	/**
	 * Konstruktor az osztályhoz.
	 * Létrehozza az ablak grafikus elemeit.
	 */
	public GUI() {
		
		p1 = null;
		p2 = null;
		
		setTitle("Yu-Gi-Oh! kártyajáték");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 633);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		gameListener = new GameListener();
		
		menu1Panel = new JPanel();
		menu1Panel.setBackground(Color.DARK_GRAY);
		menu1Panel.setBounds(10, 11, 104, 114);
		contentPane.add(menu1Panel);
		menu1Panel.setLayout(null);
		
		btnNew = new JButton("Új");
		btnNew.setBounds(9, 11, 87, 23);
		btnNew.setActionCommand("new");
		btnNew.addActionListener(gameListener);
		menu1Panel.add(btnNew);
		
		btnGiveup = new JButton("Feladás");
		btnGiveup.setBounds(9, 45, 87, 23);
		btnGiveup.setEnabled(false);
		btnGiveup.setActionCommand("giveup");
		btnGiveup.addActionListener(gameListener);
		menu1Panel.add(btnGiveup);
		
		btnExit = new JButton("Kilépés");
		btnExit.setBounds(9, 79, 87, 23);
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(gameListener);
		menu1Panel.add(btnExit);
		
		player2Panel = new JPanel();
		player2Panel.setBackground(Color.LIGHT_GRAY);
		player2Panel.setBounds(124, 82, 566, 190);
		contentPane.add(player2Panel);
		player2Panel.setLayout(null);
		
		player2SpellCardZonePanel = new JPanel();
		player2SpellCardZonePanel.setBounds(10, 11, 546, 78);
		player2Panel.add(player2SpellCardZonePanel);
		player2SpellCardZonePanel.setLayout(new GridLayout(1, 4, 0, 0));
		
		player2MonsterCardZonePanel = new JPanel();
		player2MonsterCardZonePanel.setBounds(10, 100, 546, 79);
		player2Panel.add(player2MonsterCardZonePanel);
		player2MonsterCardZonePanel.setLayout(null);
		
		/*ImagePanel testImage = new ImagePanel("monstercard_small");
		testImage.setBounds(10, 11, 100, 100);
		testImage.addMouseListener(gameListener);
		player2MonsterCardZonePanel.add(testImage);*/
		
		player1Panel = new JPanel();
		player1Panel.setBackground(Color.LIGHT_GRAY);
		player1Panel.setBounds(124, 319, 566, 190);
		contentPane.add(player1Panel);
		player1Panel.setLayout(null);
		
		player1MonsterCardZonePanel = new JPanel();
		player1MonsterCardZonePanel.setBounds(10, 11, 546, 78);
		player1Panel.add(player1MonsterCardZonePanel);
		
		player1SpellCardZonePanel = new JPanel();
		player1SpellCardZonePanel.setBounds(10, 100, 546, 79);
		player1Panel.add(player1SpellCardZonePanel);
		
		infoLabel = new JLabel("Yu-Gi-Oh! kártyajáték");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(124, 283, 566, 25);
		contentPane.add(infoLabel);
		
		menu2Panel = new JPanel();
		menu2Panel.setBackground(Color.GRAY);
		menu2Panel.setBounds(10, 136, 104, 160);
		contentPane.add(menu2Panel);
		menu2Panel.setLayout(null);
		
		btnDeckRandom = new JButton("Véletlen");
		btnDeckRandom.setEnabled(false);
		btnDeckRandom.setBounds(10, 11, 84, 23);
		btnDeckRandom.setActionCommand("deckRandom");
		menu2Panel.add(btnDeckRandom);
		
		btnDeckDefault = new JButton("Alap");
		btnDeckDefault.setBounds(10, 45, 84, 23);
		btnDeckDefault.setActionCommand("deckDefault");
		menu2Panel.add(btnDeckDefault);
		
		btnDeckLoaded = new JButton("Saját");
		btnDeckLoaded.setBounds(10, 79, 84, 23);
		btnDeckLoaded.setActionCommand("deckLoaded");
		menu2Panel.add(btnDeckLoaded);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 113, 84, 2);
		menu2Panel.add(separator);
		
		btnDeckSave = new JButton("Mentés");
		btnDeckSave.setEnabled(false);
		btnDeckSave.setBounds(10, 126, 84, 23);
		btnDeckSave.setActionCommand("deckSave");
		menu2Panel.add(btnDeckSave);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(124, 11, 350, 60);
		contentPane.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(340, 520, 350, 60);
		contentPane.add(panel_5);
		
		P1Lifepoints = new JLabel("Életpontok száma: -");
		P1Lifepoints.setBounds(124, 520, 206, 14);
		contentPane.add(P1Lifepoints);
		
		P2Lifepoints = new JLabel("Életpontok száma: -");
		P2Lifepoints.setBounds(484, 11, 206, 14);
		contentPane.add(P2Lifepoints);
		
		viewCardTop = new JLabel("Sötét varázsló (sötét, *******) ");
		viewCardTop.setForeground(Color.GRAY);
		viewCardTop.setBounds(124, 543, 206, 14);
		contentPane.add(viewCardTop);
		
		viewCardBottom = new JLabel("ATK: 2500 DEF: 2100");
		viewCardBottom.setForeground(Color.GRAY);
		viewCardBottom.setBounds(124, 566, 206, 14);
		contentPane.add(viewCardBottom);
		
		JPanel menu3panel = new JPanel();
		menu3panel.setLayout(null);
		menu3panel.setBackground(Color.DARK_GRAY);
		menu3panel.setBounds(10, 307, 104, 114);
		contentPane.add(menu3panel);
		
		JButton btnPhaseBattle = new JButton("Harci fázis");
		btnPhaseBattle.setEnabled(false);
		btnPhaseBattle.setBounds(10, 11, 84, 23);
		menu3panel.add(btnPhaseBattle);
		
		JButton btnPhaseMain2 = new JButton("Fő fázis 2.");
		btnPhaseMain2.setEnabled(false);
		btnPhaseMain2.setBounds(10, 45, 84, 23);
		menu3panel.add(btnPhaseMain2);
		
		JButton btnPhaseEnd = new JButton("Vég fázis");
		btnPhaseEnd.setEnabled(false);
		btnPhaseEnd.setBounds(10, 79, 84, 23);
		menu3panel.add(btnPhaseEnd);
		
		//player1MonsterCardZone = new Vector<JPanel>(5);
		for (int i = 0; i < 5; i++) {
			//player1MonsterCardZone.add(arg0)
		}
		
		setVisible(true);
	}
	
	/**
	 * Beállítja a felhasználóifelülethez rendelt két játékost.
	 * 
	 * @param p1 az első játékos
	 * @param p2 a második játékos
	 */
	public void setPlayers(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Adott üzenet megjelenítése a grafikus felületen.
	 * 
	 * @param msg a megjelenítendő üzenet
	 */
	public void showMessage(String msg) {
		infoLabel.setText(msg);
	}
	
	/**
	 * Megjeleníti az adott játékos életpontjait a saját felületén.
	 * 
	 * @param player a játékos
	 */
	public void showLifepoints(Player player) {
		if (player == p1) {
			P1Lifepoints.setText("Életpontok száma: " + player.getLifepoints());
		} else if (player == p2) {
			P2Lifepoints.setText("Életpontok száma: " + player.getLifepoints());
		}
	}
	
	/**
	 * Megjeleníti a grafikus felületen az adott kártya adatait.
	 * 
	 * @param card a megjelenítendő kártya
	 */
	public void showCard(Card card) {
		if (card instanceof MonsterCard) {
			MonsterCard mc = (MonsterCard) card;
			viewCardTop.setText( mc.getName() + " (" + mc.getType() + ", " + mc.getLevel() + ". szintű)" );
			viewCardBottom.setText( "ATK: " + mc.getAtk() + ", DEF: " + mc.getDef() );
		} else if (card instanceof SpellCard) {
			SpellCard sc = (SpellCard) card;
			viewCardTop.setText( sc.getName() );
			viewCardBottom.setText( sc.getDescription() );
		}
	}
	
}
