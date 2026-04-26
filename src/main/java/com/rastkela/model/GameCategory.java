package com.rastkela.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.Name;

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
