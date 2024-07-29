# Astronaut Scheduler Application

## Overview

The Astronaut Scheduler Application is designed to manage and observe tasks assigned to astronauts. The application uses the Observer design pattern to notify different observers when tasks are updated or added. This is useful for logging and monitoring purposes.

## Components

### AstronautSchedulerApp.java

This is the entry point of the application. It sets up the `ScheduleManager`, registers observers (`ConsoleTaskObserver` and `LogManager`), and adds tasks to the schedule.

### ConsoleTaskObserver.java

Implements the `TaskObserver` interface. It prints updates to the console whenever a task is added or updated.

### LogManager.java

Also implements the `TaskObserver` interface. It logs updates to a file named `tasks.log`.

### ScheduleManager.java

Manages the list of tasks and observers. It can add tasks and notify observers of any changes to tasks.

### Task.java

Represents a task with a name, description, and the astronaut assigned to it.

### TaskFactory.java

A utility class to create a list of predefined tasks.

### TaskObserver.java

An interface that defines the `update` method, which observers must implement to get notified about task updates.

## How It Works

1. **Initialization**:
   - `AstronautSchedulerApp` initializes the `ScheduleManager`.
   - It creates instances of `ConsoleTaskObserver` and `LogManager` and registers them with the `ScheduleManager`.

2. **Task Creation**:
   - The `TaskFactory` creates a list of tasks.

3. **Task Addition**:
   - Each task is added to the `ScheduleManager`.
   - The `ScheduleManager` notifies all registered observers of the new task.

## How to Run

1. Compile the Java files:
   ```sh
   javac *.java
