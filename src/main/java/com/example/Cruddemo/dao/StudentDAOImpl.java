package com.example.Cruddemo.dao;

import com.example.Cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class StudentDAOImpl implements StudentDAO{

    //Define field for entity manager
    private EntityManager entityManager;



    //inject entity manager using constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);

    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {

        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName ", Student.class);

        return theQuery.getResultList();

    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student where lastName =:placeholder", Student.class);


        theQuery.setParameter("placeholder", lastName);

        return theQuery.getResultList();



    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student where firstName =:placeholder", Student.class);
        query.setParameter("placeholder", firstName);

        return query.getResultList();

    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);

    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student theStudent = entityManager.find(Student.class, id);

        entityManager.remove(theStudent);

    }

    @Override
    @Transactional
    public int deleteAllStudents() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;

        //Same as below code
//        TypedQuery<Student> deleteQuery = entityManager.createQuery("DELETE FROM Student", Student.class);
//        int numRowsDeleted = deleteQuery.executeUpdate();

    }

    @Override
    public List<Student> findOldestStudent() {
//        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by age DESC", Student.class);
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE age = (SELECT max(age) FROM Student) ORDER BY firstName ASC", Student.class);

        List<Student> students = query.getResultList();

        return students;
    }

    @Override
    public List<Student> findYoungestStudent() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE age = (SELECT min(age) FROM Student) ORDER BY firstName ASC", Student.class);

        List<Student> students = query.getResultList();

        return students;
    }

    @Override
    public long count() {
        Long count = entityManager.createQuery("SELECT Count(s) FROM Student s ", Long.class).getSingleResult();

        return count;
    }

    @Override
    public List<Student> findByAgeRange(int minAge, int maxAge) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE age >= :minimum AND " +
                "age <= :maximum ORDER BY age ASC", Student.class);
        query.setParameter("minimum", minAge);
        query.setParameter("maximum", maxAge);

        List<Student> students = query.getResultList();
        return students;
    }


}
