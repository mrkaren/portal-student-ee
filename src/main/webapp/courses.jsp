<%@ page import="com.example.portalstudentee.model.Course" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
<% List<Course> courses = (List<Course>) request.getAttribute("courses"); %>
<a href="/">Home</a>
<h2>Courses</h2>

<a href="/addCourse">Add Course</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>price</th>
        <th>Action</th>
    </tr>
    <% for (Course course : courses) { %>
    <tr>
        <td><%=course.getId()%></td>
        <td><%=course.getName()%> </td>
        <td><%=course.getPrice()%></td>
        <td><a href="/deleteCourse?id=<%=course.getId()%>">delete</a> | <a href="/changeCourse?id=<%=course.getId()%>">change</a> </td>
    </tr>
    <%}%>
</table>
</body>
</html>
