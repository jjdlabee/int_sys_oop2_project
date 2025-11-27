# Design

## Design Patterns Used

The Intellectual Systems Game implements several industry-standard design patterns to ensure maintainability, extensibility, and separation of concerns.

---

## 1. State Pattern

### Purpose
Encapsulates the game's state transitions and behavior changes based on the current game phase.

### Implementation
- **Interface:** `GameState`
- **Concrete States:** 
  - `StartState` – Initial welcome screen
  - `LoadState` – Load game data
  - `PlayerSetupState` – Player registration
  - `CategorySelectState` – Category selection
  - `QuestionSelectState` – Question selection
  - `AnswerState` – Answer submission
  - `EndTurnState` – Turn completion
  - `GameOverState` – Final scores and report

### Location
`com.intellectual_systems.controller.state.*`

### Justification

**Why State Pattern?**

1. **Eliminates Complex Conditionals** – Instead of massive if-else chains checking game phase, each state handles its own logic
2. **Encapsulation** – Each state is self-contained; changes to one state don't affect others
3. **Extensibility** – New game states can be added by implementing `GameState` interface
4. **Maintainability** – State transitions are explicit and easy to trace
5. **Single Responsibility** – Each state class has one reason to change

**Example:**
```java
public class GameEngine {
    GameState state;
    
    public void setState(GameState newState) {
        this.state = newState;
    }
    
    public void renderCurrentState() {
        state.renderCurrentState();
    }
}

// Instead of:
// if (gamePhase == START) { ... }
// else if (gamePhase == LOAD) { ... }
// etc.
```

**SOLID Principle:** Open/Closed Principle (OCP) – Open for extension (add new states), closed for modification (existing states unchanged)

---

## 2. Command Pattern

### Purpose
Encapsulates user actions as objects, enabling queuing, logging, and undo functionality.

### Implementation
- **Interface:** `Command`
- **Concrete Commands:**
  - `StartCommand` – Start game
  - `PlayerSetupCommand` – Register players
  - `SelectCategoryCommand` – Choose category
  - `SelectQuestionCommand` – Choose question
  - `AnswerQuestionCommand` – Submit answer
  - `EndTurnCommand` – Complete turn
  - `GameOverCommand` – End game and log events
  - `GameReportCommand` – Generate report

### Location
`com.intellectual_systems.command.*`

### Justification

**Why Command Pattern?**

1. **Decoupling Actions from Actors** – States don't directly modify game logic; they invoke commands
2. **Auditability** – Every user action is a command that can be logged
3. **Undo/Redo Support** – Commands can potentially be undone or replayed
4. **Flexible Invocation** – Commands can be queued, scheduled, or executed conditionally
5. **Event Tracking** – Integrates seamlessly with event logging system

**Example:**
```java
public class AnswerState implements GameState {
    @Override
    public void renderCurrentState() {
        // User selects answer
        char choice = scanner.next().charAt(0);
        
        // Invoke command instead of modifying game state directly
        AnswerQuestionCommand cmd = new AnswerQuestionCommand(gameEngine, category, value, choice);
        cmd.execute(); // Command encapsulates the action
    }
}
```

**SOLID Principle:** Single Responsibility Principle (SRP) – Each command has one responsibility; Dependency Inversion (DIP) – Depend on Command interface, not concrete implementations

---

## 3. Observer Pattern

### Purpose
Notifies multiple listeners of game events in a decoupled manner.

### Implementation
- **Subject:** `GameEvent` – Maintains listeners and notifies them
- **Observer Interface:** `GameEventListener`
- **Concrete Observer:** `EventLogger` – Listens to game events and logs them

### Location
`com.intellectual_systems.logging.*`

### Justification

**Why Observer Pattern?**

1. **Loose Coupling** – The game engine doesn't need to know about loggers; it just notifies observers
2. **Scalability** – New listeners (e.g., analytics, UI updates) can be added without modifying GameEngine
3. **Separation of Concerns** – Logging logic is isolated from game logic
4. **Dynamic Subscription** – Listeners can be added/removed at runtime

**Example:**
```java
public class GameEngine {
    private GameEvent gameEvent = new GameEvent();
    private EventLogger eventLogger = new EventLogger();
    
    public GameEngine() {
        // Register observer
        this.gameEvent.addListener(this.eventLogger);
    }
    
    public void addPlayerGameEvent(String activity, Turn turn) {
        // Notify all observers
        this.gameEvent.newGameEvent(this.gameId, activity, turn);
    }
}

// EventLogger (observer) receives notification
@Override
public void updateOnGameEvent(GameEvent event) {
    eventLog.add(event); // Log the event
}
```

**SOLID Principle:** Open/Closed Principle (OCP) – Open for new observers without modifying existing code

---

## 4. Strategy Pattern

### Purpose
Encapsulates different data parsing strategies (JSON, XML, CSV) behind a common interface.

### Implementation
- **Interface:** `GameDataParser`
- **Concrete Strategies:**
  - `JsonParser` – Parse JSON format
  - `XmlParser` – Parse XML format
  - `CsvParser` – Parse CSV format

### Location
`com.intellectual_systems.parser.*`

### Justification

**Why Strategy Pattern?**

1. **Runtime Flexibility** – Users can choose parsing strategy at runtime
2. **Extensibility** – New data formats (YAML, TOML, etc.) can be added by implementing `GameDataParser`
3. **Encapsulation** – Each parser's algorithm is self-contained
4. **Testability** – Each strategy can be tested independently
5. **No Duplicated Logic** – Avoids giant if-else chains for format selection

**Example:**
```java
public void execute() {
    String choice = scanner.next(); // User selects format: 1 (JSON), 2 (XML), 3 (CSV)
    
    GameDataParser parser;
    switch (choice) {
        case "1": parser = new JsonParser(); break;
        case "2": parser = new XmlParser(); break;
        case "3": parser = new CsvParser(); break;
    }
    
    parser.parse(filePath); // Execute chosen strategy
}
```

**SOLID Principle:** Single Responsibility Principle (SRP) – Each parser handles one format; Liskov Substitution (LSP) – Any parser can be substituted for another

---

## 5. Data Transfer Object (DTO) Pattern

### Purpose
Immutable snapshots of mutable game events to prevent data corruption during logging.

### Implementation
- **Class:** `LoggedEvent` – Immutable snapshot of a game event
- **Usage:** EventLogger creates LoggedEvent copies to avoid referencing mutable GameEvent

### Location
`com.intellectual_systems.logging.LoggedEvent`

### Justification

**Why DTO Pattern?**

1. **Data Integrity** – Immutable snapshots prevent accidental modifications
2. **Prevents Aliasing Bugs** – Storing references to mutable objects caused duplicated rows in logs
3. **Clear Separation** – Distinguishes between in-game mutable events and logged immutable records
4. **Thread Safety** – Immutable objects are inherently thread-safe (important for future concurrency)

**Example:**
```java
// Before: Storing mutable GameEvent references caused duplicates
List<GameEvent> eventLog = new ArrayList<>();
eventLog.add(event); // Stores reference to mutable object

// After: Create immutable snapshot
LoggedEvent snapshot = new LoggedEvent(
    event.getCaseID(),
    event.getTurn().getPlayer().getUsername(),
    event.getActivity(),
    event.getTimestamp(),
    // ... other fields
);
eventLog.add(snapshot); // Stores immutable copy
```

**SOLID Principle:** Dependency Inversion (DIP) – Depend on immutable interfaces

---

## SOLID Principles Application

### 1. Single Responsibility Principle (SRP)

**Definition:** A class should have only one reason to change.

**Application:**
- `GameEngine` – Manages game state and turn flow
- `EventLogger` – Handles event logging only
- `CSVLogger` – Handles CSV writing only
- `GameBoard` – Handles board representation only
- `PlayerSetupCommand` – Handles player setup only

**Example:**
```java
// ✓ Good: Each class has one responsibility
class EventLogger {
    void updateOnGameEvent(GameEvent event) { /* Log event */ }
}

class CSVLogger {
    void logGameEvents(List<LoggedEvent> events) { /* Write CSV */ }
}

// ✗ Bad: Mixed responsibilities
class EventLoggerAndReporter {
    void logEvent() { }
    void generateReport() { }
}
```

---

### 2. Open/Closed Principle (OCP)

**Definition:** Classes should be open for extension but closed for modification.

**Application:**
- **State Pattern** – Add new states without modifying GameEngine or existing states
- **Parser Strategy** – Add new parsers without changing LoadState
- **Observer Pattern** – Add new event listeners without modifying GameEvent

**Example:**
```java
// ✓ Open for extension: New parser added without modifying existing code
public class YamlParser implements GameDataParser {
    @Override
    public void parse(String filePath) { /* YAML parsing logic */ }
}

// Use it immediately in LoadState without changes
parser = new YamlParser();
```

---

### 3. Liskov Substitution Principle (LSP)

**Definition:** Derived classes must be substitutable for their base types without breaking behavior.

**Application:**
- All `GameState` implementations can replace each other seamlessly
- All `GameDataParser` implementations are interchangeable
- All `Command` implementations honor the execute() contract

**Example:**
```java
// ✓ Any GameState can be used interchangeably
GameState state = new StartState();      // Works
state = new LoadState();                 // Works
state = new PlayerSetupState();          // Works

// Any parser works with LoadState
GameDataParser parser = new JsonParser();  // Works
parser = new XmlParser();                  // Works
parser = new CsvParser();                  // Works
```

---

### 4. Interface Segregation Principle (ISP)

**Definition:** Clients should not depend on interfaces they don't use.

**Application:**
- `GameState` interface has only two methods: `renderCurrentState()` and `renderNextState()`
- `Command` interface has only one method: `execute()`
- `GameEventListener` interface has only one method: `updateOnGameEvent()`
- `GameDataParser` interface has only one method: `parse()`

**Example:**
```java
// ✓ Specific, focused interfaces
public interface Command {
    void execute();
}

public interface GameEventListener {
    void updateOnGameEvent(GameEvent event);
}

// ✗ Bloated interface (bad)
public interface GameInterface {
    void execute();
    void render();
    void validate();
    void log();
    void report();
    // ... many more methods
}
```

---

### 5. Dependency Inversion Principle (DIP)

**Definition:** Depend on abstractions, not concretions.

**Application:**
- GameEngine depends on `GameState` interface, not concrete states
- LoadState depends on `GameDataParser` interface, not concrete parsers
- GameEvent depends on `GameEventListener` interface, not EventLogger directly
- Commands depend on GameEngine interface, not concrete implementation

**Example:**
```java
// ✓ Depend on abstraction
public class GameEngine {
    GameState state; // Depends on interface
    
    public void setState(GameState newState) {
        this.state = newState; // Can accept any GameState
    }
}

// ✗ Depend on concrete class (tight coupling)
public class GameEngine {
    StartState state; // Depends on concrete class
}
```

---

## Architecture Overview

### Component Diagram

```
┌─────────────────────────────────────────────────────────┐
│                   App (Main)                            │
│                 Entry Point                             │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              GameEngine (Controller)                    │
│         - State Management                             │
│         - Turn Flow Control                            │
│         - Event Broadcasting                           │
└────────┬─────────────────────────────┬─────────────────┘
         │                             │
         ▼                             ▼
    ┌────────────┐              ┌─────────────────┐
    │ GameState  │              │ GameEvent       │
    │ (State)    │              │ (Observer)      │
    └────────────┘              └────────┬────────┘
         │                               │
         ├─ StartState                   ▼
         ├─ LoadState              ┌──────────────┐
         ├─ PlayerSetupState       │ EventLogger  │
         ├─ CategorySelectState    └──────┬───────┘
         ├─ QuestionSelectState           │
         ├─ AnswerState                   ▼
         ├─ EndTurnState           ┌──────────────┐
         └─ GameOverState          │ CSVLogger    │
                                   │ LoggedEvent  │
                                   └──────────────┘

    ┌─────────────┐
    │   Command   │ (Command Pattern)
    ├─────────────┤
    ├─ StartCommand
    ├─ PlayerSetupCommand
    ├─ SelectCategoryCommand
    ├─ SelectQuestionCommand
    ├─ AnswerQuestionCommand
    ├─ EndTurnCommand
    ├─ GameOverCommand
    └─ GameReportCommand

    ┌─────────────────────┐
    │ GameDataParser      │ (Strategy Pattern)
    ├─ JsonParser        │
    ├─ XmlParser         │
    └─ CsvParser         │
```

---

## Design Decisions Explained

### 1. Why State Pattern for Game Flow?

**Alternative Considered:** Method chaining / Sequential execution

**Decision Rationale:**
- State pattern makes game phases explicit and testable
- Easier to add/remove game states
- Prevents invalid state transitions
- Better for debugging and visualization

---

### 2. Why Command Pattern for User Actions?

**Alternative Considered:** Direct method calls in states

**Decision Rationale:**
- Commands enable comprehensive event logging
- Facilitates undo/redo if needed
- Commands can be queued or scheduled
- Clear audit trail of player actions
- Decouples states from game logic

---

### 3. Why Observer Pattern for Event Logging?

**Alternative Considered:** Direct coupling of GameEngine to EventLogger

**Decision Rationale:**
- Multiple listeners can be added (future analytics, UI updates, etc.)
- Easy to enable/disable logging without code changes
- Reduces coupling between engine and logger
- Supports extensibility for future features

---

### 4. Why Strategy Pattern for Data Parsing?

**Alternative Considered:** Giant if-else switch for each format

**Decision Rationale:**
- Easy to add new data formats without modifying LoadState
- Each parser is independently testable
- Clear separation of parsing logic
- Follows Open/Closed Principle

---

### 5. Why DTO for Event Logging?

**Alternative Considered:** Storing references to mutable GameEvent

**Decision Rationale:**
- Fixed critical bug where log rows were duplicated/overwritten
- GameEvent is reused (mutated) repeatedly; storing references caused aliasing bugs
- LoggedEvent snapshots ensure data integrity
- Immutable objects are thread-safe

---

## Testing Strategy

### Unit Tests
- Model classes (Player, Question, Category) tested independently
- Parsers tested with sample data files
- Commands tested with mock GameEngine

### Integration Tests
- State transitions verified
- Event logging verified end-to-end
- CSV output verified

### Location
`src/test/java/com/intellectual_systems/AppTest.java`

---

## Future Enhancements

Potential improvements maintaining current design:

1. **Undo/Redo** – Implement undo() in Command interface
2. **Persistence** – Add database support by extending Strategy pattern
3. **Multiplayer Networking** – Add network state decorator
4. **Difficulty Levels** – Add decorator pattern for question difficulty
5. **Analytics** – Add new Observer for game analytics

---

## References

1. Gang of Four Design Patterns
2. SOLID Principles by Robert C. Martin
3. Clean Code by Robert C. Martin
4. Refactoring: Improving the Design of Existing Code by Martin Fowler

---

Last Updated: November 2025
