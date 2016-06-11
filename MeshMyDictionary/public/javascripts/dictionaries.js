$.holdReady(true);
$.getScript("js/endpoint.js", function() {
    $.holdReady(false);
});

var makeDocumentList = function(roadMap) {
	var html = "";
	for(var i = roadMap.length - 1; i >= 0; i--) {
		html += "<div class='row'>"
			 + "<div class='col-xs-10 item-keyword'>"
			 + "<a href='" + '#' + "' class='list-group-item modify' data-keyword='" + roadMap[i].keyword + "'><strong>" + roadMap[i].keyword + "</strong></a>"
			 + "</div>" /* end of col-xs-8 */
			 + "<div class='col-xs-2'>"
			 + "<a class='btn icon-btn btn-danger pull-right remove' data-keyword='" + roadMap[i].keyword + "' href='#'>"
			 + "<span class='glyphicon btn-glyphicon glyphicon-minus img-circle text-danger'></span>"
			 + "Remove"
			 + "</a>"
			 + "</div>" /* end of col-xs-4 */
			 + "</div>"  /* end of row */
			 + "<p></p>";
	}
	
	return html;
};

$(document).ready(function() {
	getDocumentRoadMap(function(result) {
		if (result.roadMap.length === 0) {
			var html = "<h4>no dictionary</h4>";	
			$("#documentList").append(html);
		} else {
			$("#documentList").append(makeDocumentList(result.roadMap));
			$('.modify').click(function() {
				var keyword = $(this).data('keyword');
				
				location.href = '/search?keyword=' + keyword;
			});
			$('.remove').click(function() {
				var keyword = $(this).data('keyword');
				console.log(keyword);
				
				$('#remove-item-keyword').empty();
				$('#remove-item-keyword').data('keyword', keyword);
				$('#remove-item-keyword').append("<strong>" + keyword + "</strong>");
				
				$('#remove-item-modal').modal('show');
			});
			$('#remove-document').click(function() {
				var keyword = $('#remove-item-keyword').data('keyword');
				
				documentDelete(keyword, function(result) {
					location.reload();
				});
			});
		}
	});
});