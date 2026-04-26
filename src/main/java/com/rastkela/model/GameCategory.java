package com.rastkela.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class GameCategory implements Serializable{
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany (mappedBy = "category")
    private List<Game> games;
}
