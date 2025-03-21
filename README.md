# Adatbáziselérés JDBC technológiával

## Beugró kérdések
- Ismertesd a JDBC három legfontosabb interfészét, amelyeket egy lekérdezés során használnod kell!
- Mely két metódussal hajthatsz végre lekérdezéseket? Melyiket mikor használod?
- Miért jobb a PreparedStatement a Statementnél?
- Hogyan nyered ki az adatokat a lekérdezés során kapott kurzorból?

## Beadandó

Egy PDF formátumú jegyzőkönyvet kérünk feltölteni legalább a kötelező feladatrészről.

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

1. A `BookDao` osztály konstruktorában nyissunk egy JDBC kapcsolatot az adatbázishoz.
Mivel a program futása során a kapcsolatot végig használni fogjuk, tároljuk tagváltozóban.
A JDBC URL a következő formátumú:
`jdbc:hsqldb:mem:db`

2. Ha a programot először indítjuk, akkor még nem létezik a tábla, amelyben a könyvek
adatait tárolni fogjuk. Ezért a `BookDao` konstruktorában hozzuk is létre.

3. A `BookDao` osztály `close()` metódusában gondoskodjunk a kapcsolat lezárásáról.

### A könyvek listázása - közös feladat

A legelső megvalósítandó művelet a listázás. Módosítsuk úgy a megfelelő metódust, hogy
elvégezze a lekérdezést, majd a kapott adatokból készítsen `Book` objektumokat, és listaként
adja vissza őket. A többi metódus törzsét egyelőre kommentezzük ki.

### Új könyv felvitele - közös feladat

Szeretnénk immár a felület segítségével beszúrni új könyveket. Ehhez írjuk meg a
`persist()` metódust! Próbáljuk is ki a mentést!

> :warning: **Figyelem**: elvárt a biztonsági szempontból is helyes megoldás, azaz SQL injection sebezhetőség nem elfogadható!

### Könyv törlése - kötelező

Valósítsuk meg a `delete()` metódust is, hogy törölni tudjunk! Próbáljuk ki a törlést!

### Könyv módosítása - opcionális

Valósítsuk meg a `save()` metódust! Próbáljuk ki egy meglévő bejegyzés módosítását!
