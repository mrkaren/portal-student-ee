package com.example.portalstudentee.servlet;

import com.example.portalstudentee.model.Student;
import com.example.portalstudentee.service.CourseService;
import com.example.portalstudentee.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/changeStudent")
public class ChangeStudentServlet extends HttpServlet {

    private CourseService courseService = new CourseService();
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("student", studentService.getStudentById(id));

        req.setAttribute("courses", courseService.getAllCourses());
        req.getRequestDispatcher("/changeStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.getStudentById(id);

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int courseId = Integer.parseInt(req.getParameter("course_id"));
        student.setName(name);
        student.setSurname(surname);
        student.setEmail(email);
        student.setCourse(courseService.getCourseById(courseId));
        studentService.changeStudent(student);

        resp.sendRedirect("/students");

    }
}
