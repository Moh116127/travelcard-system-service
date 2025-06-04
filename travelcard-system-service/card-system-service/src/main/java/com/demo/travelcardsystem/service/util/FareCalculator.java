package com.demo.travelcardsystem.service.util;

import com.demo.travelcardsystem.businessrule.Rule;
import com.demo.travelcardsystem.businessrule.TravelStrategy;
import com.demo.travelcardsystem.entity.Journey;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class FareCalculator {

    private final TravelStrategy travelStrategy;
    
    @Autowired
    public FareCalculator(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public Comparator<Rule> fareComparator = (firstRule, secondRule) -> {
        //sort by min value, firstRule - secondRule will give ascending order
        if (firstRule.getChargeableFare() < secondRule.getChargeableFare()) {
            return -1;
        } else if (firstRule.getChargeableFare() > secondRule.getChargeableFare()) {
            return 1;
        }
        return 0;
    };

    /**
     * Find applicable fare for a journey
     *
     * @param journey Journey to get fare for
     * @return Fare for the journey
     */
    public double calculateFare(Journey journey) {
        List<Rule> applicableRules = travelStrategy.getRuleCollection().getRules()
                .stream()
                .filter(rule -> rule.isEligible(journey))
                .sorted(fareComparator)
                .collect(Collectors.toList());

        Rule applicableRule = applicableRules.stream().findFirst().orElse(null);

        if (applicableRule == null) return 0.0;
        return applicableRule.getChargeableFare();
    }

    /**
     * Get max fare from rule collection
     *
     * @return Max fare
     */
    public double getMaximumFare() {
        return travelStrategy.getRuleCollection().getMaxFare();
    }
    
    // Getter for travelStrategy
    public TravelStrategy getTravelStrategy() {
        return travelStrategy;
    }
}
