$(document).ready(function(){
	
	$('#odjavaLink').on('click', function(event) {
		$.get('OdjavaServlet', function(data) {
			console.log(data.status);

			if (data.status == 'NEPRIJAVLJEN') {
				window.location.replace('prijava.html');
				return;
			}
		});
	
		event.preventDefault();
		return false;
	});
	
	
	
	function menuBar(){
		$.get('KorisnikServlet', {'action' : 'ulogovaniKorisnikUloga'}, function(data){
			console.log(data.status);
			
			if(data.status == 'NEPRIJAVLJEN'){
				
				$('#odjava').remove();
				$('#meni').append('<li id="prijava"><a href="prijava.html">Prijava</a></li>'+
				  '<li id="registracija"><a href="registracija.html">Registracija</a></li>');
				
			}
			else{
				
				$('#meni').append('<li><a href="mojNalog.html">Moj nalog</a></li>'+
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
		$.get('FilmServlet', {'action':'getAll'}, function(data){
			var filmovi = data.filmovi;
			for(i in filmovi){
				if(filmovi[i].obrisan == false){
					$('#filmovi').append(
							'<tr>'+
								'<td><a href ="film.html?id=' + filmovi[i].id +'">'+ filmovi[i].naziv +'</td>'+
								'<td>'+ filmovi[i].reziser +'</td>'+
								'<td>'+ filmovi[i].glumci  +'</td>'+
								'<td>'+ filmovi[i].zanr  +'</td>'+
								'<td>'+ filmovi[i].trajanje  +"min" + '</td>'+
								'<td>'+ filmovi[i].distributer  +'</td>'+
								'<td>'+ filmovi[i].zemlja  +'</td>'+
								'<td>'+ filmovi[i].godinaProizvodnje  +'</td>'+
								'<td>'+ filmovi[i].opis  +'</td>'+
							'</tr>'
							)
				}
			
			}
		});
	}
	
	
	
	
	
	
	$('#dodajBtn').on('click', function(event){
		var filmovi = [];
		
		$('#dodavanjeAdmin').fadeIn();
		$('#dodajBtn2').show();
			
			$('#dodajBtn2').on('click', function(event){
				var nazivFilma = $('#nazivFilma').val();
				var reziser = $('#reziser').val();
				var glumci = $('#glumci').val();
				var zanr = $('#zanr').val();
				var trajanje = $('#trajanje').val();
				var distributer = $('#distributer').val();
				var zemlja = $('#zemlja').val();
				var godina = $('#godina').val();
				var opis = $('#opis').val();
		
			
				event.preventDefault();
				var json = {
						'action' : 'add',
						'nazivFilma' : nazivFilma,
						'reziser' : reziser,
						'glumci' : glumci,
						'zanr' : zanr,
						'trajanje' : trajanje,
						'distributer' : distributer,
						'zemlja' : zemlja,
						'godina' : godina,
						'opis' : opis,
						
				}
				
				$.post('FilmServlet', json, function(data){
					event.preventDefault();
					if(data.status = "success"){
						location.reload();
					}
					else{
						alert("Greska!")
						location.reload();
					}
					
				});		
				
			
		});
	});
	
	
	
	
	getTable();
	menuBar();
	
});