# Gilded Rose Refactoring Kata

## 📋 Overview
This repository contains a professional refactoring of the Gilded Rose Kata using **Java 23**. The project has been evolved from a legacy procedural implementation into a modern, clean, and testable architecture centered on **Composition** and **Immutability**.

---

## 🏗️ Architectural Evolution

### 1. From Inheritance to Association/Composition
We have moved away from rigid class hierarchies. The system now uses **Association** and **Composition**:
* **`BetterItem` (Record)**: Composes `Quality` and `Expiration` components rather than inheriting from a base class.
* **Encapsulation**: Business rules (e.g., Quality limits 0-50, or 80 for legendary) are encapsulated within the `Quality` record.

### 2. Strategy Design Pattern
We replaced complex branching and nested loops with the **Strategy Pattern**:
* **`Update` Interface**: Defines a common contract for evolving item states.
* **Concrete Strategies**: `AgedBrie`, `Backstage`, `Conjured`, and `Default` implement specific behaviors.
* **Registry Lookup**: A `Map<String, Update>` provides $O(1)$ lookup for the correct strategy based on the item name.

### 3. The Adapter Pattern
Since we are forbidden from altering the legacy `Item` class (the "Goblin's code"), we implemented an **Adapter** approach in `GildedRose.java`. It maps the legacy `Item` to our internal `BetterItem` record, processes the logic, and syncs the results back.

---

## 🛠️ Tech Stack
* **Java 23**: Utilizing `Records`, `Switch Expressions`, and `Math.clamp`.
* **Build Tool**: Gradle.
* **Logging**: SLF4J with Logback.
* **Testing**:
    * **JUnit 5**: For class-level unit tests and TDD.
    * **Cucumber**: For BDD acceptance criteria.
* **Analysis**: JaCoCo (Coverage) and SonarQube (Static Analysis).

---

## 🧼 Refactoring Principles Applied
1.  **Package Organization**: Logic moved to `com.gildedrose.rules` and `com.gildedrose.updater`.
2.  **Clean Naming**: Removed redundant "Updater" suffixes from class names.
3.  **No Comments**: Code is self-documenting through clear naming and small methods.
4.  **Constant Extraction**: All magic strings and literals moved to `ItemRules.java`.
5.  **No Nested Loops**: Streamlined `GildedRose` logic to a single linear pass.

---

## 🟢 BDD (Behavior Driven Development)

To add or verify features using Cucumber:
1.  **Write Scenario**: Edit `src/test/resources/com/gildedrose/GildedRose.feature`.
2.  **Step Definitions**: Update `src/test/java/com/gildedrose/StepDefinitions.java` to match Gherkin steps.
3.  **Run**: Execute `./gradlew cucumber`.

---

## 🚀 Execution Commands

### Build and Test
```powershell
# Clean and run all JUnit + Cucumber tests
./gradlew clean test

# Start local SonarQube
docker run -d --name sonarqube -p 9000:9000 sonarqube:latest

# Set your token and run analysis
$env:SONAR_TOKEN = "your_token_here"

# Run tests and send data to SonarQube
./gradlew clean test sonar `
  "-Dsonar.projectKey=Java-Cucumber" `
  "-Dsonar.projectName=Java-Cucumber" `
  "-Dsonar.host.url=http://localhost:9000" `
  "-Dsonar.token=$env:SONAR_TOKEN"