# Pan-African Language Keyboard

## Storyline

PanType builds a mobile keyboard for southern African languages that include characters beyond ASCII. Inputs can contain click symbols like ǀ, ǃ, and ǁ, plus accented vowels such as ē and ò, all of which require Unicode-aware handling. The engine must distinguish ASCII from Unicode, inspect character positions, and process multi-character linguistic units safely. It also needs to normalize and compare language labels and apply uppercase conversion for display behavior. This project demonstrates practical Java String and Unicode processing in a beginner-friendly way.

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
PanAfricanLanguageKeyboard/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── strings/
                            └── pantype/
                                ├── PanAfricanLanguageKeyboardDemo.java
                                └── KeyboardStringEngine.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/strings/pantype/*.java
java -cp out com.ndlovu.javacore.strings.pantype.PanAfricanLanguageKeyboardDemo
```
