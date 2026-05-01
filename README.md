# Gilded Rose Refactoring Kata

## Overview
A professional refactoring of the Gilded Rose Kata in **Java 23**, evolving a legacy procedural implementation into a modern, clean, testable architecture using **Composition** and **Immutability**.

---

## Tech Stack

| Concern | Tool / Version                                      |
|---|-----------------------------------------------------|
| Language | Java 23 (Records, Switch Expressions, `Math.clamp`) |
| Build | Gradle / Maven 3.9.6 (Wrapper)                      |
| Logging | SLF4J + Logback                                     |
| Unit Testing | JUnit 5                                             |
| Acceptance Testing | Cucumber (BDD)                                      |
| Coverage | JaCoCo                                              |
| Static Analysis | SonarQube                                           |

---

## Architectural Evolution

### 1. Composition over Inheritance
- **`BetterItem` (Record)**: Composes `Quality` and `Expiration` components instead of inheriting from a base class.
- **Encapsulation**: Business rules (e.g., Quality limits 0–50, or 80 for legendary) live inside the `Quality` record.

### 2. Strategy Pattern
- **`Update` Interface**: Common contract for evolving item states.
- **Concrete Strategies**: `AgedBrie`, `Backstage`, `Conjured`, and `Default` implement specific behaviours.
- **Registry Lookup**: `Map<String, Update>` provides O(1) strategy dispatch by item name.

### 3. Adapter Pattern
Since the legacy `Item` class cannot be modified, `GildedRose.java` acts as an **Adapter** — mapping `Item` → `BetterItem`, applying logic, then syncing results back.

---

## Design Patterns Used

| Pattern | Where |
|---|---|
| **Strategy** | `Update` interface + `AgedBrie`, `Backstage`, `Conjured`, `Default` |
| **Adapter** | `GildedRose.java` bridging legacy `Item` ↔ `BetterItem` |
| **Factory (Enum)** | `ItemType` enum maps item names to strategy suppliers |

---

## Refactoring Principles Applied

1. **Package Organisation** — Logic split into `com.gildedrose.rules` and `com.gildedrose.updater`.
2. **Clean Naming** — No redundant suffixes; updater classes live in their package, so the name says it all.
3. **No Comments** — Self-documenting code via clear naming and small, focused methods.
4. **Constant Extraction** — All magic strings and literals centralised in `ItemRules.java`.
5. **No Nested Loops** — `GildedRose` performs a single linear pass.
6. **Modern Java** — `record`, `sealed interface`, `List.of`, `Map.of` used throughout.

---

## Refactoring Strategy

Followed **Expand and Contract** combined with **TDD**:

| Step | Action |
|---|---|
| Baseline | 100% regression coverage via Cucumber + legacy `TexttestFixture` |
| Decomposition | Extracted the "Big If" → `switch` → separate `Update` classes |
| Domain Modelling | Introduced `Quality` and `Expiration` records for boundary logic |
| Decoupling | `Item` reduced to a pure DTO; all logic moved to new types |
| Final Polish | 100% unit test coverage on every new component |

**Core architectural shifts:**
- Mutable state → **Immutable Records** (`BetterItem`, `Quality`, `Expiration`)
- Open class hierarchy → **Sealed Interface** (`Update`) — closed, exhaustive strategy set
- Magic string dispatch → **Enum Factory** (`ItemType`) mapping names to strategies via `Supplier`

---

## Testing Approach

- **TDD** — No updater logic added without a failing test first; `Conjured` item developed test-first.
- **JUnit 5** — Class-level unit tests for all updaters and domain types; `GildedRose` invoked directly via test cases (not through `main`).
- **Cucumber** — BDD acceptance scenarios covering end-to-end behaviour.
- **Boundary Analysis** — Thresholds tested explicitly: 0, 1, 6, 11 days; quality at 0, 49, 50, 80.
- **Immutability Checks** — Tests assert original objects are never mutated; every update returns a fresh `BetterItem`.

---

## BDD with Cucumber

1. **Write scenario** — Edit `src/test/resources/com/gildedrose/GildedRose.feature`.
2. **Step definitions** — Update `src/test/java/com/gildedrose/StepDefinitions.java`.
3. **Run** — `./gradlew cucumber`

---

## Commit Strategy

- **Granular commits** — One logical change per commit (e.g., extract strategy, add record, remove magic literals).
- **Functional messages** — Describe *what changes for the domain*, not the technical operation.

| ✅ Do | ❌ Avoid |
|---|---|
| `Age Brie now gains quality correctly after sell-by date` | `Refactor AgedBrieUpdater to implement Update interface` |
| `Conjured items degrade twice as fast as normal items` | `Add Conjured class with Update strategy` |
| `Quality never exceeds 50 or drops below 0` | `Add clamping logic to Quality record` |

---

## How to Build and Test

The project supports both builds as **Gradle** or **Maven**. 
Choose your tool wisely:
### Gradle
```bash
# Build and run all tests (JUnit + Cucumber)
./gradlew clean test
```
### Maven
```bash
# Build and run all tests (JUnit + Cucumber)
./mvnw clean test
```
### SonarQube Analysis

```bash
# Start SonarQube locally
docker run -d --name sonarqube -p 9000:9000 sonarqube:latest

or

docker start sonarqube
```
### Gradle
```powershell
# Run analysis (PowerShell)
$env:SONAR_TOKEN = "your_token_here"

./gradlew clean test sonar `
  "-Dsonar.projectKey=Java-Cucumber" `
  "-Dsonar.projectName=Java-Cucumber" `
  "-Dsonar.host.url=http://localhost:9000" `
  "-Dsonar.token=$env:SONAR_TOKEN"
```

```bash
# Run analysis (Bash/Linux/macOS)
export SONAR_TOKEN="your_token_here"

./gradlew clean test sonar \
  -Dsonar.projectKey=Java-Cucumber \
  -Dsonar.projectName=Java-Cucumber \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=$SONAR_TOKEN
```

### Maven

```powershell
# PowerShell
.\mvnw clean verify sonar:sonar `
  "-Dsonar.projectKey=Java-Cucumber" `
  "-Dsonar.projectName=Java-Cucumber" `
  "-Dsonar.host.url=http://localhost:9000" `
  "-Dsonar.login=$env:SONAR_TOKEN"
# PowerShell Coverage
./gradlew clean test jacocoTestReport sonar `
  "-Dsonar.projectKey=Java-Cucumber" `
  "-Dsonar.projectName=Java-Cucumber" `
  "-Dsonar.host.url=http://localhost:9000" `
  "-Dsonar.token=$env:SONAR_TOKEN"
```

```bash
# Run analysis (Bash/Linux/macOS)

.\mvnw clean verify sonar:sonar \
  -Dsonar.projectKey=Java-Cucumber \
  -Dsonar.projectName=Java-Cucumber \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=$SONAR_TOKEN
```
### Container

#### Build
```bash
docker build -t gilded-rose-java . 
```

#### Run
To run a 5-day simulation using Docker:
```bash
docker run --rm gilded-rose-java 2
```

#### Custom Log Level
```bash
docker run --rm -e "DEFAULT_LOG_LEVEL=INFO" gilded-rose-java 2
```
#### Axoniq server
```bash
docker run -d -p 8024:8024 -p 8124:8124 --name axonserver axoniq/axonserver
```
#Run
```bash
./gradlew bootRun
```