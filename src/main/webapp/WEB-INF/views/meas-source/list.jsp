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
    <title>Measuring source list</title>

    <style>
        table,th, td{
            border: 2px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<div>
    <h4><a href="/meas-source/add">+ DODAJ OBIEKT</a> </h4>
</div>

<table style="background-color: lightsteelblue">
    <thead style="background-color: gray">
    <tr >
        <th>Nazwa</th>
        <th>Połączenie</th>
        <th>Pomiary:rejestry</th>
        <th>Rejestracja danych</th>
        <th>ACTIONS</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="measSource" items="${listMeasSourceForm}">
        <tr>
            <td style="width: 150px"> ${measSource.name} </td>
            <td style="width: 150px"> ${measSource.connectionString} </td>
            <td style="width: 150px">
                <c:forEach var="sampleDef" items="${measSource.sampleDefs}">
                    ${sampleDef.name} : ${sampleDef.register}<br/>
                </c:forEach>
            </td>
            <td style="width: 150px"> ${measSource.recording} </td>

            <td style="width: 500px">
                <a href="/meas-source/delete/${measSource.id}">USUŃ</a>
                <a href="/meas-source/edit/${measSource.id}">EDYTUJ</a>
                <a href="/meas-source/${measSource.id}/sample-def/list">DODAJ REJ.</a>
                <a href="/read/${measSource.id}">POMIAR</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
