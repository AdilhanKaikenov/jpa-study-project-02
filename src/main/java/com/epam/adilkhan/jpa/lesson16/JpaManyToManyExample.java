package com.epam.adilkhan.jpa.lesson16;

import com.epam.adilkhan.jpa.lesson16.entity.Course;
import com.epam.adilkhan.jpa.lesson16.entity.CourseStudent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaManyToManyExample {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<CourseStudent> studentsListOne = new ArrayList<CourseStudent>();
        List<CourseStudent> studentsListTwo = new ArrayList<CourseStudent>();
        Set<Course> coursesOne = new HashSet<Course>();
        Set<Course> coursesTwo = new HashSet<Course>();

        CourseStudent courseStudentOne = new CourseStudent("Chan", "Li");
        CourseStudent courseStudentTwo = new CourseStudent("Jessica", "Alba");

        Course courseOne = new Course("course_1");
        Course courseTwo = new Course("course_2");
        Course courseThree = new Course("course_3");
        Course courseFour = new Course("course_4");

        coursesOne.add(courseOne);
        coursesOne.add(courseTwo);

        coursesTwo.add(courseThree);
        coursesTwo.add(courseFour);

        courseStudentOne.setLikedCourses(coursesOne);
        courseStudentTwo.setLikedCourses(coursesTwo);

        studentsListOne.add(courseStudentOne);
        studentsListTwo.add(courseStudentTwo);

        courseOne.setLikes(studentsListOne);
        courseTwo.setLikes(studentsListOne);

        courseThree.setLikes(studentsListTwo);
        courseFour.setLikes(studentsListTwo);

        entityManager.persist(courseStudentOne);
        entityManager.persist(courseStudentTwo);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
