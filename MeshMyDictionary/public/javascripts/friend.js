$.holdReady(true);
$.getScript("js/endpoint.js", function() {
    $.holdReady(false);
});

$(document).ready(function() {
	getFriendList(function(result) {
		if (typeof (result.friendList) === 'undefined') {
			var html = "no friend";	
			$("#friendList").append(html);
		} else {
			var html = "<div class='page-header'>"
				+ "<h1>Friends</h1>"
				+ "</div>"
				+ "<div class='list-group'>";
			result.friendList.forEach(function(friend) {
				html += "<div class='row'>"
					+ "<div class='list-group-item' id='friend-name'>" + friend.name
					+ "<button type='button' class='btn btn-danger pull-right'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button>"
					+ "</div>"
					+ "<div class='list-group-item' id='friend-email'>" + friend.email + "</div>"
					+ "</div>"
					+ "<p></p>";
					
				/*html += "<div class='list-group-item'>"
					+ "<div class='container-fluid' id='test-red'><h4 class='list-group-item-heading'>" + friend.name + "</h4></div>"
					+ "<div class=container-fluid' id='test-blue'><button type='button' class='btn btn-default'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span</button></div>" 
					+ "<p class='list-group-item-text'>" + friend.email + "</p>"
					+ "</div>";*/
			});
			html += "</div>";
			$("#friendList").append(html);
		}
	});
});