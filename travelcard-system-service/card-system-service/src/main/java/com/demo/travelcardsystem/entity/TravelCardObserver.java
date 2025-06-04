package com.demo.travelcardsystem.entity;

import com.demo.travelcardsystem.service.util.FareCalculator;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class TravelCardObserver implements Observer<TravelCard> {

    private final FareCalculator fareCalculator;
    
    @Autowired
    public TravelCardObserver(FareCalculator fareCalculator) {
        this.fareCalculator = fareCalculator;
    }
    
    // Getter for fareCalculator
    public FareCalculator getFareCalculator() {
        return fareCalculator;
    }

    @Override
    public void reactOnChange(TravelCard travelCard) {
        Journey journey = travelCard.getCurrentJourney();

        // Journey Completed, Charge the actual fare now.
        if (journey.isJourneyCompleted()) {
            double actualFare = fareCalculator.calculateFare(journey);
            // Add the max fare first (because we initially deducted max fare)
            travelCard.addCredit(fareCalculator.getMaximumFare());
            // Deduct the actual fare now
            travelCard.debitAmount(actualFare);

        } else {
            // Journey just started, Charge the max fare now
            travelCard.debitAmount(fareCalculator.getMaximumFare());
        }
    }
}
