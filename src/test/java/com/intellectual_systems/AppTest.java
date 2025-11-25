package com.intellectual_systems;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import java.io.File;
import java.util.List;

/**
 * Comprehensive test suite for Multi-Player Jeopardy Game application.
 */
public class AppTest 
{
    private GameManager gameManager;
    private FileParser fileParser;
    private ScoringEngine scoringEngine;
    private EventLogger eventLogger;

    @Before
    public void setUp() {
        gameManager = new GameManager();
        fileParser = new FileParser();
        scoringEngine = new ScoringEngine();
        eventLogger = new EventLogger();
    }

    // File Parsing Tests
    @Test
    public void testLoadCSVFileSuccessfully() {
        File testFile = new File("src/test/resources/questions.csv");
        List<Question> questions = fileParser.parseCSV(testFile);
        assertNotNull("Questions should not be null", questions);
        assertTrue("Should load questions from CSV", questions.size() > 0);
    }

    @Test
    public void testLoadJSONFileSuccessfully() {
        File testFile = new File("src/test/resources/questions.json");
        List<Question> questions = fileParser.parseJSON(testFile);
        assertNotNull("Questions should not be null", questions);
        assertTrue("Should load questions from JSON", questions.size() > 0);
    }

    @Test
    public void testLoadXMLFileSuccessfully() {
        File testFile = new File("src/test/resources/questions.xml");
        List<Question> questions = fileParser.parseXML(testFile);
        assertNotNull("Questions should not be null", questions);
        assertTrue("Should load questions from XML", questions.size() > 0);
    }

    @Test
    public void testInvalidFileThrowsException() {
        File invalidFile = new File("nonexistent.csv");
        assertThrows(FileNotFoundException.class, () -> fileParser.parseCSV(invalidFile));
    }

    // Player Management Tests
    @Test
    public void testAddPlayerSuccessfully() {
        Player player = new Player("Alice");
        gameManager.addPlayer(player);
        assertTrue("Player should be added", gameManager.getPlayers().contains(player));
    }

    @Test
    public void testPlayerCountLimitEnforced() {
        for (int i = 0; i < 5; i++) {
            Player player = new Player("Player" + i);
            gameManager.addPlayer(player);
        }
        assertTrue("Maximum 4 players allowed", gameManager.getPlayers().size() <= 4);
    }

    @Test
    public void testMinimumOnePlayerRequired() {
        assertTrue("Game should support at least 1 player", gameManager.getMinPlayers() == 1);
    }

    // Scoring Tests
    @Test
    public void testCorrectAnswerIncreasesScore() {
        Player player = new Player("Bob");
        int initialScore = player.getScore();
        scoringEngine.updateScore(player, 100, true);
        assertTrue("Score should increase on correct answer", player.getScore() > initialScore);
    }

    @Test
    public void testIncorrectAnswerDecreasesScore() {
        Player player = new Player("Charlie");
        player.setScore(100);
        int initialScore = player.getScore();
        scoringEngine.updateScore(player, 100, false);
        assertTrue("Score should decrease on incorrect answer", player.getScore() < initialScore);
    }

    @Test
    public void testScoreCannotBelowZero() {
        Player player = new Player("Diana");
        player.setScore(50);
        scoringEngine.updateScore(player, 100, false);
        assertTrue("Score should not go below zero", player.getScore() >= 0);
    }

    // Question Management Tests
    @Test
    public void testQuestionMarkedAsAnswered() {
        Question question = new Question("Science", 100, "What is H2O?", "Water");
        assertFalse("Question should not be answered initially", question.isAnswered());
        question.markAsAnswered();
        assertTrue("Question should be marked as answered", question.isAnswered());
    }

    @Test
    public void testPreventQuestionReuse() {
        Question question = new Question("History", 200, "Who was Napoleon?", "Napoleon Bonaparte");
        question.markAsAnswered();
        assertFalse("Answered question should not be selectable", !question.isAnswered());
    }

    @Test
    public void testAnswerValidation() {
        Question question = new Question("Math", 50, "2+2=?", "4");
        assertTrue("Correct answer should be validated", 
            scoringEngine.validateAnswer(question, "4"));
        assertFalse("Incorrect answer should not be validated", 
            scoringEngine.validateAnswer(question, "5"));
    }

    // Event Logging Tests
    @Test
    public void testEventLogCreated() {
        File logFile = eventLogger.createEventLog();
        assertNotNull("Event log file should be created", logFile);
        assertTrue("Event log file should exist", logFile.exists());
    }

    @Test
    public void testEventLogged() {
        GameEvent event = new GameEvent("Player1", "Answer Question", "Science", 100, "Correct", 200);
        eventLogger.logEvent(event);
        List<GameEvent> events = eventLogger.getEvents();
        assertTrue("Event should be logged", events.contains(event));
    }

    @Test
    public void testEventTimestampISO() {
        GameEvent event = new GameEvent("Player2", "Score Updated", "History", 50, "Correct", 150);
        String timestamp = event.getTimestamp();
        assertTrue("Timestamp should be ISO format", timestamp.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}"));
    }

    // Report Generation Tests
    @Test
    public void testSummaryReportGenerated() {
        gameManager.addPlayer(new Player("TestPlayer"));
        File report = gameManager.generateReport("TXT");
        assertNotNull("Report should be generated", report);
        assertTrue("Report file should exist", report.exists());
    }

    @Test
    public void testReportContainsPlayerScores() {
        Player player = new Player("Eve");
        player.setScore(500);
        gameManager.addPlayer(player);
        String reportContent = gameManager.generateReport("TXT").toString();
        assertTrue("Report should contain player name", reportContent.contains("Eve"));
    }

    @Test
    public void testMultipleFormatSupport() {
        assertTrue("Should support TXT format", gameManager.isSupportedFormat("TXT"));
        assertTrue("Should support PDF format", gameManager.isSupportedFormat("PDF"));
        assertTrue("Should support DOCX format", gameManager.isSupportedFormat("DOCX"));
    }

    // Game Flow Tests
    @Test
    public void testGameInitialization() {
        gameManager.initializeGame();
        assertTrue("Game should be initialized", gameManager.isGameActive());
    }

    @Test
    public void testGameTermination() {
        gameManager.initializeGame();
        gameManager.endGame();
        assertFalse("Game should be terminated", gameManager.isGameActive());
    }

    @Test
    public void testTurnRotationBetweenPlayers() {
        gameManager.addPlayer(new Player("Player1"));
        gameManager.addPlayer(new Player("Player2"));
        gameManager.initializeGame();
        Player currentPlayer = gameManager.getCurrentPlayer();
        gameManager.nextTurn();
        assertNotEquals("Turn should rotate to next player", currentPlayer, gameManager.getCurrentPlayer());
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(true);
    }
}