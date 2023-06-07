package com.example.student_management.service;

import com.example.student_management.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);

    void deleteStudent(Integer id);

    void addStudent(Student student);

    void editStudent(Student student);

    Student findByStudent(Integer id);
}
