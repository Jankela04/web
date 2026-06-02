package com.rastkela.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.exception.ForbiddenException;
import com.rastkela.exception.UnauthorizedException;
import com.rastkela.model.GameCategory;
import com.rastkela.service.AuthService;
import com.rastkela.service.GameCategoryService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/category")
@RestController
public class GameCategoryController {

    @Autowired
    private GameCategoryService categoryService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<GameCategory>> getAllCategories(HttpSession session) {
        boolean isLoggedIn = authService.isLoggedIn(session);

        if(!isLoggedIn){
            throw new UnauthorizedException("You must be logged in");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameCategory> getCategory(@PathVariable Long id, HttpSession session) {
        boolean isLoggedIn = authService.isLoggedIn(session);

        if(!isLoggedIn){
            throw new UnauthorizedException("You must be logged in");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.findOne(id));
        }
    }
    
    @PostMapping
    public ResponseEntity<GameCategory> createCategory(HttpSession session, @RequestParam String name, @RequestParam String description) {
        boolean isAdmin = authService.isAdmin(session);

        if(!isAdmin){
            throw new ForbiddenException("Admin privileges required");
        }

        if(categoryService.existsByName(name)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        GameCategory newCategory = categoryService.createCategory(name, description);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<GameCategory> updateCategoryName(HttpSession session, @PathVariable Long id, @RequestParam String name) {
        boolean isAdmin = authService.isAdmin(session);

        if(!isAdmin){
            throw new ForbiddenException("Admin privileges required");
        }

        GameCategory updatedCategory = categoryService.changeCategoryName(id, name);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameCategory> removeCategory(HttpSession session, @PathVariable Long id) {
        boolean isAdmin = authService.isAdmin(session);

        if(!isAdmin){
            throw new ForbiddenException("Admin privileges required");
        }

        GameCategory deletedCategory = categoryService.deleteCategory(id);

        return ResponseEntity.status(HttpStatus.OK).body(deletedCategory);
    }
    
    
    
}
