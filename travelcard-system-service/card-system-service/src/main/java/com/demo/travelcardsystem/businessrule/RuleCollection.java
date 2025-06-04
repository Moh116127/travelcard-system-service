package com.demo.travelcardsystem.businessrule;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class RuleCollection {
    private final Set<Rule> ruleSet;
    private double maxFare;
    
    public RuleCollection() {
        this.ruleSet = new HashSet<>();
    }

    public void addRules(Rule rule) {
        this.ruleSet.add(rule);
    }

    public Set<Rule> getRules() {
        return this.ruleSet;
    }
    
    // Explicit setter for maxFare
    public void setMaxFare(double maxFare) {
        this.maxFare = maxFare;
    }
    
    // Explicit getter for maxFare
    public double getMaxFare() {
        return this.maxFare;
    }
}
