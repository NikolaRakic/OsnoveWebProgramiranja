$(document).ready(function(){
	
	var idFilma = window.location.search.slice(1).split('?')[0].split('=')[1];
	
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
				alert("Nemate pravo pristupa!")
				window.location.replace('pocetna.html');
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
	
		$.get('ProjekcijaServlet', {'action':'getAllForMovie', 'idFilma' : idFilma}, function(data){
			var projekcije = data.projekcije;
			
			for(i in projekcije){
				var dt = new Date(projekcije[i].datumPrikazivanja);
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
				
				$('#tbodyid').append(
				'<tr>'+
					'<td><a href ="projekcija.html?id=' + projekcije[i].id +'">'+ projekcije[i].datumPrikazivanja +'</a></td>'+
					'<td>'+ projekcije[i].tipProjekcije +'</td>'+
					'<td>'+ projekcije[i].sala.naziv +'</td>'+
					'<td>'+ projekcije[i].cenaKarte +" din" +'</td>'+
					'<td><a href = "kupikartu.html?id=' +projekcije[i].id + '">' + "Kupi kartu" + '</a></td>'+
				'</tr>'
				)
			}
		});
		
	}
	getTable()
	menuBar()
});