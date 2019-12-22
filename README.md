# Ohjelmistotekniikka, harjoitustyö

Tähän repositorioon palautan kurssin *Ohjelmistotekniikka* harjoitustehtäviä.

**Repositorio täydentyy kurssin edetessä**

## WordApp

Sovellus mahdollistaa muinaiskreikan sanaston opiskelun. Maven-projekti löytyy sijainnista [WordApp](https://github.com/jobpurho/ot-harjoitustyo/tree/master/WordApp).

## Dokumentaatio

[Vaatimusmaarittely](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/kayttoohje.md)

[Testausdokumentti](https://github.com/jobpurho/ot-harjoitustyo/tree/master/dokumentointi/testaus.md)

## Releaset

[viikko5](https://github.com/jobpurho/ot-harjoitustyo/releases/tag/viikko5)

[viikko6](https://github.com/jobpurho/ot-harjoitustyo/releases/tag/viikko6)

[loppupalautus](https://github.com/jobpurho/ot-harjoitustyo/releases/tag/loppupalautus)


## Komentorivitoiminnot

### Ohjelman suoritus
```
mvn compile exec:java -Dexec.mainClass=wordapp.Main
```

### Testien suoritus
```
mvn test
```

### Testikattavuusraportti
```
mvn jacoco:report
```

### Jarin generointi
```
mvn package
```

### JavaDoc
```
mvn javadoc:javadoc
```