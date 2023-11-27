package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entity.Food;
import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import com.in28minutes.rest.webservices.restfulwebservices.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodRepository foodRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addFood (
            @RequestParam String name,
            @RequestParam String category) {
        Food food = new Food();
        food.setCategory(category);
        food.setName(name);
        foodRepository.save(food);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Food> getAllUsers() {
        // This returns a JSON or XML with the users
        return foodRepository.findAll();
    }
}
