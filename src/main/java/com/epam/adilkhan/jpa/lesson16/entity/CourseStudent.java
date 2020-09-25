package com.epam.adilkhan.jpa.lesson16.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course_student")
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    private String firstname;
    private String lastname;

    /**
     * The owning side is course student and is where we use the @JoinTable annotation. We have to specify there, the name of
     * the table that links both tables (student_course). JoinColumns points to the owning side table (course student) and
     * InverseJoinColumns points to the inverse table of the owning side (course). Cascade Merge and Persist
     * but not Cascade.Remove because if delete a course, I don’t want to remove the students from that course.
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "student_course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<Course> likedCourses;

    public CourseStudent() {
    }

    public CourseStudent(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public CourseStudent(String firstname, String lastname, Set<Course> courses) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.likedCourses = courses;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Course> getLikedCourses() {
        return this.likedCourses;
    }

    public void setLikedCourses(Set<Course> likedCourses) {
        this.likedCourses = likedCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseStudent that = (CourseStudent) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
