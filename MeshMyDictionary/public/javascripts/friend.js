$.holdReady(true);
$.getScript("js/endpoint.js", function() {
    $.holdReady(false);
});

var makeFriendList = function(friendList) {
	var html = "";
	for(var i = 0; i < friendList.length; i++) {
		html += "<div class='list-group-item' id='friend-name'>"
			+ "<div class='row'>"
			+ "<div class='col-xs-10'><strong>" + friendList[i].name + "</strong></div>"
			+ "<div class='col-xs-2'>"
			+ "<a class='btn icon-btn btn-danger pull-right unfollow' data-index='" + friendList[i].index + "' href='#'>"
			+ "<span class='glyphicon btn-glyphicon glyphicon-minus img-circle text-danger'></span>"
			+ "Remove"
			+ "</a>"
			+ "</div>" /* end of col-xs-2 */
			+ "</div>" /* end of row */
			+ "</div>" /* end of list-group-item */
			+ "<div class='list-group-item' id='friend-email'>" + friendList[i].email + "</div>"
			+ "<br />"
			+ "<br />";
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
