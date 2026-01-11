package com.example.portalstudentee.servlet;

import com.example.portalstudentee.model.Student;
import com.example.portalstudentee.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> allStudents = studentService.getAllStudents();

        req.setAttribute("students", allStudents);
        req.getRequestDispatcher("students.jsp").forward(req, resp);
    }
}
