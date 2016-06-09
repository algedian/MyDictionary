$.getScript("js/header.js");

function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
		console.log('User signed out.');
		$.ajax({
			url : meshServerAddress + '/logout',
			type : 'post',
			contentType : 'application/json;charset=UTF-8',
			success : function(result) {
				console.log("result: ", result);
			},
		    error: function(err){
		        console.log("err: ", err);
		    }
		});
	});
}

function onSignIn(googleUser) {
	// Useful data for your client-side scripts:
	var profile = googleUser.getBasicProfile();
	console.log("ID: " + profile.getId()); // Don't send this directly to your server!
	console.log('Full Name: ' + profile.getName());
	console.log('Given Name: ' + profile.getGivenName());
	console.log('Family Name: ' + profile.getFamilyName());
	console.log("Image URL: " + profile.getImageUrl());
	console.log("Email: " + profile.getEmail());

	// The ID token you need to pass to your backend:
	var idToken = googleUser.getAuthResponse().id_token;
	console.log("ID Token: " + idToken);
	
	$.ajax({
		url : meshServerAddress + '/login',
		type : 'post',
		contentType : 'application/json;charset=UTF-8',
		data : JSON.stringify({idToken:idToken}),
		success : function(result) {
			console.log("result: ", result);
			location.href = '/main';
		},
	    error: function(err){
	        console.log("err: ", err);
	    }
	});
}
