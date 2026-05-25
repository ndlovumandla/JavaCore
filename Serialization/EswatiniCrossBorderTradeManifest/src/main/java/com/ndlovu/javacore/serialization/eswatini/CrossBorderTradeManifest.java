package com.ndlovu.javacore.serialization.eswatini;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one USB-transfer cargo manifest shared between South Africa and Eswatini border systems.
 */
public class CrossBorderTradeManifest implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String manifestReference;
    private final String vehicleRegistration;
    private final String driverFullName;
    private final List<String> goodsDescriptions;
    private final double declaredValueZar;
    private final List<String> phytosanitaryCertificates;
    private String originatingBorderPost;

    private transient boolean usbCheckpointVerified;

    /**
     * Builds one manifest snapshot that can be serialized for offline border transfer.
     */
    public CrossBorderTradeManifest(
            String manifestReference,
            String vehicleRegistration,
            String driverFullName,
            List<String> goodsDescriptions,
            double declaredValueZar,
            List<String> phytosanitaryCertificates,
            String originatingBorderPost,
            boolean usbCheckpointVerified) {
        this.manifestReference = manifestReference;
        this.vehicleRegistration = vehicleRegistration;
        this.driverFullName = driverFullName;
        this.goodsDescriptions = new ArrayList<>(goodsDescriptions);
        this.declaredValueZar = declaredValueZar;
        this.phytosanitaryCertificates = new ArrayList<>(phytosanitaryCertificates);
        this.originatingBorderPost = originatingBorderPost;

        // transient means this runtime-only flag is not written into serialized bytes.
        this.usbCheckpointVerified = usbCheckpointVerified;
    }

    /**
     * Returns a concise line used by both border consoles for quick manifest confirmation.
     */
    public String summaryLine() {
        return "Manifest=" + manifestReference
                + " | Vehicle=" + vehicleRegistration
                + " | Driver=" + driverFullName
                + " | Goods=" + goodsDescriptions.size()
                + " | Value=R" + String.format("%.2f", declaredValueZar)
                + " | BorderPost=" + originatingBorderPost;
    }

    /**
     * Returns whether the in-memory USB verification flag is currently set.
     */
    public boolean isUsbCheckpointVerified() {
        return usbCheckpointVerified;
    }

    /**
     * Rebuilds temporary checkpoint state after deserialization on the receiving border system.
     */
    public void markUsbCheckpointVerified() {
        usbCheckpointVerified = true;
    }

    /**
     * Restores serialized fields and applies backward-compatible defaults for older file versions.
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        // defaultReadObject restores all non-transient serialized fields.
        objectInputStream.defaultReadObject();

        // Graceful compatibility: older files may not have newer optional fields populated.
        if (originatingBorderPost == null || originatingBorderPost.isBlank()) {
            originatingBorderPost = "Unknown-Origin-Post";
        }

        // transient field is always reset and must be rebuilt by receiving workflow.
        usbCheckpointVerified = false;
    }
}
