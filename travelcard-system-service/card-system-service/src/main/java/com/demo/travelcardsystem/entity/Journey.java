package com.demo.travelcardsystem.entity;

import com.demo.travelcardsystem.constant.TransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Journey {
    private Station startStation;
    private Station endStation;
    private TransportType transportType;
    private boolean journeyCompleted;
    
    // Explicit getters and setters
    public Station getStartStation() {
        return startStation;
    }
    
    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }
    
    public Station getEndStation() {
        return endStation;
    }
    
    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }
    
    public TransportType getTransportType() {
        return transportType;
    }
    
    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }
    
    public boolean isJourneyCompleted() {
        return journeyCompleted;
    }
    
    public void setJourneyCompleted(boolean journeyCompleted) {
        this.journeyCompleted = journeyCompleted;
    }
}
