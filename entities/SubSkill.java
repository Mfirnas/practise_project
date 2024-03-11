package com.ey.dt.masterdata.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class SubSkill {

    @Id
    @Column(name = "sub_skill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;

    public SubSkill(String name) {
        this.name = name;
    }
}
