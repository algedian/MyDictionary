<!-- Document Service

* Show document list
* Create a new document
* Edit documents
* Delete documents 

Search Service

* Search By keyword
	* show the document of the user's
	* show the documents of friends'
	-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>Document Page</title>

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
<body>
	<div class="col-lg-6">
		<!-- Button trigger modal -->
		<p>
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#createModal">Create new Document</button>
		</p>
		<div class="input-group">
			<input type="text" class="form-control" id="searchDocumentsByKeyword"
				placeholder="Search documents By Keyword"> <span
				class="input-group-btn">
				<button  class="btn btn-default" type="button" onclick="searchDocument()">search!</button>
			</span>
		</div>
		<h2>Search Result</h2>
		<div id="searchedUserDocument" class="panel panel-info">
		
		</div>
		
		<h4>results from friends'</h4>
		<ul id="searchDocumentList" class="list-group">
		</ul>

		<!-- Create btn Modal -->
		<div class="modal fade" id="createModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<input type="text" id="newKeyword" class="form-control"
							placeholder="keyword" aria-describedby="sizing-addon1">
					</div>
					<div class="modal-body">
						<textarea class="form-control" id="newDocument"
							placeholder="contents"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" onclick="createDocument();">Create</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Create btn Modal -->

	</div>
</body>