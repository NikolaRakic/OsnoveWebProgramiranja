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
				
				$('#odjava').remove();
				$('#meni').append('<li id="prijava"><a href="prijava.html">Prijava</a></li>'+
				  '<li id="registracija"><a href="registracija.html">Registracija</a></li>');
				
			}
			else{
				
				$('#meni').append('<li><a href="mojnalog.html?korIme=' + data.korisnickoIme +'">Moj nalog</a></li>'+
						'<li><a href="mojeKarte.html">Moje karte</a></li>');
				if(data.ulogovaniKorisnikUloga == 'ADMIN'){
					
					$('#meni').append('<li id="korisnici"><a class="active" href="korisnici.html">Korisnici</a></li>'+
									'<li id="izvestavanje"><a href="izvestavanje.html">Izvestavanje</a></li>'+
									'<li><a href="karte.html">Sve karte</a></li>');
					
				}
				
			}
			
		});
	}
	
	
	
	
	function getTable(){
		$.get('KorisnikServlet', {'action':'getAll'}, function(data){
			var korisnici = data.korisnici;
			for(i in korisnici){
				var dt = new Date(korisnici[i].datumRegistracije);
				var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
				var vreme = dt.getHours() + ":" + dt.getMinutes()
					$('#korisniciTable').append(
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