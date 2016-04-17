<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Login Test</title>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="768889397569-ifca7s46aplo8i4ikt95ba6ihjmmfdlf.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>

<body>

	<h1>This is a test page for google web login</h1>

	<div class="g-signin2" data-onsuccess="onSignIn"></div>

	<a href="#" onclick="signOut();">Sign out</a>

	<script>
		function signOut() {
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function() {
				console.log('User signed out.');
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
			var id_token = googleUser.getAuthResponse().id_token;
			console.log("ID Token: " + id_token);

			var xhr = new XMLHttpRequest();
			xhr.open('POST', 'http://localhost:8080/ajou/user/login');
			//xhr.open('POST', 'http://dijkstra.ajou.ac.kr:8080/MyDictionaryMVC/login');
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			xhr.onload = function() {
				console.log('Signed in as: ' + xhr.responseText);
				getUserByEmailTest();
				window.location= "user/loginTestFlow";
			};

			xhr.send('idtoken=' + id_token);
		};

		function getUserByEmailTest() {

			var httpRequest;
			if (window.XMLHttpRequest) { // 모질라, 사파리등 그외 브라우저, ...
				httpRequest = new XMLHttpRequest();
			} else if (window.ActiveXObject) { // IE 8 이상
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}

			//var email = encodeURI(encodeURIComponent('borichyaa@gmail.com'));
			var email = 'borichyaa@gmail.com';
			var emailarr = email.split("@");
			var id = emailarr[0];
			var domain = emailarr[1];
			console.log(domain);
			/*httpRequest.onreadystatechange = function(data) {
				if (httpRequest.readyState === 4) {
					if (httpRequest.status === 200) {
						console.log(httpRequest.responseText);
					} else {
						console.log(data);
						console.log('There was a problem with the request.');
					}
				}
			};
			
			httpRequest.open('POST', 'http://localhost:8080/ajou/user/' + "'" + 'borichyaa@gmail.com' + "'", true);
			//httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			httpRequest.setRequestHeader('Accept', 'application/json');
			httpRequest.send(domain);
			*/
			$.ajax({
				url:'http://localhost:8080/ajou/user/getUserByEmail',
				type: 'post',
				contentType: 'text/plain',
				data: email,
				dataType: 'json',
				success: function(data){
					console.log(data);
				}
			});
		}
	</script>
</body>
</html>
