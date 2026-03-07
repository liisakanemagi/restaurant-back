# Nutikas restorani reserveerimissüsteem - CGI proovitöö

## Kasutatud tehnoloogiad

* **Backend:** Java 21, Spring Boot 3, Gradle
* **Andmebaas:** PostgreSQL
* **Dokumentatsioon:** Swagger (OpenAPI)
* **Frontend:** Vue.js (lisandub hiljem)

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
*generateMockReservations funktsionaalsuse loomine, kus õppisin ja kasutasin "Random" funktsionaalsust.
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
  *et tagada back-endi töökindlus ja välistada vigased broneeringud.
* **Ajakulu:** ~ 1,5 tundi


### AI kasutamine
* **Kasutasin tema abi eelkõige uute funktsionaalsuste (nt Spring Data JPA päringud, Stream-API) sügavamaks mõistmiseks 
* ja koodi töökindluse kontrollimiseks. Kogu rakenduse arhitektuurne loogika ja ärireeglite defineerimine on minu kontrolli all.**

* **Abi projekti arhidektuuri planeerimises** 
* **Restorani saaliplaani koordinaatide loogika osas konsulteerimine.** 
* **Entity klasside sisu valideerimine ja ülevaatus** 
* **Tugi andmebaasiühenduse silumisel ja konfiguratsiooni kontrollimisel** 
* **Konsultatsioon andmete genereerimise (MockDataService) loogika ja ajahalduse osas** 
* **Java Stream API funktsionaalsuse õppimine** 
* **SearchService' filtreerimisalgoritmi valideerimine (ai aitas minu loogika üle vaadata)** 
* **AI aitas meelde tuletada sort() ja Integer.compare kirjutamise loogikat** 
* **Konsulteerisin AI-ga, et luua existsBy päring ReservationRepository-s.** 
* **Konsulteerisin AI-ga heade & selgete commit-sõnumite loomiseks** 