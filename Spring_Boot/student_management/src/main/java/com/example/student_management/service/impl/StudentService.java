package com.example.student_management.service.impl;

import com.example.student_management.model.Student;
import com.example.student_management.repository.IStudentRepository;
import com.example.student_management.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void editStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findByStudent(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}
