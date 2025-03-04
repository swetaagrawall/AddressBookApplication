package com.example.addressbookapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;
}