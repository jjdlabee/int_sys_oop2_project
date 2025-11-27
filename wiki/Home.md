# Intellectual Systems OOP2 Project Wiki

Welcome to the **Intellectual Systems Game** wiki! This documentation covers the architecture, design patterns, and development approach for this Java-based Jeopardy-style educational game.

## Quick Navigation

- **[Introduction](./Introduction)** – Team information and project scope
- **[Design](./Design)** – Design patterns, architecture, and SOLID principles application
- **[Implementation](./Implementation)** – Setup instructions and file format details
- **[Test Summary](./Test-Summary)** – Test suite, execution, and results

## Project Overview

The **Intellectual Systems Game** is a terminal-based Jeopardy-style quiz application developed as part of the UWI COMP 3607 OOP2 course. The system allows multiple players to answer category-based questions with point values, tracks player scores, and logs all game events for analysis.

### Key Features

- 🎮 **Multi-player Support** – Support for multiple concurrent players
- 🏆 **Scoring System** – Dynamic score management and tracking
- 📊 **Event Logging** – Comprehensive CSV-based event logging
- 🗂️ **Multiple Data Formats** – Parse game data from JSON, XML, and CSV
- 📋 **Game Reports** – Generate game summaries and final scores
- 🎯 **Jeopardy-Style Gameplay** – Category selection, question answering, and points

### Technology Stack

| Technology | Version |
|------------|---------|
| Java | 25 |
| Maven | 3.x |
| Apache Commons CSV | 1.14.1 |
| JSON Simple | 1.1.1 |
| Apache PDFBox | 3.0.5 |
| iText 7 | 7.2.0 |

---

## Documentation Sections

### [Introduction](./Introduction)
- Team information
- Project scope
- System objectives
- Core gameplay mechanics

### [Design](./Design)
- Design patterns used
- Pattern justification and implementation
- SOLID principles application
- Architecture overview

### [Implementation](./Implementation)
- Project setup and prerequisites
- Build and run instructions
- Project structure overview
- File format specifications (JSON, XML, CSV)
- Event logging output format
- Extension guidelines (adding parsers, states, observers)
- Testing and troubleshooting

### [Test Summary](./Test-Summary)
- Test strategy and framework
- Test suite overview (15 tests across 5 categories)
- Detailed test cases with descriptions
- Test execution and coverage reports
- Performance benchmarks
- Quality metrics and maintenance guidelines

---

## Getting Started

To get started with the project:
1. Read the **[Introduction](./Introduction)** for project overview
2. Follow the **[Implementation](./Implementation)** guide for setup and build instructions
3. Review the **[Design](./Design)** section to understand the architecture
4. Check the **[Test Summary](./Test-Summary)** for testing information and results
5. Refer to the main README.md in the repository root for quick reference

## Contributing

When contributing to this project, please ensure:
1. Code follows the SOLID principles
2. Appropriate design patterns are used
3. Event logging is implemented for tracking
4. Test coverage is maintained

---

Last Updated: November 2025
