package com.example.portalstudentee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private int id;
    private String name;
    private String surname;
    private String email;
    private Course course;
}
