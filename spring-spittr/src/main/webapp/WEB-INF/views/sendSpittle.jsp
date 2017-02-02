<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Send Spittle</title>
</head>
<body>
<h1>Send Spittle</h1>
<form method="POST">
    Title: <input type="text" name="title" /><br/>
    Message: <textarea cols="60" rows="2" type="text" name="message" >Text</textarea>
    <input type="submit" value="SendSpittle" />
</form>

</body>
</html>
