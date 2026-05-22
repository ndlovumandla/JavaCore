# Bloemfontein Hospital Billing

This project models Medicross Hospital's billing system in Bloemfontein. It shows how one contract can support medical aid claims, government subsidy billing, and private cash billing through the same command set. The design uses a `BillingMethod` interface and an abstract base class for shared state logic, so each billing channel can apply its own rules while the system stays uniform.

## Project Structure

```text
BloemfonteinHospitalBilling/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── abstraction/
                            └── hospitalbilling/
                                ├── BloemfonteinHospitalBillingDemo.java
                                ├── BillingMethod.java
                                ├── ComplianceLoggable.java
                                ├── AbstractBillingChannel.java
                                ├── MedicalAidBilling.java
                                ├── GovernmentSubsidyBilling.java
                                └── PrivateCashBilling.java
```

## How to Run

From the `BloemfonteinHospitalBilling` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/abstraction/hospitalbilling/*.java
java -cp out com.ndlovu.javacore.abstraction.hospitalbilling.BloemfonteinHospitalBillingDemo
```

## Storyline

Sister Modiegi needs one billing dashboard that can trigger the same actions across different payment channels. Karabo solves this with interface-based contracts and shared abstract logic. New billing channels can be added by implementing the contract, without rewriting the entire billing workflow.
