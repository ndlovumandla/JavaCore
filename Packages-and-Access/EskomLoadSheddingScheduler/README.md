# Eskom Load-Shedding Scheduler

## Storyline

Eskom's load-shedding scheduler serves millions of users and must separate public, internal, and core responsibilities carefully. After a serious access-control incident, the engineering team reviewed every class and method to enforce least privilege. This project models that audit by splitting code into packages and applying Java access modifiers deliberately. Public methods expose safe schedule output, protected methods support controlled subclass operations, and private/core internals stay locked down. The code is designed as a beginner tutorial for package structure, imports, and access modifier decisions.

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
EskomLoadSheddingScheduler/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── packagesaccess/
                            ├── app/
                            │   └── EskomLoadSheddingSchedulerDemo.java
                            └── core/
                                ├── LoadSheddingCore.java
                                └── CoreAuditTool.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/packagesaccess/core/*.java src/main/java/com/ndlovu/javacore/packagesaccess/app/*.java
java -cp out com.ndlovu.javacore.packagesaccess.app.EskomLoadSheddingSchedulerDemo
```
