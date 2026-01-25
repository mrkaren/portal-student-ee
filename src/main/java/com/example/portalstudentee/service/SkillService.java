package com.example.portalstudentee.service;

import com.example.portalstudentee.db.DBConnectionProvider;
import com.example.portalstudentee.model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<Skill> getStudentSkillsByStudentId(int studentId) {
        List<Skill> studentSkills = new ArrayList<>();
        String sql = "SELECT * FROM student_skill WHERE student_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentSkills.add(getSkillById(resultSet.getInt("skill_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentSkills;
    }


    public Skill getSkillById(int skillId) {
        String sql = "SELECT * FROM skill WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, skillId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getInt("id"));
                skill.setName(resultSet.getString("name"));
                return skill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM skill";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getInt("id"));
                skill.setName(resultSet.getString("name"));
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

}
