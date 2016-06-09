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
app.use(logger('dev'));
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
app.all('/', serviceManager.index);
app.all('/main', serviceManager.main);
app.all('/friend', serviceManager.friend);

/**
 * end point
 * */
app.post('/login', serviceManager.login);
app.post('/logout', serviceManager.logout);
app.post('/getUserDocumentList', serviceManager.getUserDocumentList);
app.post('/getFriendList', serviceManager.getFriendList);

app.all('/getDocument', serviceManager.getDocument);

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
