$(document).ready(function(){
	
	var datumReg;
	var korIme = window.location.search.slice(1).split('?')[0].split('=')[1];
	var ulogovaniKorisnik;
	var ulogovaniKorisnikUloga;
	console.log('Korisnicko ime je: ' + korIme);
	
	
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
			console.log(data.korisnickoIme);
			
			if(data.status == 'NEPRIJAVLJEN'){
				alert("Niste prijavljeni!");
				window.location.replace('pocetna.html');
				return;
				
			}
			else{
				ulogovaniKorisnik = data.korisnickoIme;
				ulogovaniKorisnikUloga = data.ulogovaniKorisnikUloga;
				
				$('#meni').append('<li class="active"><a href="mojnalog.html?korIme=' + data.korisnickoIme +'">Moj nalog</a></li>');
				
				if(ulogovaniKorisnik == korIme){
					$('#izmeniBtn').show();
					$('#korisnikRadio').show();
				
				}
				
				if(ulogovaniKorisnikUloga == 'ADMIN'){
					
					$('#meni').append('<li id="korisnici"><a href="korisnici.html">Korisnici</a></li>');
									
					$('#izmeniBtn').show();
					$('#obrisiBtn').show();
					$('#adminLabel').show();
					$('#adminRadio').show();
					
				}
				
			}
			
		});
	}
	
	
	
	
	function getTable(){
		$.get('KorisnikServlet',{'korIme' : korIme, 'action' : 'getOne'}, function(data){
			
			
				korisnik = data.korisnik;
				datumReg = data.datumRegistracije;
				console.log(data)
				
			if(ulogovaniKorisnik == korIme || ulogovaniKorisnikUloga == 'ADMIN'){
					
				if(data.status == 'success'){
					
					
					$('#korisnickoIme').text(korisnik.korisnickoIme);
					$('#datumRegistracije').text(data.datumRegistracije);
					$('#uloga').text(korisnik.uloga);
					
				}
				if(data.status == 'failure'){
					window.location.replace('pocetna.html');
				}
			}
			else{
				alert("Nemate pravo pristupa!")
				window.location.replace('pocetna.html');
			}
	});
}
	
	
	
	
	
	function getKarte(){
		$.get('KartaServlet',{'korIme' : korIme, 'action' : 'getKarteZaKorisnika'}, function(data){
					
				if(data.status == 'success'){
					//alert("Uspeo je!")
					var karte = data.karte;
					
					
					for(i in karte){
						var dt = new Date(karte[i].vremeProdaje);
						var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
						var vreme = dt.getHours() + ":" + dt.getMinutes()
						
						
						$('#tbodyy').append(
								'<tr>'+
									'<td><a href ="karta.html?id=' + karte[i].id +'">'+ datum + " " + vreme +'</a></td>'+
									
								'</tr>'
								)
							
					}
					
				}
				if(data.status == 'failure'){
					window.location.replace('pocetna.html');
				}
			
			
	});
}
	
	
	
	
	$('#izmeniBtn').on('click', function(event){
		$('#izmenaAdmin').fadeIn();
		var dt = new Date(korisnik.datumRegistracije);

		$('#korIme').val(korisnik.korisnickoIme);
		$('#datumRegistracije1').val(datumReg);
		
		if(korisnik.uloga == "KORISNIK"){
			$('#korisnikRadio').prop('checked',true);
			$('#adminLabel').hidden;
			document.getElementById("adminRadio").disabled = true;
		}
		
		
		
		
		$('#izmeniBtn2').on('click', function(event){
			var korisnickoIme = $('#korIme').val();
			var lozinka = $('#lozinka').val();
			var admin = $('#admin').is(':checked');
			
			
			var json = {
					'action' : 'edit',
					'korisnickoIme' : korisnickoIme,
					'lozinka' : lozinka,
					'admin' : admin,
					
			};
			
			$.post('KorisnikServlet', json, function(data){
				console.log("Status je: " + data.status);
				if(data.status == "success"){
					location.reload();
				}
				else{
					alert("Pogresno uneti podaci!")
				}
				event.preventDefault();
				return;
			});
			
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
			'korisnickoIme': korIme, 
		};
		//event.preventDefault();

		console.log(json);
		$.post('KorisnikServlet', json, function(data) {
			if (data.status == 'success') {
				window.location.replace('korisnici.html');
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
	
	getKarte()
	menuBar()
	getTable()
	
});