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
					
					alert("Niste prijavljeni!")
					window.location.replace('prijava.html');
					
				}
				
				if(data.ulogovaniKorisnikUloga == 'KORISNIK'){
					alert("Nemate pravo pristupa!")
					window.location.replace('pocetna.html');
				}
					
					if(data.ulogovaniKorisnikUloga == 'ADMIN'){
						alert("ADMIN")
						
						$('#meni').append('<li><a href="mojnalog.html?korIme=' + data.korisnickoIme +'">Moj nalog</a></li>'+
						'<li><a href="mojeKarte.html">Moje karte</a></li>');
						
						$('#meni').append('<li id="korisnici"><a href="korisnici.html">Korisnici</a></li>'+
										'<li id="izvestaj"><a href="izvestaj.html">Izvestaj</a></li>'+
										'<li><a href="karte.html">Sve karte</a></li>');
						$('#dodajBtn').show();
					}
					
				
				
			});
		}
		
		
		
		function getTable(){
			$.get('ProjekcijaServlet', {'action' : 'izvestaj'}, function(data){
				
			});
		}
		
		
		
menuBar()
getTable()
	
});