$(document).ready(function(){
	var film;
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
					$('#izmeniBtn').show();
					$('#obrisiBtn').show();
				}
				
			}
			
		});
	}
	
	
	function getTable(){
		$.get('FilmServlet',{'id' : id, 'action' : 'getOne'}, function(data){
			film = data.film;
			
			if(data.status == 'success'){
				
				
				$('#nazivFilma').text(film.naziv);
				$('#reziser').text(film.reziser);
				$('#glumci').text(film.glumci);
				$('#zanr').text(film.zanr);
				$('#trajanje').text(film.trajanje + " min");
				$('#distributer').text(film.distributer);
				$('#zemlja').text(film.zemlja);
				$('#godina').text(film.godinaProizvodnje);
				$('#opis').text(film.opis);
			}
			if(data.status == 'failure'){
				alert("Pogresan id!")
				window.location.replace('pocetna.html');
			}
	});
}
	
	
	$('#izmeniBtn').on('click', function(event){
		$('#izmenaAdmin').fadeIn();
		$('#naziv').val(film.naziv);
		$('#reziserEdit').val(film.reziser);
		$('#glumciEdit').val(film.glumci);
		$('#zanrEdit').val(film.zanr);
		$('#trajanjeEdit').val(film.trajanje);
		$('#distributerEdit').val(film.distributer);
		$('#zemljaEdit').val(film.zemlja);
		$('#godinaEdit').val(film.godinaProizvodnje);
		$('#opisEdit').val(film.opis);
		
		
		
		
		
		$('#izmeniBtn2').on('click', function(event){
			var newNaziv = $('#naziv').val();
			var newReziser = $('#reziserEdit').val();
			var newGlumci = $('#glumciEdit').val();
			var newZanr = $('#zanrEdit').val();
			var newTrajanje = $('#trajanjeEdit').val();
			var newDistributer = $('#distributerEdit').val();
			var newZemlja = $('#zemljaEdit').val();
			var newGodina = $('#godinaEdit').val();
			var newOpis = $('#opisEdit').val();
			
			
			
			var json = {
					'action' : 'edit',
					'id' : id,
					'naziv' : newNaziv,
					'reziser' : newReziser,
					'glumci' : newGlumci,
					'zanr' : newZanr,
					'trajanje' : newTrajanje,
					'distributer' : newDistributer,
					'zemlja' : newZemlja,
					'godina' : newGodina,
					'opis' : newOpis
			};
			if(newNaziv == "" || newReziser == "" || newGlumci == "" || newZanr == "" || newTrajanje == 0 || newDistributer == "" || newZemlja == "" || newGodina == 0 || newOpis == "") {
				alert("NISU UNETI SVI PODACI!")
			}
			else{
				console.log(json);
				$.post('FilmServlet', json, function(data){
					if(data.status == "success"){
						location.reload();
					}
					else{
						alert("Pogresno uneti podaci!")
					}
				});
			}
			
			event.preventDefault();
			return;
		});
		
		
		$('#odustaniBtn').on('click', function(event){
			$('#izmenaAdmin').fadeOut();
			
			event.preventDefault();
			return false;
		});
		
	});
	
	
	
	$('#obrisiBtn').on('click', function(event) {
		json = {
			'action': 'delete',
			'id': id, 
		};
		event.preventDefault();

		console.log(json);
		$.post('FilmServlet', json, function(data) {
			if (data.status == 'success') {
				window.location.replace('filmovi.html');
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
	
	
	
	$('#odjavaLink').on('click', function(event) {
		$.get('OdjavaServlet', function(data) {
			console.log(data.status);

			if (data.status == 'NEPRIJAVLJEN') {
				window.location.replace('prijava.html');
				return;
			}
		});
		
	});
	
	getTable()
	menuBar()
	
});