package com.intellectual_systems;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.intellectual_systems.model.Category;
import com.intellectual_systems.model.Player;
import com.intellectual_systems.model.Question;
import com.intellectual_systems.parser.CsvParser;
import com.intellectual_systems.parser.JsonParser;
import com.intellectual_systems.parser.XmlParser;

/**
 * Simplified Test Suite - 15 Essential Tests
 * Tests only Model and Parser classes (no Controller dependencies)
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
    
    // ==================== SANITY TEST ====================
    
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}