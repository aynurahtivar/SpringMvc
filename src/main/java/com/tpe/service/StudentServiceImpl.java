package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service            //Bean olusturur,componentin gelimisi

public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository repository;

    @Override
    public void saveStudent(Student student) {
        repository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }
}
