package hu.unideb.inf.yugioh.data;

import hu.unideb.inf.yugioh.main.Card;
import hu.unideb.inf.yugioh.main.Deck;
import hu.unideb.inf.yugioh.main.Game;
import hu.unideb.inf.yugioh.main.Generator;
import hu.unideb.inf.yugioh.main.MonsterCard;
import hu.unideb.inf.yugioh.main.SpellCard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játék adatainak kezelését végző statikus osztály.
 * 
 * @author Rácz Roland
 */
public class DataManager {
	
	/**
	 * Naplózáshoz szükséges logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(DataManager.class);
	
	/**
	 * Egy XML fájlból beolvassa az adatokat és egy paklit állít elő.
	 * 
	 * @param filename az XML fájl neve
	 * @return az előállított pakli
	 */
	public static Deck loadDeckFromFile(String filename) {
		
		Vector<Card> cards = new Vector<Card>();
		
		try {
			
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			File dir =  new File(System.getProperty("user.home") + "/yugiohcg");
			InputStream in = new FileInputStream( dir.getPath() + "/" + filename);
			
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			
			CardItem item = null;
			
			while (eventReader.hasNext()) {
				
				XMLEvent event = eventReader.nextEvent();
				
				if (event.isStartElement()) {
					
					StartElement startElement = event.asStartElement();
					
					if (startElement.getName().getLocalPart().equals("card")) {
						Iterator<?> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = (Attribute) attributes.next();
							if (attribute.getName().toString().equals("type")) {
								if (attribute.getValue().equals("monster")) {
									item = new MonsterCardItem();
								} else {
									item = new SpellCardItem();
								}
							}
						}
					}
					if (startElement.getName().getLocalPart().equals("name")) {
						event = eventReader.nextEvent();
						item.setName(event.asCharacters().getData());
					}
					if (startElement.getName().getLocalPart().equals("description")) {
						event = eventReader.nextEvent();
						item.setDescription(event.asCharacters().getData());
					}
					if (startElement.getName().getLocalPart().equals("atk")) {
						event = eventReader.nextEvent();
						((MonsterCardItem)item).setAtk(Integer.parseInt(event.asCharacters().getData()));
					}
					if (startElement.getName().getLocalPart().equals("def")) {
						event = eventReader.nextEvent();
						((MonsterCardItem)item).setDef(Integer.parseInt(event.asCharacters().getData()));
					}
					if (startElement.getName().getLocalPart().equals("type")) {
						event = eventReader.nextEvent();
						((MonsterCardItem)item).setType(event.asCharacters().getData());
					}
					if (startElement.getName().getLocalPart().equals("level")) {
						event = eventReader.nextEvent();
						((MonsterCardItem)item).setLevel(Integer.parseInt(event.asCharacters().getData()));
					}
					if (startElement.getName().getLocalPart().equals("effectType")) {
						event = eventReader.nextEvent();
						((SpellCardItem)item).setEffectType(event.asCharacters().getData());
					}
					if (startElement.getName().getLocalPart().equals("effectTargetClass")) {
						event = eventReader.nextEvent();
						((SpellCardItem)item).setEffectTargetClass(event.asCharacters().getData());
					}
					if (startElement.getName().getLocalPart().equals("effectVal1")) {
						event = eventReader.nextEvent();
						((SpellCardItem)item).setEffectVal1(Integer.parseInt(event.asCharacters().getData()));
					}
					if (startElement.getName().getLocalPart().equals("effectVal2")) {
						event = eventReader.nextEvent();
						((SpellCardItem)item).setEffectVal2(Integer.parseInt(event.asCharacters().getData()));
					}
					
				}
				
				if (event.isEndElement()) {
					
					EndElement endElement = event.asEndElement();

					if (endElement.getName().getLocalPart().equals("card")) {
						
						if (item instanceof MonsterCardItem) {
							cards.add( ((MonsterCardItem) item).asMonsterCard() );
						} else if (item instanceof SpellCardItem) {
							cards.add( ((SpellCardItem) item).asSpellCard() );
						}
						
					}
				}
				
			}
			
		} catch (XMLStreamException e) {
			logger.error("XML hiba.");
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található: " + filename);
		}
		
		Game.getGUI().showMessage("Pakli betöltve: " + filename);
		logger.info("Pakli betöltve: " + filename);
		return new Deck(cards);
		
	}
	
	/**
	 * Létrehoz egy csomópontot.
	 * 
	 * @param eventFactory felhasználandó eventFactory
	 * @param eventWriter felhasználandó eventWriter
	 * @param elementName az elem neve
	 * @param value az elem értéke
	 * @param numberOfTabs az elem elé rakandó tabok száma
	 */
	private static void createNode(XMLEventFactory eventFactory, XMLEventWriter eventWriter, String elementName, String value, int numberOfTabs) {

		try {
			
			XMLEvent end = eventFactory.createDTD("\n");
			XMLEvent tab = eventFactory.createDTD("\t");
			
			StartElement startElement = eventFactory.createStartElement("", "", elementName);
			Characters val = eventFactory.createCharacters(value);
			EndElement endElement = eventFactory.createEndElement("", "", elementName);
			for (int i = 0; i < numberOfTabs; i++) {
				eventWriter.add(tab);
			}
			eventWriter.add(startElement);
			eventWriter.add(val);
			eventWriter.add(endElement);
			eventWriter.add(end);
			
		} catch (XMLStreamException e) {
			logger.error("XML hiba.");
		}
		
	}
	
	/**
	 * Elmenti a paraméterként átadott paklit a felhasználó mappáján belül egy yugiohcg nevű mappában egy XML fájlba.
	 * 
	 * @param deck az elmentendő pakli
	 */
	public static void saveDeckToFile(Deck deck) {
		try {
			
			Calendar calendar = Calendar.getInstance();
			
			XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
			
			File dir =  new File(System.getProperty("user.home") + "/yugiohcg");
			dir.mkdir();
			String filename = Generator.randomName();
			OutputStream out = new FileOutputStream( dir.getPath() + "/" + filename + ".xml" );
			
			XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(out);
			
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent end = eventFactory.createDTD("\n");
			XMLEvent tab = eventFactory.createDTD("\t");
			
			StartDocument startDocument = eventFactory.createStartDocument();
			EndDocument endDocument = eventFactory.createEndDocument();
			eventWriter.add(startDocument);
			
			eventWriter.add(end);
			StartElement deckStartElement = eventFactory.createStartElement("", "", "deck");
			eventWriter.add(deckStartElement);
			eventWriter.add(end);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			createNode(eventFactory, eventWriter, "dateCreated", dateFormat.format(calendar.getTime()), 1);
			
			for (Card card : deck.getCards()) {
				
				boolean isMonsterCard = card instanceof MonsterCard;
				
				StartElement cardStartElement = eventFactory.createStartElement("", "", "card");
				Attribute cardAttribute = eventFactory.createAttribute("type", isMonsterCard ? "monster" : "spell" );
				EndElement cardEndElement = eventFactory.createEndElement("", "", "card");
				
				eventWriter.add(tab);
				eventWriter.add(cardStartElement);
				eventWriter.add(cardAttribute);
				eventWriter.add(end);
				
				createNode(eventFactory, eventWriter, "name", card.getName(), 2);
				createNode(eventFactory, eventWriter, "description", card.getDescription(), 2);
				
				if (isMonsterCard) {
					
					createNode(eventFactory, eventWriter, "atk", Integer.toString(((MonsterCard)card).getAtk()), 2);
					createNode(eventFactory, eventWriter, "def", Integer.toString(((MonsterCard)card).getDef()), 2);
					createNode(eventFactory, eventWriter, "type", ((MonsterCard)card).getType(), 2);
					createNode(eventFactory, eventWriter, "level", Integer.toString(((MonsterCard)card).getLevel()), 2);
					
				} else {

					createNode(eventFactory, eventWriter, "effectType", ((SpellCard)card).getEffect().getType(), 2);
					createNode(eventFactory, eventWriter, "effectTargetClass", ((SpellCard)card).getEffect().getTargetClass(), 2);
					createNode(eventFactory, eventWriter, "effectVal1", Integer.toString(((SpellCard)card).getEffect().getVal1()), 2);
					createNode(eventFactory, eventWriter, "effectVal2", Integer.toString(((SpellCard)card).getEffect().getVal2()), 2);
					
				}

				eventWriter.add(tab);
				eventWriter.add(cardEndElement);
				eventWriter.add(end);
				
			}
			
			eventWriter.add(endDocument);
			eventWriter.close();
			
			out.close();
			
			Game.getGUI().showMessage("Pakli elmentve: " + filename + ".xml");
			logger.info("Pakli elmentve.");
			
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található.");
		} catch (XMLStreamException e) {
			logger.error("XML hiba.");
		} catch (IOException e) {
			logger.error("IOException.");
		}
		
	}
	
	/**
	 * Visszaadja az elmentett paklik XML fájljainak nevét.
	 * 
	 * @return az elmentett paklik XML fájljainak neve
	 */
	public static Vector<String> getSavedDecks() {
		Vector<String> savedDecks = new Vector<String>();
		File dir = new File(System.getProperty("user.home") + "/yugiohcg");
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().endsWith(".xml")) {
					savedDecks.add(file.getName());
				}
			}
		}
		return savedDecks;
	}
	
	/**
	 * Törli az adott XML fájlt a felhasználó könyvtárából.
	 * 
	 * @param filename törlendő fájl neve
	 * @return sikeres volt-e a törlés
	 */
	public static boolean deleteXML(String filename) {
		File file = new File(System.getProperty("user.home") + "/yugiohcg/" + filename);
		if (file.delete()) {
			Game.getGUI().showMessage("Pakli törölve: " + filename);
			logger.info("A törlés sikeres: " + filename);
			return true;
		} else {
			logger.error("A törlés sikertelen: " + filename);
			return false;
		}
	}

}
