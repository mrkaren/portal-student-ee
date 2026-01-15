<%--
  Created by IntelliJ IDEA.
  User: karen
  Date: 08.01.26
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Course</title>
</head>
<body>

<a href="/">Home</a>
<a href="/courses">Courses</a>

<form action="/addCourse" method="post">
  Name: <input type="text" name="name"><br>
  Price: <input type="number" name="price"><br>
  <input type="submit" value="Add Course">
</form>

</body>
</html>
