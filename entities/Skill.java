package com.ey.dt.masterdata.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "skill")

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "skill")
    @Valid
    private String name;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "P_Skill_Sub_Skill",joinColumns = @JoinColumn(name = "P_skill_id"),inverseJoinColumns = @JoinColumn(name = "sub_skill_id"))
    Set<SubSkill>subSkills;




    /***
     * hashCode and equal methods are Override for avoiding the duplicate objects.
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return  true;
        Skill skill=(Skill) obj;
        return Objects.equals(name,skill.name);
    }
}
