package com.example.portalstudentee.servlet;

import com.example.portalstudentee.model.Course;
import com.example.portalstudentee.model.Skill;
import com.example.portalstudentee.model.Student;
import com.example.portalstudentee.service.CourseService;
import com.example.portalstudentee.service.SkillService;
import com.example.portalstudentee.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/addStudent")
@MultipartConfig(maxFileSize = 50 * 1024 * 1024, maxRequestSize = 100 * 1024 * 1024, fileSizeThreshold = 1024 * 1024)
public class AddStudentServlet extends HttpServlet {

    private CourseService courseService = new CourseService();
    private StudentService studentService = new StudentService();
    private SkillService skillService = new SkillService();

    private static final String IMAGE_UPLOAD_PATH = "/Users/karen/Data/lessons/Java-2025/portal-student-ee/upload-images/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("skills", skillService.getAllSkills());
        req.setAttribute("courses", courseService.getAllCourses());
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int courseId = Integer.parseInt(req.getParameter("course_id"));
        String[] skills = req.getParameterValues("skills");
        List<Skill> skillsList = new ArrayList<>();

        if(skills != null && skills.length > 0) {
            for (String skillId : skills) {
                skillsList.add(skillService.getSkillById(Integer.parseInt(skillId)));
            }
        }
        Part pic = req.getPart("pic");

        String pictureName = System.currentTimeMillis() + "_" + pic.getSubmittedFileName();
        pic.write(IMAGE_UPLOAD_PATH + pictureName);


        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setEmail(email);
        student.setCourse(courseService.getCourseById(courseId));
        student.setPictureName(pictureName);
        student.setSkills(skillsList);
        studentService.addStudent(student);

        resp.sendRedirect("/students");

    }
}
