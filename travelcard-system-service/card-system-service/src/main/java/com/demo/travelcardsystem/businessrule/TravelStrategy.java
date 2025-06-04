package com.demo.travelcardsystem.businessrule;

import com.demo.travelcardsystem.constant.TransportType;
import com.demo.travelcardsystem.constant.Zone;
import com.demo.travelcardsystem.entity.ZonePair;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Data
@Component
public class TravelStrategy {

    private final RuleCollection ruleCollection;
    
    @Value("${fare.zone1:2.50}")
    private double zone1Fare;
    
    @Value("${fare.outside-zone1:2.00}")
    private double outsideZone1Fare;
    
    @Value("${fare.two-zones-with-zone1:3.00}")
    private double twoZonesWithZone1Fare;
    
    @Value("${fare.two-zones-without-zone1:2.25}")
    private double twoZonesWithoutZone1Fare;
    
    @Value("${fare.three-zones:3.20}")
    private double threeZonesFare;
    
    @Value("${fare.bus-journey:1.80}")
    private double busJourneyFare;
    
    @Value("${fare.maximum:7.00}")
    private double maximumFare;
    
    private Consumer<Double> anyWhereInZoneOneStrategy;
    private Consumer<Double> anyOneZoneOutsideZoneOneStrategy;
    private Consumer<Double> anyTwoZoneIncludingZoneOneStrategy;
    private Consumer<Double> anyTwoZoneExcludingZoneOneStrategy;
    private Consumer<Double> anyThreeZoneStrategy;
    private BiConsumer<Double, TransportType> anyJourneyByBus;
    
    @Autowired
    public TravelStrategy(RuleCollection ruleCollection) {
        this.ruleCollection = ruleCollection;
        
        // Initialize the strategy lambdas
        this.anyWhereInZoneOneStrategy = chargeableAmount -> {
            Rule rule = new Rule();
            rule.setChargeableFare(chargeableAmount);

            //Create all possible ZonePair for Zone 1
            ZonePair zonePair = new ZonePair();
            zonePair.setFromZone(Zone.ONE);
            zonePair.setToZone(Zone.ONE);
            rule.addZonePair(zonePair);

            ruleCollection.addRules(rule);
        };

        this.anyOneZoneOutsideZoneOneStrategy = chargeableAmount -> {
            Rule rule = new Rule();
            rule.setChargeableFare(chargeableAmount);

            //create all possible pair of any zone outside zone one.
            ZonePair zonePair1 = new ZonePair();
            zonePair1.setFromZone(Zone.TWO);
            zonePair1.setToZone(Zone.TWO);
            rule.addZonePair(zonePair1);
            
            ZonePair zonePair2 = new ZonePair();
            zonePair2.setFromZone(Zone.THREE);
            zonePair2.setToZone(Zone.THREE);
            rule.addZonePair(zonePair2);

            ruleCollection.addRules(rule);
        };

        this.anyTwoZoneIncludingZoneOneStrategy = chargeableAmount -> {
            Rule rule = new Rule();
            rule.setChargeableFare(chargeableAmount);

            //create all possible pair of any zone outside zone one.
            ZonePair zonePair1 = new ZonePair();
            zonePair1.setFromZone(Zone.ONE);
            zonePair1.setToZone(Zone.TWO);
            rule.addZonePair(zonePair1);
            
            ZonePair zonePair2 = new ZonePair();
            zonePair2.setFromZone(Zone.TWO);
            zonePair2.setToZone(Zone.ONE);
            rule.addZonePair(zonePair2);
            
            ZonePair zonePair3 = new ZonePair();
            zonePair3.setFromZone(Zone.ONE);
            zonePair3.setToZone(Zone.THREE);
            rule.addZonePair(zonePair3);
            
            ZonePair zonePair4 = new ZonePair();
            zonePair4.setFromZone(Zone.THREE);
            zonePair4.setToZone(Zone.ONE);
            rule.addZonePair(zonePair4);

            ruleCollection.addRules(rule);
        };

        this.anyTwoZoneExcludingZoneOneStrategy = chargeableAmount -> {
            Rule rule = new Rule();
            rule.setChargeableFare(chargeableAmount);

            //create all possible pair of any two zone excluding zone one.
            ZonePair zonePair1 = new ZonePair();
            zonePair1.setFromZone(Zone.TWO);
            zonePair1.setToZone(Zone.THREE);
            rule.addZonePair(zonePair1);
            
            ZonePair zonePair2 = new ZonePair();
            zonePair2.setFromZone(Zone.THREE);
            zonePair2.setToZone(Zone.TWO);
            rule.addZonePair(zonePair2);

            ruleCollection.addRules(rule);
        };

        this.anyThreeZoneStrategy = chargeableAmount -> {
            Rule rule = new Rule();
            rule.setChargeableFare(chargeableAmount);

            ruleCollection.addRules(rule);
        };

        this.anyJourneyByBus = (chargeableAmount, transType) -> {
            Rule rule = new Rule();
            rule.setChargeableFare(chargeableAmount);
            rule.setTransportType(transType);

            ruleCollection.addRules(rule);
        };
    }

    // Getter for ruleCollection
    public RuleCollection getRuleCollection() {
        return ruleCollection;
    }

    public RuleCollection loadAllBusinessRules() {
        anyWhereInZoneOneStrategy.accept(zone1Fare);
        anyOneZoneOutsideZoneOneStrategy.accept(outsideZone1Fare);
        anyTwoZoneIncludingZoneOneStrategy.accept(twoZonesWithZone1Fare);
        anyTwoZoneExcludingZoneOneStrategy.accept(twoZonesWithoutZone1Fare);
        anyThreeZoneStrategy.accept(threeZonesFare);
        anyJourneyByBus.accept(busJourneyFare, TransportType.BUS);

        this.ruleCollection.setMaxFare(maximumFare);

        return this.ruleCollection;
    }
}
