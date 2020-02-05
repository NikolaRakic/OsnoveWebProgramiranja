$(document).ready(function(){
	
	var id = window.location.search.slice(1).split('?')[0].split('=')[1];
	
	
	
	function menuBar(){
		$.get('KorisnikServlet', {'action' : 'ulogovaniKorisnik'}, function(data){
			console.log(data.status);
			
			if(data.status == 'NEPRIJAVLJEN'){
				
				$('#odjava').remove();
				$('#meni').append('<li id="prijava"><a href="prijava.html">Prijava</a></li>'+
				  '<li id="registracija"><a href="registracija.html">Registracija</a></li>');
				
			}
			else{
				
				$('#meni').append('<li><a href="mojnalog.html?korIme=' + data.korisnickoIme +'">Moj nalog</a></li>'+
						'<li><a href="mojeKarte.html">Moje karte</a></li>');
				if(data.ulogovaniKorisnikUloga == 'ADMIN'){
					
					$('#meni').append('<li id="korisnici"><a href="korisnici.html">Korisnici</a></li>'+
									'<li id="izvestavanje"><a href="izvestavanje.html">Izvestavanje</a></li>'+
									'<li><a href="karte.html">Sve karte</a></li>');
					$('#izmeniBtn').show();
					$('#obrisiBtn').show();
				}
				
			}
			
		});
	}
	
	
	
	
	
	function getTable(){
		$.get('KartaServlet',{'id' : id, 'action' : 'getOne'}, function(data){
			karta = data.karta;
			console.log(karta);
			if(data.status == 'success'){
				
				
				$('#nazivFilma').text(karta.projekcija.film.naziv);
				$('#vremeProjekcije').text(karta.projekcija.datumPrikazivanja);
				$('#tipProjekcijeKarta').text(karta.projekcija.tipProjekcije);
				$('#sala').text(karta.projekcija.sala.naziv);
				$('#sediste').text(karta.sediste.redniBroj);
				$('#cena').text(karta.projekcija.cenaKarte);
				
			}
			if(data.status == 'failure'){
				alert("Pogresan id!")
				window.location.replace('pocetna.html');
			}
	});
}
	
	
	
	
	menuBar();
	getTable()
});