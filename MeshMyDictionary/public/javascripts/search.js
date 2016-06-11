$.holdReady(true);
$.getScript("js/endpoint.js", function() {
    $.holdReady(false);
});

var getParameter = function (param) {
    var returnValue;
    var url = location.href;
    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');
    for (var i = 0; i < parameters.length; i++) {
        var varName = parameters[i].split('=')[0];
        if (varName.toUpperCase() == param.toUpperCase()) {
            returnValue = parameters[i].split('=')[1];
            return decodeURIComponent(returnValue);
        }
    }
};

var makeFriendsDictionaryList = function(dictionaryList, keyword) {
	var html = "";
	for(var i = dictionaryList.length - 1; i >= 0; i--) {
		var dictionary = dictionaryList[i];
		html += "<div class='list-group-item' id='friend-name'><h4>" + dictionary.userId + "'s " + keyword + "</h4></div>"
		 	  + "<div class='list-group-item' id='friend-dictionary'>" + dictionary.document
		 	  + "<br/><br/>";
		html += makeDictionaryTime(dictionary);
		html += "</div>" + "<p></p>";
	}
	
	return html;
};

var makeDictionaryTime = function(dictionary) {
	var date = "<strong>This document was created at " + new Date(dictionary.createTime).toUTCString() + "</strong>";
	if(dictionary.updateTime && dictionary.updateTime !== dictionary.createTime) {
		date += "<p></p><strong>This document was modified at " + new Date(dictionary.updateTime).toUTCString() + "</strong>";
	}
	
	return date;
};

$(document).ready(function() {
	var keyword = getParameter('keyword');
	
	$('input[name="keyword"]').val(keyword);
	if(keyword == '') {
		$('.success').hide();
	} else {
		$('.fail').hide();
		$('#dictionary-header').empty();
		$('#dictionary-header').append("<h1>" + keyword + "</h1>");
		searchKeyword(keyword, function(result) {
			var dictionary = result.dictionary;
			var friendsDictionaryList = result.friendsDictionaryList;
			
			if(dictionary) {
				$('#document').val(dictionary.document);
				$('#date').append(makeDictionaryTime(dictionary));
			}
			
			if(friendsDictionaryList.length === 0 ) {
				// 아몰랑!
			} else {
				$('#dictionaryList').append(makeFriendsDictionaryList(friendsDictionaryList, keyword));
			}
			// dictionaryList
			console.log(result);
		});
	}
	
/** time out fail!!!!!
 * 	$('.modal').on('shown', function () {
	    clearTimeout($(this).data('hideInteval'));
	    var id = setTimeout(function(){
	    	$(this).modal('hide');
	    });
	    $(this).data('hideInteval', id);
	});*/
	
	$('button#document-submit').click(function() {
		var document = $('#document').val();
		documentUpdate(keyword, document, function(result) {
			console.log(result);
			$("#submit-result-modal-success").modal('show');
		}, function(e) {
			$("#submit-result-modal-fail").modal('show');
		});
	});
});