package com.example.portalstudentee.servlet;

import com.example.portalstudentee.model.Course;
import com.example.portalstudentee.service.CourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/courses")
public class CoursesServlet extends HttpServlet {

    private CourseService courseService = new CourseService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> allCourses = courseService.getAllCourses();

        req.setAttribute("courses", allCourses);
        req.getRequestDispatcher("courses.jsp").forward(req, resp);
    }
}
