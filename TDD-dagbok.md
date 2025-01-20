# Parprogrammering Victor och Kelley
## Ons Jan 8
### Kelley Skolos
Test för en användare ska ha en klass (User)
- Konstruktor med name.
- Attribut: Height, Weight, Age, Name.
- Getter-metoder för alla attribut.

### Victor Ek
Test för en löprunda (Activity)

Konstruktorn för klassen ska ta
- Distans (km)
- Tid (timmar, minuter, sekunder)
- Datum (År-Månad-Dag)
- Om inget datum anges skall dagens datum användas.
- Avancerat: Om Distans eller Tid saknas skall applikationen kasta ett Exception.
- Klassen ska även ha ett attribut för id (String) som ska vara unikt

Vi skapade tid som Duration-objekt och fick exceptions automatiskt när strängen som skickats in var ogiltig. Vi behövde då gå tillbaka till implementationen och göra om den som en String för att se att testet felar först vilket det gjorde.

Jag missade att byta kodgren till en annan när vi började med Activity-klassen så all dagens kod commitades på User-kodgrenen.

Vi lärde oss snabbt att det är viktigt att komma ihåg att skriva @Test före varje test annars felar den bara om det är ett kompileringsfel, för koden i testet aldrig körs.

## Tors Jan 9
### Kelley Skolos
- Lade till activities hashmap och tillhörande tester för User och addActivity

### Victor Ek
- Lade till id i Activity med en static counter som gör att den blir unik för varje activity.
- Exception test som ser till att distance och duration inte kan sättas till 0, då throwar den IllegalArgumentException eller NullpointerException ifall Duration-objektet är null.
- Ändra så att User använder id från activity-objektet som key när det läggs till i hashmappen.
- Lagt till test och metoder för att kunna räkna ut fitnessScore: daysSinceLastActivity, minutesPerKilometer och averageSpeed. 

Det gick väldigt smidigt i början med de grundläggande delarna med test och implementation av konstruktorer och getters och setters, det kändes dock inte som att jag lärde mig så mycket på detta utan att det mest tog tid att skriva.

När jag gjorde VG-delen läste jag om hur man kan använda lombok-library för att med annoteringar autogenerera getters och setters och sedan exkludera de delarna från test och jacoco.

Vi glömde ibland att skriva ett test innan implementationen och fick då gå tillbaka i koden och kommentera bort delar så att testen först felar.


## Fre Jan 10
### Kelley Skolos
- Bytte ut LocalDateTime till LocalDate, tog bort användning av Instant

### Victor Ek
- Skapa test för och implementation av totalDistance, averageDistance och uträkning av fitnessScore. påbörja skriva test för printActivities
- Skrev klart test för printActivities, implementation återstår

## Mån Jan 13
### Kelley Skolos
- Skrev implementation för getActivityById
- Lade till kod för att leda om System.out och System.in till byteArrayStreams (input och output) så att vi kan testa kod som använder System.out.println och scanner.
- lineSeparator behövdes för att vi märkte att radbrytningar i terminalen skiljer sig mellan macOs och windows, med en variabel gjordes testen systemoberoende.

### Victor Ek
- Lade till test och implementation för printActivityById och deleteActivityById
- Skapade app med main och test för createUser. 

### Kelley Skolos
- Skapade testCreateActivity
- Uppdaterade test för User och ändrade Duration input till Duration istället för String

Vi kände oss osäkra på vad vi skulle testa i detta steget, vi hamnade ofta i att vi testade samma saker igen men genom input och output i terminalen istället för direkt via metoderna i User och Activity-klasserna. Att testa detta är svårt och vi fick erfara att det är lätt att göra fel, vi fick felsöka mycket. I slutet på dagen var det en del som inte fungerade och vi kunde inte komma på varför.

## Tis Jan 14
### Kelley Skolos
- Ändrade formateringen av koden för ByteArrayInputStream, korrigera ordningen som vi skicka input från App.java createActivity metoden, tog bort user.addActivity i metoden createActivity och printa istället ut den direkt när den skapats.
- Korrigera formateringen av test i testActivity för toString-metoden.
- Skapade test för att bekräfta att activities finns, implementera en fungerande meny i App.java, översatte en del text, osv.
- Skapade printDetails-metod i App och skrev test för hasActivity.

### Victor Ek
- Flyttade setup från beforeeach till beforeall i TestUser

Vi kom genom diskussion med Max fram till att vi inte hade behövt skriva denna del (App-klassen med en main-metod och in-/output i terminalen) som tagit upp större delen av de senaste två dagarna och lämnade kvar det vi skrivit (som vi till slut fått att fungera skapligt). Var och en av oss fick göra med den vad vi ville när vi fortsätte med VG-delen.

Det hände genomgående i projektet att koden fungerade olika beroende på hur och på vilken dator vi körde den. När vi körde maven test blev några test röda men när vi körde testen direkt i IDEn så var de gröna. Vi fick köra maven clean och maven test före nästan varje gång för att få en JaCoCo-rapport som visade rätt resultat.

# VG-delen
## Tors Jan 16
### Victor Ek
- Bytte namn på activity till record
- Tog bort App and TestApp
- Steg för steg refaktorera till grönt igen. Klar med addRecord, hasRecord, getRecordById, printRecords.
- Uppdatera tests till att använda en mock av fileStorage: hasTotalDistance, printsDetailsWhenGivenIdExist, throwsExceptionWhenRecordIdNotFound, deleteRecordWhenGivenId, throwsExceptionWhenDeletingNonExistingRecord. refactorerade implementation till att använda fileStorage. getTotalDistance återstår

Jag hade först tänkt att jag skulle lämna kvar G-delens kod och skriva helt ny kod för VG-delarna. Jag började med att skapa en tom Record-klass som ärvde från Activity-klassen, jag kunde sedan casta Activity till Record och vice versa. När jag skulle skriva testen som mockade FileStorage så valde jag att refaktorera de befintliga testen, då jag kom på mig själv med att jag inte enkelt kunde undvika att av misstag ha kvar beroenden till den gamla implementationen i den nya. Anledningen var för att det kändes konstigt att ha två implementationer i samma kodbas med stora gemensamma delar så jag bytte namnet på Activity till Record istället.   
Ju mer jag skrev om test till att använda FileStorage-mocken på något sätt ju mer tog jag bort anrop till addActivity som jag hade överallt från G-delen, detta för att user.addActivity inte skulle mockas och om det som addActivity gjorde med mocken behövde bara testas en gång och resterande test för andra delar av koden blev då som en bonus oberoende av implementationen av addActivity.

## Fre Jan 17
### Victor Ek
- Ändrade test och refaktorerade kod för averageDistance, totalDistance och fitnessScore till att använda fileStorage mock, grönt igen.
- lade till test för getFilteredRecords, rött.

## Sön Jan 19
### Victor Ek
- tog bort alla try catch block, förenklade kod
- implementerade och refaktorerade printRecordsFilteredByDistance, grönt.
- 100% kodtäckning (lade till exkludering av FileStorage-dummy för JaCoCo)

Jag hade först en massa felhantering med IOException som inte var en del av kraven för uppgiften som jag behövde skriva test för catch-grenen för att få upp min kodtäckning i JaCoCo. Istället för att skriva test tog jag bort try catch blocken och alla metoder som hanterar FileStorage kasta nu IOException så att standard-exception-hanteraren får dem istället för att jag fångar dem. 

 
