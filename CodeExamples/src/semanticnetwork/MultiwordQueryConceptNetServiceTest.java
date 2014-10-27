package semanticnetwork;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import com.google.common.collect.Lists;

//public class MultiwordQueryConceptNetServiceTest extends ConceptNetServiceTestBase {
	public class MultiwordQueryConceptNetServiceTest  {

//    @Autowired
//    MultiwordQueryConceptNetService multiwordQueryConceptNetService;
//    
//    private ParseReader getParseReaderOfInput(String input)     {
//        SRUAnalysis tree = dialogUtility.stringToSruAnalysis(input);
//        return new ParseReader(tree, input);
//    }
//
//    @Test
//    public void testSelectEdgesForSpainSevilla() {
//        // Test just the first string returned by selectQueryStrings().
//        String selectedWord = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("My parents traveled to Spain last summer.")).get(0);
//        assertEquals("spain", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        // Require "Sevilla" in all replies. In order to focus the test on selectEdges(), we set the additional query words ourselves.
//        List<String> addlQueryWords = Lists.newArrayList("sevilla");
//        multiwordQueryConceptNetService.setAdditionalQueryWords(addlQueryWords);
//        
//        // Select edges, but without filtering out any relations.
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        
//        List<String> replies = Lists.newArrayList();
//        String expected = "sevilla is part of spain";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//      
//
//        // Verify that no unwanted responses are included.
//        replies = Lists.newArrayList();
//        expected = "bilbao is part of spain";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//
//        replies = Lists.newArrayList();
//        expected = "spain is part of europe";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//
//    }
//
//    @Test
//    public void testSelectEdgesForBusTransport() {
//        // Test all strings returned by selectQueryStrings().
//        List<String> expectedWords = Lists.newArrayList("bus", "transport", "transported");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("They transported the bus overseas."));
//        assertEquals(expectedWords, selectedWords);
//        
//        // Create a query based on "bus," and set the additional query words to what remains in the list.
//        ConceptNetQuery query = new ConceptNetQuery(selectedWords.remove(0));
//        multiwordQueryConceptNetService.setAdditionalQueryWords(selectedWords);
//        
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        
//        List<String> replies = Lists.newArrayList();
//        String expected = "bus can transport people";   // Known to be one of the replies which ConceptNet returns.
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//      
//
//        // Verify that no unwanted responses are included.
//        replies = Lists.newArrayList();
//        expected = "you are likely to find a bus in a bus stop";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//
//        replies = Lists.newArrayList();
//        expected = "a bus is for traveling";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//
//    }
//
//    /**
//     * Lookups on pronouns should always fail. The base class has more thorough testing of this; here we are making sure the
//     * filtering extends to subclasses.
//     */
//    @Test
//    public void testSelectEdgesForPronouns() {
//        List<String> addlWords = Lists.newArrayList("play");
//        
//        ConceptNetQuery query = new ConceptNetQuery("you");
//        multiwordQueryConceptNetService.setAdditionalQueryWords(addlWords);
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//    }
//    
//    @Test
//    public void testSelectEdgesForGoodBe() {
//        // Test all strings returned by selectQueryStrings().
//        List<String> expectedWords = Lists.newArrayList("good");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("Eggs are good for you"));
//        assertEquals(expectedWords, selectedWords);
//        
//        // Create a query based on the first word, and set the additional query words to what remains in the list.
//        ConceptNetQuery query = new ConceptNetQuery(selectedWords.remove(0));
//        multiwordQueryConceptNetService.setAdditionalQueryWords(selectedWords);
//        
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        
//        // Verify that no unwanted responses are included.
//        List<String> replies = Lists.newArrayList();
//        String expected = "good most suitable or right for particular purpose";   // Known to be one of the replies which ConceptNet returns.
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//
//        replies = Lists.newArrayList();
//        expected = "are";   // Known to be one of the replies which ConceptNet returns.
//        found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//
//        replies = Lists.newArrayList();
//        expected = "is";   // Known to be one of the replies which ConceptNet returns.
//        found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//}
//
//    @Test
//    public void testSelectEdgesForBrotherControl() {
//        // Test all strings returned by selectQueryStrings().
//        List<String> expectedWords = Lists.newArrayList("brother", "control", "controls");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("The girl controls her brother."));
//        assertEquals(expectedWords, selectedWords);
//        
//        // Create a query based on the first word, and set the additional query words to what remains in the list.
//        ConceptNetQuery query = new ConceptNetQuery(selectedWords.remove(0));
//        multiwordQueryConceptNetService.setAdditionalQueryWords(selectedWords);
//        
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        
//        // Verify that no unwanted responses are included.
//        List<String> replies = Lists.newArrayList();
//        String expected = "belief in supernatural power";   // Known to be one of the replies which ConceptNet returns.
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//
//    @Test
//    public void testSelectEdgesForFlorida() {
//        List<String> expectedWords = Lists.newArrayList("florida", "vacation", "vacation");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("My parents vacation in Florida."));
//        assertEquals(expectedWords, selectedWords);
//        
//        // Create a query based on the first word, and set the additional query words to what remains in the list.
//        ConceptNetQuery query = new ConceptNetQuery(selectedWords.remove(0));
//        multiwordQueryConceptNetService.setAdditionalQueryWords(selectedWords);
//        
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "florida is a favorite vacation destination";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        // Verify that no unwanted responses are included.
//        replies = Lists.newArrayList();
//        expected = "florida is part of the united states";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//
//    /**
//     * Verify that we eliminate "helps answer the question" replies.
//     * We do this with MultiwordQuery because these are frequently IsA relationships, and those relationships are disallowed in the Base.
//     */
//    @Test
//    public void testSelectEdgesForGhostAndHelpsAnswerTheQuestion() {
//        List<String> expectedWords = Lists.newArrayList("ghost", "help", "helps");
//        // Use a verb that is found in the reply you want to test against, so that first it gets picked up, then later
//        // filtered out.
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("He helps a ghost."));
//        assertEquals(expectedWords, selectedWords);
//        
//        // Create a query based on the first word, and set the additional query words to what remains in the list.
//        ConceptNetQuery query = new ConceptNetQuery(selectedWords.remove(0));
//        multiwordQueryConceptNetService.setAdditionalQueryWords(selectedWords);
//                
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        
//        // Verify that no unwanted responses are included.
//        List<String> replies = Lists.newArrayList();
//        String expected = "helps answer the question";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//    
//    /**
//     * The verb of the input sentence must be a whole-word match in the reply.
//     */
//    @Test
//    public void testRequireVerbBeWholeWordMatch() {
//        List<String> expectedWords = Lists.newArrayList("bus", "sit", "sat");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("Winifred sat in the bus."));
//        assertEquals(expectedWords, selectedWords);
//        
//        // Create a query based on the first word, and set the additional query words to what remains in the list.
//        ConceptNetQuery query = new ConceptNetQuery(selectedWords.remove(0));
//        multiwordQueryConceptNetService.setAdditionalQueryWords(selectedWords);
//        
//        List<Edge> actualEdges = multiwordQueryConceptNetService.selectEdges(query, multiwordQueryConceptNetService.getUnwantedRelations());
//        
//        // Verify that no unwanted responses are included.
//        // ("transit" contains the word "sit")
//        List<String> replies = Lists.newArrayList();
//        String expected = "form of mass transit";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, multiwordQueryConceptNetService);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//
//    /**
//     * Verify that if we don't find more than one query string, MultiwordQueryConceptService returns no query strings at all.
//     */
//    @Test
//    public void testSelectQueryStringsForLikeYou() {
//        // Test all strings returned by selectQueryStrings().
//        List<String> expectedWords = Lists.newArrayList();
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("I like you."));
//        assertEquals(expectedWords, selectedWords);
//    }
//
//    @Test
//    public void testSelectQueryStringsForLikeYouAndCheese() {
//        // Test all strings returned by selectQueryStrings().
//        // TODO: Ideally selectQueryStrings() would return "cheese", "like" but in the absence of a complete parse we get something else.
//        //List<String> expectedWords = Lists.newArrayList("cheese", "like");
//        List<String> expectedWords = Lists.newArrayList();
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("I like both you and cheese."));
//        assertEquals(expectedWords, selectedWords);
//    }
//
//    @Test
//    public void testSelectQueryStringsForLikeYouWithCheese() {
//        // Test all strings returned by selectQueryStrings().
//        List<String> expectedWords = Lists.newArrayList("cheese", "like", "like");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("I like you with cheese."));
//        assertEquals(expectedWords, selectedWords);
//    }
//
//    /**
//     * Do not do an object-verb query if the verb is "be".
//     */
//    @Test
//    public void testSelectQueryStringsForIsHot() {
//        List<String> expectedWords = Lists.newArrayList("hot");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("It's hot."));
//        assertEquals(expectedWords, selectedWords);
//    }
//    @Test
//    public void testSelectQueryStringsForAmHot() {
//        List<String> expectedWords = Lists.newArrayList("hot");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("I'm hot."));
//        assertEquals(expectedWords, selectedWords);
//    }
//    @Test
//    public void testSelectQueryStringsForBeAreHot() {
//        List<String> expectedWords = Lists.newArrayList("hot");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("They're hot."));
//        assertEquals(expectedWords, selectedWords);
//    }
//    @Test
//    public void testSelectQueryStringsForKnowTheyAreHot() {
//        List<String> expectedWords = Lists.newArrayList("hot");
//        List<String> selectedWords = multiwordQueryConceptNetService.selectQueryStrings(getParseReaderOfInput("I know they are hot."));
//        assertEquals(expectedWords, selectedWords);
//    }
//
//    /**
//     * Questions are not acceptable for the base ConceptNet service.
//     */    @Test
//    public void testSelectEdgesForQuestions() {
//        String sentence = "Want to grab some lunch?";
//        ConceptNetReturnVal returnVal = multiwordQueryConceptNetService.getReply(getParseReaderOfInput(sentence));
//        assertNull(returnVal);
//    }

}
