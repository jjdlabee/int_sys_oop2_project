# Implementation

## Getting Started

This page provides step-by-step instructions for building, running, and extending the Intellectual Systems Game.

---

## Prerequisites

### System Requirements
- **Operating System:** Windows, macOS, or Linux
- **Java Version:** JDK 25 or higher
- **Build Tool:** Maven 3.6+
- **RAM:** Minimum 512 MB (recommended 1 GB+)
- **Disk Space:** ~500 MB for project and dependencies

### Installation

#### Windows
1. Download JDK 25 from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Eclipse Adoptium](https://adoptium.net/)
2. Install Maven from [Apache Maven](https://maven.apache.org/download.cgi)
3. Add Java and Maven to your system PATH
4. Verify installation:
   ```powershell
   java -version
   mvn -version
   ```

#### macOS
```bash
# Using Homebrew
brew install openjdk@25
brew install maven

# Verify installation
java -version
mvn -version
```

#### Linux (Ubuntu/Debian)
```bash
sudo apt-get update
sudo apt-get install openjdk-25-jdk maven

# Verify installation
java -version
mvn -version
```

---

## Project Setup

### 1. Clone or Download Repository
```bash
git clone <repository-url>
cd int_sys_oop2_project
```

### 2. Build the Project
```bash
mvn clean install
```

This command:
- Cleans previous builds (`clean`)
- Downloads dependencies
- Compiles source code
- Runs unit tests
- Packages the application

### 3. Run the Application

#### Option A: From IDE (Recommended)
1. Open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Locate `Main.java` in `src/main/java/com/intellectual_systems/app/`
3. Right-click → Run

#### Option B: From Terminal
```bash
cd target
java -cp int_sys_oop2_project-1.jar com.intellectual_systems.app.Main
```

#### Option C: Using Maven
```bash
mvn exec:java -Dexec.mainClass="com.intellectual_systems.app.Main"
```

---

## Project Structure

```
int_sys_oop2_project/
├── src/
│   ├── main/
│   │   ├── java/com/intellectual_systems/
│   │   │   ├── app/
│   │   │   │   └── Main.java                    # Entry point
│   │   │   ├── controller/
│   │   │   │   ├── GameEngine.java              # Game state manager
│   │   │   │   ├── GameState.java               # State interface
│   │   │   │   ├── TurnManager.java             # Turn flow
│   │   │   │   └── state/                       # State implementations
│   │   │   │       ├── StartState.java
│   │   │   │       ├── LoadState.java
│   │   │   │       ├── PlayerSetupState.java
│   │   │   │       ├── CategorySelectState.java
│   │   │   │       ├── QuestionSelectState.java
│   │   │   │       ├── AnswerState.java
│   │   │   │       ├── EndTurnState.java
│   │   │   │       └── GameOverState.java
│   │   │   ├── command/
│   │   │   │   ├── Command.java                 # Command interface
│   │   │   │   ├── StartCommand.java
│   │   │   │   ├── PlayerSetupCommand.java
│   │   │   │   ├── SelectCategoryCommand.java
│   │   │   │   ├── SelectQuestionCommand.java
│   │   │   │   ├── AnswerQuestionCommand.java
│   │   │   │   ├── EndTurnCommand.java
│   │   │   │   ├── GameOverCommand.java
│   │   │   │   └── GameReportCommand.java
│   │   │   ├── model/
│   │   │   │   ├── Player.java                  # Player entity
│   │   │   │   ├── Question.java                # Question entity
│   │   │   │   ├── Category.java                # Category entity
│   │   │   │   ├── Turn.java                    # Turn entity
│   │   │   │   ├── GameBoard.java               # Board display
│   │   │   │   └── GameSummary.java             # Game statistics
│   │   │   ├── parser/
│   │   │   │   ├── GameDataParser.java          # Parser interface
│   │   │   │   ├── JsonParser.java
│   │   │   │   ├── XmlParser.java
│   │   │   │   └── CsvParser.java
│   │   │   ├── logging/
│   │   │   │   ├── GameEvent.java               # Event subject
│   │   │   │   ├── GameEventListener.java       # Observer interface
│   │   │   │   ├── EventLogger.java             # Event observer
│   │   │   │   ├── LoggedEvent.java             # Immutable event snapshot
│   │   │   │   └── CSVLogger.java               # CSV export
│   │   │   └── reporting/
│   │   │       ├── TxtReportGenerator.java
│   │   │       └── PdfReportGenerator.java
│   │   └── resources/
│   ├── test/
│   │   └── java/com/intellectual_systems/
│   │       └── AppTest.java                     # Unit tests
│   └── wiki/
│       ├── Home.md
│       ├── Introduction.md
│       ├── Design.md
│       └── Implementation.md
├── pom.xml                                       # Maven configuration
├── README.md
└── target/                                       # Build output
    ├── classes/                                  # Compiled code
    ├── test-classes/
    └── int_sys_oop2_project-1.jar
```

---

## File Format Details

### Input Formats

The application supports three data input formats for game questions. Each format must contain the same structural information: categories with questions.

#### 1. JSON Format

**File Extension:** `.json`

**Structure:**
```json
{
  "categories": [
    {
      "name": "Variables & Data Types",
      "questions": [
        {
          "value": 100,
          "questionText": "What is a variable?",
          "answers": ["A container for data", "A type of loop", "A function", "A class"],
          "correctAnswer": "A"
        },
        {
          "value": 200,
          "questionText": "What is the difference between int and double?",
          "answers": ["int holds whole numbers, double holds decimals", "No difference", "int is faster", "double is smaller"],
          "correctAnswer": "A"
        }
      ]
    },
    {
      "name": "Control Structures",
      "questions": [
        {
          "value": 100,
          "questionText": "What is a loop?",
          "answers": ["Repeated code execution", "A type of variable", "A class method", "An import statement"],
          "correctAnswer": "A"
        }
      ]
    }
  ]
}
```

**Field Descriptions:**
- `categories` (array) – List of question categories
- `name` (string) – Category name (e.g., "Variables & Data Types")
- `questions` (array) – Questions in this category
- `value` (integer) – Point value (100, 200, 300, 400, 500)
- `questionText` (string) – The question prompt
- `answers` (array) – Four answer options (A, B, C, D)
- `correctAnswer` (string) – Correct option letter ("A", "B", "C", or "D")

**Example File Location:**
```
sample_game_JSON.json
```

---

#### 2. XML Format

**File Extension:** `.xml`

**Structure:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<game>
  <category name="Variables & Data Types">
    <question value="100">
      <text>What is a variable?</text>
      <answers>
        <answer option="A">A container for data</answer>
        <answer option="B">A type of loop</answer>
        <answer option="C">A function</answer>
        <answer option="D">A class</answer>
      </answers>
      <correct>A</correct>
    </question>
    <question value="200">
      <text>What is the difference between int and double?</text>
      <answers>
        <answer option="A">int holds whole numbers, double holds decimals</answer>
        <answer option="B">No difference</answer>
        <answer option="C">int is faster</answer>
        <answer option="D">double is smaller</answer>
      </answers>
      <correct>A</correct>
    </question>
  </category>
  <category name="Control Structures">
    <question value="100">
      <text>What is a loop?</text>
      <answers>
        <answer option="A">Repeated code execution</answer>
        <answer option="B">A type of variable</answer>
        <answer option="C">A class method</answer>
        <answer option="D">An import statement</answer>
      </answers>
      <correct>A</correct>
    </question>
  </category>
</game>
```

**Element Descriptions:**
- `<game>` – Root element
- `<category name="...">` – Category with name attribute
- `<question value="...">` – Question with point value
- `<text>` – Question text
- `<answers>` – Container for answer options
- `<answer option="A|B|C|D">` – Individual answer with letter option
- `<correct>` – Correct answer letter

**Example File Location:**
```
sample_game_XML.xml
```

---

#### 3. CSV Format

**File Extension:** `.csv`

**Structure:**
```csv
Category,Value,Question,Answer_A,Answer_B,Answer_C,Answer_D,Correct
Variables & Data Types,100,What is a variable?,A container for data,A type of loop,A function,A class,A
Variables & Data Types,200,What is the difference between int and double?,int holds whole numbers double holds decimals,No difference,int is faster,double is smaller,A
Control Structures,100,What is a loop?,Repeated code execution,A type of variable,A class method,An import statement,A
```

**Column Descriptions:**
| Column | Description |
|--------|-------------|
| `Category` | Category name |
| `Value` | Point value (100-500) |
| `Question` | Question text |
| `Answer_A` | Option A answer text |
| `Answer_B` | Option B answer text |
| `Answer_C` | Option C answer text |
| `Answer_D` | Option D answer text |
| `Correct` | Correct answer (A, B, C, or D) |

**Example File Location:**
```
sample_game_CSV.csv
```

---

### Output Formats

#### Event Log (game_events_log.csv)

**Auto-Generated File:** `game_events_log.csv`

**Structure:**
```csv
Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result
GAME001,player1,Select Player Count,,,,B,
GAME001,player1,Enter Player Name,,,,player1,
GAME001,player1,Start Game,,,,Start,
GAME001,player1,Select Category,,Variables & Data Types,,
GAME001,player1,Select Question Value,,Variables & Data Types,100,
GAME001,player1,Answer Question,,Variables & Data Types,100,B,Incorrect
GAME001,player2,Answer Question,,Control Structures,200,D,Incorrect
```

**Column Descriptions:**
| Column | Description |
|--------|-------------|
| `Case_ID` | Game identifier (GAME001, GAME002, etc.) |
| `Player_ID` | Username of player performing action |
| `Activity` | Type of activity (Start Game, Answer Question, etc.) |
| `Timestamp` | When the event occurred (currently empty) |
| `Category` | Selected category (if applicable) |
| `Question_Value` | Point value of question (if applicable) |
| `Answer_Given` | Player's answer choice (if applicable) |
| `Result` | Outcome of action (Correct/Incorrect/N/A) |

**Features:**
- **Append Mode:** Existing events are never overwritten
- **One Write per Game:** All events from a game written when game ends
- **Immutable Records:** Event snapshots prevent data corruption
- **Audit Trail:** Complete history of all game actions

---

## Extending the Application

### Adding a New Data Parser

To support a new file format (e.g., YAML):

1. **Create Parser Class:**
```java
package com.intellectual_systems.parser;

import java.util.List;
import com.intellectual_systems.model.Category;

public class YamlParser implements GameDataParser {
    @Override
    public void parse(String filePath) {
        // Implementation: Read YAML file and parse into Category objects
        // Call GameDataFactory or equivalent to create Category/Question objects
    }
}
```

2. **Register Parser in LoadState:**
```java
public class LoadState implements GameState {
    @Override
    public void renderCurrentState() {
        // ... existing code ...
        case "4" -> {
            filePath = "path/to/sample_game_YAML.yaml";
            parser = new YamlParser();
        }
    }
}
```

3. **Add Tests:**
- Create sample YAML file
- Add test case in `AppTest.java`

### Adding a New Game State

To add a new game phase:

1. **Create State Class:**
```java
package com.intellectual_systems.controller.state;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.controller.GameState;

public class CustomState implements GameState {
    private final GameEngine gameEngine;
    
    public CustomState(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    
    @Override
    public void renderCurrentState() {
        // Implement current state logic
    }
    
    @Override
    public void renderNextState() {
        // Transition to next state
        gameEngine.setState(new NextState(gameEngine));
        gameEngine.renderCurrentState();
    }
}
```

2. **Add State Transition:**
- Modify an existing state's `renderNextState()` to transition to your new state
- Update state diagram documentation

### Adding a New Event Observer

To listen to game events (e.g., for analytics):

1. **Implement Observer:**
```java
package com.intellectual_systems.logging;

public class AnalyticsLogger implements GameEventListener {
    @Override
    public void updateOnGameEvent(GameEvent event) {
        // Process event for analytics
        System.out.println("Event recorded: " + event.getActivity());
    }
}
```

2. **Register Observer:**
```java
public class GameEngine {
    public GameEngine(GameState startState) {
        this.gameEvent = new GameEvent();
        this.eventLogger = new EventLogger();
        this.gameEvent.addListener(this.eventLogger);
        
        // Add new observer
        AnalyticsLogger analyticsLogger = new AnalyticsLogger();
        this.gameEvent.addListener(analyticsLogger);
    }
}
```

---

## Running Tests

### Execute All Tests
```bash
mvn test
```

### Execute Specific Test
```bash
mvn test -Dtest=AppTest#testPlayerCreation
```

### Generate Test Coverage Report
```bash
mvn clean test jacoco:report
```

Report location: `target/site/jacoco/index.html`

---

## Troubleshooting

### Issue: "Java not found"
**Solution:**
- Ensure JDK 25+ is installed
- Add Java to system PATH
- Verify: `java -version`

### Issue: "Maven not found"
**Solution:**
- Install Maven 3.6+
- Add Maven to system PATH
- Verify: `mvn -version`

### Issue: Build fails with "invalid target release"
**Solution:**
- Update `pom.xml` Java version property:
```xml
<maven.compiler.source>25</maven.compiler.source>
<maven.compiler.target>25</maven.compiler.target>
```
- Run: `mvn clean install`

### Issue: Application won't read input
**Solution:**
- Ensure running from terminal (not IDE debug mode)
- Or modify `Main.java` to remove try-with-resources on Scanner

### Issue: CSV file shows duplicates
**Solution:**
- Delete existing `game_events_log.csv`
- Run game again
- Check that `LoggedEvent` snapshots are being used in `EventLogger`

### Issue: Tests fail with NullPointerException
**Solution:**
- Ensure test data is properly initialized
- Check that Category objects have Questions added
- Verify Player objects are created before use

---

## Building for Distribution

### Create Executable JAR
```bash
mvn clean package
```

Output: `target/int_sys_oop2_project-1.jar`

### Run JAR File
```bash
java -jar target/int_sys_oop2_project-1.jar
```

### Create Source Distribution
```bash
mvn clean assembly:assembly
```

---

## Performance Optimization

### Heap Memory Tuning
For large datasets, increase heap memory:
```bash
java -Xmx512m -jar target/int_sys_oop2_project-1.jar
```

### Build Performance
Speed up builds by skipping tests:
```bash
mvn clean install -DskipTests
```

---

## Development Tips

1. **Use IDE Features:**
   - Code completion
   - Refactoring tools
   - Built-in debugger

2. **Enable Logging:**
   - Modify `EventLogger` to print more details
   - Add debug statements in critical sections

3. **Version Control:**
   - Commit after each feature
   - Keep commits atomic and well-described

4. **Code Quality:**
   - Follow naming conventions (camelCase for methods/variables, PascalCase for classes)
   - Keep methods small and focused
   - Add Javadoc comments for public APIs

---

## Additional Resources

- **Java Documentation:** https://docs.oracle.com/en/java/
- **Maven Documentation:** https://maven.apache.org/
- **JUnit 4 Testing:** https://junit.org/junit4/
- **Design Patterns:** See [Design](./Design) page

---

Last Updated: November 2025
