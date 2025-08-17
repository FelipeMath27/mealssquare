package com.pragma.mealssquare.infraestructure.input.rest;

import com.pragma.mealssquare.application.dto.*;
import com.pragma.mealssquare.application.handler.IDishHandler;

import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.security.IJwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishRestController {
    private final IDishHandler iDishHandler;
    private final IJwtTokenProvider iJwtTokenProvider;

    @PostMapping("/create-dish")
    public ResponseEntity<Void> saveDish(@RequestBody DishDTORequest dishDTORequest){
        log.info("{}",dishDTORequest);
        iDishHandler.saveDish(dishDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update-dish")
    public ResponseEntity<Void> updateDish(@RequestBody DishUpdateDTORequest dishUpdateDTORequest, Authentication authentication){
        String email = authentication.getName();
        iDishHandler.updateDish(dishUpdateDTORequest, email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status-dish")
    public ResponseEntity<Void> updateStatusDish(@RequestBody DishDTOStatusRequest dishDTOStatusRequest, Authentication authentication){
        iDishHandler.updateStatusDish(dishDTOStatusRequest,authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list-dishes")
    public ResponseEntity<PageDTOResponse<DishDTOResponse>> listDishes(@RequestParam Long idRestaurant,
                                                      @RequestParam int page,
                                                      @RequestParam int size,
                                                      @RequestParam(required = false) Long idCategory) {
        log.info("{}", ConstantsErrorMessage.LISTENER_OK_CONTROLLER);
        return ResponseEntity.ok(iDishHandler.getDishesByIdRestaurant(idRestaurant, page, size, idCategory));
    }
}

