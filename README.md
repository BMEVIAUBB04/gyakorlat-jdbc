# Adatbáziselérés JDBC technológiával

## Beugró kérdések
- Ismertesd a JDBC három legfontosabb interfészét, amelyeket egy lekérdezés során használnod kell!
- Mely két metódussal hajthatsz végre lekérdezéseket? Melyiket mikor használod?
- Miért jobb a PreparedStatement a Statementnél?
- Hogyan nyered ki az adatokat a lekérdezés során kapott kurzorból?

## 2021-ben távoktatási instrukciók

2021 tavasszal, amennyiben a gyakorlat (még/már/továbbra is) távoktatásban kerül terítékre, az alábbit kérjük.

- Otthoni teljesítés esetén nincs beugró. A fenti kérdésekkel ennek ellenére is
  ellenőrizd a tudásod.
- Dokumentáld a gyakorlatot és a dokumentációt ("jegyzőkönyvet") add be. A
  jegyzőkönyvben azt várjuk, hogy bizonyítod a feladatok elvégzését. Minden
  feladatnál készíts egy képernyőképet a feladat eredményéről. Ne a kódot fényképezd le,
  hanem keress módot arra, hogy bemutasd, működik a megoldásod. Például, mutasd meg,
  hogy a kódodat hogyan hívod meg, és utána mi az eredménye - bekerül egy rekord a
  táblába, vagy hasonló. Minden feladatról elég egyetlen kép, ha az egyben tudja
  az eredményt mutatni. A gyakorlat akkor minősül teljesítettnek, ha a három önálló
  feladatból kettőt elvégeztél, és dokumentáltad.

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

0. Indítsuk el az SQL Servert lokálisan telepítve vagy pedig Dockerből:
`docker run -e ACCEPT_EULA=Y -e MSSQL_SA_PASSWORD=Hatteralk2021 -p 1433:1433 mcr.microsoft.com/mssql/server:2017-latest`
   
Töltsük le, és indítsuk el az Eclipse fejlesztőkörnyezetet:
https://www.eclipse.org/downloads/packages/release/2020-12/r/eclipse-ide-java-developers

Importáljuk a repository-ból a vázat.

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

### A könyvek listázása - közös feladat

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
