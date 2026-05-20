package com.rastkela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rastkela.model.Game;
import java.util.List;



public interface GameRepository extends JpaRepository<Game,Long>{

    // za pretragu/filtriranje, mada mislim da je bolje mozda to raditi na frontendu
    List<Game> findByNameContainingIgnoreCase(String name); 
    List<Game> findByCategoryNameContainingIgnoreCase(String name);
}
