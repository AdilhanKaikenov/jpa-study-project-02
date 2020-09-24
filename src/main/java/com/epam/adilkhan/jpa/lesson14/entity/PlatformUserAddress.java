package com.epam.adilkhan.jpa.lesson14.entity;

import javax.persistence.*;

@Entity
@Table(name = "platform_user_address")
public class PlatformUserAddress {

    @Id
    @GeneratedValue
    private Long id;

    private String city;

    private String street;

    // The mappedBy element designates the property or field in the entity that is the owner of the relationship.
    // The PlatformUserAddress side of the relationship is called the non-owning side.
    @OneToOne(mappedBy = "platformUserAddress")
    private PlatformUser platformUser;

    public PlatformUserAddress() {
    }

    public PlatformUserAddress(String city, String street, PlatformUser platformUser) {
        this.city = city;
        this.street = street;
        this.platformUser = platformUser;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public PlatformUser getPlatformUser() {
        return this.platformUser;
    }

    public void setPlatformUser(PlatformUser platformUser) {
        this.platformUser = platformUser;
    }
}
