<%@ page import="com.example.portalstudentee.model.Course" %><%--
  Created by IntelliJ IDEA.
  User: karen
  Date: 08.01.26
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Course</title>
</head>
<% Course course = (Course) request.getAttribute("course"); %>
<body>

<a href="/">Home</a>
<a href="/courses">Courses</a>

<form action="/changeCourse" method="post">
    <input type="hidden" name="id" value="<%=course.getId()%>">
  Name: <input type="text" name="name" value="<%=course.getName()%>"><br>
  Price: <input type="number" name="price" value="<%=course.getPrice()%>"><br>
  <input type="submit" value="Change Course">
</form>

</body>
</html>
