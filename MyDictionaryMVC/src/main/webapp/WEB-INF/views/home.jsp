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

<P>  The time on the server is ${serverTime}. </P>

<form:form id="loginForm" method="post" action="loginTest" modelAttribute="loginBean">
	<form:label path="userId">ID </form:label>
	<form:input path="userId" /> <br />
	<div>
		<input type="submit" value="submit" />
	</div>
</form:form> 
</body>
</html>
