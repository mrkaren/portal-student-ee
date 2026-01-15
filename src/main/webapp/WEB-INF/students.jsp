<%@ page import="java.util.List" %>
<%@ page import="com.example.portalstudentee.model.Student" %>
<%@ page import="com.example.portalstudentee.model.User" %>
<%@ page import="com.example.portalstudentee.model.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students - Student Portal</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<% List<Student> students = (List<Student>) request.getAttribute("students"); %>
<% User user = (User) session.getAttribute("user"); %>

<div class="container">
    <header class="page-header">
        <a href="${pageContext.request.contextPath}/" class="back-link">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M19 12H5M12 19l-7-7 7-7"/>
            </svg>
            Back to Home
        </a>
        <h1>Students</h1>
        <p class="subtitle">Manage student records</p>
    </header>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/addStudent" class="btn">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none"
                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <line x1="12" y1="5" x2="12" y2="19"></line>
                <line x1="5" y1="12" x2="19" y2="12"></line>
            </svg>
            Add Student
        </a>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Email</th>
                <th>Course</th>
                <%if (user.getRole() == UserRole.ADMIN) {%>
                <th>Actions</th>
                <%}%>

            </tr>
            </thead>
            <tbody>
            <% for (Student student : students) { %>
            <tr>
                <td><%= student.getId() %>
                </td>
                <td><%= student.getName() %>
                </td>
                <td><%= student.getSurname() %>
                </td>
                <td><%= student.getEmail() %>
                </td>
                <td><%= student.getCourse().getName() %>
                </td>
                <%if (user.getRole() == UserRole.ADMIN) {%>

                <td class="actions-cell">
                    <a href="${pageContext.request.contextPath}/changeStudent?id=<%= student.getId() %>"
                       class="btn-small btn-edit">Edit</a>
                    <a href="${pageContext.request.contextPath}/deleteStudent?id=<%= student.getId() %>"
                       class="btn-small btn-delete">Delete</a>
                </td>
                <%}%>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
