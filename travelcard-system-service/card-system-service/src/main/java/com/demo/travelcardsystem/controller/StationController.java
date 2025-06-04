package com.demo.travelcardsystem.controller;

import com.demo.travelcardsystem.model.response.StationResponse;
import com.demo.travelcardsystem.service.TravellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/station")
@CrossOrigin
@Api(tags = "Station Operations", description = "APIs for station information")
public class StationController {

    private final TravellerService travellerService;
    
    @Autowired
    public StationController(TravellerService travellerService) {
        this.travellerService = travellerService;
    }

    @GetMapping
    @ApiOperation(value = "Get all stations with zones", notes = "Retrieves a list of all stations with their respective zones")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved stations list", response = StationResponse.class, responseContainer = "List")
    })
    public List<StationResponse> getAllStationsWithZones() {
        return travellerService.getAllStationsWithZones();
    }
} 