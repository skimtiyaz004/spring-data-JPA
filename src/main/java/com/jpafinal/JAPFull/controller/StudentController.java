package com.jpafinal.JAPFull.controller;

import com.jpafinal.JAPFull.dto.StudentDTO;
import com.jpafinal.JAPFull.entity.Student;
import com.jpafinal.JAPFull.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO req){
        studentService.addStudent(req);
        return new ResponseEntity<>("Data added", HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getAllStudent(){
        List<Student> studentList=studentService.getAllStudentByQuery();
        return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleStudent(@PathVariable long id){
         Student  student =studentService.getSingleStudentByQuery(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
}
