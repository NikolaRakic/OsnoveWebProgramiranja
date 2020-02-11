function sortiraj(){
	vrednostSortiranja = $('#sort').val();
	nacinSortiranja = $('#redosled').val();
	
	var json = {
			'action' : 'sort',
			'vrednostSortiranja' : vrednostSortiranja,
			'nacinSortiranja' : nacinSortiranja
	}
	
	$('#tbodyid').empty();
	$.get('KorisnikServlet', json, function(data){
	if(data.status == 'success'){
			
			var korisnici = data.korisnici;
	
			for(i in korisnici){
				var dt = new Date(korisnici[i].datumRegistracije);
				console.log(dt)
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
				
				$('#tbodyid').append(
						'<tr>'+
							'<td><a href ="mojnalog.html?korisnickoime=' + korisnici[i].korisnickoIme +'">'+ korisnici[i].korisnickoIme +'</td>'+
							'<td>'+ datum + " " + vreme +'</td>'+
							'<td>'+ korisnici[i].uloga  +'</td>'+
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
				'pretragaInput' : pretragaInput
				
		}
		
		$('#tbodyid').empty();
		$.get('KorisnikServlet', json, function(data){
		if(data.status == 'success'){
				
				var korisnici = data.korisnici;
				
				for(i in korisnici){
					var dt = new Date(korisnici[i].datumRegistracije);
					console.log(dt)
					var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
					var vreme = dt.getHours() + ":" + dt.getMinutes()
					
					$('#tbodyid').append(
							'<tr>'+
								'<td><a href ="mojnalog.html?korisnickoime=' + korisnici[i].korisnickoIme +'">'+ korisnici[i].korisnickoIme +'</td>'+
								'<td>'+ datum + " " + vreme +'</td>'+
								'<td>'+ korisnici[i].uloga  +'</td>'+
							'</tr>'
							)
						
				}
				
			}
			else{
				
			}
			event.preventDefault();
		});
	};





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
		$.get('KorisnikServlet', {'action' : 'ulogovaniKorisnik'}, function(data){
			console.log(data.status);
			
			if(data.status == 'NEPRIJAVLJEN'){
				alert("Niste prijavljeni!");
				window.location.replace('pocetna.html');
				return;
				
				
			}
			else{
				
				$('#meni').append('<li><a href="mojnalog.html?korIme=' + data.korisnickoIme +'">Moj nalog</a></li>');
				if(data.ulogovaniKorisnikUloga == 'ADMIN'){
					
					$('#meni').append('<li id="korisnici"><a class="active" href="korisnici.html">Korisnici</a></li>');
					
				}
				
			}
			
		});
	}
	
	
	
	
	function getTable(){
		$.get('KorisnikServlet', {'action':'getAll'}, function(data){
			var korisnici = data.korisnici;
			for(i in korisnici){
				var dt = new Date(korisnici[i].datumRegistracije);
				console.log(dt)
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
					$('#tbodyid').append(
							'<tr>'+
								'<td><a href ="mojnalog.html?korisnickoime=' + korisnici[i].korisnickoIme +'">'+ korisnici[i].korisnickoIme +'</td>'+
								'<td>'+ datum + " " + vreme +'</td>'+
								'<td>'+ korisnici[i].uloga  +'</td>'+
							'</tr>'
							)
				
			
			}
		});
	}
	
	menuBar();
	getTable();
	
});