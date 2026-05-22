package com.ndlovu.javacore.polymorphism.johannesburgprintshop;

/**
 * Represents a print job in Thandi's billing queue.
 */
public abstract class PrintJob {

    protected final String jobReference;
    protected final String customerName;

    /**
     * Stores the common details that every print job needs.
     */
    protected PrintJob(String jobReference, String customerName) {
        this.jobReference = jobReference;
        this.customerName = customerName;
    }

    /**
     * Builds the shared part of every print quote so subclasses can reuse the same summary.
     */
    protected String baseSummary() {
        return "Job reference: " + jobReference + "\n"
                + "Customer: " + customerName;
    }

    /**
     * Calculates the cost for the current job so each subclass can apply its own pricing rule.
     */
    public abstract double calculateCost();

    /**
     * Overloaded helper that creates a short summary with a custom discount note for the dashboard.
     */
    public String summarize(String discountLabel) {
        return baseSummary() + "\nDiscount note: " + discountLabel + "\nCost: R" + String.format("%.2f", calculateCost());
    }
}
