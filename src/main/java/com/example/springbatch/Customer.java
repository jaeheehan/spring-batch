package com.example.springbatch;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String age;

    @OneToOne(mappedBy = "customer")
    private Address address;
}
