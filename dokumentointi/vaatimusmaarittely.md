# Vaatimusmäärittely


## Sovelluksen tarkoitus
Sovellus on tarkoitettu teologeille klassisten kielten sanaston opiskelua varten. Sovellus antaa opiskeltavan kielen sanan ja käyttäjä yrittää kirjoittaa oikean merkityksen. Opiskeltava kieli on perusversiossa muinaiskreikkaa ja vastaukset ovat englantia. Perusversiossa sanastossa on vain muutamia sanoja, jotta ohjelman toiminnan kokeilu olisi helppoa.

## Käyttäjät
Sovelluksella on vain yhdenlaisia käyttäjiä.

## Perusversion toiminnallisuus
- Käyttäjä voi valita, aloittaako opiskelun alusta vai jatkaako siitä, mihin viimeksi jäi
- Käyttäjä antaa käännösehdotuksen ja ohjelma kertoo, onko se oikein. Jos se ei ole oikein, mahdolliset oikeat vastaukset näytetään ja sanaa kysytään myöhemmin uudestaan.
- Käyttäjä voi tallentaa tilanteen niin, että seuraavalla kerralla sovelluksen avatessa hänellä on opiskeltavana viime kerran oikeiden vastausten rajaama sanasto.
- Perusversiossa on tekstikäyttöliittymä

## Jatkokehitys

Perusversion jälkeen lisätään seuraavat toiminnallisuudet ajan antamissa rajoissa:
- Graafinen käyttöliittymä
- Sanastona on laajempi kreikka-englanti -sanasto, joka on ladattu ja muokattu täältä: https://github.com/billmounce/dictionary/blob/master/dictionary.txt
- Oppimisen tehostamiseksi sanaa kysytään uudestaan myöhemmin, vaikka vastaus olisi oikein.
- Vastauksen nopeus vaikuttaa siihen, kuinka monta kertaa kysymykseen pitää vastata uudestaan oikein ennen kuin sana poistuu kysyttävistä sanoista.
- Käyttäjä voi valita opiskeltavan sanaston laajuuden (yllä mainitussa sanastossa on kerrottu sanojen esiintyvyys tietyssä korpuksessa, mitä käytetään rajaukseen).
- Sanojen yhteyteen voi tallentaa vihjeitä.
- Vastaus voi olla oikein, vaikka siinä olisi pieni kirjoitusvirhe.

Seuraavia muutoksia voidaan tehdään sellaisissa rajoissa, että tietokantatauluja tai tiedostoja ei tule liikaa:
- Vaihtoehtoisiksi opiskeltaviksi kieliksi lisätään Heprea ja Latina
- Käyttäjä voi luoda omia sanastoja, joihon voi kirjoittaa sanoja ja niiden mahdollisia käännöksiä
- Sanaston voi lisätä CSV-tiedostosta (se käyttäjän on täytynyt tehdä jotenkin muuten)
- Harjoiteltavan sanaston voi valita ja jokaista varten on tallennettu tilanne, mihin on aiemmin päästy
