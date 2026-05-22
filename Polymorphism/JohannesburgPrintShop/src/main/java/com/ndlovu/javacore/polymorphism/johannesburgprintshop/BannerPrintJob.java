package com.ndlovu.javacore.polymorphism.johannesburgprintshop;

/**
 * Represents a large-format banner job charged by square metre.
 */
public final class BannerPrintJob extends PrintJob {

    private final double widthMetres;
    private final double heightMetres;
    private final double ratePerSquareMetre;

    /**
     * Creates a banner job and reuses the shared print-job details through the parent class.
     */
    public BannerPrintJob(String jobReference, String customerName, double widthMetres, double heightMetres,
                          double ratePerSquareMetre) {
        super(jobReference, customerName);
        this.widthMetres = widthMetres;
        this.heightMetres = heightMetres;
        this.ratePerSquareMetre = ratePerSquareMetre;
    }

    /**
     * Calculates the banner price because area is the billing rule for this job type.
     */
    @Override
    public double calculateCost() {
        return widthMetres * heightMetres * ratePerSquareMetre;
    }
}
