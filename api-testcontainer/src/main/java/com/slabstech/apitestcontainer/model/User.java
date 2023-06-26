package com.slabstech.apitestcontainer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userNumber;
    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private LocalDate creationDate;
    private LocalDate lastLoginDate;

    private boolean active;

    private String deliveryDate;
    private DeliveryState deliveryState;

    private Integer status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}

