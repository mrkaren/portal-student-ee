<%@ page import="com.example.portalstudentee.model.Course" %>
<%@ page import="com.example.portalstudentee.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.portalstudentee.model.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Courses - Student Portal</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<% List<Course> courses = (List<Course>) request.getAttribute("courses"); %>
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
        <h1>Courses</h1>
        <p class="subtitle">Manage your course catalog</p>
    </header>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/addCourse" class="btn">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none"
                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <line x1="12" y1="5" x2="12" y2="19"></line>
                <line x1="5" y1="12" x2="19" y2="12"></line>
            </svg>
            Add Course
        </a>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <%if (user.getRole() == UserRole.ADMIN) {%>
                <th>Actions</th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <% for (Course course : courses) { %>
            <tr>
                <td><%= course.getId() %>
                </td>
                <td><%= course.getName() %>
                </td>
                <td>$<%= course.getPrice() %>
                </td>
                <%if (user.getRole() == UserRole.ADMIN) {%>

                <td class="actions-cell">
                    <a href="${pageContext.request.contextPath}/changeCourse?id=<%= course.getId() %>"
                       class="btn-small btn-edit">Edit</a>
                    <a href="${pageContext.request.contextPath}/deleteCourse?id=<%= course.getId() %>"
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
