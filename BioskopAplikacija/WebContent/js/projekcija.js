$(document).ready(function(){
	
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
					$('#dodajBtn').show();
				}
				
			}
			
		});
	}
	
	
	function getTable(){
		$.get('ProjekcijaServlet',{'id' : id, 'action' : 'getOne'}, function(data){
		
			var projekcija = data.projekcija;
			var brSlobodnihMesta = data.brSlobodnihSedista;
			if(data.status == 'success'){
				
				var dt = new Date(projekcija.datumPrikazivanja);
				$('#nazivFilma').append('<a href="film.html?id=' + projekcija.film.id +'">'+ projekcija.film.naziv +'</a>');
				$('#datumIVreme').text(data.datumPrikazivanja);
				$('#tipProjekcijeTd').text(projekcija.tipProjekcije);
				$('#sala').text(projekcija.sala.naziv);
				$('#cena').text(projekcija.cenaKarte + " din");
				$('#brSlobodnihMesta').text(brSlobodnihMesta);
			}
			else{
				alert("Pogresan id!")
				window.location.replace('pocetna.html');
			}
	});
}
	
	
	function getKarte(){
		$.get('KartaServlet',{'idProjekcije' : id, 'action' : 'getAll'}, function(data){
			if(data.status = "success"){
				var karte = data.karte;
				for(i in karte){
					var dt = new Date(karte[i].vremeProdaje);
					var datum = dt.getDate()+'-'+(dt.getMonth()+1)+'-'+dt.getFullYear();
					var vreme = dt.getHours() + ":" + dt.getMinutes()
					$('#karteZaProjekciju').append(
							'<tr>'+
								'<td><a href=karta.html?id='+karte[i].id + '>' + datum + " " + vreme + '</td>'+
								'<td><a href="mojnalog.html?korIme='+ karte[i].kupacKarte.korisnickoIme +'">' + karte[i].kupacKarte.korisnickoIme + '</td>' +
							'</tr>'
					)
				}
			}
		});
	}
	
	
	
	$('#odjavaLink').on('click', function(event) {
		$.get('OdjavaServlet', function(data) {
			console.log(data.status);

			if (data.status == 'NEPRIJAVLJEN') {
				window.location.replace('prijava.html');
				return;
			}
		});
	});
	
	$('#obrisiBtn').on('click', function(event) {
		$.post('ProjekcijaServlet',{'id':id, 'action' : 'delete'} ,function(data) {
			if(data.status == 'success'){
				window.location.replace('pocetna.html');
				return;
			}
			else{
				alert("Greska!")
				window.location.replace('pocetna.html');
				return;
			}
		
		});
		
	});
	
	menuBar();
	getTable();
	getKarte()
});