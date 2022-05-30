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
    <title>Edit measuring source</title>

</head>

<body>
<form:form method="POST" modelAttribute="editMeasSourceForm">
    Nazwa: <form:input path="name"/>
    <br/>
    <form:errors path="name"/>
    <br/>
    ip: <form:input path="ip"/>
    <br/>
    <form:errors path="ip"/>
    <br/>
    port: <form:input path="port"/>
    <br/>
    <form:errors path="port"/>
    <br/>

    Protokół:
    <form:select path="commProtocol.id" itemValue="id" itemLabel="name" items="${protocols}"/>
    <br/>
    <form:errors path="commProtocol"/>
    <br/>

    <input type="hidden" name="measSourceId" value="${measSourceId}">
    <input type="submit">
</form:form>
</body>
</html>
