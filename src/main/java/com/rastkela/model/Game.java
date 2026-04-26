package com.rastkela.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private String path; // putanja do html fajla

    @Column
    private String image; // putanja

    @ManyToOne(fetch = FetchType.LAZY) //zamena za id sad kad je kreiran category id
    @JoinColumn(name = "category_id", nullable = false)
    private GameCategory category;

    @Column
    private LocalDate addedDate;

    @Column(nullable = false)
    private boolean active;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public String getImage() {
        return image;
    }

    public GameCategory getCategory() {
        return category;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
