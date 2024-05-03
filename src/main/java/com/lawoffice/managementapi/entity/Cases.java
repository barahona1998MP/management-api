package com.lawoffice.managementapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name =  "cases")
public class Cases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCases")
    private Integer idCases;

    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idService", referencedColumnName = "idService")
    private ServiceType serviceType;

    @ManyToOne
    @JoinColumn(name = "idStatus", referencedColumnName = "idStatus")
    private StatusCase statusCase;

    @Column(name = "dateOpening")
    private LocalDate dateOpening;
}
