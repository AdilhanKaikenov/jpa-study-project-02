package com.epam.adilkhan.jpa.lesson23.entity;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.fullname"),
        @NamedQuery(name = "User.findByFullname", query = "FROM User u WHERE fullname = ?1"),
        @NamedQuery(name = "User.count", query = "SELECT COUNT(*) FROM User u"),
        @NamedQuery(name = "User.removeAll", query = "DELETE FROM User u")
})
/**
 * The persistence provider doesn’t parse these queries and sends them directly to the database.
 * That enables you to use all SQL features supported by your database.
 */
@NamedNativeQueries({
        @NamedNativeQuery(name = "User.findAllUsers", query = "SELECT * FROM users u", resultClass = User.class),
        @NamedNativeQuery(name = "User.selectUserEmailById", query = "SELECT u.email FROM users u Where u.id = :id"),
})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fullname;
    private String email;
    private String password;

    public User() {
    }

    public User(String fullname, String email, String password) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
