<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Class</th>
        <th>Singleton</th>
    </tr>
    <c:forEach items="${contextData.beansInfo}" var="beanInfo">
        <tr>
            <td>${beanInfo.id}</td>
            <td>${beanInfo.clazz}</td>
            <td>${beanInfo.singleton}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
