package com.pragma.mealssquare.infraestructure.input.rest;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.application.dto.DishUpdateDTORequest;
import com.pragma.mealssquare.application.handler.IDishHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishRestController {
    private final IDishHandler iDishHandler;

    @PostMapping("/create-dish")
    public ResponseEntity<Void> saveDish(@RequestBody DishDTORequest dishDTORequest,
                                         @RequestHeader String emailOwner){
        log.info("{}",dishDTORequest);
        iDishHandler.saveDish(dishDTORequest,emailOwner);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update-dish")
    public ResponseEntity<Void> updateDish(@RequestBody DishUpdateDTORequest dishUpdateDTORequest,
                                           @RequestHeader String emailOwner){
        iDishHandler.updateDish(dishUpdateDTORequest, emailOwner);
        return ResponseEntity.noContent().build();
    }
}
