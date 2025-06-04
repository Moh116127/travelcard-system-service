package com.demo.travelcardsystem.entity;

import com.demo.travelcardsystem.constant.Zone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    private String name;
    private Set<Zone> zones;
    
    // Explicit getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Zone> getZones() {
        return zones;
    }
    
    public void setZones(Set<Zone> zones) {
        this.zones = zones;
    }
}
