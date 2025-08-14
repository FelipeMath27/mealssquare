package com.pragma.mealssquare.infraestructure.input.rest;

import com.pragma.mealssquare.application.dto.PageDTOResponse;
import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.application.handler.IRestaurantHandler;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/meals-square")
public class RestaurantRestController {
    private final IRestaurantHandler iRestaurantHandler;

    public RestaurantRestController(IRestaurantHandler iRestaurantHandler) {
        this.iRestaurantHandler = iRestaurantHandler;
    }

    @PostMapping("/create-restaurant")
    public ResponseEntity<Void> createRestaurant(@Valid @RequestBody RestaurantDTORequest restaurantDTORequest){
        iRestaurantHandler.saveListRestaurant(restaurantDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list-restaurants")
    public ResponseEntity<PageDTOResponse<RestaurantDTOResponse>> listRestaurantsDTORequest(@RequestParam int page,
                                                                                            @RequestParam int size) {
        log.info(ConstantsErrorMessage.LISTENER_OK_CONTROLLER);
        return ResponseEntity.ok(iRestaurantHandler.getAllRestaurants(page,size));
    }
}
