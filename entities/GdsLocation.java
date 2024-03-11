package com.ey.dt.masterdata.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gds_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GdsLocation extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "location", length = 50)
    private String location;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;
}
