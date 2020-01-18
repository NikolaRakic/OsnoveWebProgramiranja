DROP TABLE IF EXISTS korisnici;
DROP TABLE IF EXISTS sedista;
DROP TABLE IF EXISTS filmovi
DROP TABLE IF EXISTS projekcije;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS karte;


CREATE TABLE korisnici (
    korisnickoIme VARCHAR(36) NOT NULL,
    lozinka VARCHAR(36) NOT NULL,
    datumRegistracije DATE NOT NULL,
    uloga VARCHAR(10) NOT NULL DEFAULT 'KORISNIK',
    obrisan BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(korisnickoIme)
);

INSERT INTO korisnici (korisnickoIme, lozinka, datumRegistracije, uloga) VALUES ("nikola", "123", "2019-12-12", "ADMIN");
INSERT INTO korisnici (korisnickoIme, lozinka, datumRegistracije, uloga) VALUES ("mare", "123", "2019-12-12", "KORISNIK");
INSERT INTO korisnici (korisnickoIme, lozinka, datumRegistracije, uloga) VALUES ("djole", "123", "2019-12-12", "KORISNIK");

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

INSERT INTO sale (naziv, tipProjekcije) VALUES ("A1", "D2,D3,D4");
INSERT INTO sale (naziv, tipProjekcije) VALUES ("A2", "D3");
INSERT INTO sale (naziv, tipProjekcije) VALUES ("B1", "D3,D4");
INSERT INTO sale (naziv, tipProjekcije) VALUES ("B2","D4");

CREATE TABLE sedista (
	id INTEGER PRIMARY KEY,
	redniBroj INT NOT NULL,
    salaNaziv VARCHAR(255) NOT NULL,
    zauzeto BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (salaNaziv) REFERENCES sale (naziv)
);

INSERT INTO sedista (redniBroj, salaNaziv) VALUES (1,"A1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (2,"A1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (3,"A1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (4,"A1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (1,"A2");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (2,"A2");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (3,"A2");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (4,"A2");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (1,"B1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (2,"B1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (3,"B1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (4,"B1");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (1,"B2");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (2,"B2");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (3,"B2");
INSERT INTO sedista (redniBroj, salaNaziv) VALUES (4,"B2");
INSERT INTO sedista (redniBroj, salaNaziv, zauzeto) VALUES (5,"B2", TRUE);

CREATE TABLE projekcije (
	id INTEGER PRIMARY KEY,
    filmID INTEGER NOT NULL,
    salaNaziv VARCHAR(255) NOT NULL,
    tipProjekcije VARCHAR(2),
    datumPrikazivanja DATE NOT NULL,
    vremePrikazivanja TIME NOT NULL,
    cenaKarte int NOT NULL,
    admin VARCHAR(36) NOT NULL,
    obrisan BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (filmID) REFERENCES filmovi (id),
    FOREIGN KEY (salaNaziv) REFERENCES sale(naziv),
    FOREIGN KEY (admin) REFERENCES korisnici (korisnickoIme)
    );
    
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin) VALUES
(1, "A1", "D3", "2020-02-19","19:30:00", 600, "nikola");
INSERT INTO projekcije(filmID, salaNaziv, tipProjekcije, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin) VALUES
(2, "A2", "D2", "2020-02-22","13:30:00", 500, "nikola");
    
CREATE TABLE karte (
	id INTEGER PRIMARY KEY,
    projekcijaID INTEGER NOT NULL,
    sedisteID INTEGER NOT NULL,
    vremeProdaje DATETIME,
    kupacKarte VARCHAR(36) NOT NULL,
    obrisan BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (projekcijaID) REFERENCES projekcije (id),
    FOREIGN KEY (sedisteID) REFERENCES sedista (id),
    FOREIGN KEY (kupacKarte) REFERENCES korisnici(korisnickoIme)
);

INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (1,1,"2019-12-29 14:56:59", "mare");
INSERT INTO karte(projekcijaID, sedisteID, vremeProdaje, kupacKarte) VALUES (1,2,"2019-12-30 17:26:09", "djole");

