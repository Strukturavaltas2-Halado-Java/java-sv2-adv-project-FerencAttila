# Roller Nest Boxes

![Roller nestlingss in a nestbox](/img/roller.jpg)
Andy Morffew [CC-BY-2.0](https://commons.wikimedia.org/wiki/Category:CC-BY-2.0)

Az alkalmazás célja a szalakóta odúk nyilvántartásának kezelése, valamint a terepen gyűjtött információk
rendszerezése. Az elmúlt évek során kiforrott kihelyezési és ellenőrzési stratégia adminisztrációjának
egyszerűsítése mellett a gyakori hibákra tartalmaz validációkat. Az eddigi adatelemzések folyamatos
naparakész megvalósítását API hívásokon keresztül is támogatja, illetve lehetővé teszi az adatok egyszerű
integrálását a meglévő adattároló rendszerekben (Biotika).

Az alkalmazás API felületén keresztül lehet feltölteni az új odúkihelyezéseket, valamint a költések
ellenőrzése során gyűjtött adatokat.

## Entitások

### Odú (NestBox)

Feladata a kihelyezett odúkkal kapcsolatos aktuális információk kezelése, tárolása.

![UML diagram of the NestBox entity](/img/nest_box.jpg)

| HTTP metódus | Végpont                 | Leírás                                                              |
|--------------|-------------------------|---------------------------------------------------------------------|
| GET          | `"/api/nest-boxes/all"` | lekérdezi az összes odút, visszaadja egy listában                   |
| GET          | `"/api/nest-boxes`      | lekérdez egy odút `nestBoxNumber` alapján                           |
| POST         | `"/api/nest-boxes`      | létrehoz egy odút                                                   |
| PUT          | `"/api/nest-boxes`      | frissít egy odút `nestBoxNumber` alapján, a megadott paraméterekkel |
| DELETE       | `"/api/nest-boxes`      | töröl egy odút `nestBoxNumber` alapján                              |

### Fészkelés (Nest)

Az egyes odúkban regisztrált fészkelések adatainak kezeléséért felelős.

![ER diagram of the database](/img/roller_nestboxes_database.jpg)


