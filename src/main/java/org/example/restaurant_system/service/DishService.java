package org.example.restaurant_system.service;

import java.util.List;
import org.example.restaurant_system.model.Dish;
import org.example.restaurant_system.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  // add Show all dishes feature
  public  List<Dish> getAllDishes(){
    return  repository.findAll();
  }


  // get a dish by id
  public  Dish getDishById(Long id){
    return  repository.getById(id);
  }


  @Transactional
  public boolean updateDish(
       Long id,
       String name,
       Double price,
       Integer calories,
       String ingredients
  ){
int results = repository.updateDish(
     id,
     name,
     price,
     calories,
     ingredients
);

    return results > 0;
  }

 }
