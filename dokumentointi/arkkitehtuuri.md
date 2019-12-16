# Arkkitehtuurikuvaus

## Rakenne

Ohjelma koostuu pakkauksista wordapp.dao, wordapp.domain ja wordapp.ui. Ensimmäinen vastaa tiedostojen lukemisesta sekä tallentamisesta ja niiden palauttamisesta domain-luokille sopivassa muodossa. Domain vastaa sovelluslogiikasta ja ui käyttöliittymästä.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erilaista näkymää. Aluksi on näkymä, jossa voi aloittaa uuden opiskelurupeaman tai palata tallennettuun. Jos aloittaa uuden, päätyy näkymään, jossa valitaan opiskeltavan sanaston laajuus. Kolmantena on varsinainen opiskelunäkymä, jossa ohjelma näyttää sanan, jonka käyttäjä yrittää kääntää. Oikeasta tai väärästä vastauksesta tulee ilmoitus.

## Sovelluslogiikka

<img src="https://raw.githubusercontent.com/jobpurho/ot-harjoitustyo/master/dokumentointi/kuvat/sovelluslogiikka.png">

## Tiedostojen käsittely

### Sanaston luominen

Ohjelma luo sanaston, joka sisältää kysyttävän sanan käännökset. Sanaston luo FileMounceDictionary, joka lukee sanakirjatiedostoa ja muokkaa ja tallentaa sen sopivaksi tietorakenteeksi. Luokka toteuttaa rajapinnan OriginalLexicon, jonka toteutukset palauttavat sellaisen sanaston, jota ohjelma voi käyttää.

<img src="https://raw.githubusercontent.com/jobpurho/ot-harjoitustyo/master/dokumentointi/kuvat/sanakirjasekvenssi.png">

Kun sanasto on luettu tiedostosta, edellisessä kaaviossa esiintyvä createNew() -metodi luo pelin perusominaisuuksista vastaavan WordStudy -olion. Se saa parametrikseen tiedostosta luetun sisällön ja luo Lexicon -olion, jolla on kyseinen sisältö.

<img src="https://raw.githubusercontent.com/jobpurho/ot-harjoitustyo/master/dokumentointi/kuvat/sanakirjasekvenssi2.png">

### Tallennus

Tallennus tapahtuu siten, että Lexicon -olion jäljellä oleva HashMap -muotoinen sisältö tallennetaan sellaisenaan "saved.ser" -tiedostoon. Tallennuksesta vastaa FileLexiconDao -luokka.

## Merkkijonovertailu

WordStudy -olio käyttää myös StringComparison -oliota, joka kertoo, onko vastaus riittävän samanlainen jonkin mahdollisen oikean vastauksen kanssa.