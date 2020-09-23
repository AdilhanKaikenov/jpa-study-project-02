package com.epam.adilkhan.jpa.lesson11.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Access(AccessType.FIELD)
public class Gamer {

    @Id
    @GeneratedValue
    private int id;

    private String nickname;

    @Temporal(TemporalType.DATE)
    private Date joinDate;

    @Column(name = "race")
    @Enumerated(EnumType.STRING)
    private CharacterRace characterRace;

    public Gamer() {
    }

    public Gamer(String nickname, Date joinDate, CharacterRace characterRace) {
        this.nickname = nickname;
        this.joinDate = joinDate;
        this.characterRace = characterRace;
    }

    @Access(AccessType.PROPERTY)
    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public CharacterRace getCharacterRace() {
        return this.characterRace;
    }

    public void setCharacterRace(CharacterRace characterRace) {
        this.characterRace = characterRace;
    }
}

