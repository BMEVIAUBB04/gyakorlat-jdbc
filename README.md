# Adatbáziselérés JDBC technológiával

## Beugró kérdések
- Ismertesd a JDBC három legfontosabb interfészét, amelyeket egy lekérdezés során használnod kell!
    - Connection: kapcsolat felépítése
    - Statement: lekérdezés létrehozása
    - ResultSet: a kurzorobjektum, ezzel dolgozhatók fel a visszakapott sorok

- Mely két metódussal hajthatsz végre lekérdezéseket? Melyiket mikor használod?
    - executeQuery(): amikor adatot kérdezek le (SELECT); egy ResultSet típusú objektumot ad vissza, ebből nyerhetők ki a sorok
    - executeUpdate(): egyéb adatbázis-műveletekhez használható, amelyek nem adnak vissza eredményt (INSERT, UPDATE, DELETE stb.)
    
- Miért jobb a PreparedStatement a Statementnél?
    - Eltárolja a végrehajtási tervet, és ezért a későbbi lefuttatások alkalmával gyorsabban fut le.
    - Segítségével paramétereket helyettesíthetünk be biztonságosan egy lekérdezésbe, ezzel elkerülhetjük az SQL injection típusú sebezhetőségeket. 
    
- Hogyan nyered ki az adatokat a lekérdezés során kapott kurzorból?
    - A kurzor egy `ResultSet` típusú objektum.
    - Kezdetben a kurzor az első sor előttre van pozicionálva, nem hivatkozik semmire.
    - A next() metódussal lehet mindig a következő sorra váltani, ha van. Ezt `boolean` típusú visszatérési értékében jelzi. Az első sor feldolgozásához is meg kell hívni egyszer.
    - Külöböző get metódusokkal kérjük el az adatokat, attól függően, hogy milyen típusú adatot akarunk elkérni, pl. a getInt() egész számokat kér el. Ezeknek vagy az oszlop sorszámát vagy a nevét kell megadnunk, pl. `rs.getInt(1)` vagy `rs.getInt("id")`.

## A feladatok

### Az alkalmazás megismerése

A labor során egy könyvkatalógus alkalmazást fogunk továbbfejleszteni. Nézzük meg,
hogyan működik az alkalmazás! Vizsgáljuk meg a `BookDao` osztályt! Ez az osztály a felelős
az adatokon végzett műveletekért. Érdemes az adatbázis-műveleteket mindig ehhez hasonlóan
egy osztályba szervezni (ld. DAO minta). Jelenleg azonban ez az osztály csupán egy listában
tárolja az adatokat. A labor során úgy fogjuk módosítani az alkalmazást, hogy éles adatbázisba
dolgozzon. A feladatok megoldásához csupán a `BookDao` osztállyal kell dolgoznunk, a kód
többi részét nem szükséges módosítani.

### Az előkészületek

1. Csatlakozzunk az adatbázishoz, és hozzunk létre egy logikai adatbázist, amelybe dolgozni
fogunk. Ügyeljünk rá, hogy a neve legyen egyedi, pl.
`CREATE DATABASE <neptun>_jdbc;`

2. Hogy a Microsoft SQL Serverrel kommunikálni tudjunk, szükség van a JDBC driverére.
Nyissuk meg a projekt `pom.xml` konfigurációs fájlját, és vegyük fel függőségként.

3. A `BookDao` osztály konstruktorában nyissunk egy JDBC kapcsolatot az adatbázishoz.
Mivel a program futása során a kapcsolatot végig használni fogjuk, tároljuk tagváltozóban.
A JDBC URL a következő formátumú:
`jdbc:sqlserver://localhost;database=hatter`

4. Ha a programot először indítjuk, akkor még nem létezik a tábla, amelyben a könyvek
adatait tárolni fogjuk. Ezért a `BookDao` konstruktorában hozzuk is létre. Ügyeljünk rá,
hogy ha a tábla mégis létezik, akkor se kapjunk hibát. (`CREATE TABLE IF NOT EXIST`)

5. A `BookDao` osztály `close()` metódusában gondoskodjunk a kapcsolat lezárásáról.

### A könyvek listázása

A legelső megvalósítandó művelet a listázás. Módosítsuk úgy a megfelelő metódust, hogy
elvégezze a lekérdezést, majd a kapott adatokból készítsen `Book` objektumokat, és listaként
adja vissza őket. A többi metódus törzsét egyelőre kommentezzük ki.

Most szúrjunk be kézzel adatokat a táblába, és vizsgáljuk meg, hogy működik-e a listázás!

### Új könyv felvitele

Szeretnénk immár a felület segítségével beszúrni új könyveket. Ehhez írjuk meg a
`persist()` metódust! Próbáljuk is ki a mentést!

### Könyv törlése

Valósítsuk meg a `delete()` metódust is, hogy törölni tudjunk! Próbáljuk ki a törlést!

### Könyv módosítása

Valósítsuk meg a `save()` metódust! Próbáljuk ki egy meglévő bejegyzés módosítását!
