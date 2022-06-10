<%--
  wg template Michała
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${samples}" var="sample">
    <div>${sample.sampleDef.name} - ${sample.engValue} ${sample.sampleDef.unit}</div>
</c:forEach>

<div>
    <h4><a href="/meas-source/list">POWRÓT</a> </h4>
</div>


<%--<spring:url value="refresh.js" var="empFormJS" />--%>
<%--<script src="${empFormJS}"></script>--%>
<%--<script src="js/refresh.js></script>--%>
<script src="<c:url value="/static/js/refresh.js" />"></script>
</body>
</html>
