$.holdReady(true);
$.getScript("js/endpoint.js", function() {
    $.holdReady(false);
});

var makeFriendList = function(friendList) {
	var html = "";
	for(var i = 0; i < friendList.length; i++) {
		html += "<div class='row'>"
			+ "<div class='list-group-item' id='friend-name'>" + friendList[i].name
			//+ "<button type='button' class='btn btn-danger pull-right' onclick='unfollowFriend(" + friend.index + ")'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span></button>"
			+ "<a class='btn icon-btn btn-danger pull-right unfollow' data-index='" + friendList[i].index + "' href='#'>"
			+ "<span class='glyphicon btn-glyphicon glyphicon-minus img-circle text-danger'></span>"
			+ "Remove"
			+ "</a>"
			+ "</div>"
			+ "<div class='list-group-item' id='friend-email'>" + friendList[i].email + "</div>"
			+ "</div>"
			+ "<p></p>";
			
		/*html += "<div class='list-group-item'>"
			+ "<div class='container-fluid' id='test-red'><h4 class='list-group-item-heading'>" + friend.name + "</h4></div>"
			+ "<div class=container-fluid' id='test-blue'><button type='button' class='btn btn-default'><span class='glyphicon glyphicon-minus' aria-hidden='true'></span</button></div>" 
			+ "<p class='list-group-item-text'>" + friend.email + "</p>"
			+ "</div>";*/
	}
	
	return html;
};

var showList = function(result) {
	$("#friendList").empty();
	$("#friendList").append(makeFriendList(result.friendList));
	$('.unfollow').click(function() {
		var index = $(this).data('index');
		unfollowFriend(index, function(result) {
			console.log(result);
			showList(result);
		}, function(e) {
			console.log(e);
		});
	});
};

$(document).ready(function() {
	$('#friend-add-result').hide();
	getFriendList(function(result) {
		if (result.friendList.length === 0) {
			var html = "<h4>no friend</h4>";	
			$("#friendList").append(html);
		} else {
			showList(result);
		}
	});
});

$('.pull-down').each(function() {
	var $this = $(this);
	$this.css('margin-top', $this.parent().height() - $this.height())
});

$('button#friend-add-btn').click(function() {
	var email = $('form#friend-add').find('input[id=friend-email]').val();
	followFriend(email, function(result) {
		console.log(result);
		$('#friend-add-result').hide();
		showList(result);
	}, function(e) {
		if(!e.responseText) {
			console.log(e);
			return;
		}
		
		var response = JSON.parse(e.responseText);
		console.log(response);
		if(response.status === 1) {
			console.log('is already friend');
			$('#friend-add-result').show();
			$('#friend-add-result').empty();
			$('#friend-add-result').html('<strong>Warning!</strong> This email is already friend');
		} else if(response.status === 2) {
			$('#friend-add-result').show();
			$('#friend-add-result').empty();
			$('#friend-add-result').html('<strong>Warning!</strong> This email is not exist');
		} else {
			// 이런 경우가 있나?
		}
	});
});
