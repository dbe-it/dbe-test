<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
    <meta http-equiv="Content-Type" content="application/json">
</head>
<body>
<h1>Recent Spittles</h1>
<p>
<ul>
    <c:forEach items="${spittleList}" var="spittle">
        <li id="spittle_<c:out value="spittle.id"/>">
            <div class="spittleMessage">
                <a href="<c:url value="/spittles/${spittle.id}" />"><c:out value="${spittle.message}"/></a>
            </div>
            <div>
                <span class="spittleTime"><c:out value="${spittle.time}"/></span>
                <span class="spittleLocation">
            (<c:out value="${spittle.latitude}"/>,
            <c:out value="${spittle.longitude}"/>)</span>
            </div>
        </li>
    </c:forEach>
</ul>
</p>

<p>
    <a href="<c:url value="/home" />">Home</a>
</p>

</body>
</html>
