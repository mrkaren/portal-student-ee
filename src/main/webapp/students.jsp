<%@ page import="java.util.List" %>
<%@ page import="com.example.portalstudentee.model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
<% List<Student> students = (List<Student>) request.getAttribute("students"); %>
<a href="/">Home</a>

<a href="/addStudent">Add Student</a>

<h2>Students</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Course Name</th>
        <th>Action</th>
    </tr>
    <% for (Student student : students) { %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%> </td>
        <td><%=student.getSurname()%></td>
        <td><%=student.getEmail()%></td>
        <td><%=student.getCourse().getName()%></td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a> | <a href="/changeStudent?id=<%=student.getId()%>">change</a> </td>
    </tr>
    <%}%>
</table>
</body>
</html>
