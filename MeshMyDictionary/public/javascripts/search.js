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

var makeBlogResult = function(daum, naver) {
	var html = "";
	
	if(typeof(daum) !== 'undefined') {
		for(var i = daum.items.length - 1; i >= 0; i--) {
			html += "<a class='list-group-item' id='search-result-title' href='#'>" + daum.items[i].comment + "<strong> -- Daum</strong>" + "</a>";
			html += "<div class='list-group-item' id='search-result-content'>" + daum.items[i].comment + "</div><p></p>";
		}
	}
	if(typeof(naver) !== 'undefined') {
		for(var i = naver.items.length - 1; i >= 0; i--) {
			html += "<a class='list-group-item' id='search-result-title' href='" + naver.items[i].link + "' target='_blank'>" + naver.items[i].title + "<strong> -- Naver</strong>" + "</a>";
			html += "<div class='list-group-item' id='search-result-content'>" + naver.items[i].description + "</div><p></p>";
		}
	}
	
	return html;
};

var makeEncyclopediaResult = function(naver) {
	var html = "";
	
	if(typeof(naver) !== 'undefined') {
		for(var i = naver.items.length - 1; i >= 0; i--) {
			html += "<a class='list-group-item' id='search-result-title' href='" + naver.items[i].link + "' target='_blank'>" + naver.items[i].title + "<strong> -- Naver</strong>" + "</a>";
			html += "<div class='list-group-item' id='search-result-content'>";
			html += "<div class='row'>"
			html += "<div class='col-xs-2'>"
				  + "<img src='" + naver.items[i].thumbnail + "' class='img-rounded' alt='Cinque Terre' width='100%' height='30'>"
				  + "</div>";
			html += "<div class='col-xs-10' id='search-result-content'>" + naver.items[i].description + "</div>"
			html += "</div></div>";
			html += "<p></p>";
		}
	}
	
	return html;
};

var makeImageResult = function(daum, naver) {
	var html = "";
	
	if(typeof(daum) !== 'undefined') {
/*		for(var i = daum.items.length - 1; i >= 0; i--) {
			html += "<a class='list-group-item' id='search-result-title' href='#'>" + daum.items[i].comment + "<strong> -- Daum</strong>" + "</a>";
			html += "<div class='list-group-item' id='search-result-content'>" + daum.items[i].comment + "</div><p></p>";
		}*/
	}
	if(typeof(naver) !== 'undefined') {
		var j = 0;
		for(var i = naver.items.length - 1; i >= 0; i--) {
			if(j === 0) {
				html += "<div class='row'>";
				j = 6;
			}
			html += "<div class='list-group-item col-xs-2' id='search-result-image-content'>";
			html += "<a href='" + naver.items[i].link + "' target='_blank'>"
				  + "<img src='" + naver.items[i].thumbnail + "' class='img-rounded' alt='Cinque Terre' width='100%' height='60'>"
				  + "</div>"
				  + "</a>";
			if(j - 1 === 0) {
				html += "</div>";
			}
			j--;
		}
	}
	
	return html;
};

var makeSearchResult = function(result) {
	var html = "";
	
	if(result.blog !== null && result.blog.length !== 0) {
		html = "<div class='page-header'><h4>Blog</h4></div>";
		html += makeBlogResult(result.blog[0].daum, result.blog[1].naver);
	}
	if(result.encyclopedia !== null && result.encyclopedia.length !== 0) {
		html += "<div class='page-header'><h4>Encyclopedia</h4></div>";
		html += makeEncyclopediaResult(result.encyclopedia[0].naver);
	}
	if(result.image !== null && result.image.length !== 0) {
		html += "<div class='page-header'><h4>Image</h4></div>";
		html += makeImageResult(result.image[0].daum, result.image[1].naver);
	}
	
	return html;
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
		
		search(keyword, function(result) {
			console.log(result);
			$('#searchList').append(makeSearchResult(result));
			$('.loading').hide();
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