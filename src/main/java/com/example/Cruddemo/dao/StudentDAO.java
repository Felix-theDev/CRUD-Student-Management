package com.example.Cruddemo.dao;

import com.example.Cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    List<Student> findByFirstName(String firstName);

    void update(Student student);

    void delete(Integer id);

    int deleteAllStudents();

    List<Student> findOldestStudent();

    List<Student> findYoungestStudent();
    long count();

    List<Student> findByAgeRange(int minAge, int maxAge);

}
