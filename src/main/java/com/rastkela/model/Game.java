package com.rastkela.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Game {
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

    @Column
    private Long categoryId; // TODO anotacija za relaciju

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

    public Long getCategoryId() {
        return categoryId;
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

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
