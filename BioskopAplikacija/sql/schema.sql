DROP TABLE IF EXISTS karte;
DROP TABLE IF EXISTS sedista;
DROP TABLE IF EXISTS projekcije;
DROP TABLE IF EXISTS filmovi;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS korisnici;


DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



CREATE TABLE korisnici (
    korisnickoIme VARCHAR(36) NOT NULL,
    lozinka VARCHAR(36) NOT NULL,
    datumRegistracije DATE DEFAULT (DATETIME('now','localtime')),
    uloga VARCHAR(10) NOT NULL DEFAULT 'KORISNIK',
    obrisan BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(korisnickoIme)
);
		INSERT INTO korisnici (korisnickoIme, lozinka, uloga) VALUES ("nikola", "123", "ADMIN");
		INSERT INTO korisnici (korisnickoIme, lozinka, uloga) VALUES ("mare", "123", "KORISNIK");
                          INSERT INTO korisnici (korisnickoIme, lozinka, uloga) VALUES ("djole", "123", "KORISNIK");

CREATE TABLE filmovi (
	id INTEGER PRIMARY KEY,
    naziv VARCHAR(255) NOT NULL,
    reziser VARCHAR(255) NOT NULL,
    glumci VARCHAR(255),
    zanr VARCHAR(36),
    trajanje INTEGER NOT NULL,
    distributer VARCHAR(255),
    zemlja VARCHAR(255),
    godinaProizvodnje INT NOT NULL,
    opis VARCHAR(255),
    obrisan BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("Rane", "Srdjan Dragojevic", "Dusan Pekic, Nikola Kojo, Dragan Bjelogrlic", "Drama", 103, "Kobra", "Srbija", 1998, "Film je posvecen generacijama posle Tita.");
INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("Parada", "Srdjan Dragojevic", "Goran Jevtic, Nikola Kojo, Hristina Popovic", "Drama", 115, "Neue visionen", "Srbija", 2011, "Film govori o grupi gej aktivista koji pokusavaju da odrze gej paradu.");
INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("Fight Club", "David Fincher", "Adam Smith, Brad Pitt, Edward Norton", "Drama", 98, "Chuck Palahniuk", "America", 1999, "Nezadovoljan svojim kapitalistickim nacinom zivota, nesanica sa belim ogrlicama formira klub za podzemne borbe sa Tajlerom, bezbriznim prodavcem sapuna.");
INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("Parasite", "Barunson E&A", "Cho Yeo-jeong, Park So-dam", "Misterija", 112, "Neue visionen", "Koreja", 2019, "Pohlepa i klasna diskriminacija ugrozavaju novoformirani simbiotski odnos izmedju bogate porodice Park i siromasnog klana Kim.");

INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("The Dark Knight", "Christopher Nolan", "Heath Ledger, Michael Caine", "Triler", 101, "Warner Bros", "America", 2008, "Nakon sto Gordon, Dent i Batman zapocnu napad na organizovani kriminal Gothama, mafijasi unajmljuju Dzokera, psihopatskog kriminalnog majstora koji zeli sve heroje spustiti na njegov nivo.");
INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("No Time to Die", "Cary Joji Fukunaga", "Daniel Carig, Rami Malek, Ana De Armas", "Misterija", 105, "Neue visionen", "America", 2020, "Regrutovani da spasi otetog naucnika, spijun koji lovi globus Dzejms Bond nasao se na tragu misterioznog negativca, koji je naoruzan novom opasnom tehnologijom");
INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("The Godfather", "Francis Ford Coppola", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", "Krimi", 95, "Paramount Pictures‎", "America", 1972, "Don Vito Korleone, glava mafijaske porodice, odlucuje da svoje carstvo preda najmladjem sinu Mihaelu. Medjutim, njegova odluka nenamjerno stavlja u zivot svoje najmilije ozbiljnu opasnost.");
INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("Joker", "Todd Phillips", "Joaquin Phoenix, Robert De Niro, Zazie Beetz", "Drama", 111, "Golden Lyon", "Amerika", 2019, "Zauvek sam u gomili, neuspeli komicar Arthur Fleck trazi vezu dok seta ulicama Gotham Citija. Arthur nosi dvije maske - onu koju slika za svoj dnevni posao kao klovn, a zamisao koju projektuje u uzaludnom pokusaju da se osjeca kao da je dio svijeta oko sebe.");


INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("Titanic", "Variety", "Leonardo DiCaprio, Kate Winslet, Billy Zane", "Drama", 145, "White Star Line‎", "America", 1997, "Sedamnaestogodisnja Ruza potice iz aristokratske porodice i namerava se vencati. Kada se ukrca na Titanic, upoznaje umetnika Jacka Davsona i zaljubljuje se u njega.");
INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis) VALUES ("Boyhood", "Richard Linklater", "Patricia Arquette, Ellar Coltrane, Lorelei Linklater", "Komedija", 104, "Hero", "Amerika", 2014, "Djecastvo je americki dramski film iz 2014. godine koji je napisao i rezirao Richard Linklater, a glumili su Patriciju Arkuette, Ellar Coltrane, Lorelei Linklater i Ethana Havkea. ");





CREATE TABLE sale (
    naziv VARCHAR(255) NOT NULL,
    PRIMARY KEY (naziv)
);

INSERT INTO sale (naziv, tipProjekcije) VALUES ("A1", "2D,3D");
INSERT INTO sale (naziv, tipProjekcije) VALUES ("A2", "3D");
INSERT INTO sale (naziv, tipProjekcije) VALUES ("B1", "3D,4D");
INSERT INTO sale (naziv, tipProjekcije) VALUES ("B2","4D");


CREATE TABLE projekcije (
	id INTEGER PRIMARY KEY,
    filmID INTEGER NOT NULL,
    salaNaziv VARCHAR(255) NOT NULL,
    tipProjekcije VARCHAR(2),
    datumPrikazivanja DATETIME NOT NULL,
    cenaKarte int NOT NULL,
    admin VARCHAR(36) NOT NULL,
    obrisan BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (filmID) REFERENCES filmovi (id),
    FOREIGN KEY (salaNaziv) REFERENCES sale(naziv),
    FOREIGN KEY (admin) REFERENCES korisnici (korisnickoIme)
    );
 
  
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (1, "A1", "2D", "2020-02-09 16:30:00", 600, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (2, "A2", "3D", "2020-02-09 21:25:00", 500, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (3, "A1", "3D", "2020-02-09 16:30:00", 300, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "A2", "3D", "2020-02-09 21:30:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (7, "A1", "2D", "2020-02-09 16:30:00", 250, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (1, "B1", "4D", "2020-02-09 22:20:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (2, "B1", "3D", "2020-02-09 19:30:00", 450, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (3, "B2", "4D", "2020-02-09 14:45:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (7, "B1", "4D", "2020-02-09 12:30:00", 800, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "B2", "4D", "2020-02-09 15:30:00", 550, "mare");  
   
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (1, "A1", "3D", "2020-02-10 16:30:00", 600, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (1, "A2", "3D", "2020-02-10 21:25:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (2, "A1", "2D", "2020-02-10 16:30:00", 300, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (2, "A2", "3D", "2020-02-10 21:30:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (3, "A1", "3D", "2020-02-10 16:30:00", 250, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (3, "B1", "4D", "2020-02-10 22:20:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (4, "B1", "3D", "2020-02-10 19:30:00", 450, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (4, "B2", "4D", "2020-02-10 14:45:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (5, "B1", "4D", "2020-02-10 12:30:00", 800, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (5, "B2", "4D", "2020-02-10 15:30:00", 550, "mare");

    
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (5, "A1", "3D", "2020-02-11 16:30:00", 600, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (5, "A2", "3D", "2020-02-11 21:25:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "A1", "3D", "2020-02-11 16:30:00", 300, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "A2", "3D", "2020-02-11 21:30:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (7, "A1", "2D", "2020-02-11 16:30:00", 250, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (8, "B1", "3D", "2020-02-11 22:20:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (7, "B1", "4D", "2020-02-11 19:30:00", 450, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (8, "B2", "4D", "2020-02-11 14:45:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (9, "B1", "4D", "2020-02-11 12:30:00", 800, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (9, "B2", "4D", "2020-02-11 15:30:00", 550, "mare");
  
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (5, "A1", "3D", "2020-02-12 16:30:00", 600, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (5, "A2", "3D", "2020-02-12 21:25:00", 500, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "A1", "2D", "2020-02-12 16:30:00", 300, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "A2", "3D", "2020-02-12 21:30:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (10, "A1", "3D", "2020-02-12 16:30:00", 250, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (8, "B1", "4D", "2020-02-12 22:20:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (10, "B1", "3D", "2020-02-12 19:30:00", 450, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (8, "B2", "4D", "2020-02-12 14:45:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (9, "B1", "4D", "2020-02-12 12:30:00", 800, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (9, "B2", "4D", "2020-02-12 15:30:00", 550, "mare");  


  
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (2, "A1", "3D", "2020-02-13 16:30:00", 600, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (2, "A2", "3D", "2020-02-13 21:25:00", 500, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (4, "A1", "3D", "2020-02-13 16:30:00", 300, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "A2", "3D", "2020-02-13 21:30:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (9, "A1", "3D", "2020-02-13 16:30:00", 250, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (6, "B1", "3D", "2020-02-13 22:20:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (4, "B1", "4D", "2020-02-13 19:30:00", 450, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (9, "B2", "4D", "2020-02-13 14:45:00", 500, "mare");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (3, "B1", "4D", "2020-02-13 12:30:00", 800, "djole");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES (3, "B2", "4D", "2020-02-13 15:30:00", 550, "mare");  

CREATE TABLE sedista (
	id INTEGER PRIMARY KEY,
	redniBroj INT NOT NULL,
    projekcijaId INTEGER(10) NOT NULL,
    zauzeto BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (projekcijaId) REFERENCES projekcije (id)
);

INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,3);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,3);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,3);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,4);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,4);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,4);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,5);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,5);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,5);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,6);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,6);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,6);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,7);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,7);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,7);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,8);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,8);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,8);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,9);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,9);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,9);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,10);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,10);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,10);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,11);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,11);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,11);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,12);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,12);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,12);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,13);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,13);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,13);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,14);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,14);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,14);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,15);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,15);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,15);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,16);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,16);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,16);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,17);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,17);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,17);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,18);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,18);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,18);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,19);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,19);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,19);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,20);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,20);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,20);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,20);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,20);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,20);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,21);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,21);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,21);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,22);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,22);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,22);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,23);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,23);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,23);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,24);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,24);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,24);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,25);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,25);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,25);

INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,26);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,26);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,26);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,27);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,27);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,27);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,28);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,28);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,28);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,29);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,29);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,29);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,30);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,30);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,30);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,31);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,31);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,31);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,32);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,32);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,32);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,33);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,33);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,33);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,34);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,34);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,34);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,35);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,35);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,35);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,36);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,36);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,36);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,37);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,37);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,37);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,38);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,38);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,38);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,39);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,39);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,39);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,40);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,40);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,40);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,41);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,41);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,41);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,42);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,42);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,42);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,43);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,43);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,43);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,44);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,44);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,44);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,45);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,45);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,45);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,46);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,46);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,46);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,47);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,47);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,47);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,48);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,48);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,48);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,49);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,49);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,49);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,50);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,50);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,50);



CREATE TABLE karte (
	id INTEGER PRIMARY KEY,
    projekcijaID INTEGER NOT NULL,
    sedisteID INTEGER NOT NULL,
    vremeProdaje DATETIME DEFAULT (DATETIME('now','localtime')),
    kupacKarte VARCHAR(36) NOT NULL,
    obrisan BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (projekcijaID) REFERENCES projekcije (id),
    FOREIGN KEY (sedisteID) REFERENCES sedista (id),
    FOREIGN KEY (kupacKarte) REFERENCES korisnici(korisnickoIme)
);

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (1,1,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (1,2,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (2,4, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (3,7,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (3,8,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (4,10,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (4,11,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (5,13, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (6,16,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (6,17,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (7,19,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (7,20,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (8,22, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (9,25,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (9,26,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (10,28,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (10,29,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (11,32, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (12, 33,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (12,34,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (13,36,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (13,37,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (14,39, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (15,42,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (15,43,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (16,45,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (16,46,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (17,48, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (18,51,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (18,52,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (19,54,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (19,55,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (20,57, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (21,60,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (21,62,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (22,63,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (22,64,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (23,66, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (24,69,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (24,70,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (25,72,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (25,73,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (26,75, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (29,78,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (29,79,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (30,81,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (30,82,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (31,85, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (32,87,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (32,88,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (33,90,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (33,91,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (34,93, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (35,96,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (35,97,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (36,99,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (36,100,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (37,102, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (38,105,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (38,106,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (39,109,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (39,110,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (40,111, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (41,114,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (41,115,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (42,117,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (42,118,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (43,120, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (44,123,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (44,124,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (45,126,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (45,127,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (46,129, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (47,132,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (47,133,"2019-12-30 17:26:09", "djole");

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (48,135,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (48,136,"2019-12-30 17:26:09", "djole");
INSERT INTO karte(projekcijaID, sedisteID, kupacKarte) VALUES (49,138, "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (50,141,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (50,142,"2019-12-30 17:26:09", "djole");

