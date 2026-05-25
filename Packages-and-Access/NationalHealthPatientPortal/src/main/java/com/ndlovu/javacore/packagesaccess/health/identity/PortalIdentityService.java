package com.ndlovu.javacore.packagesaccess.health.identity;

/**
 * Validates requester roles before portal modules expose any patient data.
 */
public class PortalIdentityService {

    /**
     * Checks whether the requester role is approved for the portal scenario.
     */
    public boolean isKnownRole(String requesterRole) {
        return requesterRole.equals("GP")
                || requesterRole.equals("PHARMACY")
                || requesterRole.equals("SCHEME")
                || requesterRole.equals("PUBLIC");
    }
}
