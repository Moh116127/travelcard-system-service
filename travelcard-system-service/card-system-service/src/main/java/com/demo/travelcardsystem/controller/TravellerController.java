package com.demo.travelcardsystem.controller;

import com.demo.travelcardsystem.model.request.CardRegistrationRequest;
import com.demo.travelcardsystem.model.request.SwipeRequest;
import com.demo.travelcardsystem.model.response.TravelCardResponse;
import com.demo.travelcardsystem.service.TravellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/card")
@CrossOrigin
@Api(tags = "Travel Card Operations", description = "APIs for travel card management")
public class TravellerController {

    private final TravellerService travellerService;
    
    @Autowired
    public TravellerController(TravellerService travellerService) {
        this.travellerService = travellerService;
    }

    @GetMapping(value = "/ping")
    @ApiOperation(value = "Ping API", notes = "Check if the service is running")
    public String pingMe() {
        return "Service is UP and Running";
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "Register new card", notes = "Register a new travel card in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the card"),
            @ApiResponse(code = 400, message = "Invalid card data provided")
    })
    public void registerNewUser(@RequestBody CardRegistrationRequest cardRegistrationRequest) {
        travellerService.registerNewCard(cardRegistrationRequest);
    }

    @PostMapping(value = "/recharge/{rechargeAmount}")
    @ApiOperation(value = "Recharge card", notes = "Add credit to an existing travel card")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recharged the card"),
            @ApiResponse(code = 400, message = "Invalid card data or recharge amount")
    })
    public void rechargeTheCard(@RequestBody String cardNumber, @PathVariable double rechargeAmount) {
        travellerService.rechargeTheCard(cardNumber, rechargeAmount);
    }

    @PostMapping(value = "/swipe")
    @ApiOperation(value = "Swipe card", notes = "Swipe a card at a station entrance or exit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully swiped the card", response = TravelCardResponse.class),
            @ApiResponse(code = 400, message = "Invalid card data or station")
    })
    public TravelCardResponse swipeCard(@RequestBody SwipeRequest swipeRequest) {
        return travellerService.swipeCard(swipeRequest);
    }

    @GetMapping(value = "/{cardNumber}")
    @ApiOperation(value = "Get card details", notes = "Check details of a specific travel card")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved card details", response = TravelCardResponse.class),
            @ApiResponse(code = 404, message = "Card not found")
    })
    public TravelCardResponse checkCardDetail(@PathVariable String cardNumber) {
        return travellerService.checkCardDetail(cardNumber);
    }

    @GetMapping
    @ApiOperation(value = "Get all cards", notes = "Get a list of all registered card numbers")
    public List<String> fetchAllCard() {
        return travellerService.fetchAllCard();
    }
}
