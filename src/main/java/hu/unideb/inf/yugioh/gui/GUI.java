package hu.unideb.inf.yugioh.gui;

import hu.unideb.inf.yugioh.data.DataManager;
import hu.unideb.inf.yugioh.main.Card;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.Player;
import hu.unideb.inf.yugioh.main.SpellCard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.GridBagLayout;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * A játék grafikus interfészét megvalósító osztálya.
 * 
 * @author Rácz Roland
 */
/**
 * @author Roland
 *
 */
public class GUI extends JFrame {

	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(GUI.class);

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
	static JButton btnDeckLoaded;
	static JButton btnDeckSave;
	static JButton btnDeckDelete;
	
	static JPanel player2Panel;
	static JPanel player1Panel;
	static JLabel infoLabel;
	static JPanel menu2Panel;
	
	private JPanel P2SpellCardZonePanel;
	private JPanel P2MonsterCardZonePanel;
	private JPanel P1MonsterCardZonePanel;
	private JPanel P1SpellCardZonePanel;
	
	private JLabel P1Lifepoints;
	private JPanel P1GraveyardZone;
	private CardPanel P1Graveyard;
	private JPanel P1DeckZone;
	private CardPanel P1Deck;
	private JLabel P2Lifepoints;
	private JPanel P2GraveyardZone;
	private CardPanel P2Graveyard;
	private JPanel P2DeckZone;
	private CardPanel P2Deck;
	
	private JLabel viewCardTop;
	private JLabel viewCardBottom;
	
	private JList deckList;

	public JList getDeckList() {
		return deckList;
	}

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
		menu1Panel.setBackground(Color.GRAY);
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
		
		player1Panel = new JPanel();
		player1Panel.setBackground(Color.LIGHT_GRAY);
		player1Panel.setBounds(124, 319, 566, 190);
		contentPane.add(player1Panel);
		player1Panel.setLayout(null);
		
		player2Panel = new JPanel();
		player2Panel.setBackground(Color.LIGHT_GRAY);
		player2Panel.setBounds(124, 82, 566, 190);
		contentPane.add(player2Panel);
		player2Panel.setLayout(null);
		
		P2SpellCardZonePanel = new JPanel();
		P2SpellCardZonePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		P2SpellCardZonePanel.setBounds(83, 11, 473, 78);
		player2Panel.add(P2SpellCardZonePanel);
		P2SpellCardZonePanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		P2MonsterCardZonePanel = new JPanel();
		P2MonsterCardZonePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		P2MonsterCardZonePanel.setBounds(83, 100, 473, 79);
		player2Panel.add(P2MonsterCardZonePanel);
		P2MonsterCardZonePanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		P1DeckZone = new JPanel();
		P1DeckZone.setBounds(494, 101, 62, 78);
		player1Panel.add(P1DeckZone);
		P1DeckZone.setLayout(null);
		
		P1Deck = new CardPanel("cardbackground_small", null);
		P1Deck.setBounds(10, 11, 42, 59);
		P1DeckZone.add(P1Deck);
		
		P2DeckZone = new JPanel();
		P2DeckZone.setBounds(10, 11, 63, 78);
		player2Panel.add(P2DeckZone);
		P2DeckZone.setLayout(null);
		
		P2Deck = new CardPanel("cardbackground_small", null);
		P2Deck.setBounds(10, 10, 42, 59);
		P2DeckZone.add(P2Deck);
		P2Deck.setLayout(null);
		
		P2GraveyardZone = new JPanel();
		P2GraveyardZone.setBounds(10, 100, 63, 78);
		player2Panel.add(P2GraveyardZone);
		P2GraveyardZone.setLayout(null);
		
		P1MonsterCardZonePanel = new JPanel();
		P1MonsterCardZonePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		P1MonsterCardZonePanel.setBounds(10, 11, 474, 78);
		player1Panel.add(P1MonsterCardZonePanel);
		P1MonsterCardZonePanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		P1SpellCardZonePanel = new JPanel();
		P1SpellCardZonePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		P1SpellCardZonePanel.setBounds(10, 100, 474, 79);
		player1Panel.add(P1SpellCardZonePanel);
		P1SpellCardZonePanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		P1GraveyardZone = new JPanel();
		P1GraveyardZone.setBounds(494, 11, 62, 78);
		player1Panel.add(P1GraveyardZone);
		P1GraveyardZone.setLayout(null);
		
		infoLabel = new JLabel("Yu-Gi-Oh! kártyajáték");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(124, 283, 566, 25);
		contentPane.add(infoLabel);
		
		menu2Panel = new JPanel();
		menu2Panel.setBackground(Color.GRAY);
		menu2Panel.setBounds(10, 136, 104, 319);
		contentPane.add(menu2Panel);
		menu2Panel.setLayout(null);
		
		btnDeckRandom = new JButton("Véletlen");
		btnDeckRandom.setEnabled(false);
		btnDeckRandom.setBounds(10, 11, 84, 23);
		btnDeckRandom.setActionCommand("deckRandom");
		menu2Panel.add(btnDeckRandom);
		
		btnDeckLoaded = new JButton("Betöltés");
		btnDeckLoaded.setBounds(10, 217, 84, 23);
		btnDeckLoaded.setActionCommand("deckLoaded");
		btnDeckLoaded.addActionListener(gameListener);
		menu2Panel.add(btnDeckLoaded);
		
		btnDeckSave = new JButton("Mentés");
		btnDeckSave.setBounds(10, 251, 84, 23);
		btnDeckSave.setActionCommand("deckSave");
		btnDeckSave.addActionListener(gameListener);
		menu2Panel.add(btnDeckSave);
		
		deckList = new JList(DataManager.getSavedDecks());
		deckList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deckList.setBounds(10, 45, 84, 161);
		menu2Panel.add(deckList);
		
		btnDeckDelete = new JButton("Törlés");
		btnDeckDelete.setActionCommand("deckDelete");
		btnDeckDelete.addActionListener(gameListener);
		btnDeckDelete.setBounds(10, 285, 84, 23);
		menu2Panel.add(btnDeckDelete);
		
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
		menu3panel.setBounds(10, 466, 104, 114);
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
	 * Megjeleníti a grafikus felületen az adott kártyalap adatait.
	 * 
	 * @param card a megjelenítendő kártyalap
	 * @return sikeres volt-e a megjelenítés
	 */
	public boolean showCard(Card card) {
		if (card instanceof MonsterCard) {
			MonsterCard mc = (MonsterCard) card;
			viewCardTop.setText( mc.getName() + " (" + mc.getType() + ", " + mc.getLevel() + ". szintű)" );
			viewCardBottom.setText( "ATK: " + mc.getAtk() + ", DEF: " + mc.getDef() );
			return true;
		} else if (card instanceof SpellCard) {
			SpellCard sc = (SpellCard) card;
			viewCardTop.setText( sc.getName() );
			viewCardBottom.setText( sc.getDescription() );
			return true;
		}
		logger.error("A megadott kártya se nem szörny-, se nem varázslap.");
		return false;
	}
	
	/**
	 * Hozzáadja az adott panelhez az adott komponenst.
	 * 
	 * @param panel a cél panel
	 * @param component a hozzáadandó komponens
	 */
	public void addComponentToPanel(JPanel panel, Component component) {
		panel.add(component);
		Game.getWorker().refreshGUI();
	}
	
	/**
	 * Eltávolíttja az adott panelből az adott komponenst.
	 * 
	 * @param panel a cél panel
	 * @param component az eltávolítandó komponens
	 */
	public void removeComponentFromPanel(JPanel panel, Component component) {
		panel.remove(component);
		Game.getWorker().refreshGUI();
	}
	
	/**
	 * Hozzáad egy adott kártyalapot reprezentáló képet az adott játékos kártya típusának megfelelő felületéhez.
	 * 
	 * @param card a reprezentálandó kártyalap
	 * @param player a kártyalap tulajdonosa
	 * @return sikeres volt-e a hozzáadás
	 */
	public boolean addCardToField(Card card, Player player) {
		if (card instanceof MonsterCard) {
			CardPanel ip = new CardPanel("monstercard_small", card);
			ip.setBounds(10, 11, 42, 59);
			if (player == p1) {
				addComponentToPanel(P1MonsterCardZonePanel, ip);
			} else if (player == p2) {
				addComponentToPanel(P2MonsterCardZonePanel, ip);
			} else {
				logger.error("A megadott játékos nincs hozzárendelve a kezelőfelülethez.");
				return false;
			}
			logger.info("Kép hozzáadva mezőhöz. (Szörnylap)");
			return true;
		} else if (card instanceof SpellCard) {
			CardPanel ip = new CardPanel("spellcard_small", card);
			ip.setBounds(10, 11, 42, 59);
			if (player == p1) {
				addComponentToPanel(P1SpellCardZonePanel, ip);
			} else if (player == p2) {
				addComponentToPanel(P2SpellCardZonePanel, ip);
			} else {
				logger.error("A megadott játékos nincs hozzárendelve a kezelőfelülethez.");
				return false;
			}
			logger.info("Kép hozzáadva mezőhöz. (Varázslap)");
			return true;
		}
		logger.error("A megadott kártya se nem szörny-, se nem varázslap.");
		return false;
	}
	
	/**
	 * Törli az adott játékos temetőjét.
	 * Létrehoz egy, az adott kártyalapot reprezentáló képet és hozzáadja az adott játékos temetőjéhez.
	 * 
	 * @param card a reprezentálandó kártyalap
	 * @param player a kártyalap tulajdonosa
	 * @return
	 */
	public boolean addCardToGraveyard(Card card, Player player) {
		JPanel graveyard;
		if (player == p1) {
			graveyard = P1GraveyardZone;
		} else if (player == p2) {
			graveyard = P2GraveyardZone;
		} else {
			logger.error("A megadott játékos nincs hozzárendelve a kezelőfelülethez.");
			return false;
		}
		graveyard.removeAll();
		if (card instanceof MonsterCard) {
			CardPanel ip = new CardPanel("monstercard_small", card);
			ip.setBounds(10, 11, 42, 59);
			addComponentToPanel(graveyard, ip);
			logger.info("Kép hozzáadva.");
			return true;
		} else if (card instanceof SpellCard) {
			CardPanel ip = new CardPanel("spellcard_small", card);
			ip.setBounds(10, 11, 42, 59);
			addComponentToPanel(graveyard, ip);
			logger.info("Kép hozzáadva.");
			return true;
		}
		logger.error("A megadott kártya se nem szörny-, se nem varázslap.");
		return false;
	}
	
	/**
	 * Eltávolítja az adott kártyalapot reprezentáló képet az adott játékos felületéről.
	 * 
	 * @param card az adott kártyalap, melynek reprezentáló képét el kell távolítani
	 * @param player a kártyalap tulajdonosa
	 * @return sikeres volt-e az eltávolítás
	 */
	public boolean removeCardFromField(Card card, Player player) {
		JPanel panel;
		if (player == p1 && card instanceof MonsterCard) {
			panel = P1MonsterCardZonePanel;
		} else if (player == p1 && card instanceof SpellCard) {
			panel = P1SpellCardZonePanel;
		} else if (player == p2 && card instanceof MonsterCard) {
			panel = P2MonsterCardZonePanel;
		} else if (player == p2 && card instanceof SpellCard) {
			panel = P2SpellCardZonePanel;
		} else {
			logger.error("Nem megfelelő paraméter.");
			return false;
		}
		for (Component component : panel.getComponents()) {
			if (component instanceof CardPanel && ((CardPanel)component).getLinkedObject()==card) {
				removeComponentFromPanel(panel, component);
				logger.info("Kép eltávolítva.");
				return true;
			}
		}
		logger.error("Nem sikerült az eltávolítás.");
		return false;
	}
}
