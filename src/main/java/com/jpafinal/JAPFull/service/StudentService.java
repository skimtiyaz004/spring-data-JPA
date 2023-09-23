package com.jpafinal.JAPFull.service;

import com.jpafinal.JAPFull.dto.StudentDTO;
import com.jpafinal.JAPFull.entity.Guardian;
import com.jpafinal.JAPFull.entity.Student;
import com.jpafinal.JAPFull.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EntityManager entityManager;

    public void addStudent(StudentDTO req) {
        Guardian guardian=Guardian.builder()
                .email(req.getGuardianEmail())
                .mobile(req.getGuardianMobile())
                .name(req.getGuardianName())
                .build();
        Student student=Student.builder()
                .emailId(req.getEmailId())
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    public List<Student> getAllStudentByQuery() {
        String jpql = "SELECT s FROM Student s";
        TypedQuery<Student> query=entityManager.createQuery(jpql,Student.class);
        return query.getResultList();
    }

    public Student getSingleStudentByQuery(long id) {
        String jpql="SELECT s from Student s where s.studentId=:studentId";
        TypedQuery<Student> query=entityManager.createQuery(jpql,Student.class);
        query.setParameter("studentId",id);
        return  query.getSingleResult();
    }
}
