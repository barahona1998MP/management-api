package com.lawoffice.managementapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statusCase")
public class StatusCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idStatus")
    private Integer idStatus;

    @Column(name="description" ,nullable = false)
    private String description;
}
