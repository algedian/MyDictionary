var colorRed    = '\u001b[31m';
var colorGreen  = '\u001b[32m';
var colorYellow = '\u001b[33m';
var colorBlue   = '\u001b[34m';
var colorPuple  = '\u001b[35m';
var colorBG     = '\u001b[36m';
var colorTF     = '\u001b[37m';
var colorWhite  = '\u001b[1m';

exports.log = function(data) {
	console.log(colorPuple, data);
};

exports.logYellow = function(data) {
	console.log(colorYellow, data);
};

exports.logBlue = function(data) {
	console.log(colorBlue, data);
};

exports.logErr = function(data) {
	console.log(colorRed, data);
};