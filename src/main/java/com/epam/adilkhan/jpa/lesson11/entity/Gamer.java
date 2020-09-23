package com.epam.adilkhan.jpa.lesson11.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
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
}

enum CharacterRace {
    ELF, ORC, HUMAN
}