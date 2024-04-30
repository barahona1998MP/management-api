package com.lawoffice.managementapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "cases")
public class Cases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCases")
    private Integer idCases;

    @Column(name = "idCustomer")
    private Integer idCustomer;

    @Column(name = "idService")
    private Integer idService;

    @Column(name = "idStatus")
    private Integer idStatus;

    @Column(name = "dateOpening")
    private LocalDate dateOpening;
}
