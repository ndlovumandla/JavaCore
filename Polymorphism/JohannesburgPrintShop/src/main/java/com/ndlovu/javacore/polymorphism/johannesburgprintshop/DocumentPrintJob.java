package com.ndlovu.javacore.polymorphism.johannesburgprintshop;

/**
 * Represents a document printing job charged per page.
 */
public final class DocumentPrintJob extends PrintJob {

    private final int pageCount;
    private final double pricePerPage;

    /**
     * Creates a document job and reuses the shared print-job details through the parent class.
     */
    public DocumentPrintJob(String jobReference, String customerName, int pageCount, double pricePerPage) {
        super(jobReference, customerName);
        this.pageCount = pageCount;
        this.pricePerPage = pricePerPage;
    }

    /**
     * Calculates the document price because page count is the billing rule for this job type.
     */
    @Override
    public double calculateCost() {
        // Overriding: the document job uses its own formula instead of the base class.
        return pageCount * pricePerPage;
    }
}
