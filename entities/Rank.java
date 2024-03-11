package com.ey.dt.masterdata.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`rank`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rank {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rank")
    private String name;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;
}
