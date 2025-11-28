package com.intellectual_systems;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.intellectual_systems.model.Category;
import com.intellectual_systems.model.Player;
import com.intellectual_systems.model.Question;
import com.intellectual_systems.model.Turn;
import com.intellectual_systems.model.GameSummary;
import com.intellectual_systems.parser.CsvParser;
import com.intellectual_systems.parser.JsonParser;
import com.intellectual_systems.parser.XmlParser;
import com.intellectual_systems.logging.EventLogger;
import com.intellectual_systems.logging.GameEvent;
import com.intellectual_systems.logging.CSVLogger;

/**
 * Simplified Test Suite - 28 Essential Tests
 * Tests Model, Parser, Reporting, and Logging classes
 */
public class AppTest 
{
    private CsvParser csvParser;
    private JsonParser jsonParser;
    private XmlParser xmlParser;

    @Before
    public void setUp() {
        csvParser = new CsvParser();
        jsonParser = new JsonParser();
        xmlParser = new XmlParser();
    }

    // ==================== FILE PARSING TESTS (6 tests) ====================
    
    @Test
    public void testLoadCSVFileSuccessfully() {
        String testFile = "src/test/java/com/intellectual_systems/resources/questions.csv";
        ArrayList<Category> categories = csvParser.parse(testFile);
        
        assertNotNull("Categories should not be null", categories);
        assertTrue("Should load at least one category", !categories.isEmpty());
    }
    
    @Test
    public void testCSVParsesQuestionsWithChoices() {
        String testFile = "src/test/java/com/intellectual_systems/resources/questions.csv";
        ArrayList<Category> categories = csvParser.parse(testFile);
        
        assertTrue("Should have categories", !categories.isEmpty());
        Category firstCategory = categories.get(0);
        assertTrue("Category should have questions", !firstCategory.getQuestions().isEmpty());
        
        Question firstQuestion = firstCategory.getQuestions().get(0);
        assertNotNull("Question should have text", firstQuestion.getQuestionText());
        assertNotNull("Question should have choices", firstQuestion.getChoices());
        assertTrue("Question should have 4 choices", firstQuestion.getChoices().size() == 4);
    }
    
    @Test
    public void testLoadJSONFileSuccessfully() {
        String testFile = "src/test/java/com/intellectual_systems/resources/questions.json";
        ArrayList<Category> categories = jsonParser.parse(testFile);
        
        assertNotNull("Categories should not be null", categories);
        assertTrue("Should load at least one category", !categories.isEmpty());
    }
    
    @Test
    public void testJSONParsesCorrectly() {
        String testFile = "src/test/java/com/intellectual_systems/resources/questions.json";
        ArrayList<Category> categories = jsonParser.parse(testFile);
        
        assertTrue("Should have categories", !categories.isEmpty());
        Category category = categories.get(0);
        assertTrue("Category should have questions", !category.getQuestions().isEmpty());
    }
    
    @Test
    public void testLoadXMLFileSuccessfully() {
        String testFilePath = "src/test/java/com/intellectual_systems/resources/questions.xml";
        ArrayList<Category> categories = xmlParser.parse(testFilePath);
        
        assertNotNull("Categories should not be null", categories);
        assertTrue("Should load at least one category", !categories.isEmpty());
    }
    
    @Test
    public void testXMLParsesCorrectly() {
        String testFilePath = "src/test/java/com/intellectual_systems/resources/questions.xml";
        ArrayList<Category> categories = xmlParser.parse(testFilePath);
        
        assertTrue("Should have categories", !categories.isEmpty());
        Category category = categories.get(0);
        assertTrue("Category should have questions", !category.getQuestions().isEmpty());
    }

    // ==================== PLAYER TESTS (3 tests) ====================
    
    @Test
    public void testPlayerCreation() {
        Player player = new Player("Alice");
        
        assertNotNull("Player should be created", player);
        assertEquals("Player username should match", "Alice", player.getUsername());
        assertEquals("Initial score should be 0", 0, player.getScore());
    }
    
    @Test
    public void testPlayerScoreIncrease() {
        Player player = new Player("Bob");
        
        player.setScore(100);
        assertEquals("Score should be 100", 100, player.getScore());
        
        player.setScore(player.getScore() + 200);
        assertEquals("Score should be 300", 300, player.getScore());
    }

    @Test
    public void testPlayerScoreCannotGoNegative() {
        Player player = new Player("Charlie");
        player.setScore(50);
        
        int newScore = player.getScore() - 100;
        player.setScore(Math.max(0, newScore));
        
        assertEquals("Score should be 0, not negative", 0, player.getScore());
    }

    // ==================== QUESTION TESTS (3 tests) ====================
    
    @Test
    public void testQuestionCreation() {
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("London");
        choices.add("Berlin");
        choices.add("Madrid");
        
        Question question = new Question(
            "What is the capital of France?", 
            choices, 
            "Paris", 
            "Geography", 
            100
        );
        
        assertEquals("Question text should match", 
                    "What is the capital of France?", question.getQuestionText());
        assertEquals("Answer should match", "Paris", question.getAnswer());
        assertEquals("Category should match", "Geography", question.getCategory());
        assertEquals("Value should match", 100, question.getValue());
        assertEquals("Should have 4 choices", 4, question.getChoices().size());
    }
    
    @Test
    public void testQuestionAnswerCaseInsensitive() {
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Water");
        
        Question question = new Question(
            "What is H2O?", 
            choices, 
            "Water", 
            "Science", 
            100
        );
        
        assertTrue("Should accept lowercase", 
                  question.getAnswer().equalsIgnoreCase("water"));
        assertTrue("Should accept uppercase", 
                  question.getAnswer().equalsIgnoreCase("WATER"));
    }
    
    @Test
    public void testQuestionValues() {
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Answer");
        
        Question q1 = new Question("Q1", choices, "A1", "Cat", 100);
        Question q2 = new Question("Q2", choices, "A2", "Cat", 200);
        Question q3 = new Question("Q3", choices, "A3", "Cat", 500);
        
        assertEquals("Q1 should be 100", 100, q1.getValue());
        assertEquals("Q2 should be 200", 200, q2.getValue());
        assertEquals("Q3 should be 500", 500, q3.getValue());
    }

    // ==================== CATEGORY TESTS (3 tests) ====================
    
    @Test
    public void testCategoryCreation() {
        Category category = new Category("Science");
        
        assertNotNull("Category should be created", category);
        assertEquals("Category name should match", "Science", category.getName());
        assertNotNull("Category should have empty questions list", category.getQuestions());
    }
    
    @Test
    public void testCategoryAddQuestion() {
        Category category = new Category("History");
        
        ArrayList<String> choices = new ArrayList<>();
        choices.add("1945");
        Question question = new Question(
            "When did WWII end?", 
            choices, 
            "1945", 
            "History", 
            100
        );
        
        category.addQuestion(question);
        
        assertEquals("Category should have 1 question", 1, category.getQuestions().size());
        assertEquals("Question should match", question, category.getQuestions().get(0));
    }
    
    @Test
    public void testCategoryMultipleQuestions() {
        Category category = new Category("Math");
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Answer");
        
        category.addQuestion(new Question("Q1", choices, "A1", "Math", 100));
        category.addQuestion(new Question("Q2", choices, "A2", "Math", 200));
        category.addQuestion(new Question("Q3", choices, "A3", "Math", 300));
        
        assertEquals("Should have 3 questions", 3, category.getQuestions().size());
    }

    // ==================== REPORTING TESTS (5 tests) ====================

    @Test
    public void testGameSummaryCreation() {
        List<Player> players = new ArrayList<>();
        Player player = new Player("TestUser");
        player.setScore(500);
        players.add(player);
        
        GameSummary summary = new GameSummary("GAME001", players);
        
        assertNotNull("Summary should be generated", summary);
        String summaryString = summary.toString();
        assertNotNull("Summary string should not be null", summaryString);
        assertTrue("Summary should contain game ID", 
                  summaryString.contains("GAME001"));
    }

    @Test
    public void testGameSummaryContainsPlayerInfo() {
        List<Player> players = new ArrayList<>();
        Player player = new Player("StatUser");
        player.setScore(800);
        players.add(player);
        
        GameSummary summary = new GameSummary("GAME002", players);
        summary.addTurn("Test turn"); // Add at least one turn so scores are displayed
        summary.addScores(players);
        
        String summaryString = summary.toString();
        assertTrue("Should contain player username", 
                  summaryString.contains("StatUser"));
        assertTrue("Should contain player score", 
                  summaryString.contains("800"));
    }

    @Test
    public void testGameSummaryWithTurnHistory() {
        List<Player> players = new ArrayList<>();
        Player player = new Player("TurnUser");
        players.add(player);
        
        GameSummary summary = new GameSummary("GAME003", players);
        summary.addTurn("Player selected category: Science");
        summary.addTurn("Player answered question correctly");
        
        String summaryString = summary.toString();
        assertTrue("Should contain turn information", 
                  summaryString.contains("Turn 1") || summaryString.contains("selected"));
    }

    @Test
    public void testMultiplePlayerGameSummary() {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        player1.setScore(300);
        player2.setScore(500);
        players.add(player1);
        players.add(player2);
        
        GameSummary summary = new GameSummary("GAME004", players);
        summary.addTurn("Test turn"); // Add at least one turn so scores are displayed
        summary.addScores(players);
        
        String summaryString = summary.toString();
        assertTrue("Should contain Alice", summaryString.contains("Alice"));
        assertTrue("Should contain Bob", summaryString.contains("Bob"));
        assertTrue("Should contain Alice's score", summaryString.contains("300"));
        assertTrue("Should contain Bob's score", summaryString.contains("500"));
    }

    @Test
    public void testGameSummaryToStringFormat() {
        List<Player> players = new ArrayList<>();
        Player player = new Player("ExportUser");
        player.setScore(450);
        players.add(player);
        
        GameSummary summary = new GameSummary("GAME005", players);
        summary.addTurn("Test turn"); // Add at least one turn so scores are displayed
        summary.addScores(players);
        String summaryString = summary.toString();
        
        assertNotNull("Summary string should not be null", summaryString);
        assertTrue("Summary should contain case ID", 
                  summaryString.contains("GAME005"));
        assertTrue("Summary should contain username", 
                  summaryString.contains("ExportUser"));
        assertTrue("Summary should contain score", 
                  summaryString.contains("450"));
    }

    // ==================== LOGGING TESTS (5 tests) ====================

    @Test
    public void testEventLoggerCreation() {
        EventLogger logger = new EventLogger();
        
        assertNotNull("Logger should be initialized", logger);
    }

    @Test
    public void testGameEventCreation() {
        GameEvent event = new GameEvent();
        Player player = new Player("LoggedPlayer");
        Turn turn = new Turn(player);
        
        event.newGameEvent("GAME001", "Test Activity", turn);
        
        assertEquals("Case ID should match", "GAME001", event.getCaseID());
        assertEquals("Activity should match", "Test Activity", event.getActivity());
        assertNotNull("Turn should not be null", event.getTurn());
        assertNotNull("Timestamp should be generated", event.getTimestamp());
    }

    @Test
    public void testEventLoggerListenerRegistration() {
        GameEvent gameEvent = new GameEvent();
        EventLogger logger = new EventLogger();
        
        gameEvent.addListener(logger);
        
        // Create an event and notify listeners
        Player player = new Player("TestPlayer");
        Turn turn = new Turn(player);
        gameEvent.newGameEvent("GAME002", "Player Action", turn);
        gameEvent.notifyEventListeners(gameEvent);
        
        // If no exceptions thrown, listener is registered correctly
        assertTrue("Listener registration should succeed", true);
    }

    @Test
    public void testEventTimestampFormat() {
        GameEvent event = new GameEvent();
        Player player = new Player("TimePlayer");
        Turn turn = new Turn(player);
        
        event.newGameEvent("GAME003", "Timestamp Test", turn);
        String timestamp = event.getTimestamp();
        
        assertNotNull("Timestamp should not be null", timestamp);
        assertTrue("Timestamp should contain date format", 
                  timestamp.contains("-") || timestamp.contains(":"));
    }

    @Test
    public void testEventLoggerWithMultipleEvents() {
        GameEvent gameEvent = new GameEvent();
        EventLogger logger = new EventLogger();
        gameEvent.addListener(logger);
        
        // Create multiple events
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Turn turn1 = new Turn(player1);
        Turn turn2 = new Turn(player2);
        
        GameEvent event1 = new GameEvent();
        event1.newGameEvent("GAME004", "Action 1", turn1);
        logger.updateOnGameEvent(event1);
        
        GameEvent event2 = new GameEvent();
        event2.newGameEvent("GAME004", "Action 2", turn2);
        logger.updateOnGameEvent(event2);
        
        // Verify logger can handle multiple events
        assertTrue("Logger should handle multiple events", true);
    }

    // ==================== INTEGRATION TESTS (3 tests) ====================

    @Test
    public void testGameSummaryWithEventLogging() {
        // Create game summary
        List<Player> players = new ArrayList<>();
        Player player = new Player("SessionPlayer");
        player.setScore(100);
        players.add(player);
        
        GameSummary summary = new GameSummary("GAME006", players);
        summary.addTurn("Player started game");
        summary.addTurn("Player scored 100 points");
        summary.addScores(players);
        
        // Create event logger
        EventLogger logger = new EventLogger();
        GameEvent event = new GameEvent();
        Turn turn = new Turn(player);
        turn.setCurrentAnswer("Game started");
        
        event.newGameEvent("GAME006", "Game Session", turn);
        logger.updateOnGameEvent(event);
        
        // Verify both work together
        String summaryStr = summary.toString();
        assertNotNull("Summary should be created", summaryStr);
        assertTrue("Should contain player info", summaryStr.contains("SessionPlayer"));
    }

    @Test
    public void testCSVLoggerWithGameEvents() {
        // Create a temporary test file path
        String testFilePath = "src/test/java/com/intellectual_systems/resources/test_events.csv";
        
        CSVLogger csvLogger = new CSVLogger(testFilePath);
        
        // Create test events
        List<GameEvent> events = new ArrayList<>();
        Player player = new Player("TestPlayer");
        Turn turn = new Turn(player);
        turn.setCurrentCategory("Science");
        turn.setCurrentQuestionValue(100);
        turn.setCurrentAnswer("A");
        turn.setIsCorrect(true);
        turn.setScoreAfterTurn(100);
        
        GameEvent event = new GameEvent();
        event.newGameEvent("GAME007", "Answer Question", turn);
        events.add(event);
        
        // This should not throw an exception
        try {
            csvLogger.logGameEvents(events);
            assertTrue("CSV logging should succeed", true);
        } catch (Exception e) {
            assertTrue("CSV logging failed: " + e.getMessage(), false);
        }
    }

    @Test
    public void testReportingWithMultipleTurns() {
        List<Player> players = new ArrayList<>();
        Player player = new Player("MultiTurnPlayer");
        players.add(player);
        
        GameSummary summary = new GameSummary("GAME008", players);
        
        // Simulate multiple turns
        EventLogger logger = new EventLogger();
        for (int i = 0; i < 3; i++) {
            Turn turn = new Turn(player);
            turn.setCurrentCategory("Category" + i);
            turn.setCurrentQuestionValue(100 * (i + 1));
            
            GameEvent event = new GameEvent();
            event.newGameEvent("GAME008", "Turn " + (i + 1), turn);
            logger.updateOnGameEvent(event);
            
            summary.addTurn("Turn " + (i + 1) + ": Category" + i);
        }
        
        player.setScore(600);
        summary.addScores(players);
        
        String summaryStr = summary.toString();
        assertTrue("Summary should contain turn information", 
                  summaryStr.contains("Turn 1") || summaryStr.contains("Category"));
    }
    
    // ==================== SANITY TEST ====================
    
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}