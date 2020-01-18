$(document).ready(function() { // izvršava se nakon što se izgradi DOM stablo HTML dokumenta
	// keširanje referenci da se ne bi ponavljale pretrage kroz DOM stablo
	var userNameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');
	
	var messageParagraph = $('#messageParagraph');

	$('#loginSubmit').on('click', function(event) { // izvršava se na klik na dugme
		var userName = userNameInput.val();
		var password = passwordInput.val();

		var params = {
			'username': userName, 
			'password': password
		}
		// kontrola toka se račva na 2 grane
		$.post('PrijavaServlet', params, function(data) { // u posebnoj programskoj niti se šalje (asinhroni) zahtev
			// tek kada stigne odgovor izvršiće se ova anonimna funkcija
			console.log('params')
			console.log('stigao odgovor!')


			if (data.status == 'failure') {
				/*console.log('ooooooo' + data.message);
				userNameInput.val('');
				passwordInput.val('');*/
				messageParagraph.text(data.message);
				return;
			}
			if (data.status == 'success') {
				window.location.replace('pocetna.html');
			}
		});
		// program se odmah nastavlja dalje, pre nego što stigne odgovor
		console.log('poslat zahtev!')

		// zabraniti da browser obavi podrazumevanu akciju pri događaju
		event.preventDefault();
		return false;
	});
});