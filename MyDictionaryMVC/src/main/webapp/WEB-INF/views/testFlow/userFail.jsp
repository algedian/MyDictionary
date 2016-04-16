<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Goodbye world!  
</h1>
<h1>Login Fail..</h1>
<p> index	:	${user.index} </p>
<p> userId	:	${user.userId} </p>
<p> name	:	${user.name} </p>
<p> email	:	${user.email} </p>
<p> pictureURL	:	${user.pictureURL} </p>
</body>
</html>
