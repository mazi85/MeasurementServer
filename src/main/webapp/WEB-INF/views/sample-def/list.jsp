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
    <title>Measuring source registers</title>

    <style>
        table,th, td{
            border: 2px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h2>${measSourceName}</h2>
<div>
    <h4><a href="/meas-source/${measSourceId}/sample-def/add">+ DODAJ REJESTR</a> </h4>
</div>

<table style="background-color: lightsteelblue">
    <thead style="background-color: gray">
    <tr >
        <th>Nazwa</th>
        <th>numer rejestru</th>
        <th>Zakres</th>
        <th>Jednostka</th>
        <th>ACTIONS</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sampleDef" items="${listSampleDefForm}">
        <tr>
            <td style="width: 150px"> ${sampleDef.name} </td>
            <td style="width: 150px"> ${sampleDef.register} </td>
            <td style="width: 150px"> ${sampleDef.lowRange} - ${sampleDef.highRange} </td>
            <td style="width: 150px"> ${sampleDef.unit} </td>


            <td style="width: 500px">
                <a href="/meas-source/delete/${sampleDef.id}">USUŃ</a>
                <a href="/meas-source/edit/${sampleDef.id}">EDYTUJ</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <h4><a href="/meas-source/list">POWRÓT</a> </h4>
</div>

</body>
</html>
