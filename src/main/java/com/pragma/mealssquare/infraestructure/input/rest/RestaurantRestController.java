package com.pragma.mealssquare.infraestructure.input.rest;

import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.handler.IRestaurantHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals-square")
public class RestaurantRestController {
    private final IRestaurantHandler iRestaurantHandler;

    public RestaurantRestController(IRestaurantHandler iRestaurantHandler) {
        this.iRestaurantHandler = iRestaurantHandler;
    }

    @PostMapping("/create-restaurant")
    public ResponseEntity<Void> createRestaurant(@RequestBody RestaurantDTORequest restaurantDTORequest,
                                                 @RequestHeader String emailCreatorRestaurant){
        iRestaurantHandler.saveListRestaurant(restaurantDTORequest,emailCreatorRestaurant);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
