package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {



    void save(Student student);

    List<Student> findAll();

    //Student findById(Long id);
    //elimizde olmayan bir id ile student yoksa null pointer doner
    // null donmek yerine bos bir Optional objesi doner
    Optional<Student> findById(Long id);

    void delete(Long id);


}
