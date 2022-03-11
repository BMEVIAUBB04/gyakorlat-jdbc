# Adatbáziselérés JDBC technológiával

## Beugró kérdések
- Ismertesd a JDBC három legfontosabb interfészét, amelyeket egy lekérdezés során használnod kell!
- Mely két metódussal hajthatsz végre lekérdezéseket? Melyiket mikor használod?
- Miért jobb a PreparedStatement a Statementnél?
- Hogyan nyered ki az adatokat a lekérdezés során kapott kurzorból?

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

1. Csatlakozzunk az adatbázishoz `localhost` címre, `SQL Server Authentication` módszer `sa` felhasználóval és jelszóval,
és hozzunk létre egy logikai adatbázist, amelybe dolgozni
fogunk. Ügyeljünk rá, hogy a neve legyen egyedi, pl.
`CREATE DATABASE <neptun>_jdbc;`

2. Hogy a Microsoft SQL Serverrel kommunikálni tudjunk, szükség van a JDBC driverére.
Nyissuk meg a projekt `pom.xml` konfigurációs fájlját, és vegyük fel függőségként.

3. A `BookDao` osztály konstruktorában nyissunk egy JDBC kapcsolatot az adatbázishoz.
Mivel a program futása során a kapcsolatot végig használni fogjuk, tároljuk tagváltozóban.
A JDBC URL a következő formátumú:
`jdbc:sqlserver://localhost;database=<neptun>_jdbc;trustServerCertificate=true`

4. Ha a programot először indítjuk, akkor még nem létezik a tábla, amelyben a könyvek
adatait tárolni fogjuk. Ezért a `BookDao` konstruktorában hozzuk is létre.

5. A `BookDao` osztály `close()` metódusában gondoskodjunk a kapcsolat lezárásáról.

### A könyvek listázása - közös feladat

A legelső megvalósítandó művelet a listázás. Módosítsuk úgy a megfelelő metódust, hogy
elvégezze a lekérdezést, majd a kapott adatokból készítsen `Book` objektumokat, és listaként
adja vissza őket. A többi metódus törzsét egyelőre kommentezzük ki.

Most szúrjunk be kézzel adatokat a táblába, és vizsgáljuk meg, hogy működik-e a listázás!

### Új könyv felvitele - kötelező

Szeretnénk immár a felület segítségével beszúrni új könyveket. Ehhez írjuk meg a
`persist()` metódust! Próbáljuk is ki a mentést!

> :warning: **Figyelem**: elvárt a biztonsági szempontból is helyes megoldás, azaz SQL injection sebezhetőség nem elfogadható!

### Könyv törlése - opcionális

Valósítsuk meg a `delete()` metódust is, hogy törölni tudjunk! Próbáljuk ki a törlést!

### Könyv módosítása - opcionális

Valósítsuk meg a `save()` metódust! Próbáljuk ki egy meglévő bejegyzés módosítását!

## Segítség otthoni munkához

A megoldás otthoni folytatásához szükség lesz az SQL Serverre. Ezt Dockerből igen könnyen el lehet indítani, pl. az alábbi módon:

`docker run -e ACCEPT_EULA=Y -e MSSQL_SA_PASSWORD=Hatteralk2021 -p 1433:1433 mcr.microsoft.com/mssql/server:2017-latest`

Figyelni kell viszont arra, hogy ekkor az `sa` felhasználóhoz tartozó jelszó így `Hatteralk2021` lesz, ezt módosítani kell a JDBC URL-ben is. Illetve a futó adatbázisban a sémának ekkor is léteznie kell, így először itt is csatlakozni kell hozzá valamilyen kliensprogrammal, pl. az SQL Management Studioval.
