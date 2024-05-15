package org.example.restaurant_system.controller;

import java.util.List;
import org.example.restaurant_system.model.Dish;
import org.example.restaurant_system.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DishController {

  @Autowired
  private DishService service;

  @GetMapping("/")
  public String showHomePage(Model model){
    model.addAttribute("dishes", service.getAllDishes() );
    return "home";
  }

  @GetMapping("/dishes/add")
  public String showAddDishForm(Model model){
   // Dish dish = new Dish();
    model.addAttribute("dish", new Dish());

    return "add-dish";
  }


  @PostMapping("/dishes/add")
  public String addDish(
   @ModelAttribute Dish dish
  ){

    service.saveDish(dish);

    return "home";
  }

  @GetMapping("/dishes/search")
  public String showSearchForm(){
    return "search-dishes";
  }

  @GetMapping("/dishes/by-ingredient")
  public String showDishesByIngredient(@RequestParam("ingredient") String ingredient,
      Model model){
    List<Dish> dishes = service.getDishesByIngredient(ingredient);
  //  System.out.println(dishes);
    model.addAttribute("dishes",dishes );
    return "search-dishes";
  }

  @GetMapping("/dishes/most-expensive")
  public String showMostExpensiveDish(Model model){

    model.addAttribute("dishes", service.getMostExpensiveDish());
    return "search-dishes";

  }

  @GetMapping("/dishes/lowest-calories")
  public String showLowestCalories(Model model){

    model.addAttribute("dishes", service.getLowestCaloriesDish());
    return "search-dishes";

  }


  // Adding Edit Dish feature

  @GetMapping("/dishes/edit/{id}")
  public String showEditDishForm(@PathVariable Long id, Model model){
  Dish dish = service.getDishById(id);
  if (dish == null) throw new RuntimeException("Dish not found !!!");
  model.addAttribute("dish", dish);
  return "edit-dish";
  }


}
