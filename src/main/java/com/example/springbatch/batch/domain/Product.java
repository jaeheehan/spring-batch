package com.example.springbatch.batch.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    Long id;
    private String name;
    private int price;
    private String type;
}
