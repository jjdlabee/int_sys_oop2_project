# Introduction

## Team Information

**Project:** Intellectual Systems OOP2 JeopradyGame  
**Course:** COMP 3607 – Object-Oriented Programming 2  
**Institution:** University of the West Indies  
**Academic Year:** 2025/26

### Team Members

| Role | Member | ID |
|------|--------|----|
| Project Lead | Vinayak Maharaj 816036176 | 
| Testing Developer | Matthew Singh | 816035076 |
| Developer | Jonathan La Borde | 816041435 |



---

## Project Scope

### Objectives

The **Intellectual Systems Game** is designed to meet the following core objectives:

1. **Educational Game Development**
   - Create an interactive Jeopardy-style quiz game
   - Support multiple players competing simultaneously
   - Track and display real-time player scores

2. **Advanced OOP Implementation**
   - Apply industry-standard design patterns
   - Demonstrate SOLID principles in practice
   - Implement comprehensive event logging and state management

3. **Data Handling**
   - Support multiple input formats (JSON, XML, CSV)
   - Implement extensible parser interfaces
   - Log all game events for audit and analysis

4. **Software Quality**
   - Achieve maintainable, modular code architecture
   - Implement thorough unit testing
   - Provide comprehensive documentation

### System Scope

**In Scope:**
- Multi-player Jeopardy-style game mechanics
- Category and question management
- Score tracking and game statistics
- CSV/JSON/XML data parsing
- Event logging and game reports
- Multiple game format support (console UI)

**Out of Scope:**
- Graphical user interface (GUI)
- Real-time networking/multiplayer over internet
- Database persistence (file-based only)
- Mobile application development

---

## Core Gameplay Mechanics

### Game Flow

```
Start Game
  ↓
Load Game Data (JSON/XML/CSV)
  ↓
Player Setup (enter player names)
  ↓
Category Selection
  ↓
Question Selection
  ↓
Answer Question
  ↓
Score Updates
  ↓
End Turn
  ↓
[Repeat steps 4-7 until all questions answered]
  ↓
Game Over (Display Final Scores & Report)
  ↓
Log Events to CSV
```

### Game Elements

1. **Categories** – Topics containing questions (e.g., "Variables & Data Types", "Control Structures")
2. **Questions** – Queries with point values (100, 200, 300, 400, 500)
3. **Answers** – Multiple choice (A, B, C, D options)
4. **Players** – Competitors tracked by username and score
5. **Turns** – Individual player actions (category selection, answer submission)

### Scoring System

- Correct answer: Player gains points equal to question value
- Incorrect answer: Player loses points equal to question value
- Players can accumulate negative scores

---

## Key Features

### 1. Multi-Player Support
- Supports 1+ concurrent players
- Round-robin turn management
- Independent player score tracking

### 2. Data Format Flexibility
- **JSON Parser** – Parse game questions from JSON format
- **XML Parser** – Parse game questions from XML format
- **CSV Parser** – Parse game questions from CSV format
- Extensible interface allows easy addition of new formats

### 3. Event Logging
- Comprehensive event tracking of all game actions
- CSV export for analysis
- Immutable event snapshots prevent data corruption
- Append-only logging prevents overwriting historic events

### 4. Game Reports
- Final score display
- Gameplay summary with turn history
- Game statistics and player rankings

---

## System Architecture at a Glance

The system is organized into modular components:

| Component | Purpose |
|-----------|---------|
| **App** | Entry point and main program flow |
| **Controller** | Game engine and state management |
| **Command** | Encapsulated user actions |
| **Model** | Game entities (Player, Question, Category) |
| **Parser** | Data format parsing (JSON, XML, CSV) |
| **Logging** | Event tracking and CSV export |

---

## Next Steps

For detailed information about the system's design and architecture, please refer to the **[Design](./Design)** page.

For setup and running instructions, see the main **README.md** in the repository root.

---

Last Updated: November 2025
