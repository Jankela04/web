package com.rastkela.dto;

// podatke koje admin salje kada se pravi/menja igra
public class GameFormDto {
    private String name;
    private String description;
    private String path;
    private String imagePath;
    private boolean active;
    private Long categoryId;


    public GameFormDto() {
    }

    public GameFormDto(String name, String description, String path, String imagepath,boolean active, Long categoryId) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.imagePath = imagepath;
        this.active = active;
        this.categoryId = categoryId;
    }

    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagepath) {
        this.imagePath = imagepath;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
}
