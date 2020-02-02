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
	
	$('#dodajBtn').on('click', function(event){
		var filmovi = [];
		
		$('#dodavanjeAdmin').fadeIn();
		var nazivSale;
		$.get('FilmServlet', {'action' : 'getAll'}, function(data){
			for(i in data.filmovi){
				$('#nazivFilma').append("<option value='" + data.filmovi[i].naziv + "'>"+data.filmovi[i].naziv+"</option>");
				var filmJedan=[data.filmovi[i].id, data.filmovi[i].naziv]
				filmovi.push(filmJedan)
			}
			
		});
		
		$.get('SalaServlet', {'action' : 'getAll'}, function(data){
			for(i in data.sale){
				$('#nazivSale').append("<option value='" + data.sale[i].naziv + "'>"+data.sale[i].naziv+"</option>");
				
			}
			$('#tipProjekcijeBtn').on('click', function(event){
				nazivSale = $('#nazivSale').val();
				for(i in data.sale){
					if(data.sale[i].naziv == nazivSale){
						$('#tipProjekcije').empty();
						var splitovan = data.sale[i].tipProjekcije.split(',');
						for(i in splitovan){
							$('#tipProjekcije').append("<option value='" + splitovan[i] + "'>"+splitovan[i]+"</option>");
						}
						
					}
				}
				
				$('#tipProjekcije').show();
				$('#dodajBtn2').show();
				event.preventDefault();
			});
			
			$('#dodajBtn2').on('click', function(event){
				
				var nazivFilma = $('#nazivFilma').val();
				var tipProjekcije = $('#tipProjekcije').val();
				var datumPrikazivanja = $('#datumIVreme').val();
				var cenaKarte = $('#cenaKarte').val();
				
				var filmId;
				for(i in filmovi){
					if(filmovi[i][1] == nazivFilma){
						filmId = filmovi[i][0];
						
					}
					}
				
				event.preventDefault();
				var json = {
						'status' : 'add',
						'filmId' : filmId,
						'salaNaziv' : nazivSale,
						'tipProjekcije' : tipProjekcije,
						'datumPrikazivanja' : datumPrikazivanja,
						'cenaKarte' : cenaKarte
						
				}
				
				$.post('ProjekcijaServlet', json, function(data){
					event.preventDefault();
					
				});
				
					
				
				
			});
		});
	});
	
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
		$.get('ProjekcijaServlet', {'status':'getAll'}, function(data){
			var projekcije = data.projekcije;
			
			//console.log(projekcije);
			for(i in projekcije){
				var dt = new Date(projekcije[i].datumPrikazivanja);
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
				//console.log(projekcije[i].film.naziv);
				$('#projekcije').append(
				'<tr>'+
					'<td><a href ="film.html?id=' + projekcije[i].film.id +'">'+ projekcije[i].film.naziv +'</a></td>'+
					'<td><a href ="projekcija.html?id=' + projekcije[i].id +'">'+ datum + " " + vreme +'</td>'+
					'<td>'+ projekcije[i].tipProjekcije +'</td>'+
					'<td>'+ projekcije[i].sala.naziv +'</td>'+
					'<td>'+ projekcije[i].cenaKarte +" din" +'</td>'+
				'</tr>'
				)
			}
		});
	}
	
	
	
	
	$('#pronadjiBtn').on('click', function(event){
		
		var today = new Date();
		console.log(today)
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		console.log("datum je" + date);
		
		var datum = $('#datumInput').val();
		alert(datum)
		
		
	});
	
	
	
	getTable();
	menuBar();
});