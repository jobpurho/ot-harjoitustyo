# Ohjelmistotekniikka, harjoitustyö

Tähän repositorioon palautan kurssin *Ohjelmistotekniikka* harjoitustehtäviä.

**Repositorio täydentyy kurssin edetessä**

# WordApp

Sovellus mahdollistaa muinaiskreikan sanaston opiskelun. Maven-projekti löytyy sijainnista [WordApp](https://github.com/jobpurho/ot-harjoitustyo/tree/master/WordApp).

# Dokumentaatio

[Vaatimusmaarittely](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/arkkitehtuuri.md)

# Releaset
[viikko5](https://github.com/jobpurho/ot-harjoitustyo/releases/tag/viikko5)

# Komentorivitoiminnot
ohjelman suoritus:
```
mvn compile exec:java -Dexec.mainClass=wordapp.Main
```

testit:
```
mvn test
```

testikattavuus:
```
mvn jacoco:report
```

jarin generointi:
```
mvn package
```