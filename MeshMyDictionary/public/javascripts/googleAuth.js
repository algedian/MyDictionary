$.holdReady(true);
$.getScript("js/endpoint.js", function() {
	$.holdReady(false);
});

$(document).ready(function() {
	$.getScript("js/google/platform.js", function() {
		 gapi.load('auth2,signin2', function() {
			var auth2 = gapi.auth2.init();
			auth2.then(function() {
				var isSignedIn = auth2.isSignedIn.get();
				var currentUser = auth2.currentUser.get();
				var q = getParameterByName('q');
				
				if (isSignedIn && q && q === 'signOut') {
					signOut();
				}
				gapi.signin2.render('customBtn', {
			        'scope': 'profile email',
			        'width': 200,
			        'height': 50,
			        'longtitle': true,
			        'theme': 'light',
			        'onsuccess': onSignIn,
			        'onfailure': onFailure
				});
			});
		});
	});
});

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
		console.log('User signed out.');
		setSignOut(function(result) {
			window.location = window.location.pathname;
		});
	});
}

var onSignIn = function(googleUser) {
	// Useful data for client-side scripts:
	var profile = googleUser.getBasicProfile();
	console.log("ID: " + profile.getId());
	console.log('Full Name: ' + profile.getName());
	console.log('Given Name: ' + profile.getGivenName());
	console.log('Family Name: ' + profile.getFamilyName());
	console.log("Image URL: " + profile.getImageUrl());
	console.log("Email: " + profile.getEmail());

	// The ID token need to pass to backend:
	var idToken = googleUser.getAuthResponse().id_token;
	console.log("ID Token: " + idToken);
    
	var success = function(result) {
		console.log("result: ", result);
		location.href = '/main';
	};
    var fail = function(err){
        console.log("err: ", err);
    };
    
    login(idToken, success, fail);
}

var onFailure = function(error) {
	console.log(error);
}