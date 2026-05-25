# South African ID Number Validator

## Storyline

The Department of Home Affairs verifies tens of thousands of South African ID numbers every day. Each 13-digit ID string encodes birth date, gender sequence, citizenship status, and a final checksum digit. Small edits to one character can change identity details or hide fraud attempts, so validation must be strict and repeatable. This project shows how Zanele inspects ID strings character by character, checks ASCII digit rules, validates encoded fields, and confirms the Luhn checksum. The result turns a plain String into a trustworthy identity record.

## Concepts Demonstrated

- String
- charAt
- substring
- Unicode
- ASCII
- length
- equals
- toUpperCase

## Project Structure

```text
SouthAfricanIdNumberValidator/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── strings/
                            └── idvalidator/
                                ├── SouthAfricanIdNumberValidatorDemo.java
                                └── SouthAfricanIdValidator.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/strings/idvalidator/*.java
java -cp out com.ndlovu.javacore.strings.idvalidator.SouthAfricanIdNumberValidatorDemo
```
