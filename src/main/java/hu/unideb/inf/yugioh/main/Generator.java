package hu.unideb.inf.yugioh.main;

import java.util.Random;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játékhoz szükséges véletlenszerű objektumokat előállító statikus osztály.
 * 
 * @author Rácz Roland
 */
public class Generator {
	
	/**
	 * Naplózáshoz szükséges logger.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Generator.class);
	
	/**
	 * Véletlen számokat előállító objektum.
	 */
	private static Random rand = new Random();
	
	/**
	 * Egy véletlenszerű nevet ad vissza.
	 * Egy néhány szótagból álló sztringet állít elő.
	 * 
	 * @return véletlenszerű név
	 */
	public static String randomName() {
		String name = "";
		String[] syllables2 = {
				"ra", "ta", "za", "pa", "sa", "da", "ka", "la", "ga", "qa", "wa", "ma", "na", "va",
				"re", "te", "ze", "pe", "se", "de", "ke", "le", "ge", "qe", "we", "me", "ne", "ve",
				"ri", "ti", "zi", "pi", "si", "di", "ki", "li", "gi", "qi", "wi", "mi", "ni", "vi",
				"ro", "to", "zo", "po", "so", "do", "ko", "lo", "go", "qo", "wo", "mo", "no", "vo",
				"ru", "tu", "zu", "pu", "su", "du", "ku", "lu", "gu", "qu", "wu", "mu", "nu", "vu"
		};
		String[] syllables3 = {
				"ran", "tal", "zac", "pat", "san", "dar", "kal", "lao", "gav", "qar", "wat", "man", "nar", "var",
				"reg", "tep", "zen", "pet", "ses", "det", "ken", "leg", "get", "qel", "wen", "mer", "nea", "vev",
				"rin", "tin", "zin", "pil", "sin", "dir", "kit", "lin", "gin", "qil", "wio", "min", "nik", "via",
				"rom", "tom", "zol", "pok", "son", "dom", "kor", "low", "gon", "qon", "won", "mon", "now", "von",
				"ruv", "tul", "zuk", "pur", "sun", "due", "kun", "lug", "gun", "qun", "wul", "muk", "nuy", "vuo"
		};
		int n = rand.nextInt(5);
		if ( n == 0 ) {
			name += syllables2[ rand.nextInt(syllables2.length) ];
			name += syllables3[ rand.nextInt(syllables3.length) ];
		} else if ( n == 1 ) {
			name += syllables3[ rand.nextInt(syllables2.length) ];
			name += syllables3[ rand.nextInt(syllables3.length) ];
		} else if ( n == 2 ) {
			name += syllables3[ rand.nextInt(syllables2.length) ];
			name += syllables2[ rand.nextInt(syllables3.length) ];
		} else if ( n == 3 ) {
			name += syllables2[ rand.nextInt(syllables2.length) ];
			name += syllables2[ rand.nextInt(syllables2.length) ];
			name += syllables3[ rand.nextInt(syllables3.length) ];
		} else if ( n == 4 ) {
			name += syllables2[ rand.nextInt(syllables2.length) ];
			name += syllables3[ rand.nextInt(syllables2.length) ];
			name += syllables3[ rand.nextInt(syllables3.length) ];
		}
		String retname = name.substring(0, 1).toUpperCase() + name.substring(1);
		logger.info("Név kigenerálva: " + retname);
		return retname;
	}
	
	/**
	 * Egy véletlenszerű típust ad vissza.
	 * 
	 * @return véletlenszerű típus
	 */
	private static String randomType() {
		String[] types = { "fény", "sötét", "víz", "tűz", "levegő", "föld" };
		String ret = types[ rand.nextInt(types.length) ];
		logger.info("Típus kigenerálva: " + ret);
		return ret;
	}
	
	/**
	 * Egy véletlenszerű szörnylapot generál az adott játékosnak.
	 * 
	 * @param player a szörnylap tulajdonosa
	 * @return véletlenszerű szörnylap
	 */
	public static MonsterCard generateRandomMonsterCard(Player player) {
		int atk = (rand.nextInt(21)+5)*100;
		int def = (rand.nextInt(21)+5)*100;
		int level = ((atk+def)-1000)/1000+3;
		MonsterCard ret = new MonsterCard(randomName(), "-", false, randomType(), atk, def, level, false, player);
		logger.info("Szörnylap kigenerálva: " + ret);
		return ret;
	}
	
	/**
	 * Egy véletlenszerű varázslapot generál az adott játékosnak.
	 * 
	 * @param player a varázslap tulajdonosa
	 * @return véletlenszerű varázslap
	 */
	public static SpellCard generateRandomSpellCard(Player player) {
		Effect[] effects = {
				new Effect("incAtk", "MonsterCard", 300),
				new Effect("incAtk", "MonsterCard", 500),
				new Effect("decAtk", "MonsterCard", 400),
				new Effect("incDef", "MonsterCard", 300),
				new Effect("incDef", "MonsterCard", 500),
				new Effect("decDef", "MonsterCard", 400),
				new Effect("incAtkDef", "MonsterCard", 200, 200),
				new Effect("incAtkDef", "MonsterCard", 300, 300),
				new Effect("decAtkDef", "MonsterCard", 200, 200),
				new Effect("draw", "Player", 2),
				new Effect("heal", "Player", 500),
				new Effect("heal", "Player", 1000)
		};
		String[] descriptions = {
				"Megnöveli egy szörny támadópontjait 300-zal.",
				"Megnöveli egy szörny támadópontjait 500-zal.",
				"Csökkenti egy szörny támadópontjait 400-zal.",
				"Megnöveli egy szörny védelmi pontjait 300-zal.",
				"Megnöveli egy szörny védelmi pontjait 500-zal.",
				"Csökkenti egy szörny védelmi pontjait 400-zal.",
				"Megnöveli egy szörny támadó- és védelmi pontjait 200-zal.",
				"Megnöveli egy szörny támadó- és védelmi pontjait 300-zal.",
				"Csökkenti egy szörny támadó- és védelmi pontjait 300-zal.",
				"Húzhatsz 2 lapot a kezedbe a paklidból.",
				"500 életpontot szerzel.",
				"1000 életpontot szerzel.",
		};
		int index = rand.nextInt(effects.length);
		SpellCard ret = new SpellCard(randomName(), descriptions[index], false, player, effects[index]);
		logger.info("Varázslap kigenerálva: " + ret);
		return ret;
	}
	
	/**
	 * Előállít egy véletlenszerű, megadott darabszámú kártyalapokból álló paklit a megadott játékosnak.
	 *  
	 * @param player a pakli tulajdonosa
	 * @param size a pakli mérete
	 * @return véletlenszerű pakli
	 */
	public static Deck generateRandomDeck(Player player, int size) {
		Vector<Card> cards = new Vector<Card>(size);
		int numOfMonstercards = (size*70)/100;
		for (int i = 0; i < numOfMonstercards; i++) {
			cards.add( generateRandomMonsterCard(player) );
		}
		for (int i = 0; i < size-numOfMonstercards; i++) {
			cards.add( generateRandomSpellCard(player) );
		}
		Deck ret = new Deck(cards);
		logger.info("Pakli kigenerálva: " + ret);
		return ret;
	}

}
