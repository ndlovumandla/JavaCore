# JSE Listed Company Disclosure System

## Storyline

The JSE disclosure portal must separate public API access, internal compliance review, and core calculations that should never be called directly by outsiders. A security audit found API-layer code bypassing compliance checks and touching core logic directly. Priscilla refactored the code into strict packages and assigned least-privilege access modifiers to each operation. Core internals are package-private where possible, inheritance points are explicitly protected, and public methods expose only approved workflows. This project is a beginner tutorial on turning architecture and access modifiers into enforceable policy.

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
JSEListedCompanyDisclosureSystem/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── packagesaccess/
                            └── jse/
                                ├── api/
                                │   └── JSEListedCompanyDisclosureSystemDemo.java
                                ├── compliance/
                                │   └── ComplianceReviewService.java
                                └── core/
                                    ├── CoreDisclosureEngine.java
                                    └── CoreDisclosureGateway.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/packagesaccess/jse/core/*.java src/main/java/com/ndlovu/javacore/packagesaccess/jse/compliance/*.java src/main/java/com/ndlovu/javacore/packagesaccess/jse/api/*.java
java -cp out com.ndlovu.javacore.packagesaccess.jse.api.JSEListedCompanyDisclosureSystemDemo
```
