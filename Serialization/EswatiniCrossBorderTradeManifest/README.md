# Eswatini Cross-Border Trade Manifest

## Storyline

Truck drivers moving through the Oshoek border crossing carry digital cargo manifests on USB drives between South Africa and Eswatini systems. Each manifest includes vehicle details, driver identity, goods, declared values, and phytosanitary proof for fresh produce. Because border network links fail several times a day, both systems must work offline and exchange files directly. The manifest must be serialized on one side and deserialized on the other, even when software versions differ slightly. This project shows a beginner-friendly Java serialization flow with compatibility-safe recovery handling.

## Concepts Demonstrated

- Serializable
- transient
- ObjectOutputStream
- ObjectInputStream
- serialVersionUID

## Project Structure

```text
EswatiniCrossBorderTradeManifest/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── serialization/
                            └── eswatini/
                                ├── EswatiniCrossBorderTradeManifestDemo.java
                                └── CrossBorderTradeManifest.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/serialization/eswatini/*.java
java -cp out com.ndlovu.javacore.serialization.eswatini.EswatiniCrossBorderTradeManifestDemo
```

## Output Notes

- The demo writes a USB-style file named `manifest_usb.bin`.
- A transient checkpoint flag is excluded from serialized file data.
- Recovery logic handles deserialization failures gracefully.
