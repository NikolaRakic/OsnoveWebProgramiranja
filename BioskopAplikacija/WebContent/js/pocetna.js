var datumInput;
function sortiraj(){
	vrednostSortiranja = $('#sort').val();
	nacinSortiranja = $('#redosled').val();
	
	var json = {
			'action' : 'sort',
			'datum' : datumInput,
			'vrednostSortiranja' : vrednostSortiranja,
			'nacinSortiranja' : nacinSortiranja
	}
	
	$('#tbodyid').empty();
	$.get('ProjekcijaServlet', json, function(data){
	if(data.status == 'success'){
			
			var projekcije = data.projekcije;
			
			console.log(projekcije)
			for(i in projekcije){
				
				
				var dt = new Date(projekcije[i].datumPrikazivanja);
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
		
				$('#tbodyid').append(
						'<tr id="redProjekcije">'+
							'<td><a href ="film.html?id=' + projekcije[i].film.id +'">'+ projekcije[i].film.naziv +'</a></td>'+
							'<td><a href ="projekcija.html?id=' + projekcije[i].id +'">'+ datum + " " + vreme +'</td>'+
							'<td>'+ projekcije[i].tipProjekcije +'</td>'+
							'<td>'+ projekcije[i].sala.naziv +'</td>'+
							'<td>'+ projekcije[i].cenaKarte +" din" +'</td>'+
						'</tr>'
						)
					
			}
			
		}
		else{
			
		}
		event.preventDefault();
	});
};



function pretraga(){
	pretragaInput = $('#pretragaInput').val();
	
	var json = {
			'action' : 'pretraga',
			'datum' : datumInput,
			'pretragaInput' : pretragaInput
			
	}
	
	$('#tbodyid').empty();
	$.get('ProjekcijaServlet', json, function(data){
	if(data.status == 'success'){
			
			var projekcije = data.projekcije;
			
			console.log(projekcije)
			for(i in projekcije){
				
				
				var dt = new Date(projekcije[i].datumPrikazivanja);
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
		
				$('#tbodyid').append(
						'<tr id="redProjekcije">'+
							'<td><a href ="film.html?id=' + projekcije[i].film.id +'">'+ projekcije[i].film.naziv +'</a></td>'+
							'<td><a href ="projekcija.html?id=' + projekcije[i].id +'">'+ datum + " " + vreme +'</td>'+
							'<td>'+ projekcije[i].tipProjekcije +'</td>'+
							'<td>'+ projekcije[i].sala.naziv +'</td>'+
							'<td>'+ projekcije[i].cenaKarte +" din" +'</td>'+
						'</tr>'
						)
					
			}
			
		}
		else{
			
		}
		event.preventDefault();
	});
};
	
	
	




function nadjiTipProjekcije(){
	$.get('SalaServlet', {'action' : 'getAll'}, function(data){
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
}

function pronadjiProjekcije(){
	datumInput = $('#datumInput').val();
	
	$('#tbodyid').empty();
	
	$.get('ProjekcijaServlet', {'action':'getAllForDate', 'datum':datumInput}, function(data){
		
		if(data.status == 'success'){
			
			var projekcije = data.projekcije;
			
			console.log(projekcije)
			for(i in projekcije){
				
				
				var dt = new Date(projekcije[i].datumPrikazivanja);
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
		
				$('#tbodyid').append(
						'<tr id="redProjekcije">'+
							'<td><a href ="film.html?id=' + projekcije[i].film.id +'">'+ projekcije[i].film.naziv +'</a></td>'+
							'<td><a href ="projekcija.html?id=' + projekcije[i].id +'">'+ datum + " " + vreme +'</td>'+
							'<td>'+ projekcije[i].tipProjekcije +'</td>'+
							'<td>'+ projekcije[i].sala.naziv +'</td>'+
							'<td>'+ projekcije[i].cenaKarte +" din" +'</td>'+
						'</tr>'
						)
					
			}
			
		}
		else{
			
		}
		event.preventDefault();
	});
	
}

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
			$('#nazivSale').append("<option value='-'>-</option>");
			for(i in data.sale){
				$('#nazivSale').append("<option value='" + data.sale[i].naziv + "'>"+data.sale[i].naziv+"</option>");
				
			}

			
			$('#dodajBtn2').on('click', function(event){
				
				var nazivFilma = $('#nazivFilma').val();
				var tipProjekcije = $('#tipProjekcije').val();
				//var datumPrikazivanja = $('#datumIVreme').val();
				var datum = $('#datumInput1').val();
				var vreme = $('#vremeInput').val();
				var cenaKarte = $('#cenaKarte').val();
				var nazivSale = $('#nazivSale').val();
				var brojSedista = $('#brSedista').val();
				
				var filmId;
				for(i in filmovi){
					if(filmovi[i][1] == nazivFilma){
						filmId = filmovi[i][0];
						
					}
					}
				
				event.preventDefault();
				var json = {
						'action' : 'add',
						'filmId' : filmId,
						'salaNaziv' : nazivSale,
						'tipProjekcije' : tipProjekcije,
						'datumPrikazivanja' : datum + " " + vreme,
						'cenaKarte' : cenaKarte,
						'brojSedista' : brojSedista
						
				}
				
				$.post('ProjekcijaServlet', json, function(data){
					location.reload();
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
				
				$('#meni').append('<li><a href="mojnalog.html?korIme=' + data.korisnickoIme +'">Moj nalog</a></li>');
				if(data.ulogovaniKorisnikUloga == 'ADMIN'){
					
					$('#meni').append('<li id="korisnici"><a href="korisnici.html">Korisnici</a></li>');
					$('#dodajBtn').show();
				}
				
			}
			
		});
	}
	
	
	function getTable(){
		var datum = $('#datumInput').val();
	
		$.get('ProjekcijaServlet', {'action':'getAllForDate'}, function(data){
			var projekcije = data.projekcije;
			
			//console.log(projekcije);
			for(i in projekcije){
				var dt = new Date(projekcije[i].datumPrikazivanja);
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
				//console.log(projekcije[i].film.naziv);
				$('#tbodyid').append(
				'<tr id="redProjekcije">'+
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
	
	
	getTable();
	
//	$('#pronadjiBtn').on('click', function(event){
		
//		var datum = $('#datumInput').val();
//		$('#tbodyid').empty();
//		alert(datum)
//		$.get('ProjekcijaServlet', {'action':'getAllForDate', 'datum':datum}, function(data){
//			alert(data.projekcije1)
//			if(data.status == 'success'){
//				alert("USPEO JE")
//				var projekcije = data.projekcije1;
//				
//				for(i in projekcije){
//					
//					alert(projekcije[i].sala.naziv)
//					var dt = new Date(projekcije[i].datumPrikazivanja);
//					var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
//					var vreme = dt.getHours() + ":" + dt.getMinutes()
//			
//					$('#tbodyid').append(
//					'<tr>'+
//						'<td><a href ="film.html?id=' + projekcije[i].film.id +'">'+ projekcije[i].film.naziv +'</a></td>'+
//						'<td><a href ="projekcija.html?id=' + projekcije[i].id +'">'+ datum + " " + vreme +'</td>'+
//						'<td>'+ projekcije[i].tipProjekcije +'</td>'+
//						'<td>'+ projekcije[i].sala.naziv +'</td>'+
//						'<td>'+ projekcije[i].cenaKarte +" din" +'</td>'+
//					'</tr>'
//					)
//				}
//				
//			}
//			else{
//				alert("nije uspeo")
//			}
//			
//		});
		
		
//	});
	
	
	
	
	menuBar();
});