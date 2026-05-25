# Newsroom Deadline Engine

This project simulates Daily Maverick's deadline-night submission pipeline where multiple editorial desks publish in parallel. It demonstrates Java multithreading with `Thread`, `Runnable`, `synchronized`, `sleep`, and `join`. It also includes a race-condition counter demo and deadlock-safe lock ordering for final publication build operations.

## Storyline

The Daily Maverick newsroom in Cape Town runs five editorial desks on deadline night: politics, economy, sport, lifestyle, and investigations. In the old pipeline, desks were processed one after another, so later desks missed publication windows. CTO Yolanda redesigns the flow so each desk submits in parallel as its own thread into one synchronized publishing queue. The platform waits for all desks with `join()` before triggering the final build, removing the deadline bottleneck.

## Project Structure

```text
NewsroomDeadlineEngine/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── threads/
                            └── newsroom/
                                ├── NewsroomDeadlineEngineDemo.java
                                ├── ArticleSubmission.java
                                ├── SubmissionChannel.java
                                ├── SynchronizedPublishingQueue.java
                                └── EditorialDeskTask.java
```

## How to Run

From the NewsroomDeadlineEngine folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/threads/newsroom/*.java
java -cp out com.ndlovu.javacore.threads.newsroom.NewsroomDeadlineEngineDemo
```

## Concepts Demonstrated

- Thread
- Runnable
- synchronized
- sleep
- join
- race condition
- deadlock (and prevention)
