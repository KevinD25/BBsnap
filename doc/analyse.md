# Analyse

## Functionale analyse project 

### Beschrijving
Voor het vak IoT gaan wij het Blackboard Snapshot project uitwerken. Het project gaat uit verschillende delen bestaan.

De BBSnap wordt een klein toestel dat te bevestigen is tegen het plafond (of een ander oppervlak) en een energiebron bevat (batterijen en/of zonnecel) om de camera en arduino (of raspberry) te voeden. De BBSnap neemt foto’s na aanvraag van oftewel een mobiele applicatie oftewel de BBButton, en stuurt deze daarna door naar een centrale server.

De applicatie, of een web portaal, wordt de interface die leerlingen gaan gebruiken om foto aanvragen door te geven, persoonlijke foto’s te bekijken, of foto’s op te zoeken in de database. Anderzijds kunnen leerkrachten en administrators deze database ook onderhouden, becommentariëren, en verwijderen via deze applicatie/web-interface. Leerkrachten kunnen hier ook instellen of er al dan niet foto’s kunnen genomen worden. 
In de applicatie zullen beperkingen voorzien worden om ervoor te zorgen dat er maar 1 foto kan genomen worden binnen een bepaalde periode om zo te voorkomen dat de database overspoeld wordt met dezelfde soort foto’s.

De BBButton wordt hoofdzakelijk een kleine knop, oftewel vast naast het bord, of als afstandsbediening. Hiermee kan de leerkracht ter plaatsen een foto laten nemen, of de BBSnap deactiveren. Ook wordt er een snelle manier voorzien om het maken van foto’s te deactiveren, door middel van een switch of een andere knop.

Voor de server gaan we een server gebruiken die de foto’s ontvangt en opslaat in een mysql database samen met cursusinformatie uit Untis. De server houd ook de koppeling van BBsnaps, BBButtons en lokalen bij.

### Marktonderzoek 

foto
naam
uitleg

smartphone
Op dit moment worden de foto’s vaak met een smartphone gemaakt omdat de meeste studenten een smartphone op zak hebben.

Spiegelreflex camera
Het afstellen van de foto via lenzen zoals in een spiegel reflex camera is een techniek die we kunnen toepassen bij ons project.

IP Security Camera
Deze toestellen verzenden hun beelden over een netwerk. Dit is iets wat wij ook gaan moeten toepassen.

Bluetooth camera controller
Dit toestel verbindt via bluetooth met een smartphone om een foto te maken. Dit is een techniek die we ook gaan moeten toepassen voor de afstandsbediening van de leerkracht.

Capture
Deze app verbind via wifi met een GoPro camera om zo een foto te kunnen maken via deze Gopro camera. Dit gaan wij ook moeten toepassen op de app van de leerlingen. Ook kunnen alle foto’s bekeken worden via deze app. Dit is een functionaliteit die wij ook willen toepassen.

Infrarood camera controller
Deze controller bedient een camera van Nikon draadloos via infrarood. Dit is een techniek die we kunnen gebruiken voor de communicatie van onze afstandsbediening naar de camera.

Smart WIFI Remote
Deze afstandsbediening verbind via wifi met een GoPro om zo foto’s te kunnen maken. Dit is een techniek die we ook kunnen gebruiken voor de communicatie van onze afstandsbediening naar de camera.

HD Security Camera with Solar Panel supply
Deze HD beveiligingscamera wordt van stroom voorzien via een zonnepaneel die een batterij oplaad. Dit willen wij ook inbouwen in onze camera.

Window Mounted Solar Charger
Dit zonnepaneel wordt via een zuignap aan een venster bevestigd om zo een toestel op te laden. We kunnen ook een zonnepaneel maken die via zuignappen aan een venster wordt bevestigd om zo meer lichtinval te krijgen op het zonnepanneel.

### Diagrammen

#### Algemene architectuur

#### Gedetailleerde diagrammen

#### Schema's van het product

#### Fysiek design (Optioneel)

#### Niet functionele analyse 
Availability
Doordat elke leerling een app heeft en de prof een knop heeft kan er op elk moment een foto worden gemaakt door iedereen. Het toestel zal ook altijd aan (slaapstand) staan en dus beschikbaar zijn om een foto te maken. Dit zorgt ervoor dat de mission capable rate theoretisch gezien 100% is. Dit wordt echter beperkt doordat de leerkracht het toestel op niet beschikbaar kan zetten. Als hij dit doet kan er geen foto worden gemaakt. Als er een foto gemaakt is zal er een kleine delay zijn waarin geen foto’s kunnen worden gemaakt. Dit is om te voorkomen dat er geen meerdere foto’s van dezelfde leerstof in de database staan. Dit zorgt er natuurlijk ook wel voor dat de availability omlaag zal gaan.

Environmental protection
De camera zal een zonnepaneel hebben. Dit zonnepaneel zal een batterij opladen waardoor het systeem volledig zichzelf kan onderhouden. Omdat we gebruik maken groene energie draagt dit product ook mee aan een betere bescherming van het milieu.

Extensibility
In volgende versies kan de camera serieuze upgrades krijgen. Zo kan de camera bijvoorbeeld ook de functie krijgen om als webcam te worden gebruikt. Als de camera ook een webcam is, kan hij worden gebruikt voor het livestreamen van de les. Ook kan er een film optie worden toegevoegd waardoor een stuk van de les opgenomen kan worden. 

Response time (aanpassen als beslist is dat afstandsbediening via wifi zou werken)
Het systeem heeft een hoge response time. Vanaf het moment dat er op de knop of op de app wordt geklikt zal er snel een foto worden gemaakt. Er zal wel een kleine vertraging op zitten en deze zal groter zijn bij de app als bij de afstandsbediening. De afstandsbediening zal een vertraging oplopen omdat het signaal moet worden verzonden van de afstandsbediening naar de camera. Bij de app zal er eerst een bericht naar de server worden gestuurd. De server stuurt dan een bericht door naar de camera die een foto zal nemen. Dit zorgt ervoor dat er een grotere vertraging is bij de app. Als er een foto wordt gemaakt, zal er binnen een kleine tijdspanne geen foto meer kunnen worden gemaakt om te voorkomen dat er dubbele foto’s zijn. Op zulke momenten is er dus geen response. Als de leerkracht het systeem op niet beschikbaar zet is er geen mogelijkheid om foto’s te nemen. Dit zorgt er ook voor dat er geen response is.

## Functionaliteit

## Release Plan 

## Inventarisatie Hardware
Device:
Camera
    Robotshop 
    SPI/I2C interface, vermoedelijk deftige resolutie? Vervangbare lens
Micro SD card reading and writing board
    Robotshop 
    SPI interface 
Micro SD card
Led light
Wifi module (esp 8266)
    Toegang tot internet nodig voor verzenden foto naar server
Infrarood ontvanger
    Sparkfun 
energiezuinig
Button (as reset button)
zonnepaneel / solar cell plaat
    Antratek
Step up converter
Battery regulator
Batterij
Behuizing 

Controller:
2 xButton 
Infrarood zender
behuizing
Batterijvoeding
ATTiny

Server:
Voorlopig raspberry pi

## Inventarisatie Software 
App: React Native / Java
Web framework: React
Servers: Express.js en Node.js
Database: MySQL

## Test document
Testen voor het maken van een foto:
Computer aansluiten aan microcontroller en manueel commando geven
Op knop van afstandsbediening drukken (IR/Bluetooth/Wifi verbinding moet werken)
Op knop van app drukken (wifi verbinding moet werken)

Testen van IR/Bluetooth/Wifi verbinding van camera met afstandsbediening:
Computer aansluiten aan microcontroller en manueel commando geven. Kijken op computer die aangesloten is aan andere microcontroller of er een bestand ontvangen wordt of kijken of er een foto is gemaakt (als deze functionaliteit al werkt)
Op knop op afstandsbediening drukken. Kijken op computer die aangesloten is aan andere microcontroller of er een bestand ontvangen wordt of kijken of er een foto is gemaakt (als deze functionaliteit al werkt)

Testen van verbinding over Wifi tussen camera en app:
Op knop op app drukken. Kijken op computer die aangesloten is aan microcontroller van camera of er een bestand ontvangen wordt of kijken of er een foto is gemaakt (als deze functionaliteit al werkt)
Computer aansluiten aan microcontroller en manueel commando geven. Kijken op pc die aangesloten is aan smartphone of er een bestand wordt ontvangen (als deze functionaliteit er al is).

Testen verbinding app met database:
Manueel data vanuit database versturen. Kijken of app data binnen krijgt op aangesloten pc of op de app zelf (als deze functionaliteit er al is).
Foto’s opvragen vanuit de app (als deze functionaliteit al beschikbaar is)
Hardcoded data versturen vanuit app naar server. Kijken of database data binnen krijgt.
Foto versturen naar database. Kijken of database data binnen krijgt. (als deze functionaliteit al beschikbaar is)

Testen verbinding camera met database
Manueel data vanuit database versturen. Kijken of camera data binnen krijgt op aangesloten pc.
Commando opvragen vanuit database. Kijken of camera data binnen krijgt op aangesloten pc.
Hardcoded data versturen vanuit camera naar server. Kijken of database data binnen krijgt.
Foto versturen naar database. Kijken of database data binnen krijgt. (als deze functionaliteit al beschikbaar is)

Testen verbinding web framework met database:
Manueel data vanuit database versturen. Kijken of web framework data binnen krijgt.
Foto’s opvragen vanuit de app (als deze functionaliteit al beschikbaar is)
Hardcoded data versturen vanuit web framework naar server. Kijken of database data binnen krijgt.
Foto versturen naar database. Kijken of database data binnen krijgt. (als deze functionaliteit al beschikbaar is)

Testen verbinding database met webuntis:
Kijken of data van webuntis in database binnenkomt
Kijken of foto’s bij juiste les staan (als deze functionaliteit al beschikbaar is)

Testen werking zonnepaneel:
Met multimeter spanning meten als batterij is losgekoppeld en er licht aanwezig is. Dit licht moet wel het zonnepaneel energie kunnen geven dus geen licht van bijvoorbeeld een TL-lamp. Tijdens deze testen gaan we het verschil meten tussen de reactie op led,TL of halogeen licht
Kijken of toestel werkt zonder aangesloten batterij.

Testen werking batterij:
Met multimeter spanning meten als zonnepaneel is afgedekt.
Kijken of toestel werkt met afgedekt zonnepaneel.

Testen of er geen dubbele foto’s kunnen worden gemaakt:
2 keer snel achter elkaar foto maken met app en kijken of er maar 1 foto in de database staat
2 keer snel achter elkaar foto maken met afstandsbediening en kijken of er maar 1 foto in de database staat
2 keer snel achter elkaar foto maken met web framework en kijken of er maar 1 foto in de database staat

Testen of camera werkt:
Commando uitvoeren via aangesloten pc en kijken of er data aankomt op de microcontroller van de camera of kijken ofdat er een foto in de database komt te staan.
Foto maken via app en kijken of er data aankomt op de microcontroller van de camera of kijken ofdat er een foto in de database komt te staan.
Foto maken via web framework en kijken of er data aankomt op de microcontroller van de camera of kijken ofdat er een foto in de database komt te staan.
Foto maken via afstandsbediening en kijken of er data aankomt op de microcontroller van de camera of kijken ofdat er een foto in de database komt te staan.

Testen of mogelijkheid tot foto nemen gedeactiveerd kan worden:
Via web interface de mogelijkheid tot foto’s nemen disablen en nagaan of de microcontroller deze commando’s effectief uitvoert.
Foto proberen nemen via applicatie en nagaan of de microcontroller data ontvangt, of er een wijziging gebeurd is in de database.
Foto proberen nemen via web framework en nagaan of de microcontroller data ontvangt, of er een wijziging gebeurd is in de database.
Foto proberen nemen via afstandsbediening en nagaan of de microcontroller data ontvangt, of er een wijziging gebeurd is in de database.

Testen of mogelijkheid tot foto nemen terug geactiveerd kan worden:
Via web interface de mogelijkheid tot foto’s nemen enablen en nagaan of de microcontroller deze commando’s effectief uitvoert.
Foto proberen nemen via applicatie en nagaan of de microcontroller data ontvangt, of er een wijziging gebeurd is in de database.
Foto proberen nemen via web framework en nagaan of de microcontroller data ontvangt, of er een wijziging gebeurd is in de database.
Foto proberen nemen via afstandsbediening en nagaan of de microcontroller data ontvangt, of er een wijziging gebeurd is in de database.


## Verdediging
https://openhomeautomation.net/wireless-camera

ir:
https://maker.pro/ar
