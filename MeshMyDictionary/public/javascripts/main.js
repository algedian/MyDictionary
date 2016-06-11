$.holdReady(true);
$.getScript("js/endpoint.js", function() {
    $.holdReady(false);
});

$(document).ready(function() {
	initSession(function(result) {
		console.log(result);
		getDocumentList(function(result) {
			result.documentList.forEach(function(document) {
				var html = "<div class='page-header'>"
					+ "<h1>" + document.keyword + "</h1>"
					+ "</div>"
					+ "<div class='jumbotron' id='main-container'>"
					+ "<xmp>" + document.document + "</xmp>"
					+ "<br/><br/><strong>This document was created by " + result.userName + " at " + new Date(document.createTime).toUTCString() + "</strong>";
				if(document.updateTime && document.updateTime !== document.createTime) {
					html += "<p></p><strong>This document was modified at " + new Date(document.updateTime).toUTCString() + "</strong>";
				}
				
				html += "</div>";
					
				$("#documentList").append(html);
			});
		});
	});
});