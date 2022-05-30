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
    <title>Edit sample definition</title>

</head>

<body>
<form:form method="POST" modelAttribute="editSampleDefForm">
    Nazwa: <form:input path="name"/>
    <br/>
    <form:errors path="name"/>
    <br/>
    Rejestr: <form:input path="register"/>
    <br/>
    <form:errors path="register"/>
    <br/>
    LR: <form:input path="lowRange"/>
    <br/>
    <form:errors path="lowRange"/>
    <br/>
    HR: <form:input path="highRange"/>
    <br/>
    <form:errors path="highRange"/>
    <br/>
    unit: <form:input path="unit"/>
    <br/>
    <form:errors path="unit"/>
    <br/>
    <input type="hidden" name="sampleDefId" value="${sampleDefId}">
    <input type="submit">
</form:form>
</body>
</html>
