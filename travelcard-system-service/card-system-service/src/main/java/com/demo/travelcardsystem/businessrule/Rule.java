package com.demo.travelcardsystem.businessrule;

import com.demo.travelcardsystem.constant.TransportType;
import com.demo.travelcardsystem.entity.Journey;
import com.demo.travelcardsystem.entity.ZonePair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    private Set<ZonePair> zonePairs = new HashSet<>();
    private TransportType transportType = null;
    private Double chargeableFare;

    public void addZonePair(ZonePair zonePair) {
        zonePairs.add(zonePair);
    }

    public boolean isEligible(Journey journey) {
        if (transportType != null) {
            return journey.getTransportType() == transportType;
        }

        // This step should be skipped if there are no zone pairs or the journey is not yet completed
        if (journey.getEndStation() == null || zonePairs.isEmpty()) {
            return false;
        }

        return zonePairs.stream().anyMatch(zonePair -> zonePair.isApplicableFor(journey));
    }
    
    // Explicit getters and setters
    public Set<ZonePair> getZonePairs() {
        return zonePairs;
    }

    public void setZonePairs(Set<ZonePair> zonePairs) {
        this.zonePairs = zonePairs;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Double getChargeableFare() {
        return chargeableFare;
    }

    public void setChargeableFare(Double chargeableFare) {
        this.chargeableFare = chargeableFare;
    }
}
