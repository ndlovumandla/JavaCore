# National Health Patient Portal

## Storyline

South Africa's National Department of Health is building a central patient portal that connects hospitals, GPs, pharmacies, and medical schemes. Each participant needs different access rights under POPIA: GPs can update clinical notes, pharmacies can read prescriptions, and medical schemes can view billing codes only. Access boundaries must be enforced in code, not just policy documents. Lungelo structures the project into clinical, billing, identity, and app packages, then applies least-privilege access modifiers to every method. This project demonstrates how Java package design and access modifiers encode real legal access rules.

## Concepts Demonstrated

- package
- import
- public
- private
- protected
- default
- access modifier

## Project Structure

```text
NationalHealthPatientPortal/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── packagesaccess/
                            └── health/
                                ├── app/
                                │   └── NationalHealthPatientPortalDemo.java
                                ├── billing/
                                │   ├── BillingCodeService.java
                                │   └── BillingAuditTool.java
                                ├── clinical/
                                │   └── ClinicalRecordService.java
                                └── identity/
                                    └── PortalIdentityService.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/packagesaccess/health/clinical/*.java src/main/java/com/ndlovu/javacore/packagesaccess/health/billing/*.java src/main/java/com/ndlovu/javacore/packagesaccess/health/identity/*.java src/main/java/com/ndlovu/javacore/packagesaccess/health/app/*.java
java -cp out com.ndlovu.javacore.packagesaccess.health.app.NationalHealthPatientPortalDemo
```
