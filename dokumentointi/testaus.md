# Testausdokumentti

Ohjelmaa on testattu JUnitilla.

## Yksikkö- ja integraatiotestaus

Kaikkia domain -luokkia testataan integraatiotesteillä. Lisäksi dao -luokkia FileLexiconDao ja FileMounceDictionary testataan,

[domain-testit](https://github.com/jobpurho/ot-harjoitustyo/tree/master/WordApp/src/test/java/wordapp/domain)

[dao-testit](https://github.com/jobpurho/ot-harjoitustyo/tree/master/WordApp/src/test/java/wordapp/dao)

### Testauskattavuus

Testien rivikattavuus on 95% ja haarautumiskattavuus 89%.

<img src="https://raw.githubusercontent.com/jobpurho/ot-harjoitustyo/master/dokumentointi/kuvat/sovelluslogiikka.png">

## Järjestelmätestaus

Järjestelmätestaus on tehty manuaalisesti.

### Asennus

Ohjelmaa on testattu Linuxilla. Windowsilla ohjelma ei tällä hetkellä osaa löytää sanakirjatiedostoa.