package com.demo.travelcardsystem.entity;

import com.demo.travelcardsystem.businessrule.RuleCollection;
import com.demo.travelcardsystem.businessrule.TravelStrategy;
import com.demo.travelcardsystem.service.util.FareCalculator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelCard implements Observable {

    @EqualsAndHashCode.Include
    private String cardNumber;
    private double balance;
    private Journey currentJourney;
    private List<Observer> observerCollection = new ArrayList<>();

    public synchronized void addCredit(double amount) {
        balance += amount;
    }

    public synchronized void debitAmount(double amount) {
        balance -= amount;
    }

    @Override
    public void notifyAllObservers() {
        observerCollection.forEach(observer -> {
            observer.reactOnChange(this);
        });
    }

    @Override
    public void registerObserver(Observer observer) {
        observerCollection.add(observer);
    }
    
    // Explicit getters and setters
    public String getCardNumber() {
        return cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public Journey getCurrentJourney() {
        return currentJourney;
    }
    
    public void setCurrentJourney(Journey currentJourney) {
        this.currentJourney = currentJourney;
    }
    
    public List<Observer> getObserverCollection() {
        return observerCollection;
    }
    
    public void setObserverCollection(List<Observer> observerCollection) {
        this.observerCollection = observerCollection;
    }
    
    // Explicit equals/hashCode that considers only the cardNumber
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCard that = (TravelCard) o;
        return Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }
}
