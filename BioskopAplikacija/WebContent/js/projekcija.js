$(document).ready(function(){
	
	var id = window.location.search.slice(1).split('?')[0].split('=')[1];
	console.log('id je: ' + id);
	
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
					$('#dodajBtn').show();
				}
				
			}
			
		});
	}
	
	
	function getTable(){
		$.get('ProjekcijaServlet',{'id' : id, 'status' : 'getOne'}, function(data){
		
			var projekcija = data.projekcija;
			var brSlobodnihMesta = data.brSlobodnihSedista;
			if(data.status == 'success'){
				
				var dt = new Date(projekcija.datumPrikazivanja);
				$('#nazivFilma').text(projekcija.film.naziv);
				$('#datumIVreme').text(data.datumPrikazivanja);
				$('#tipProjekcijeTd').text(projekcija.tipProjekcije);
				$('#sala').text(projekcija.sala.naziv);
				$('#cena').text(projekcija.cenaKarte + " din");
				$('#brSlobodnihMesta').text(brSlobodnihMesta);
			}
			else{
				alert("Pogresan id!")
				window.location.replace('pocetna.html');
			}
	});
}
	
	
	
	$('#odjavaLink').on('click', function(event) {
		$.get('OdjavaServlet', function(data) {
			console.log(data.status);

			if (data.status == 'NEPRIJAVLJEN') {
				window.location.replace('prijava.html');
				return;
			}
		});
	});
	
	$('#obrisiBtn').on('click', function(event) {
		$.post('ProjekcijaServlet',{'id':id, 'status' : 'delete'} ,function(data) {
			if(data.status == 'success'){
				window.location.replace('pocetna.html');
				return;
			}
			else{
				alert("Greska!")
				window.location.replace('pocetna.html');
				return;
			}
		
		});
		
	});
	
	menuBar();
	getTable();
});