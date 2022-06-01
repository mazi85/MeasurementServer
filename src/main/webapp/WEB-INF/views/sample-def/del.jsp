<%--
  wg template Michała
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete sample Def confirmation</title>
</head>
<body>
<form method="post">
    <input type="hidden" value="${sampleDefId}" name="sampleDefId">
    USUNĄĆ DEFINICJĘ? <input type="submit" value="POTWIERDZAM"> <p>&emsp;</p> <a href="/meas-source/${measSourceId}/sample-def/list">ANULUJ</a>
</form>
</body>
</html>
