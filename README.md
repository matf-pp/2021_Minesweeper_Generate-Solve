# 2021_Minesweeper_GenerateAndSolve

## Inspiracija za ovaj projekat

Inspiracija za ovaj projekat je igrica Minesweeper.Cilj igre je da se otvore sva polja bez detoniranja mina.Postoje tri stanja polja: otvoreno, neotvoreno i flagged (postavljena zastavica na tom polju).Zastavice nam sluze kao pomoc da odredimo gde se nalaze mine i njihov broj je jednak broju mina.Svako otvoreno polje koje nije mina sadrzi podatak o tome koliko mina se nalazi u susednim poljima.Detaljnije o igri na sledecem linku: https://en.wikipedia.org/wiki/Minesweeper_(video_game).

## O projektu

Ovaj projekat se sastoji iz *dva dela*:
* Nasumicni generator mape za igru Minesweeper koju korisnik moze da resava
* Automatski resavac za nasumicno generisanu mapu

Za oba dela postoje tri *nivoa tezine* koji se mogu izabrati: Beginner, Intermediate i Advanced. U zavisnosti od nivoa tezine, odredjuju se dimenzije table za igru, kao i broj mina koje treba pronaci.
![Alt text](/Users/sanjamenjakmaksimovic/Desktop/prviprozor.png?raw=true)

### Generator

Igracu se dopusta da odabere prvo polje na tabli. To polje ne sme biti mina. Zatim, generisu se nasumicne koordinate X i Y, i na taj nacin se postavljaju mine na tabli. Nakon toga se sva ostala polja azuriraju tako da u sebi imaju broj koji odgovara ukupnom broju mina u njihovih 8 susednih polja.

Igrac moze nastaviti da otvara polja i oznacava mine, bas kao i u poznatoj igri. Igrac pobedjuje ukoliko oznaci sve mine, a gubi ukoliko otvori minu.

### Resavac

Kako bi univerzalni resavac koji resava zadatu tablu kao covek bilo veoma tesko napisati (Postoje slucajevi u kojima je nemoguce odabrati tacnu lokaciju mine bez "nagadjanja"), resavac u sklopu ovog projekta ima malo drugaciji pristup.
Ovaj resavac primice tablu na cijim poljima su brojevi od 0 do 9. Ovi brojevi predstavljaju *broj mina u okolini tog polja, ukljucujuci i to konkretno polje*.
Nakon pokretanja resavaca, dobija se mapa sa jednim mogucim rasporedom mina u tako postavljenoj tabli.

## Jezik i Tehnologije

Projekat je radjen u jeziku Kotlin, u okruzenju IntelliJ IDEA. Od biblioteka, za GUI je koriscen TornadoFX.

## Autori
* Mila Lukic (milalukic222@gmail.com)
* Tara Menjak Maksimovic (taramm19@gmail.com)
