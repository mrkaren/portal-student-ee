package com.example.portalstudentee.service;


import com.example.portalstudentee.db.DBConnectionProvider;
import com.example.portalstudentee.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CourseService courseService = new CourseService();

    public void addStudent(Student student) {
        String sql = "INSERT INTO student(name, surname, email, course_id, picture_name) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getCourse().getId());
            preparedStatement.setString(5, student.getPictureName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                students.add(getStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id = " + id;

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return getStudentFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeStudent(Student student) {
        String sql = "UPDATE student SET name = ?, surname = ?, email = ?, course_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getCourse().getId());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Student getStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setSurname(resultSet.getString("surname"));
        student.setEmail(resultSet.getString("email"));
        student.setCourse(courseService.getCourseById(resultSet.getInt("course_id")));
        student.setPictureName(resultSet.getString("picture_name"));
        return student;
    }

}
