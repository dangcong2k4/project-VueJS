package com.example.student_management.controller;

import com.example.student_management.model.Student;
import com.example.student_management.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<Page<Student>> findAll(
            @PageableDefault(value = 5) Pageable pageable) {
        Page<Student> students = studentService.findAll(pageable);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteFood(@PathVariable("id") Integer id) {
        Student student = studentService.findByStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createFood(@RequestBody  Student student) {
        System.out.println(student.getName());
        System.out.println(student.getDateOfBirth());
        System.out.println(student.getPoint());
        studentService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<?> editFood(@RequestBody @Validated Student student) {
        studentService.editStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Integer id) {
        Student student = studentService.findByStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
