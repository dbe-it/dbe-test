<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>Welcome to Spittr</h1>
<a href="<c:url value="/spittles" />">Show Spittles</a> |
<a href="<c:url value="/spitter/register" />">Register Spitter</a> |
<a href="<c:url value="/spittles/send" />">Send Spittle</a>
</body>
</html>
