package com.ndlovu.javacore.collections.foodbank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Coordinates intake, indexing, duplicate checks, and dispatch routing for the Johannesburg hub.
 */
public class FoodBankHubService {

    private final ArrayList<FoodParcel> intakeLedger = new ArrayList<>();
    private final HashMap<String, Donor> donorsById = new HashMap<>();
    private final HashMap<String, Beneficiary> beneficiariesById = new HashMap<>();
    private final HashSet<String> knownParcelIds = new HashSet<>();
    private final LinkedList<FoodParcel> dispatchQueue = new LinkedList<>();

    /**
     * Registers one donor for fast ID-based lookup in hub operations.
     */
    public void registerDonor(Donor donor) {
        donorsById.put(donor.donorId(), donor);
    }

    /**
     * Registers one beneficiary for fast allocation and routing lookup.
     */
    public void registerBeneficiary(Beneficiary beneficiary) {
        beneficiariesById.put(beneficiary.beneficiaryId(), beneficiary);
    }

    /**
     * Receives a parcel into ordered intake storage while preventing duplicate IDs.
     */
    public void receiveParcel(FoodParcel foodParcel) {
        // HashSet provides fast duplicate detection so repeated IDs are rejected immediately.
        if (!knownParcelIds.add(foodParcel.parcelId())) {
            throw new IllegalArgumentException("Duplicate parcel ID detected: " + foodParcel.parcelId());
        }

        // ArrayList preserves insertion order for intake review and daily reconciliation.
        intakeLedger.add(foodParcel);
    }

    /**
     * Builds dispatch queue for one zone by ranking beneficiaries and selecting relevant parcels.
     */
    public void prepareDispatchQueue(String zone) {
        dispatchQueue.clear();

        // Iterator safely removes moved parcels from intake without concurrent modification errors.
        Iterator<FoodParcel> intakeIterator = intakeLedger.iterator();
        while (intakeIterator.hasNext()) {
            FoodParcel intakeParcel = intakeIterator.next();
            if (zone.equalsIgnoreCase(intakeParcel.targetZone())) {
                dispatchQueue.add(intakeParcel);
                intakeIterator.remove();
            }
        }
    }

    /**
     * Returns beneficiaries in one zone sorted by highest need score first.
     */
    public List<Beneficiary> prioritizedBeneficiariesForZone(String zone) {
        ArrayList<Beneficiary> zoneBeneficiaries = new ArrayList<>();

        // HashMap iteration collects matching organizations from keyed storage.
        for (Map.Entry<String, Beneficiary> beneficiaryEntry : beneficiariesById.entrySet()) {
            if (zone.equalsIgnoreCase(beneficiaryEntry.getValue().zone())) {
                zoneBeneficiaries.add(beneficiaryEntry.getValue());
            }
        }

        // Collections.sort applies utility-based ordering for predictable dispatch decisions.
        Collections.sort(zoneBeneficiaries, Comparator.comparingInt(Beneficiary::needScore).reversed());
        return zoneBeneficiaries;
    }

    /**
     * Dispatches the next parcel in FIFO order from the queue.
     */
    public FoodParcel dispatchNextParcel() {
        return dispatchQueue.pollFirst();
    }

    /**
     * Returns a read-only intake snapshot for dashboard reporting.
     */
    public List<FoodParcel> intakeSnapshot() {
        return Collections.unmodifiableList(intakeLedger);
    }

    /**
     * Returns a read-only donor index for diagnostics and reporting.
     */
    public Map<String, Donor> donorSnapshot() {
        return Collections.unmodifiableMap(donorsById);
    }

    /**
     * Returns all known parcel IDs for audit checks.
     */
    public Set<String> parcelIdSnapshot() {
        return Collections.unmodifiableSet(knownParcelIds);
    }

    /**
     * Returns current queue size for operations monitoring.
     */
    public int dispatchQueueSize() {
        return dispatchQueue.size();
    }
}
