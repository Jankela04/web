package com.rastkela.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import com.rastkela.enums.UserRole;


@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    @Email(message = "Loš email format")
    private String email;

    @Column(nullable = false)
    private String password; // TODO: lozinka treba biti hesirana

    @Column
    private LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    private String profilePicture;

    @Column
    private LocalDate registrationDate;

    @Column(nullable = false)
    private boolean blocked;

    // TODO: potencijalno polje lastActive

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Session> sessions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review>  reviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserAchievement>  achievements;

    public User() {}

    public List<Session> getSessions() {
        return sessions;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<UserAchievement> getAchievements() {
        return achievements;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserRole getRole() {
        return role;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
