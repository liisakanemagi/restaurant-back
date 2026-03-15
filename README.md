# Nutikas restorani reserveerimissüsteem

## Kasutatud tehnoloogiad

* **Backend:** Java 21, Spring Boot 3, Gradle
* **Andmebaas:** H2 (in-memory) arenduseks / PostgreSQL valmidus
* **Dokumentatsioon:** Swagger (OpenAPI)
* **Frontend:** Vue.js (lisandub hiljem)

## Käivitamine

Projekt on seadistatud kasutama mälupõhist H2 andmebaasi, mis teeb käivitamise väga lihtsaks.

1.  **Ava terminal** projekti juurkaustas.
2.  **Käivita rakendus** vastavalt oma operatsioonisüsteemile:

    *   **Windows (PowerShell):**
        ```sh
        .\gradlew bootRun
        ```
    *   **Windows (Command Prompt / cmd.exe):**
        ```sh
        gradlew bootRun
        ```
    *   **Linux / macOS:**
        ```sh
        ./gradlew bootRun
        ```
    *Märkus: Kui `bootRun` käsk ebaõnnestub, proovi esmalt `.\gradlew clean` (või `./gradlew clean`), et puhastada vanad ehitusfailid.*

3.  **Andmebaas:** Rakendus loob käivitamisel automaatselt andmebaasi ja tabelid ning impordib algandmed (`create.sql` ja `import.sql`).
4.  **Dokumentatsioon:** API-ga tutvumiseks ja päringute testimiseks ava brauseris Swagger UI:
    * [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
5.  **Andmebaasi konsool:** Andmebaasi sisu vaatamiseks ava H2 konsool:
    * [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    * **JDBC URL:** `jdbc:h2:mem:restaurant_db`
    * **User Name:** `sa`
    * **Password:** (jäta tühjaks)

*Märkus: Kui soovid kasutada PostgreSQL-i, on vastav seadistus `application.properties` failis kommenteeritud kujul olemas.*

## Ärireeglid

* **Broneeringu kestus:** Süsteem eeldab, et iga broneering kestab fikseeritult 2 tundi.
* **Broneeringute kellaajad:** Mock-andmed genereeritakse vahemikus 12:00–21:00 pooletunniste sammudega (nt 12:30, 14:00) 14 päevaks.
* **Laua mahutavus:** Broneeringut ei lubata luua, kui inimeste arv on suurem kui laua maksimaalne kohtade arv. Laudade nimekiri sorteeritakse vastavalt külaliste arvule, et jagada külalised laudadesse võimalikult ökonoomselt.
* **Laudade filtreerimise võimalused:** Klient saab valida, kas ta soovib broneerida ratastooliga ligipääsetavat lauda, lauda akna all või lauda privaatses vaheseinaga alas.
* **Saadavuse kontroll:** Süsteem kontrollib, et samal kellaajal ei oleks laud juba teise broneeringuga hõivatud (arvestades 2h akent).
* **Lisakontroll:** Süsteem ei luba luua broneeringuid, kui kliendiandmed ei ole sisestatud.

## Tulevikuvalmidus

Kuigi esmane vaade on suunatud kliendile, on backend ehitatud laiendatavust silmas pidades:

* **Admin-liidese valmidus:** `updateRestaurantTable` funktsionaalsus, mis toetab laudade asukohtade (koordinaatide) ja andmete muutmist.
* **Haldusfunktsioonid:** API toetab broneeringute kustutamist ja kõigi broneeringute vaatamist, mis on vajalik restorani personalile.

## Arhitektuur

* **Controller-Service-Repository muster**
* **DTO-d ja Mapperid:** Entity-objekte ei eksponeerita otse välja. Kasutatakse MapStruct-stiilis mappareid, et teisendada andmed DTO-deks, mis liiguvad kliendi ja serveri vahel.

## Päevik ja ajakulu

### Päev 1
* **Tegevused:**
    * Tegevuskava loomine ja projekti algatamine.
    * PostgreSQL seadistamine.
    * Swaggeri integreerimine.
    * Giti hoidla loomine.
* **Ajakulu:** ~ 45 minutit

### Päev 2
* **Tegevused:**
    * Restorani plaani ja filtrite läbimõtlemine.
    * DB tabelite täitmine andmetega.
    * Entity klasside loomine.
* **Ajakulu:** ~ 3 tundi

### Päev 3
* **Tegevused:**
    * `getTables` funktsionaalsuse loomine.
    * Silmitsi seismine probleemiga, kus rakendus oli ühendatud vale andmebaasi instantsiga (oli väga õpetlik probleemilahendamine).
* **Ajakulu:** ~ 2 tundi

### Päev 4
* **Tegevused:**
    * `updateRestaurantTable` funktsionaalsuse loomine, et admin saaks tulevikus muuta laua asukohti või muid andmeid.
    * `generateMockReservations` funktsionaalsuse loomine (õppisin ja kasutasin `Random` funktsionaalsust).
* **Ajakulu:** ~ 3 tundi

### Päev 5
* **Tegevused:**
    * `getReservations` funktsionaalsuse loomine (algse andmevahetuse kontrollimiseks ja vundamendi loomiseks - hiljem saab sinna juurde vajadusel laiendada CRUD loogika adminile).
    * `getAviailableRestaurantTables` funktsionaalsuse loomine, et otsida laudu vastavalt ajale ja külaliste arvule (õppisin `Stream` funktsionaalsust).
* **Ajakulu:** ~ 2 tundi

### Päev 6
* **Tegevused:**
    * `getAviailableRestaurantTables` muutus `getSuitableRestaurantTables` - broneerija eelistuste filtrite lisamine ja sobivate laudade sorteerimine vastavalt külaliste arvule.
* **Ajakulu:** ~ 1,5 tundi

### Päev 7
* **Tegevused:**
    * Kliendi telefoninumbri lisamine broneeringusse.
    * Broneeringu kustutamise funktsioon (adminile vajadusel tulevikuks).
    * `updateRestaurantTable` funktsiooni parandus (tagastab nüüd DTO mitte Entity).
    * Broneeringu loomine valideerib nüüd ka kliendi nime ja telefoninumbri olemasolu.
* **Ajakulu:** ~ 1,5 tundi

### Päev 8
* **Tegevused:**
    * `postReservation` funktsionaalsuse ja valideerimisahela loomine - lisasin kontrollid (saadavus, mahutavus), et tagada back-endi töökindlus ja välistada vigased broneeringud.
* **Ajakulu:** ~ 1,5 tundi

### Päev 9
* **Tegevused:**
    * `MockdataService` funktsionaalsuse täiustamine, et ta genereeriks broneeringuid 14 päeva peale (mitte 1 päev).
    * README täiendamine ja viimistlus.
    * Projekti ettevalmistamine teistes arvutites käivitamiseks (H2 andmebaasile üleminek).
* **Ajakulu:** ~ 2 tundi

---
**Ajakulu kokku:** ~ 17h 15min
*(Pidasin oluliseks pigem kvaliteeti ja koodi sisulist mõistmist ja õppimist kui kiirust.)*

### AI kasutamine
AI poolt pakutud lahendused on minu poolt üle kontrollitud, rida-realt läbi kirjutatud ja kohandatud vastavalt projekti spetsiifikale. Kasutasin AI abi eelkõige uute funktsionaalsuste (nt Spring Data JPA päringud, Stream-API) sügavamaks mõistmiseks ja koodi töökindluse kontrollimiseks. Kogu rakenduse arhitektuurne loogika ja ärireeglite defineerimine on minu kontrolli all.

* **Abi projekti arhitektuuri planeerimises.**
* **Restorani saaliplaani koordinaatide loogika osas konsulteerimine.**
* **Entity klasside sisu valideerimine ja ülevaatus.**
* **Tugi andmebaasiühenduse silumisel ja konfiguratsiooni kontrollimisel.**
* **Konsultatsioon andmete genereerimise (`MockDataService`) loogika ja ajahalduse osas.**
* **Java Stream API funktsionaalsuse õppimine.**
* **`SearchService`'i filtreerimisalgoritmi valideerimine.**
* **Abi `sort()` ja `Integer.compare` loogika meeldetuletamisel.**
* **Konsultatsioon `existsBy` päringu loomisel `ReservationRepository`-s.**
* **Konsultatsioon heade ja selgete commit-sõnumite loomiseks.**
