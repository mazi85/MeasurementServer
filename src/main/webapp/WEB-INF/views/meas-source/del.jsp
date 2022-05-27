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
    <title>Delete meas source confirmation</title>
</head>
<body>
<form method="post">
    <input type="hidden" value="${measSourceId}" name="measSourceId">
    USUNĄĆ ŹRÓDŁO i JEGO DANE? <input type="submit" value="POTWIERDZAM"> <p>&emsp;</p> <a href="/meas-source/list">ANULUJ</a>
</form>
</body>
</html>
