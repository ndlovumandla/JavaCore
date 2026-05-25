/*
 * Trucks crossing at Oshoek carry cargo manifest data on USB drives when border links go offline.
 * The manifest includes driver identity, vehicle details, goods, values, and certificate references.
 * South African and Eswatini systems must serialize and deserialize these files reliably across versions.
 * Precious keeps serialVersionUID stable for compatibility and marks temporary checkpoint state as transient.
 * This demo writes a manifest to disk, reads it back, and shows safe recovery behavior on the receiving side.
 */

package com.ndlovu.javacore.serialization.eswatini;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Demonstrates beginner-friendly serialization and recovery for cross-border USB manifest exchange.
 */
public class EswatiniCrossBorderTradeManifestDemo {

    private static final String USB_MANIFEST_FILE = "manifest_usb.bin";

    /**
     * Runs full border flow: create manifest, serialize to USB file, deserialize, and re-verify.
     */
    public static void main(String[] args) {
        CrossBorderTradeManifest outboundManifest = createSouthAfricanOutboundManifest();

        System.out.println("=== South Africa Border Console ===");
        System.out.println("Prepared outbound manifest: " + outboundManifest.summaryLine());
        System.out.println("USB checkpoint verified in memory: " + outboundManifest.isUsbCheckpointVerified());

        writeManifestToUsb(outboundManifest, USB_MANIFEST_FILE);

        System.out.println();
        System.out.println("=== Eswatini Border Console ===");

        CrossBorderTradeManifest inboundManifest = readManifestFromUsb(USB_MANIFEST_FILE);
        if (inboundManifest == null) {
            System.out.println("Unable to restore manifest from USB. Escalate for manual processing.");
            return;
        }

        System.out.println("Loaded inbound manifest: " + inboundManifest.summaryLine());
        System.out.println("USB checkpoint verified after deserialize: " + inboundManifest.isUsbCheckpointVerified());

        inboundManifest.markUsbCheckpointVerified();
        System.out.println("USB checkpoint verified after Eswatini review: " + inboundManifest.isUsbCheckpointVerified());
    }

    /**
     * Creates realistic manifest data that the South African side stores for border transfer.
     */
    private static CrossBorderTradeManifest createSouthAfricanOutboundManifest() {
        List<String> goodsDescriptions = List.of(
                "Fresh oranges - 120 crates",
                "Avocados - 80 crates",
                "Packaged maize meal - 250 bags");

        List<String> phytosanitaryCertificates = List.of(
                "PHYTO-ZA-2026-8841",
                "PHYTO-ZA-2026-8842");

        return new CrossBorderTradeManifest(
                "MAN-OSHOEK-2026-0091",
                "ND-47-GP",
                "Sibusiso Dlamini",
                goodsDescriptions,
                485_750.00,
                phytosanitaryCertificates,
                "Oshoek-SA",
                true);
    }

    /**
     * Serializes manifest data to a USB file so it can be transported offline across the border.
     */
    private static void writeManifestToUsb(CrossBorderTradeManifest manifestToWrite, String usbFilePath) {
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(usbFilePath))) {
            // ObjectOutputStream writes Serializable object state into binary file bytes.
            objectOutputStream.writeObject(manifestToWrite);
            System.out.println("Manifest written to USB file: " + usbFilePath);
        } catch (IOException ioException) {
            System.out.println("Failed to write manifest file: " + ioException.getMessage());
        }
    }

    /**
     * Deserializes manifest data from USB and handles version mismatch errors gracefully.
     */
    private static CrossBorderTradeManifest readManifestFromUsb(String usbFilePath) {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(usbFilePath))) {
            // ObjectInputStream reconstructs a serialized object graph from file bytes.
            return (CrossBorderTradeManifest) objectInputStream.readObject();
        } catch (InvalidClassException invalidClassException) {
            // serialVersionUID mismatch indicates incompatible software versions across environments.
            System.out.println("Version mismatch while reading manifest: " + invalidClassException.getMessage());
            return null;
        } catch (IOException | ClassNotFoundException generalException) {
            System.out.println("Failed to read manifest file: " + generalException.getMessage());
            return null;
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Serializable: CrossBorderTradeManifest implements Serializable for USB file persistence.
    - transient: usbCheckpointVerified is runtime-only and intentionally excluded from serialized data.
    - ObjectOutputStream: writes manifest object data to a binary USB file.
    - ObjectInputStream: reads binary USB bytes and reconstructs the manifest object.
    - serialVersionUID: fixed class version ID supports compatibility checks between software versions.

    Interview questions this code prepares you to answer:
    - Why does a class need to implement Serializable before writing with ObjectOutputStream?
    - What types of fields are good candidates for transient?
    - What happens if serialVersionUID differs between writer and reader systems?
    - How do ObjectOutputStream and ObjectInputStream relate in object persistence workflows?
    - How can deserialization failures be handled safely in production systems?

    Common mistake and how this code avoids it:
    - Mistake: expecting transient flags to persist after deserialization.
      Avoidance: the receiving flow explicitly re-verifies and sets checkpoint status in memory.
    */
}
