$(document).ready(function(){
	
	var id = window.location.search.slice(1).split('?')[0].split('=')[1];
	var karta;
	
	
	function menuBar(){
		$.get('KorisnikServlet', {'action' : 'ulogovaniKorisnik'}, function(data){
			console.log(data.status);
			
			if(data.status == 'NEPRIJAVLJEN'){
				alert("Nemate pravo pristupa!");
				window.location.replace('pocetna.html');
				
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
					$('#vlasnikKarte1').show();
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
				$('#cena').text(karta.projekcija.cenaKarte + " din");
				$('#vlasnikKarte').text(karta.kupacKarte.korisnickoIme);
				
			}
			if(data.status == 'failure'){
				alert("Pogresan id!")
				window.location.replace('pocetna.html');
			}
	});
}
	
	
	
	
	$('#obrisiBtn').on('click', function(event) {
		json = {
			'action': 'delete',
			'id': id, 
		};
		

		console.log(json);
		$.post('KartaServlet', json, function(data) {
			if (data.status == 'success') {
				window.location.replace('pocetna.html');
				return;
			}
			else{
				alert("Greska!")
				window.location.replace('pocetna.html');
			}
		});

		event.preventDefault();
		return false;
	});
	
	
	
	
	menuBar();
	getTable()
});