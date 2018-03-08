<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
    <table border="1" cellpadding ="8" cellspacing="0">
        <tr>
            <th>Datetime</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <c:forEach items = "${listMeals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <c:if  test="${meal.exceed}">
                <tr  bgcolor="red">
            </c:if>
            <c:if  test="${!meal.exceed}">
                <tr  bgcolor="green">
            </c:if>

                <td>${fn:replace(meal.dateTime, "T"," ")}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>