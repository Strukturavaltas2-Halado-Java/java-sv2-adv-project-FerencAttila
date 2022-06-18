# Roller Nest Boxes
(2022-06-18)

![Roller nestlingss in a nestbox](/img/roller.jpg)

Andy Morffew [CC-BY-2.0](https://commons.wikimedia.org/wiki/Category:CC-BY-2.0)

Az alkalmazás célja a szalakóta odúk nyilvántartásának kezelése, valamint a terepen gyűjtött információk
rendszerezése. Az elmúlt évek során kiforrott kihelyezési és ellenőrzési stratégia adminisztrációjának
egyszerűsítése mellett a gyakori hibákra tartalmaz validációkat. Az eddigi adatelemzések folyamatos
naparakész megvalósítását API hívásokon keresztül is támogatja, illetve lehetővé teszi az adatok egyszerű
integrálását a meglévő adattároló rendszerekben (Biotika).

Az alkalmazás API felületén keresztül lehet feltölteni az új odúkihelyezéseket, valamint a költések
ellenőrzése során gyűjtött adatokat.

_A dokumentációban szereplő, alkalmazás által biztosított url természetesen helyi gépen történő futtatás
esetén érvényesek. A 'http://localhost:8080' rész helyett mindig az aktuális kiszolgáló url-jét és a
megfelelő portszámot kell behelyettesíteni._

![ER diagram of the database](/img/roller_nestboxes_database.jpg)

## Entitások

### Odú (NestBox)

Feladata a kihelyezett odúkkal kapcsolatos aktuális információk kezelése, tárolása.

![UML diagram of the NestBox entity](/img/nest_box_uml.jpg)

Az adatbázisban alkalmazott elvárások az egyes attribútumokkal kapcsolatban:

* nest_box_id: primary key, auto increment, not null
* nest_box_number: unique, not null,
* eov_x: not null,
* eov_y: not null

| HTTP metódus | Végpont               | Leírás                                                              |
|--------------|-----------------------|---------------------------------------------------------------------|
| GET          | `/api/nest-boxes/all` | lekérdezi az összes odút, visszaadja egy listában                   |
| GET          | `/api/nest-boxes`     | lekérdez egy odút `nestBoxNumber` alapján                           |
| POST         | `/api/nest-boxes`     | létrehoz egy odút                                                   |
| PUT          | `/api/nest-boxes`     | frissít egy odút `nestBoxNumber` alapján, a megadott paraméterekkel |
| DELETE       | `/api/nest-boxes`     | töröl egy odút `nestBoxNumber` alapján                              |

Példa url, bean validáció és mintadatok az egyes végpontokon:

`/api/nest-boxes/all` (GET):

GET http://localhost:8080/api/nest-boxes/all

accept:

    [
    {
    "nestBoxNumber": "1600",
    "coordinates": {
    "eovX": 764872,
    "eovY": 271458
    },
    "direction": "SW",
    "height": 9.5
    },
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "direction": "N",
    "height": 3.5
    }
    ]

Response code: 200

`/api/nest-boxes` (GET):

GET http://localhost:8080/api/nest-boxes?nest-box-number=1600

accept:

    {
    "nestBoxNumber": "1600",
    "coordinates": {
    "eovX": 764872,
    "eovY": 271458
    },
    "direction": "SW",
    "height": 9.5
    }

Response code: 200

`/api/nest-boxes` (POST):

POST http://localhost:8080/api/nest-boxes

nestBoxNumber:
- egyedi (adatbázisban nem szerepelhet),
- nem lehet üres és null

eovX:
- minimum érték: 426400
- maximum érték: 937500
- nem lehet null

eovY:
- minimum érték: 43000
- maximum érték: 361400
- nem lehet null

direction:
- a Quarter enum valamely értékét veheti fel: N, NE, E, SE, S, SW, W, NW
- lehet null

height:
- minimum érték: 1
- maximum érték 10
- nem lehet null

content:

    {
    "nestBoxNumber": "1485",
    "coordinatesCommand": {
    "eovX": 758615,
    "eovY": 257498
    },
    "direction": "N",
    "height": 3.5
    }

accept:
    
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "direction": "N",
    "height": 3.5
    }

Response code: 201

`/api/nest-boxes` (PUT):

PUT http://localhost:8080/api/nest-boxes

nestBoxNumber:
- egyedi (adatbázisban nem szerepelhet),
- nem lehet üres és null

direction:
- a Quarter enum valamely értékét veheti fel: N, NE, E, SE, S, SW, W, NW
- lehet null

height:
- minimum érték: 1
- maximum érték 10
- nem lehet null

content:

    {
    "nestBoxNumber": "1600",
    "direction": "NE",
    "height": 6.5
    }

accept:

    {
    "nestBoxNumber": "1600",
    "coordinates": {
    "eovX": 764872,
    "eovY": 271458
    },
    "direction": "NE",
    "height": 6.5
    }

Response code: 200

`/api/nest-boxes` (DELETE):

DELETE http://localhost:8080/api/nest-boxes?nest-box-number=1600

Nem lehet törölni olyan odút, amelyhez fészkelések tartoznak.  
Nem ad vissza semmilyen adatot.

Response code: 204

### Fészkelés (Nest)

Az egyes odúkban regisztrált fészkelések adatainak kezeléséért felelős.

![UML diagram of the Nest entity](/img/nest_uml.jpg)

Az adatbázisban alkalmazott elvárások az egyes attribútumokkal kapcsolatban:

* nest_id: primary key, auto increment, not null
* nest_box_id: foreign key, not null
* date_of_survey: not null
* observer: not null

| HTTP metódus | Végpont             | Leírás                                                                                      |
|--------------|---------------------|---------------------------------------------------------------------------------------------|
| GET          | `/api/nests`        | lekérdezi az összes fészkelést, illetve odúszám és/vagy költő faj szerint szűr              |
| GET          | `/api/zoology-data` | lekérdezi az összes fészkelkést, a zoológiai adatbázisnak megfelelő formátumban adja vissza |
| POST         | `/api/nests`        | létrehoz egy fészkelést terepen gyűjtött adatok alapján                                     |                                                 |

Példa url, bean validáció és mintadatok az egyes végpontokon:

`/api/nests` (GET):

Összes fészkelés lekérdezése:

GET http://localhost:8080/api/nests

accept:

    [
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2015-06-18",
    "species": "Corvus monedula",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    },
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2022-06-18",
    "species": "Coracias garrulus",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    }
    ]

Response code: 200

Fészkelések odúszám alapján szűrve:

GET http://localhost:8080/api/nests?nest-box-number=1485

accept:

    [
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2015-06-18",
    "species": "Corvus monedula",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    },
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2022-06-18",
    "species": "Coracias garrulus",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    }
    ]

Response code: 200

Fészkelések faj alapján szűrve:

GET http://localhost:8080/api/nests?species=Corvus%20monedula

    [
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2015-06-18",
    "species": "Corvus monedula",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    },
    {
    "nestBoxNumber": "1600",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2015-06-18",
    "species": "Corvus monedula",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    }
    ]

Response code: 200

Fészkelések odúszám és faj alapján szűrve:

GET http://localhost:8080/api/nests?nest-box-number=1485&species=Corvus%20monedula

    [
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2022-06-18",
    "species": "Coracias garrulus",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    },
    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2019-06-18",
    "species": "Coracias garrulus",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    }
    ]

Response code: 200

`/api/nests` (POST):

POST http://localhost:8080/api/nests

Nem tölthető fel olyan fészkelés, ahol az odúszám, a dátum és az adatközlő kombinációja megegyezik egy,
már az adatbázisban szereplő rekorddal. 

nestBoxNumber (odúszám):
- léteznie kell az adatbázisban
- nem lehet üres és null

dateOfSurvey (adatgyűjtés dátuma):
- nem lehet jövőbeli
- nem lehet null

species (fészkelő faj):
- minimum 5 karakter hosszú
- maximum 45 karakter hosszú
- lehet null

numberOfNestlings (tojások és/vagy fiókák száma):
- pozitív vagy nulla értéket vehet fel
- maximum 20

observer (adatközlő):
- nem lehet üres és null
- minimum 5 karakter hosszú
- maximum 100 karakter hosszú

content:

    {
    "nestBoxNumber": "1485",
    "dateOfSurvey": "2022-06-18",
    "species": "Coracias garrulus",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    }

accept:

    {
    "nestBoxNumber": "1485",
    "coordinates": {
    "eovX": 758615,
    "eovY": 257498
    },
    "dateOfSurvey": "2015-06-18",
    "species": "Corvus monedula",
    "numberOfNestlings": 4,
    "observer": "John Doe"
    }

Response code: 201

## Alkalmazott technológiák

Az alkalmazás egy tipikus három rétegű alkalmazás, amely két controller rétegen keresztül valósítja meg a
kommunikációt. Az innen érkező adatok egyetlen service rétegben találkoznak, ahol az üzleti logika valósul
meg. A két entitás önálló repository rétegeken keresztül csatlakozik a MariaDB adatbázishoz.

Az alkalmazás docker környezetben is futtatható. Ehhez először el kell készíteni az alkalmazás build-jét
majd a docker mappában ki kell adni a `docker-compose up -d` parancsot. Enek hatására három konténer indul
el. Egy a production adatbázis, amelyhez az alkalmazás  a `jdbc:mariadb://localhost:3306/roller`
hivatkozáson kesresztül csatlakozik. A tesztesetek futtatásához önálló MariaDb adatbázis áll rendelkezésre, a
`jdbc:mariadb://localhost:3307/roller-test` címen. Maga az alkalmazás alapértelmezetten docker futtatása
esetén is a 8080-as porton keresztül érhető el.

### Spring keretrendszer

Az alkalmazás a Spring keretrendszer 2.7.0 verziójával készült.

Fontosabb spring függőségek:

Production:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation

Test:

- Spring Boot Starter Webflux
- Spring Boot Starter Test

További alkalmazott könyvtárak:

- Lombok
- MapStruct (1.5.1.Final)
- Zalando Problem Spring Web Starter (0.27.0)


### Adatbázis

Az alkalmazás az adatokat MariaDb adtabázis 10.8-as verzióján futó adatbázisba menti.
Az adatbázis migrálását FlyWay migrácuós eszközzel valósítottam meg.

### OpenApi documentáció

Az API dokumentáció grafikus verziója a Swagger UI technológiát használja és az alkalmazás futtatásakor
elérhető a http://localhost:8080/swagger-ui/index.html címen. Ehhez a Springdoc OpenApi UI 1.6.9-es
verzióját használtam.

A json openapi dokumentum a http://localhost:8080/v3/api-docs címen érhető el, szintén az alkalmazás
futtatásakor.
