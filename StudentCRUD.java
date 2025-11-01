package com.example.hibernateapp;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class StudentCRUD {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // CREATE
            Student s1 = new Student("Ravi Kumar", "Java", "ravi@example.com");
            session.beginTransaction();
            session.save(s1);
            session.getTransaction().commit();

            // READ
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Student> students = session.createQuery("from Student", Student.class).list();
            for (Student s : students) System.out.println(s.getName());
            session.getTransaction().commit();

            // UPDATE
            session = factory.getCurrentSession();
            session.beginTransaction();
            Student updateStudent = session.get(Student.class, 1);
            updateStudent.setCourse("Spring Boot");
            session.getTransaction().commit();

            // DELETE
            session = factory.getCurrentSession();
            session.beginTransaction();
            Student deleteStudent = session.get(Student.class, 2);
            if (deleteStudent != null) session.delete(deleteStudent);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
