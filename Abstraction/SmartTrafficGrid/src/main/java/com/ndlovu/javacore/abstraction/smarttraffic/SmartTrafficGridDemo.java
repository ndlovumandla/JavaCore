/*
 * Johannesburg is piloting a smart traffic grid across Sandton intersections.
 * Naledi needs one command system that can control traffic lights, pedestrian crossings, and emergency override sensors.
 * Each device must support activate, deactivate, and reportStatus, even though the hardware internals differ.
 * To solve this cleanly, the design uses a TrafficDevice interface contract plus an abstract base class for shared state.
 * The control room can then manage diverse devices through one unified API.
 */

package com.ndlovu.javacore.abstraction.smarttraffic;

import java.util.List;

/**
 * Runs the smart traffic grid simulation and demonstrates abstraction with interfaces.
 */
public class SmartTrafficGridDemo {

    /**
     * Creates city devices, performs unified commands, and prints reports for Naledi's dashboard.
     */
    public static void main(String[] args) {
        // Interface contract: the list stores different hardware types using one TrafficDevice abstraction.
        List<TrafficDevice> sandtonGridDevices = List.of(
                new StandardTrafficLight("SND-01"),
                new PedestrianCrossing("SND-02"),
                new EmergencyOverrideSensor("SND-03")
        );

        printControlHeader();

        // Polymorphic interface usage: one call signature activates all device types.
        for (TrafficDevice gridDevice : sandtonGridDevices) {
            gridDevice.activate();
            System.out.println(gridDevice.reportStatus());
        }

        System.out.println();

        // Multiple inheritance via interfaces: each device can also be treated as a RemoteMonitor.
        for (TrafficDevice gridDevice : sandtonGridDevices) {
            RemoteMonitor monitoredDevice = (RemoteMonitor) gridDevice;
            System.out.println("Telemetry -> " + monitoredDevice.telemetry());
        }

        System.out.println();

        // Unified shutdown flow: one command contract deactivates every device safely.
        for (TrafficDevice gridDevice : sandtonGridDevices) {
            gridDevice.deactivate();
            System.out.println(gridDevice.reportStatus());
        }

        System.out.println("Naledi now controls diverse traffic hardware through one clean abstraction.");
    }

    /**
     * Prints a short heading so the output reads like a city operations dashboard.
     */
    private static void printControlHeader() {
        System.out.println("=== City of Johannesburg Smart Traffic Grid ===\n");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - abstract class: AbstractTrafficDevice holds shared state and common behavior for all devices.
    - interface: TrafficDevice defines the command contract that every device must implement.
    - implements: AbstractTrafficDevice implements TrafficDevice and RemoteMonitor to honour both contracts.
    - contract: activate/deactivate/reportStatus create a stable API for the control room.
    - multiple inheritance: classes gain multiple behaviours through implementing multiple interfaces.

    Interview questions this code helps answer:
    - When should you use an interface versus an abstract class?
    - What does "contract" mean in Java design?
    - How does Java support multiple inheritance safely?
    - Why put shared state logic in an abstract base class?
    - How does abstraction simplify hardware control systems?

    Common mistake and how this code avoids it:
    - Mistake: duplicating lifecycle state logic in every concrete device class. This code keeps shared state in AbstractTrafficDevice and leaves only hardware-specific behaviour in subclasses.
    */
}
