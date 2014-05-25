yugioh-cardgame
===============

A Yu-Gi-Oh! gyüjtögetős kártyajátékon alapuló, JAVA-ban írt játék.

Leírás
--------------------

A program az eredeti játék egyszerűsített szabályait implementálja.

Lehetőség van a véletlenszerűen generált kártyákból álló paklik XML fájlokba való mentésére, azok betöltésére valamint azok törlésére. A mentések a felhasználó home mappáján belül egy **yugiohcg** nevű mappába kerülnek.

A játék indításakor a számítógépes és az emberi játékos közül véletlenszerűen dől el, hogy ki kezd. A játék ezen felül különböző véletlenszerű objektumokat előállító metódusokat alkalmaz, melyeket egy különálló osztály tartalmaz.

A program minden csomagja, minden osztálya, minden mezője és minden tesztosztálya dokumentálva van, és a dokumentáció elérhető JavaDocban.

A program rendelkezik JUnit tesztekkel, melyek a Cobertura alapján jelenleg (0.1-es verzió esetén) közel 60%-os lefedettségűek. A tesztelést nehezíti a játék azon tulajdonságát, hogy bizonyos esetekben szükség van a felhasználói interakciókra.

A játék szabályai
--------------------

### Kezdés

Minden játékos 8000 életponttal és egy 40 darab kártyalapból álló paklival kezd. A játék indításakor mindkét játékos felhúz 5 lapot a kezébe.

### Egy játékos köre

A két játékos felváltva egymás után következik. Egy játékos köre a következő kötött sorrendű fázisokból áll:

#### Húzási fázis (DrawPhase)

Az aktuális játékos felhúz egy lapot a paklija tetejéről. Ha nem tud húzni, mert a paklija már nem tartalmaz lapokat, az aktuális játékos automatikusan veszít.

#### Előkészítő fázis (StandbyPhase)

Előkészítő szakasz, amely jelenleg nem bír funkcionalitással.

#### Fő fázis (MainPhase)

Ebben a fázisban az aktuális játékos a következő lehetőség közül válaszhat tetszőleges mennyiségben egymásután, amíg az lehetséges:

1. Kézben lévő szörnylap **leidézés**e a játékos mezőjére. A szörnylap támadó (*bal kattintás*) és védelmi (*jobb kattintás*) módban is leidézhető. Utóbbi esetben a kártya lefordítva kerül a mezőre és az ellenfél nem láthatja, milyen kártya az. Ha az idézendő szörnylap szintje 5-ös vagy 6-os, szükséges egy darab szörnylapot feláldozni a mezőről és a játékos temetőjébe küldeni. Ha az idézendő szörnylap szintje 7-es vagy nagyobb, kettő áldozatra van szükség. Egy körben egy játékos legfeljebb egy szörnylapot idézhet meg.
2. Kézben lévő varázslap lerakása a játékos mezőjére, **aktiválás**a (egyedi hatásának érvényesítése), majd temetőbe rakása. Egy játékos egy körben korlátlan számú varázslapot aktiválhat.
3. A játékos saját mezőjén lévő szörnyek támadóból védelmi, védelmiből támadó állásba fordítása. Amennyiben le van fordítva, a kártyalap felfordul.

#### Harci fázis (BattlePhase)

Ebben a fázisban az aktuális játékos minden mezőn lévő, támadó állásban lévő szörnylapja támadhat. (*A felhasználó ezt a megfelelő kártyalapokra sorban kattintva érheti el.*) A következő esetek lehetségesek:

1. Az ellenfélnek nincsenek szörnylapjai megidézve. Ekkor az adott szörnylap az ellenfél életpontjait közvetlenül támadhatja meg, azaz a támadó szörny támadópontjainak megfelelő mennyiségű életpontot veszít az ellenfél.
2. Ellenséges szörnylap megtámadása. (*A felhasználó ezt a célpont kijelölésével érheti el.*)

#### Vég fázis (EndPhase)

Az utolsó fázis egy játékos körében. Ha az aktuális játékosnak 6-nál több lapja van annyi lapot kell eldobnia a kezéből a temetőjébe, amíg 6 darab nem lesz a kezében.

### A győztes

1. Ha egy játékos életpontjai a játék bármely pillanatában nulláig vagy az alá csökkennek, az a játékos veszít és a másik nyer.
2. Ha egy játékos nem tud több lapot húzni a paklijából, mert a kártyái elfogytak, az a játékos veszít és a másik nyer.

### A játék kártyatípusai

A játék kétfajta kártyalapot használ:

#### Szörnykártya

Ezek a lapok a következő tulajdonságokkal bírnak:

1. **Név**
2. **Leírás**
3. **Típus:** A szörnylap típusa.
4. **Szint:** A szörnylap megidézésénél van szerepe (1-4: szabadon idézhető, 5-6: egy áldozat, 7-: kettő áldozat)
5. **Támadópontok:** Ezt az értéket kell figyelembe venni harci fázis esetén, ha a szörnykártya támadó állásban van.
6. **Védelmi pontok:** Ezt az értéket kell figyelembe venni harci fázis esetén, ha a szörnykártya védelmi állásban van.

Ha egy szörnylapot legyőznek, a temetőbe kerül. Ha támadó állásban volt, a támadó szörny támadópontjain és a legyőzött szörny támadópontjainak különbségét le kell vonni a legyőzött szörny tulajdonosának életpontjaiból.

#### Varázskártya

Ezek a lapok a következő tulajdonságokkal bírnak:

1. **Név**
2. **Leírás**
3. **Hatás:** Ez az egyedi hatás érvényesül egy másik kártyára aktiválás során.
