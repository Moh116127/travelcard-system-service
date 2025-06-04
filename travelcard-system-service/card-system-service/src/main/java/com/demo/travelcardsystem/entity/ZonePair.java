package com.demo.travelcardsystem.entity;

import com.demo.travelcardsystem.constant.Zone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZonePair {
    private Zone fromZone;
    private Zone toZone;
    
    // Explicit getter and setter for fromZone
    public Zone getFromZone() {
        return fromZone;
    }
    
    public void setFromZone(Zone fromZone) {
        this.fromZone = fromZone;
    }
    
    // Explicit getter and setter for toZone
    public Zone getToZone() {
        return toZone;
    }
    
    public void setToZone(Zone toZone) {
        this.toZone = toZone;
    }

    public boolean isApplicableFor(Journey journey) {
        return journey.getStartStation().getZones().contains(fromZone) && 
               journey.getEndStation().getZones().contains(toZone);
    }
}
