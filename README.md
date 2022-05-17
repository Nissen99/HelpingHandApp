# HelpingHandApp


Formålet med projektet er at lave en app der kan bruges både af folk der står og mangler en hjælpende hånd, men også folk der er villig til at hjælpe til.
Idéen er at hvis man står og skal bruge hjælp kan man lave et opslag. Der vil så være en person i nærheden der kan komme med en hjælpende hånd.
Betaling/lignende vil ikke blive håndteret i appen, men man kan jo i et post skrive hvis man er villig til at betale eller give et stykke pizza og en kold øl. 



I udformelsen af kravene beskrives 2 forskellige type brugere i en transaktion:"Hjælper" og "Søgende".
Hvor en hjælper er en person der leder efter et sted de kan hjælpe.
En søgende er en person der leder efter folk til at hjælpe dem.

Som bruger er man ikke låst i en rolle, men kan skifte på transktions basis.



## Her er  krav i prioriteret rækkefølge: 

1. Som søgende vil jeg oprette et opslag, for at hjælpere kan finde mit problem.     (DONE)

2. Som hjælper vil jeg se en oversigt over opslag, for at finde opslag jeg kan hjælpe ved.   (DONE)

3. Som hjælper vil jeg kontakte søgende, for at svare på opslag.    (DONE)

4. Som bruger vil jeg logge ind i systemet, for at identificere mig.   (DONE)

5. Som søgende vil jeg filtrere listen af opslag så jeg kun ser mine opslag, for kun at se mine egne opslag. (DONE)

6. Som bruger vil jeg filtrere listen af opslag så jeg kun ser opslag jeg har interageret med, for at se de opslag der er mest relevant for mig. (DONE)

7. Som søgende vil jeg sætte hvor mange hjælpere jeg leder efter, for at give hjælpere mere information.

8. Som hjælper vil jeg se hvor mange hjælpere et opslag leder efter, for at se om der er behov for min hjælp.

9. Som søgende vil jeg putte lokation på mine opslag, for at hjælpere nær mig er interesseret.  

10c. Som hjælper vil jeg søge efter opslag på lokation, for at finde opslag nær mig.



## Hvordan appen opfylder krav for projektet:
1. Basic - Det er en app som virker med kode der er nemt at tilføje til.
2. Interaction - Her kan man både oprette posts og kommentarer.
3. Navigation - Her har jeg 3 forskellige views man som bruger hele tiden skifter i mellem + login. Der bliver endda sendt data mellem ShowPost og InspectPost.
4. UX - Her sørger jeg for ikke at have radondante knapper, fx åbner jeg log in med det samme og venter ikke på at brugere vælger det. Jeg bruger også en android template og følger konventionerne der så brugere får en bekendt brugeroplevelse.
5. List - Jeg har lister både i ShowPost og InspectPost, her bruger jeg RecycleWiew.
6. Persistence - Jeg gemmer Post med deres kommentarer, jeg gemmer også display name i databasen, så jeg senere kan få det udfra Uid.
7. Networking - Jeg kunne ikke finde en usecase for dette projekt 
8. Firebase - Jeg bruger firebase log in med AuthUI. Jeg bruger Firebase Realtime database til alt mit data.
9. Quality - Her lærte vi Unit Test lidt for sent til jeg syntes det gav mening. Men alt er black box testet hvor jeg også så vidt muligt har prøvet "ZOMBIE" black box test.
 
