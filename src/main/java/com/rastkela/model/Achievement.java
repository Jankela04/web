package com.rastkela.model;

import java.io.Serializable;

import jakarta.persistence.*;


/*  Predifinisana lista postignuca koja se ne moze menjati */
@Entity
public class Achievement implements Serializable {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String condition;
}
