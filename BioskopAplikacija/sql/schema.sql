DROP TABLE IF EXISTS karte;
DROP TABLE IF EXISTS sedista;
DROP TABLE IF EXISTS projekcije;
DROP TABLE IF EXISTS filmovi;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS korisnici;






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


CREATE TABLE sale (
    naziv VARCHAR(255) NOT NULL,
    tipProjekcije VARCHAR(10) NOT NULL,
    PRIMARY KEY (naziv)
);

INSERT INTO sale (naziv, tipProjekcije) VALUES ("A1", "2D,3D,4D");
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
    
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES
(1, "A1", "3D", "2020-02-19 16:30:00", 600, "nikola");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, cenaKarte, admin) VALUES
(2, "A2", "2D", "2020-02-16 21:00:00", 500, "nikola");
    


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
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (4,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (4,1);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (4,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (1,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (2,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (3,2);
INSERT INTO sedista (redniBroj, projekcijaId) VALUES (4,2);
INSERT INTO sedista (redniBroj, projekcijaId, zauzeto) VALUES (5,2, TRUE);



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

