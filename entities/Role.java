package com.ey.dt.masterdata.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role", length = 50)
    private String name;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;
}
