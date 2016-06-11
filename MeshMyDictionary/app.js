var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var session = require('express-session');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
// app.set('view engine', 'ejs');
app.set('view engine', 'html');
app.engine('html', require('ejs').renderFile);

app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
// app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
	extended : false
}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use('/js', express.static(path.join(__dirname, 'public/javascripts')));
app.use('/css', express.static(path.join(__dirname, 'public/stylesheets')));
app.use('/css/fonts', express.static(path.join(__dirname, 'public/fonts')));

// session setting
var secret = 'fhdlldfdfjfdj';
app.use(session({
	secret : secret,
	resave : false,
	saveUninitialized : true,
	store : new session.MemoryStore(),
	maxAge : 3600000
}));
app.use(function(req, res, next) {
	res.locals.session = req.session;
	next();
});


/**
 * route list
 * */
var serviceManager = require('./routes/servicemanager');
app.get('/', serviceManager.index);
app.get('/main', serviceManager.main);
app.get('/friend', serviceManager.friend);
app.get('/documentRoadMap', serviceManager.documentRoadMap);

app.get('/search', serviceManager.search);

/**
 * end point
 * */
app.post('/endpoint/login', serviceManager.login);
app.post('/endpoint/logout', serviceManager.logout);
app.post('/endpoint/initSession', serviceManager.initSession);
app.post('/endpoint/getUserDocumentList', serviceManager.getUserDocumentList);
app.post('/endpoint/getDocumentRoadMap', serviceManager.getDocumentRoadMap);
app.post('/endpoint/getFriendList', serviceManager.getFriendList);
app.post('/endpoint/followFriend', serviceManager.followFriend);
app.post('/endpoint/unfollowFriend', serviceManager.unfollowFriend);

app.post('/endpoint/searchDocumentByKeyword', serviceManager.searchDocumentByKeyword);
app.post('/endpoint/searchMetaByKeyword', serviceManager.searchMetaByKeyword);

app.post('/endpoint/documentUpdate', serviceManager.documentUpdate);
app.post('/endpoint/documentDelete', serviceManager.documentDelete);

// error handlers
// catch 404 and forward to error handler
app.use(function(req, res, next) {
	var err = new Error('Not Found');
	err.status = 404;
	next(err);
});

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
	app.use(function(err, req, res, next) {
		res.status(err.status || 500);
		res.render('error', {
			message : err.message,
			error : err
		});
	});
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
	res.status(err.status || 500);
	res.render('error', {
		message : err.message,
		error : {}
	});
});

module.exports = app;

//npm install
//npm start
