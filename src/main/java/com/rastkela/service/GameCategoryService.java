package com.rastkela.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rastkela.model.GameCategory;
import com.rastkela.repository.GameCategoryRepository;
import com.rastkela.repository.GameRepository;

@Service
public class GameCategoryService {

    @Autowired
    private GameCategoryRepository categoryRepository;

    @Autowired
    private GameRepository gameRepository;

    public List<GameCategory> findAll(){
        return categoryRepository.findAll();
    }

    public GameCategory findOne(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    public GameCategory createCategory(String name, String description){
        GameCategory newCategory = new GameCategory();

        newCategory.setName(name);
        newCategory.setDescription(description);

        return categoryRepository.save(newCategory);
    }
    

    public GameCategory changeCategoryName(Long categoryId, String newName){
        GameCategory newCategory = categoryRepository.findById(categoryId).orElseThrow();
        newCategory.setName(newName);

        return categoryRepository.save(newCategory);
    }

    public GameCategory deleteCategory(Long categoryId){
        GameCategory categoryToDelete = categoryRepository.findById(categoryId).orElseThrow();

        if(gameRepository.existsByGameCategory(categoryToDelete)){
            throw new RuntimeException("Nije moguce brisanje kategorije jer sadrzi aktivne igre");
        }

        categoryRepository.delete(categoryToDelete);

        return categoryToDelete;
    }
}
