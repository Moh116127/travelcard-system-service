package com.demo.travelcardsystem.service;

import com.demo.travelcardsystem.entity.Journey;
import com.demo.travelcardsystem.entity.Station;
import com.demo.travelcardsystem.entity.TravelCard;
import com.demo.travelcardsystem.exception.InvalidCardException;
import com.demo.travelcardsystem.exception.InvalidDataProvidedException;
import com.demo.travelcardsystem.exception.InvalidRechargeAmount;
import com.demo.travelcardsystem.model.request.CardRegistrationRequest;
import com.demo.travelcardsystem.model.request.SwipeRequest;
import com.demo.travelcardsystem.model.response.StationResponse;
import com.demo.travelcardsystem.model.response.TravelCardResponse;
import com.demo.travelcardsystem.repository.InMemoryCardTransactionRepository;
import com.demo.travelcardsystem.service.util.TravelCardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TravellerService {

    private final InMemoryCardTransactionRepository inMemoryCardTransactionRepository;
    private final TravelCardConverter travelCardConverter;
    
    @Autowired
    public TravellerService(InMemoryCardTransactionRepository inMemoryCardTransactionRepository, 
                          TravelCardConverter travelCardConverter) {
        this.inMemoryCardTransactionRepository = inMemoryCardTransactionRepository;
        this.travelCardConverter = travelCardConverter;
    }

    /**
     * This method register new user/card in the system
     *
     * @param cardRegistrationRequest
     */
    public void registerNewCard(CardRegistrationRequest cardRegistrationRequest) {
        if(cardRegistrationRequest == null || cardRegistrationRequest.getCardNumber() == null || cardRegistrationRequest.getCardNumber().isEmpty()) {
            throw new InvalidCardException("This card is Invalid. Please use a valid card");
        }

        if(cardRegistrationRequest != null && cardRegistrationRequest.getBalance() < 0 ) {
            throw new InvalidRechargeAmount("Recharge amount must not be negative");
        }

        TravelCard travelCard = new TravelCard();
        travelCard.setCardNumber(cardRegistrationRequest.getCardNumber());
        travelCard.setBalance(cardRegistrationRequest.getBalance());

        inMemoryCardTransactionRepository.registerNewCard(travelCard);
    }

    /**
     * This method is to recharge existing card. Otherwise, InvalidCardException is thrown
     *
     * @param cardNumber
     * @param rechargeAmount
     */
    public void rechargeTheCard(String cardNumber, double rechargeAmount) {
        if(cardNumber == null || cardNumber.isEmpty()) {
            throw new InvalidCardException("This card is Invalid. Please use a valid card");
        }

        if(rechargeAmount < 0 ) {
            throw new InvalidRechargeAmount("Recharge amount must not be negative");
        }

        TravelCard travelCard = inMemoryCardTransactionRepository.findCardByCardNumber(cardNumber);
        travelCard.addCredit(rechargeAmount);
    }


    public TravelCardResponse swipeCard(SwipeRequest swipeRequest) {

        //If Transport Type is not selected throw exception
        if(null == swipeRequest.getTransportType()) throw new InvalidDataProvidedException();

        TravelCard travelCard = inMemoryCardTransactionRepository.findCardByCardNumber(swipeRequest.getCardNumber());
        Station station = inMemoryCardTransactionRepository.findStationByName(swipeRequest.getStationName());
        if (null != travelCard.getCurrentJourney()) { // Cardholder is in-transit
            // set the end-station in Current Journey of TravelCard
            travelCard.getCurrentJourney().setEndStation(station);
            // mark the journey as complete of TravelCard
            travelCard.getCurrentJourney().setJourneyCompleted(true);
            // notify the TravelCardObserver to debit the fare.
            travelCard.notifyAllObservers();
            // once current Journey is completed. set it as null
            travelCard.setCurrentJourney(null);
        } else {
            // prepare a journey and set the starting station and mode of transport
            Journey journey = new Journey();
            journey.setStartStation(station);
            journey.setTransportType(swipeRequest.getTransportType());
            journey.setJourneyCompleted(false);

            //Set current journey in the TravelCard
            travelCard.setCurrentJourney(journey);
            // notify the TravelCardObserver to debit the fare(Max fare as it is start of journey).
            travelCard.notifyAllObservers();
        }

        //prepare response and return
       return travelCardConverter.travelCard2TravelCardResponseConverter.apply(travelCard);

    }

    public TravelCardResponse checkCardDetail(String cardNumber) {
        TravelCard travelCard = inMemoryCardTransactionRepository.findCardByCardNumber(cardNumber);
        return travelCardConverter.travelCard2TravelCardResponseConverter.apply(travelCard);
    }

    public List<String> fetchAllCard() {
       return inMemoryCardTransactionRepository.fetchAllCardNumber();
    }
    
    /**
     * Get all stations with their respective zones
     * 
     * @return List of StationResponse containing station names and their zones
     */
    public List<StationResponse> getAllStationsWithZones() {
        Set<Station> stations = inMemoryCardTransactionRepository.getAllStations();
        List<StationResponse> stationResponses = new ArrayList<>();
        
        for (Station station : stations) {
            StationResponse response = new StationResponse();
            response.setStationName(station.getName());
            response.setZones(station.getZones());
            stationResponses.add(response);
        }
        
        return stationResponses;
    }
}
