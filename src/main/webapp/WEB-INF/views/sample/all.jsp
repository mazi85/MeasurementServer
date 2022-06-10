<%--
  wg template Michała
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${samples}" var="sample">
    ${sample.sampleDef.name} ${sample.engValue} ${sample.sampleDef.unit} <br/>
</c:forEach>

<div>
    <h4><a href="/meas-source/list">POWRÓT</a> </h4>
</div>

</body>
</html>
