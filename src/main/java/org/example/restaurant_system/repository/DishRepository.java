package org.example.restaurant_system.repository;

import java.util.List;
import org.example.restaurant_system.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DishRepository extends JpaRepository<Dish, Long> {
   //Create a method to retrieve the most expensive dish.
  @Query(value = "SELECT * FROM dishes ORDER BY price DESC LIMIT 1", nativeQuery = true)
  Dish findTopByOrderByPriceDesc();
  //Design a query to find the dish with the minimum calories.
  @Query(value = "SELECT * FROM dishes ORDER BY calories ASC LIMIT 1", nativeQuery = true)
  Dish findTopByOrderByCaloriesAsc();
  //Develop a method to list dishes based on a specific ingredient.
  @Query("SELECT  d FROM Dish d WHERE d.ingredients LIKE %:ingredient%")
  List<Dish> findByIngredientsContaining(@Param("ingredient") String ingredient);


  @Modifying
  @Query("UPDATE Dish d SET d.name=:name, d.price=:price, d.calories=:calories, d.ingredients=:ingredients WHERE d.id=:id")
  int updateDish(
      @Param("id") Long id,
      @Param("name") String name,
      @Param("price") Double price,
      @Param("calories") Integer calories,
      @Param("ingredients") String ingredients
  );

}
