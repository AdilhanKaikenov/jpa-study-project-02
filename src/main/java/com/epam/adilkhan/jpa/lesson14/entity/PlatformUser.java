package com.epam.adilkhan.jpa.lesson14.entity;

import javax.persistence.*;

@Entity
@Table(name = "platform_user")
public class PlatformUser {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_address_id")
    private PlatformUserAddress platformUserAddress;

    public PlatformUser() {
    }

    public PlatformUser(String username) {
        this.username = username;
    }

    public PlatformUser(String username, PlatformUserAddress platformUserAddress) {
        this.username = username;
        this.platformUserAddress = platformUserAddress;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlatformUserAddress getPlatformUserAddress() {
        return this.platformUserAddress;
    }

    public void setPlatformUserAddress(PlatformUserAddress platformUserAddress) {
        this.platformUserAddress = platformUserAddress;
    }
}
