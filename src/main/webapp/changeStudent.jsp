<%@ page import="com.example.portalstudentee.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.portalstudentee.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Student</title>
</head>
<body>
<% Student student = (Student) request.getAttribute("student"); %>
<% List<Course> courses = (List<Course>) request.getAttribute("courses"); %>
Change Student

<form action="/changeStudent" method="post">
    <input type="hidden" name="id" value="<%=student.getId()%>"><br>
    Name: <input type="text" name="name" value="<%=student.getName()%>"><br>
    Surname: <input type="text" name="surname" value="<%=student.getSurname()%>"><br>
    Email: <input type="text" name="email" value="<%=student.getEmail()%>"><br>
    Course: <select name="course_id">
    <% for (Course course : courses) { %>
    <% if (course.getId() == student.getCourse().getId()) { %>
    <option value="<%=course.getId()%>" selected><%=course.getName()%>
    </option>
    <%} else { %>
    <option value="<%=course.getId()%>"><%=course.getName()%>
    </option>

    <%}%>
    <% }%>
</select>
    <input type="submit" value="Submit">
</form>
</body>
</html>
