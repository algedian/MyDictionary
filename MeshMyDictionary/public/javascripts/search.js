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

var makeScrapHtml = function(title, link, data) {
	title = title.replace(/'/g, "");
	link = link.replace(/'/g, "");
	data = data.replace(/'/g, "");
	var html = "<div class='col-xs-1'>";
		html += "<button type='button' class='btn btn-info btn-sm scrap' data-title='" + title + "' data-link='" + link + "' data-data='" + data + "'>Scrap</button>";
		html += "</div>";
	return html;
};

var makeTextHTML = function(site, link, title, content) {
	var html = "<a class='list-group-item' id='search-result-title' href='" + link + "' target='_blank'>";
	html += title + "<strong> -- " + site + "</strong>";
	html += "</a>";
    html += "<div class='list-group-item' id='search-result-content'>";
    html += "<div class='row'>";
    html += "<div class='col-xs-11'>" + content + "</div>";
    html += makeScrapHtml(title, link, content);
    html += "</div>"; /* end of row */
    html += "</div>"; /* end of list-group-item */
	
    return html;
};

var makeBlogResult = function(daum, naver) {
	var html = "";
	
    if(typeof(daum) !== 'undefined') {
        for(var i = daum.items.length - 1; i >= 0; i--) {
            var temp = daum.items[i].description == '' ? "" : $.parseHTML(daum.items[i].description)[0].data;
            html += makeTextHTML('daum', daum.items[i].link, $.parseHTML(daum.items[i].title)[0].data, temp);
            html += "<p></p>";
        }
}
	if(typeof(naver) !== 'undefined') {
		for(var i = naver.items.length - 1; i >= 0; i--) {
            html += makeTextHTML('naver', naver.items[i].link, naver.items[i].title, naver.items[i].description);
            html += "<p></p>";
		}
	}
	
	return html;
};

var makeEncyclopediaResult = function(naver) {
	var html = "";
	
	if(typeof(naver) !== 'undefined') {
		for(var i = naver.items.length - 1; i >= 0; i--) {
			html += "<a class='list-group-item' id='search-result-title' href='" + naver.items[i].link + "' target='_blank'>";
			html += naver.items[i].title + "<strong> -- Naver</strong>";
			html += "</a>";
			html += "<div class='list-group-item' id='search-result-content'>";
			html += "<div class='row'>"
			html += "<div class='col-xs-2'>";
			html += "<img src='" + naver.items[i].thumbnail + "' class='img-rounded' alt='Null img' width='100%' height='30'>";
			html += "</div>"; /* end of col-xs-2 */
			html += "<div class='col-xs-9' id='search-result-content'>" + naver.items[i].description + "</div>"
			html += makeScrapHtml(naver.items[i].title, naver.items[i].link, naver.items[i].description);
			html += "</div>"; /* end of row */
			html += "</div>"; /* end of list-group-item */
			html += "<p></p>";
		}
	}
	
	return html;
};

var makeImageResult = function(daum, naver) {
	var html = "";
	var j = 0;
	
	if(typeof(daum) !== 'undefined') {
		for(var i = daum.items.length - 1; i >= 0; i--) {
			if(j === 0) {
				html += "<div class='row'>";
				j = 6;
			}
			html += "<div class='list-group-item col-xs-2' id='search-result-image-content'>";
			html += "<a href='" + daum.items[i].link + "' target='_blank'>"
				  + "<img src='" + daum.items[i].thumbnail + "' class='img-rounded' alt='Cinque Terre' width='100%' height='60'>"
				  + "</div>"
				  + "</a>";
			if(j - 1 === 0) {
				html += "</div>";
			}
			j--;
		}
	}
	if(typeof(naver) !== 'undefined') {
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
		if(j - 1 !== -1) {
			html += "</div>";
		}
	}
	
	return html;
};

var makeVideoResult = function(daum, youtube) {
	var html = "";
	for(var i = daum.items.length - 1; i >= 0; i--) {
		html += "<div class='list-group-item embed-responsive embed-responsive-16by9'>";
		html += "<iframe class='embed-responsive-item' src='" + daum.items[i].player_url + "'></iframe>"
		html += "</div>";
	}
	
	for(var i = youtube.items.length - 1; i >= 0; i--) {
		html += "<div class='list-group-item embed-responsive embed-responsive-16by9'>";
		html += "<iframe class='embed-responsive-item' src='http://www.youtube.com/embed/" + youtube.items[i].videoId + "'></iframe>"
		html += "</div>";
	}
	
	return html;
};

var makeSearchResult = function(result) {
	var html = "";
	
	if(typeof(result.blog) !== 'undefined' && result.blog !== null && result.blog.length !== 0) {
		html = "<div class='page-header'><h4>Blog</h4></div>";
		html += makeBlogResult(result.blog[0].daum, result.blog[1].naver);
	}
	if(typeof(result.encyclopedia) !== 'undefined' && result.encyclopedia !== null && result.encyclopedia.length !== 0) {
		html += "<div class='page-header'><h4>Encyclopedia</h4></div>";
		html += makeEncyclopediaResult(result.encyclopedia[0].naver);
	}
	if(typeof(result.image) !== 'undefined' && result.image !== null && result.image.length !== 0) {
		html += "<div class='page-header'><h4>Image</h4></div>";
		html += makeImageResult(result.image[0].daum, result.image[1].naver);
	}
	if(typeof(result.video) !== 'undefined' && result.video !== null && result.video.length !== 0) {
		html += "<div class='page-header'><h4>Video</h4></div>";
		html += makeVideoResult(result.video[0].daum, result.video[1].youtube);
	}
	
	return html;
};

var scrapHandler = function() {
	$('.scrap').click(function() {
		var title = $(this).data('title');
		var link = $(this).data('link');
		var data = $(this).data('data');
		
		var linkHTML = "<a href='" + link + "'>" + title + "</a>";
		$('#document').val($('#document').val() + "\n" + data + "\nref: " + linkHTML + "\n");
	    if($('#document').length) {
	    	$('#document').scrollTop($('#document')[0].scrollHeight - $('#document').height());
	    }
	});
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
				$('#dictionaryList').hide();
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
			scrapHandler();
		}, function(result) {
			var res = JSON.parse(result.responseText);
			console.log(res);
			$('#searchList').append(makeSearchResult(res.result));
			$('.loading').hide();
			scrapHandler();
		});
	}
	
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