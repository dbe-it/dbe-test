<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<h1>A Splittle</h1>
<p>
<ul>
    <li id="spittle_<c:out value="spittle.id"/>">
        <div class="spittleMessage">
            <c:out value="${spittle.message}"/>
        </div>
        <div>
            <span class="spittleTime"><c:out value="${spittle.time}"/></span>
            <span class="spittleLocation">
(<c:out value="${spittle.latitude}"/>,
<c:out value="${spittle.longitude}"/>)</span>
        </div>
    </li>
</ul>
</p>
<p>
    <a href="<c:url value="/spittles" />">Show Spittles</a> |
    <a href="<c:url value="/home" />">Home</a>
</p>
</body>
</html>
