<!-- Friend Service
* Show Friend List
* Find Friend by Email
	* Follow Friend
* Unfollow Friend  -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<title>Friend Page</title>

<meta charset="utf-8">

<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<!-- link href="../css/result.css" rel="stylesheet"> -->

<!-- Google Platfom Library api -->
<script src="https://apis.google.com/js/platform.js" async defer></script>

<!-- Specify google app client ID -->
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="768889397569-ifca7s46aplo8i4ikt95ba6ihjmmfdlf.apps.googleusercontent.com">

<script src="<c:url value="/resources/js/demo.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/demo.css"/>">

</head>
<body onload="getFriendList();">
	<div id="searchFriend">
		<div class="inline">
			<label for="searchFriendByEmail">Search Friend</label>
			<input type="text" id="searchFriendByEmail" placeholder="Search a Friend By Email">
			<button type="button" class="btn btn-default" onclick="searchFriend();">Find</button>
		</div>
		<div>
			<h2>Search Result</h2>
			<ul id="searchFriendList" class="list-group"></ul>
		</div>
	</div>
	<div>
		<h2>Friend List</h2>
		<ul id="friendList" class="list-group"></ul>
	</div>
</body>