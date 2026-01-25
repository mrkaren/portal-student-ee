package com.example.portalstudentee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {

    private static final String IMAGE_UPLOAD_PATH = "/Users/karen/Data/lessons/Java-2025/portal-student-ee/upload-images/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pictureName = req.getParameter("picture_name");
        File file = new File(IMAGE_UPLOAD_PATH + pictureName);
        if(!file.exists()){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        resp.setContentType("image/jpeg");
        resp.setHeader("Content-Type", "image/jpeg");

        ServletOutputStream outputStream = resp.getOutputStream();

        try (InputStream inputStream = new FileInputStream(file)) {
            IOUtils.copy(inputStream, outputStream);
        }

    }
}
