$(document).ready(function(){
	
	
	var id = window.location.search.slice(1).split('?')[0].split('=')[1];
	var korIme;
	var uloga;
	
	function menuBar(){
		$.get('KorisnikServlet', {'action' : 'ulogovaniKorisnik'}, function(data){
			console.log(data.status);
			korIme = data.korisnickoIme;
			
			if(data.status == 'NEPRIJAVLJEN'){
				alert("Nemate pravo pristupa!");
				window.location.replace('pocetna.html');
				
			}
			else{
				
				$('#meni').append('<li><a href="mojnalog.html?korIme=' + data.korisnickoIme +'">Moj nalog</a></li>');
				
				if(data.ulogovaniKorisnikUloga == 'ADMIN'){
					uloga = data.ulogovaniKorisnikUloga;
					
					$('#meni').append('<li id="korisnici"><a href="korisnici.html">Korisnici</a></li>');
					
					
				}
				
			}
			
		});
	}
	
	
	
	
	
	function getTable(){
		$.get('ProjekcijaServlet',{'id' : id, 'action' : 'getOne'}, function(data){
			
			var projekcija = data.projekcija;
			if(data.status == 'success'){
				
				var dt = new Date(projekcija.datumPrikazivanja);
				$('#nazivFilma').append('<a href="film.html?id=' + projekcija.film.id +'">'+ projekcija.film.naziv +'</a>');
				$('#vremeProjekcije').append('<a href="projekcija.html?id=' + projekcija.id +'">'+ data.datumPrikazivanja +'</a>');
				$('#tipProjekcije1').text(projekcija.tipProjekcije);
				$('#sala').text(projekcija.sala.naziv);
				$('#cena').text(projekcija.cenaKarte + " din");
				//sva sedista za projekciju koja nisu zauzeta
				$.get('SedisteServlet',{'projekcijaId' : id, 'action' : 'getAll'}, function(data){
					var sedista = data.slobodnaSedista;
					
					var danasnjiDatum = new Date;
					
					if(sedista == "" || +danasnjiDatum >= +dt || uloga == "ADMIN"){
						
						$('#kupiKartuBtn').hide();
					}
					else{
						
						$('#kupiKartuBtn').show();
						for(i in sedista){
							console.log(sedista[i].id)
							$('#sedista').append("<option value='" + sedista[i].id + "'>"+sedista[i].redniBroj+"</option>");
						}
					}
					
				});
				$('#sedista').append();
			}
			else{
				alert("Pogresan id!")
				window.location.replace('pocetna.html');
			}
	});
}
	
	
	
	$('#kupiKartuBtn').on('click', function(event) {
		var projekcijaId = id;
		var kupacKarte = $('#nazivFilma').val();
		var sediste = $('#sedista').val();
		var json = {
				'action' : 'add',
				'idProjekcije' : id,
				'kupacKarte' : korIme,
				'sediste' : sediste
		}
		$.post('KartaServlet', json, function(data){
			if(data.status = "success"){
				window.location.replace('projekcija.html?id=' + projekcijaId);
			}
			
		});
	});
	
	
	
	menuBar()
	getTable()
});