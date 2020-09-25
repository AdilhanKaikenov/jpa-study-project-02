package com.epam.adilkhan.jpa.lesson16.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    private String courseName;

    @ManyToMany(mappedBy = "likedCourses")
    private List<CourseStudent> likes;

    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseName, List<CourseStudent> students) {
        this.courseName = courseName;
        this.likes = students;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<CourseStudent> getLikes() {
        return this.likes;
    }

    public void setLikes(List<CourseStudent> likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != null ? !id.equals(course.id) : course.id != null) return false;
        return courseName != null ? courseName.equals(course.courseName) : course.courseName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        return result;
    }
}
