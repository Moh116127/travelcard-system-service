package com.demo.travelcardsystem.model.response;

import com.demo.travelcardsystem.constant.Zone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationResponse {
    private String stationName;
    private Set<Zone> zones;
    
    // Explicit getters and setters
    public String getStationName() {
        return stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    public Set<Zone> getZones() {
        return zones;
    }
    
    public void setZones(Set<Zone> zones) {
        this.zones = zones;
    }
} 