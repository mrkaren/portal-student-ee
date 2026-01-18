package com.example.portalstudentee.servlet;

import com.example.portalstudentee.model.Course;
import com.example.portalstudentee.service.CourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/changeCourse")
public class ChangeCourseServlet extends HttpServlet {

    private CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Course courseById = courseService.getCourseById(id);
        if (courseById == null) {
            req.getSession().setAttribute("message", "Course not found with id " + id);
            resp.sendRedirect("/courses");
        } else {
            req.setAttribute("course", courseById);
            req.getRequestDispatcher("/WEB-INF/changeCourse.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Course course = courseService.getCourseById(id);

        if (course == null) {
            req.getSession().setAttribute("message", "Course not found with id " + id);
        } else {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            course.setName(name);
            course.setPrice(price);
            courseService.changeCourse(course);
        }
        resp.sendRedirect("/courses");


    }
}
