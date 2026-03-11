# Nutikas restorani reserveerimissüsteem

## Kasutatud tehnoloogiad

* **Backend:** Java 21, Spring Boot 3, Gradle
* **Andmebaas:** PostgreSQL
* **Dokumentatsioon:** Swagger (OpenAPI)
* **Frontend:** Vue.js (lisandub hiljem)

## Käivitamine

* **Andmebaasi ettevalmistus:** Veendu, et sul on töös PostgreSQL server. Loo uus tühi andmebaas (nt nimega restaurant_db).
* **Konfigureerimine:** Seadista failis src/main/resources/application.properties järgmised andmed:
* spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
* spring.datasource.username=SINU_KASUTAJA
* spring.datasource.password=SINU_PAROOL
* **Käivitamine:** Liigu projekti juurkausta ja käivita rakendus:
* ./gradlew bootRun
* **Andmete genereerimine:** Andmete genereerimine: Esimesel käivitamisel loob rakendus automaatselt tabelid ja genereerib 
* 14 päeva testandmed
** *Dokumentatsioon:** API-ga tutvumiseks ja päringute testimiseks ava brauseris Swagger UI: http://localhost:8080/swagger-ui/index.html

## Ärireeglid

* **Broneeringu kestus:** Süsteem eeldab, et iga broneering kestab fikseeritult 2 tundi.
* **Broneeringute kellaajad:** Mock-andmed genereeritakse vahemikus 12:00–21:00 pooletunniste sammudega (nt 12:30, 14:00),
* 14 päevaks.
* **Laua mahutavus:** Broneeringut ei lubata luua, kui inimeste arv on suurem kui laua maksimaalne kohtade arv. Lauadade
* nimekiri sorteeritakse vastavalt külaliste arvule, et jagada külalised laudadesse võimalikult õkonoomlselt. 
* **Laudade filtreerimise võimalused:** Klient saab valida, kas ta soovib broneerida ratastooliga ligipääsetavat lauda,
* lauda akna all või lauda privaatses vaheseinaga alas. 
* **Saadavuse kontroll:** Süsteem kontrollib, et samal kellaajal ei oleks laud juba teise broneeringuga hõivatud (arvestades 2h akent).
* **Lisakontroll:** Süsteem ei luba luua broneeringuid, kui kliendiandmed ei ole sisestatud.

## Tulevikuvalmidus

* Kuigi esmane vaade on suunatud kliendile, on backend ehitatud laiendatavust silmas pidades:

* **Admin-liidese valmidus: updateRestaurantTable funktsionaalsus, mis toetab laudade asukohtade (koordinaatide) ja 
* andmete muutmist.
* **Haldusfunktsioonid: API toetab broneeringute kustutamist ja kõigi broneeringute vaatamist, mis on vajalik restorani personalile.

## Arhidektuur

* **Controller-Service-Repository muster**
* **DTO-d ja Mapperid** Entity-objekte ei eksponeerita otse välja. Kasutatakse MapStruct-stiilis mappareid, et teisendada 
* andmed DTO-deks, mis liiguvad kliendi ja serveri vahel.

## Päevik ja ajakulu

### Päev 1

* **Tegevused:** Tegevuskava loomine, Projekti algatamine, PostgreSQL seadistamine, Swaggeri integreerimine, Giti hoidla
  loomine.
* **Ajakulu:** ~ 45 minutit

### Päev 2

* **Tegevused:** Restorani plaani ja filtrite läbimõtlemine
* DB tabelite täitmine andmetega
* entity klasside loomine.
* **Ajakulu:** ~ 3 tundi

### Päev 3

* **Tegevused:** getTables funktsionaalsuse loomine
* silmitsi seismine probleemiga, kus rakendus oli ühendatud vale andmebaasi instanstiga (oli väga õpetlik probleemilahendamine)
* **Ajakulu:** ~ 2 tundi

### Päev 4

* **Tegevused:** updateRestaurantTable funktsionaalsuse loomine, et admin saaks tulevikus muuta laua asukohti või muid andmeid,
* generateMockReservations funktsionaalsuse loomine, kus õppisin ja kasutasin "Random" funktsionaalsust.
* **Ajakulu:** ~ 3 tundi

### Päev 5

* **Tegevused:** getReservations funktsionaalsuse loomine (Algse andmevahetuse kontrollimiseks ja vundamendi loomiseks -
* hiljem saab sinna juurde vajadusel laiendada CRUD loogika adminile)
* getAviailableRestaurantTables funktsionaalsuse loomine, et otsida laudu vastavalt ajale ja külaliste arvule (sealjuures 
õppisin "Stream" funktsionaalsust)
* **Ajakulu:** ~ 2 tundi

### Päev 6

* **Tegevused:** getAviailableRestaurantTables muutus getSuitableRestaurantTables - broneerija eelistuste filtrite lisamine
ja sobivate laudade sorteerimine vastavalt külaliste arvule.
* **Ajakulu:** ~ 1,5 tundi

### Päev 7

* **Tegevused:** Kliendi telefoninumbri lisamine broneeringusse
* Broneeringu kustutamise funktsioon (adminile vajadusel tulevikuks)
* updateRestaurantTable funkstiooni parandus(tagastab nüüd DTO mitte Entity)
* Broneeringu loomine valideerib nüüd ka kliendi nime ja -telefoninumbri olemasolu
* **Ajakulu:** ~ 1,5 tundi

### Päev 8

* **Tegevused:** postReservation funktsionaalsuse ja valideerimisahela loomine - lisasin kontrollid (saadavus, mahutavus),
* et tagada back-endi töökindlus ja välistada vigased broneeringud.
* **Ajakulu:** ~ 1,5 tundi

### Päev 9

* **Tegevused:** MockdataService funktsionaalsuse täiustamine, et ta genereeriks broneeringuid 14 päeva peale (mitte 1 päev)
* README täiendamine ja viimistlus
* **Ajakulu:** ~ 2 tundi

* **Ajakulu kokku ~ 17h 15min** 
* (pidasin oluliseks pigem kvaliteeti ja koodi sisulist mõistmist ja õppimist kui kiirust.)

### AI kasutamine
* AI poolt pakutud lahendused on minu poolt üle kontrollitud, rida-realt läbi kirjutatud ja kohandatud vastavalt projekti spetsiifikale.
*  Kasutasin AI abi eelkõige uute funktsionaalsuste (nt Spring Data JPA päringud, Stream-API) sügavamaks mõistmiseks 
* ja koodi töökindluse kontrollimiseks. Kogu rakenduse arhitektuurne loogika ja ärireeglite defineerimine on minu kontrolli all.

* Abi projekti arhidektuuri planeerimises
* Restorani saaliplaani koordinaatide loogika osas konsulteerimine. 
* Entity klasside sisu valideerimine ja ülevaatus
* Tugi andmebaasiühenduse silumisel ja konfiguratsiooni kontrollimisel
* Konsultatsioon andmete genereerimise (MockDataService) loogika ja ajahalduse osas 
* Java Stream API funktsionaalsuse õppimine
* SearchService' filtreerimisalgoritmi valideerimine
* AI aitas meelde tuletada sort() ja Integer.compare kirjutamise loogikat
* Konsulteerisin AI-ga, et luua existsBy päring ReservationRepository-s. 
* Konsulteerisin AI-ga heade & selgete commit-sõnumite loomiseks
