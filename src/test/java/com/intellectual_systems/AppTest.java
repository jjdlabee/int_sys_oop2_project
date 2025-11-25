package com.intellectual_systems;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import java.io.File;
import java.util.List;

import com.intellectual_systems.controller.GameEngine;
import com.intellectual_systems.model.Player;
import com.intellectual_systems.model.Question;
import com.intellectual_systems.parser.CsvParser;
import com.intellectual_systems.parser.JsonParser;
import com.intellectual_systems.parser.XmlParser;

/**
 * Test suite for Multi-Player Jeopardy Game application.
 */
public class AppTest 
{
    private GameEngine gameEngine;
    private CsvParser csvParser;
    private JsonParser jsonParser;
    private XmlParser xmlParser;

    @Before
    public void setUp() {
        gameEngine = new GameEngine();
        csvParser = new CsvParser();
        jsonParser = new JsonParser();
        xmlParser = new XmlParser();
    }

    // File Parsing Tests
    @Test
    public void testLoadCSVFileSuccessfully() {
        File testFile = new File("src/test/java/com/intellectual_systems/resources/questions.csv");
        List<Question> questions = csvParser.parse(testFile);
        assertNotNull("Questions should not be null", questions);
        assertTrue("Should load questions from CSV", questions.size() > 0);
    }

    @Test
    public void testLoadJSONFileSuccessfully() {
        File testFile = new File("src/test/java/com/intellectual_systems/resources/questions.json");
        List<Question> questions = jsonParser.parse(testFile);
        assertNotNull("Questions should not be null", questions);
        assertTrue("Should load questions from JSON", questions.size() > 0);
    }

    @Test
    public void testLoadXMLFileSuccessfully() {
        File testFile = new File("src/test/java/com/intellectual_systems/resources/questions.xml");
        List<Question> questions = xmlParser.parse(testFile);
        assertNotNull("Questions should not be null", questions);
        assertTrue("Should load questions from XML", questions.size() > 0);
    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void testInvalidFileThrowsException() throws Exception {
        File invalidFile = new File("src/test/java/com/intellectual_systems/resources/nonexistent.csv");
        csvParser.parse(invalidFile);
    }

    // Player Management Tests
    @Test
    public void testAddPlayerSuccessfully() {
        Player player = new Player("Alice");
        gameEngine.addPlayer(player);
        assertTrue("Player should be added", gameEngine.getPlayers().contains(player));
    }

    @Test
    public void testPlayerCountLimitEnforced() {
        for (int i = 0; i < 5; i++) {
            Player player = new Player("Player" + i);
            gameEngine.addPlayer(player);
        }
        assertTrue("Maximum 4 players allowed", gameEngine.getPlayers().size() <= 4);
    }

    @Test
    public void testMinimumOnePlayerRequired() {
        assertTrue("Game should support at least 1 player", gameEngine.getMinPlayers() == 1);
    }

    // Scoring Tests
    @Test
    public void testCorrectAnswerIncreasesScore() {
        Player player = new Player("Bob");
        int initialScore = player.getScore();
        player.updateScore(100, true);
        assertTrue("Score should increase on correct answer", player.getScore() > initialScore);
    }

    @Test
    public void testIncorrectAnswerDecreasesScore() {
        Player player = new Player("Charlie");
        player.setScore(100);
        int initialScore = player.getScore();
        player.updateScore(100, false);
        assertTrue("Score should decrease on incorrect answer", player.getScore() < initialScore);
    }

    @Test
    public void testScoreCannotBelowZero() {
        Player player = new Player("Diana");
        player.setScore(50);
        player.updateScore(100, false);
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
        assertTrue("Answered question should not be selectable", question.isAnswered());
    }

    @Test
    public void testAnswerValidation() {
        Question question = new Question("Math", 50, "2+2=?", "4");
        assertTrue("Correct answer should be validated", 
            question.isCorrectAnswer("4"));
        assertFalse("Incorrect answer should not be validated", 
            question.isCorrectAnswer("5"));
    }

    // Game Flow Tests
    @Test
    public void testGameInitialization() {
        gameEngine.initializeGame();
        assertTrue("Game should be initialized", gameEngine.isGameActive());
    }

    @Test
    public void testGameTermination() {
        gameEngine.initializeGame();
        gameEngine.endGame();
        assertFalse("Game should be terminated", gameEngine.isGameActive());
    }

    @Test
    public void testTurnRotationBetweenPlayers() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        gameEngine.addPlayer(player1);
        gameEngine.addPlayer(player2);
        gameEngine.initializeGame();
        Player currentPlayer = gameEngine.getCurrentPlayer();
        gameEngine.nextTurn();
        assertNotEquals("Turn should rotate to next player", currentPlayer, gameEngine.getCurrentPlayer());
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(true);
    }
}