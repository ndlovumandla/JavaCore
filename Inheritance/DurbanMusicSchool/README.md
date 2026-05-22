# Durban Music School

This subproject tells the story of the Bayview Music Academy in Durban, where students are all part of one shared administration system but still belong to different instrument streams. The shared `MusicStudent` class holds the common enrollment and billing data, while the piano, guitar, voice, and Zulu percussion classes each override their grading and report logic in their own files.

## What this demo shows

- `extends` for building instrument-specific student types from one shared parent class
- `super` for reusing the common enrollment setup in each subclass constructor
- method overriding for stream-specific grading and report generation
- the IS-A relationship between `MusicStudent` and each child class
- polymorphism through a `List<MusicStudent>` that prints different reports at runtime

## Project structure

```text
DurbanMusicSchool/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── inheritance/
                            └── durbanmusicschool/
                                ├── DurbanMusicSchoolDemo.java
                                ├── MusicStudent.java
                                ├── PianoStudent.java
                                ├── GuitarStudent.java
                                ├── VoiceStudent.java
                                └── ZuluPercussionStudent.java
```

## How to run
### Using javac directly

From the `DurbanMusicSchool` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/inheritance/durbanmusicschool/*.java
java -cp out com.ndlovu.javacore.inheritance.durbanmusicschool.DurbanMusicSchoolDemo
```

## Storyline

Bayview Music Academy in Durban wants one shared student system for everyone, but each instrument stream has its own grading expectations and recital demands. Piano students are judged on technique and sight-reading, guitar students on chord fluency and band readiness, voice students on pitch and breath control, and Zulu percussion students on rhythm and ensemble timing. The code models that real-world split by storing shared admin behavior in the parent class and stream-specific behavior in each subclass.