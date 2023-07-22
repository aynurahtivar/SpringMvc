package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository     //bu classtan bir obje bean olusturuyor , component a gore daha gelismistir

public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(student);      //kayit varsa update, yoksa olusturur
        //saveOrUpdate methodunu kullanacagimiz icin servicede update methodu olusturmadik


        tx.commit();
        session.close();

    }

    @Override
    public List<Student> findAll() {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Student> studentList = session.createQuery("FROM Student", Student.class).getResultList();

        tx.commit();
        session.close();

        return studentList;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
