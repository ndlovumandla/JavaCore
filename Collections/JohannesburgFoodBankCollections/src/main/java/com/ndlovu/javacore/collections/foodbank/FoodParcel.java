package com.ndlovu.javacore.collections.foodbank;

/**
 * Represents one donated food parcel moving through intake and dispatch.
 */
public record FoodParcel(String parcelId, String donorId, FoodCategory foodCategory, int quantityUnits, String targetZone) {

    /**
     * Returns a compact parcel label for operational logs.
     */
    public String label() {
        return parcelId + " [" + foodCategory + "] qty=" + quantityUnits + " zone=" + targetZone;
    }
}
