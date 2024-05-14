package org.example.restaurant_system.service;

import java.util.List;
import org.example.restaurant_system.model.Dish;
import org.example.restaurant_system.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService {

  @Autowired
  private DishRepository repository;

  // save a new dish
  public Dish saveDish(Dish dish){
    return repository.save(dish);
  }

  // get the most expensive dish
  public Dish getMostExpensiveDish(){
    return repository.findTopByOrderByPriceDesc();
  }


  // get the lowest calories dish
  public Dish getLowestCaloriesDish(){
    return  repository.findTopByOrderByCaloriesAsc();
  }


  // get a list dishes based on a specific ingredient.
  public  List<Dish> getDishesByIngredient(String ingredient){
    return repository.findByIngredientsContaining(ingredient);
  }
 }
