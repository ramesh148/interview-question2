package com.example.demo.persistence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="TBL_EMPLOYEES")
public class ArrayEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="store")
    private String store;

    //Setters and getters

    @Override
    public String toString() {
        return "ArrayEntity [id=" + id + ", store=" + store + "]";
    }
}

