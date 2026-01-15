<%@ page import="com.example.portalstudentee.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Portal</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<% User user = (User) session.getAttribute("user"); %>
<body>
<div class="container">
    <header class="header">
        <h1>Student Portal</h1>
        <p class="subtitle">Manage your courses and students</p>
        <p style="color: white">
            <% if (user == null) { %>
            <a style="color: white" href="/login">Login</a> | <a style="color: white" href="/register">Register</a>
            <% } else { %>
            <span style="color: white">Welcome <%=user.getName() + " " + user.getSurname()%></span> <a
                style="color:white;" href="/logout">Logout</a>
            <%}%>
        </p>
    </header>

    <nav class="nav-cards">
        <a href="${pageContext.request.contextPath}/courses" class="card">
            <div class="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"/>
                    <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"/>
                    <line x1="8" y1="6" x2="16" y2="6"/>
                    <line x1="8" y1="10" x2="14" y2="10"/>
                </svg>
            </div>
            <h2>Courses</h2>
            <p>View and manage available courses</p>
        </a>

        <a href="${pageContext.request.contextPath}/students" class="card">
            <div class="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
                    <circle cx="9" cy="7" r="4"/>
                    <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
                    <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
                </svg>
            </div>
            <h2>Students</h2>
            <p>View and manage student records</p>
        </a>
    </nav>

    <footer class="footer">
        <p>&copy; 2025 Student Portal</p>
    </footer>
</div>
</body>
</html>
