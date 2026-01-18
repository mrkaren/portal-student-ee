package com.example.portalstudentee.servlet;

import com.example.portalstudentee.model.Course;
import com.example.portalstudentee.model.Student;
import com.example.portalstudentee.service.CourseService;
import com.example.portalstudentee.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet(value = "/addStudent")
@MultipartConfig(maxFileSize = 50 * 1024 * 1024, maxRequestSize = 100 * 1024 * 1024, fileSizeThreshold = 1024 * 1024)
public class AddStudentServlet extends HttpServlet {

    private CourseService courseService = new CourseService();
    private StudentService studentService = new StudentService();

    private static final String IMAGE_UPLOAD_PATH = "/Users/karen/Data/lessons/Java-2025/portal-student-ee/upload-images/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courses", courseService.getAllCourses());
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int courseId = Integer.parseInt(req.getParameter("course_id"));

        Part pic = req.getPart("pic");

        String pictureName = System.currentTimeMillis() + "_" + pic.getSubmittedFileName();
        pic.write(IMAGE_UPLOAD_PATH + pictureName);


        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setEmail(email);
        student.setCourse(courseService.getCourseById(courseId));
        student.setPictureName(pictureName);

        studentService.addStudent(student);

        resp.sendRedirect("/students");

    }
}
