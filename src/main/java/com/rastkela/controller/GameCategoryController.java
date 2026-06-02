package com.rastkela.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.model.GameCategory;
import com.rastkela.service.GameCategoryService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@RequestMapping("/api/category")
@RestController
public class GameCategoryController {

    @Autowired
    private GameCategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<GameCategory>> getAllCategories(HttpSession session) {
        // boolean isAuthorised = session.getAttribute("user").isAdmin();
        boolean isAuthorised = true;

        if(!isAuthorised){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameCategory> getCategory(@PathVariable Long id) {
        // boolean isAuthorised = session.getAttribute("user").isAdmin();
        boolean isAuthorised = true;

        if(!isAuthorised){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.findOne(id));
        }
    }
    
    @PostMapping
    public ResponseEntity<GameCategory> createCategory(HttpSession session, @RequestParam String name, @RequestParam String description) {
        // boolean isAuthorised = session.getAttribute("user").isAdmin();
        boolean isAuthorised = true;


        if(!isAuthorised){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(categoryService.existsByName(name)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(name, description));
    }
    
    
    
}
