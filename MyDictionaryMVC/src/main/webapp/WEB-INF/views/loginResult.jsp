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
</head>

<body>

	<h1>This is a test page for google web login ${LoginResult}</h1>

	<a href="#" onclick="signOut();">Sign out</a>

	<script>
		function signOut() {
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function() {
				console.log('User signed out.');
			});
		}
	</script>
</body>
</html>
