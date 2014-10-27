package semanticnetwork;

import static org.junit.Assert.*;

import java.util.List;

import semanticnetwork.ConceptNetQuery;
import semanticnetwork.ConceptNetServiceBase;
import semanticnetwork.ConceptNetQuery.Edge;
import semanticnetwork.ConceptNetQuery.Relation;
import semanticnetwork.ConceptNetQuery.Dataset;
//import net.ipsoft.eliza.dm.test.ConceptNetServiceTestBase;

import org.junit.*;
import com.google.common.collect.Lists;

//public class BaseConceptNetServiceTest extends ConceptNetServiceTestBase {
	public class BaseConceptNetServiceTest {
//    
//    @Autowired
//    ConceptNetServiceBase conceptNetServiceBase;
//    
//    private ParseReader getParseReaderOfInput(String input)     {
//        SRUAnalysis tree = dialogUtility.stringToSruAnalysis(input);
//        return new ParseReader(tree, input);
//    }
//
//    /**
//     * @category GeneralTests
//     */
//
//    private void testRelations(List<Edge> actualEdges) {
//        for(Edge edge : actualEdges)    {
//            for(Relation relation : ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS) {
//                assertNotEquals("Did not expect to find an edge with this relation. Edge = \'" + ConceptNetServiceBase.formulateReply(edge) + "\'; relation = \'" + relation.name() + "\'.", 
//                        edge.getRelation(), relation);
//            }
//        }     
//    }
//
//    private void testDatasets(List<Edge> actualEdges) {
//        for(Edge edge : actualEdges)    {
//            for(Dataset dataset : ConceptNetServiceBase.DEFAULT_UNWANTED_DATASETS) {
//                assertNotEquals("Did not expect to find an edge with this dataset. Edge = \'" + ConceptNetServiceBase.formulateReply(edge) + "\'; dataset = \'" + dataset.name() + "\'.", 
//                        edge.getDataset(), dataset);
//            }
//        }     
//    }

    
    @Test
    public void testPrettify()  {
        String actual = ConceptNetServiceBase.prettify("this is a kind person");
        assertEquals("This is a kind person.", actual);
    }

    /**
     * This test obtains a reply known not to contain a surfaceText feature, thus enabling us to test behavior when this feature does not exist.
     */
//    @Test
//    public void testFormulateReplyWhenTextEmpty()    {
//        ConceptNetQuery query = new ConceptNetQuery("brother");
//        List<Edge> actualEdges = query.getFilteredEdges(Relation.IsA, Relation.NotIsA, Relation.AtLocation, Relation.NotAtLocation, 
//                Relation.HasA, Relation.NotHasA, Relation.TranslationOf, Relation.NotTranslationOf);
//        
//        String targetReply = "";
//        for(Edge edge: actualEdges)     {
//            String reply = ConceptNetServiceBase.formulateReply(edge).toLowerCase();
//            if(reply.toLowerCase().contains("belief in supernatural power"))  {
//                targetReply = reply;
//            }
//        }
//        assertEquals("brother male person who be fellow member occurs in the context of religion strong belief in supernatural power or power that control human destiny.", targetReply);
//    }
//
//    /**
//     * Questions are acceptable for the base ConceptNet service.
//     */
//    @Test
//    public void testSelectEdgesForQuestions() {
//        String sentence = "Want to grab some lunch?";
//        ParseReader pReader = new ParseReader(dialogUtility.stringToSruAnalysis(sentence), sentence);
//        ConceptNetReturnVal returnVal = conceptNetServiceBase.getReply(pReader);
//        assertNotNull(returnVal);
//    }
//
//    
//    /**
//     * @category selectQueryStringsTests
//     * These tests verify selectQueryStrings()
//     */
//    
//    @Test
//    public void testSelectQueryStringForWhat() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("What do you like?")).get(0);
//        assertEquals("like", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForWhatForBreakfast() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("What do you eat for breakfast?")).get(0);
//        assertEquals("breakfast", selectedWord);
//     }
//
//    @Test
//    public void testSelectQueryStringForWhatWithHotdog() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("What do you like with your hotdog?")).get(0);
//        assertEquals("hotdog", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForWhatOnHotdog() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("What do you like on your hotdog?")).get(0);
//        assertEquals("hotdog", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForChickenWing() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I had a chicken wing.")).get(0);
//        assertEquals("chicken_wing", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForTeaKettle() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("Put the tea kettle on.")).get(0);
//        assertEquals("tea_kettle", selectedWord);
//    }
//
//    /**
//     * Test commented out because currently we don't ID "electric car" as a compound noun. ("Electric" is an adjective, not a noun.)
//     */
//    @Test
//    @Ignore
//    public void testSelectQueryStringForElectricCar() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I drive an electric car.")).get(0);
//        assertEquals("electric_car", selectedWord);
//    }
//
//    @Test(expected=IllegalArgumentException.class)
//    public void testSelectQueryStringForNull() {
//        conceptNetServiceBase.selectQueryStrings(null);
//    }
//
//    @Test(expected=IllegalArgumentException.class)
//    public void testSelectQueryStringForEmpty() {
//        conceptNetServiceBase.selectQueryStrings(null);
//    }
//
//    @Test
//    public void testSelectQueryStringForDirectObject() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("My parents love Spain.")).get(0);
//        assertEquals("spain", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForIndirectObject() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("My parents went to Florida.")).get(0);
//        assertEquals("florida", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForNoObject() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("My parents dance.")).get(0);
//        assertEquals("dance", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForLikeToPlaySoccer() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("He likes to play soccer.")).get(0);
//        assertEquals("soccer", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForKnowYouLoveManhattan() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I know that you'll love Manhattan.")).get(0);
//        assertEquals("manhattan", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForGoingToVisitSphinx() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("We're going to visit the Sphinx.")).get(0);
//        assertEquals("sphinx", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForUsedToBuyUSA() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("We used to buy USA.")).get(0);
//        assertEquals("usa", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForSayThatLifeIsLove() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("Are you saying that life is love?")).get(0);
//        assertEquals("love", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForReadThatReptilesAreSmarter() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I read that reptiles are smarter than you think.")).get(0);
//        assertEquals("smarter", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForALotOfTrouble() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("There's a lot of trouble in the Ukraine.")).get(0);
//        assertEquals("trouble", selectedWord);
//    }

//    @Test
//    public void testSelectQueryStringForNoSentence() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("an the")).get(0);
//        // It's "the" because the parse decides "the" is the root of the sentence.
//        assertEquals("the", selectedWord);
//    }
//
//    @Test
//    public void testSelectQueryStringForNoSentenceWithProblemKeyword() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("in the")).get(0);
//        assertEquals("in_the", selectedWord);
//    }

    /**
     * @category selectEdgesTests
     * These tests verify the edges--the results returned by ConceptNet.
     */
    

//    @Test
//    public void testSelectEdgesForStars() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("My brother and I would watch the stars.")).get(0);
//        assertEquals("star", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "an activity a star can do is explode";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        replies = Lists.newArrayList();
//        expected = "are beautiful to look at";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//
//        replies = Lists.newArrayList();
//        expected = "stars cannot be seen inside a big city";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        // Verify that no unwanted responses are included.
//        testRelations(actualEdges);
//        testDatasets(actualEdges);
//
//        // We do not want any whose relation is RelatedTo.
//        replies = Lists.newArrayList();
//        expected = "Sun is related to star";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//
//    @Test
//    public void testSelectEdgesForSpain() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("My parents traveled to Spain last summer.")).get(0);
//        assertEquals("spain", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "bilbao is part of spain";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        replies = Lists.newArrayList();
//        expected = "sevilla is part of spain";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//
//        replies = Lists.newArrayList();
//        expected = "spain is part of europe";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//    }
//
//    @Test
//    public void testSelectEdgesForFlorida() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("My parents vacation in Florida.")).get(0);
//        assertEquals("florida", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "sarasota is part of florida";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        replies = Lists.newArrayList();
//        expected = "florida is part of the united states";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//    }
//
//    @Test
//    public void testSelectEdgesForHot() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("It's hot today.")).get(0);
//        assertEquals("hot", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        @SuppressWarnings("unused")
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//
//        // Many of the edges have the same score, so we can't be absolutely sure that some of them will
//        // appear in the top n.
//        
////        // Verify that the results include expected answers.
////        List<String> replies = Lists.newArrayList();
////        String expected = "the weather can sometimes be hot";
////        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
////        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
////        
////        replies = Lists.newArrayList();
////        expected = "winter is generally cold and summer is hot";
////        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
////        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//    }
//    
//    @Test
//    public void testSelectEdgesForTorrid()  {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("It was torrid.")).get(0);
//        assertEquals("torrid", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that no unwanted responses are included.
//        testRelations(actualEdges);
//        testDatasets(actualEdges);
//
//        // We do not want any whose relation is SimilarTo.
//        List<String> replies = Lists.newArrayList();
//        String expected = "emotionally charge and vigorously energetic";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//}

//
//    @Test
//    public void testSelectEdgesForWine() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I like wine.")).get(0);
//        assertEquals("wine", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "a wine can age well";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        replies = Lists.newArrayList();
//        expected = "wine is for getting drunk";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "wine can make you drunk";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "wine is good for a romantic dinner";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//
//        // Many of the edges have the same score, so we can't be absolutely sure that some of them will
//        // appear in the top n.
//        
////        replies = Lists.newArrayList();
////        expected = "wine is for relaxing";
////        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
////        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//    }
//
//    @Test
//    public void testSelectEdgesForJazz() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I like jazz.")).get(0);
//        assertEquals("jazz", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "jazz is innovative and improvisational";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//    }
//
//    @Test
//    public void testSelectEdgesForMom() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I visited my mom last weekend.")).get(0);
//        assertEquals("mom", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        testRelations(actualEdges);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "moms can cook chicken soup";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        replies = Lists.newArrayList();
//        expected = "moms can be great doctors";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "a mom does not want her child to go hungry";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//    }
//
//    @Test
//    public void testSelectEdgesForSoccer() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I played soccer last weekend.")).get(0);
//        assertEquals("soccer", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "soccer is the favorite sport in brazil";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        replies = Lists.newArrayList();
//        expected = "soccer can cause head injuries";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "soccer is played all over the world";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "soccer is played on a soccer field";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "soccer can be dangerous";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "soccer is fun";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//        
//        replies = Lists.newArrayList();
//        expected = "soccer can cause brain damage";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);
//
//        // Verify that no unwanted responses are included.
//        testRelations(actualEdges);
//        testDatasets(actualEdges);
//        replies = Lists.newArrayList();
//        expected = "is derived from egg oval reproductive body of fowl use as food";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//}
//
//    @Test
//    public void testSelectEdgesForRhinos() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I like rhinos.")).get(0);
//        assertEquals("rhino", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that all responses are "rhino" whole-word matches.
//        for(Edge edge: actualEdges)     {
//            String reply = ConceptNetServiceBase.formulateReply(edge);
//            assertTrue("'" + reply + "' fails to match expected value '" + selectedWord + "'.", reply.toLowerCase().matches(".*\\brhino\\b.*"));
//        }
//    }
//
//    @Test
//    public void testSelectEdgesForBrother() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I saw my brother.")).get(0);
//        assertEquals("brother", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "a brother can bug his sister";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        replies = Lists.newArrayList();
//        expected = "a brother is part of a family";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        // Verify that no unwanted responses are included.
//        testRelations(actualEdges);
//        testDatasets(actualEdges);
//        replies = Lists.newArrayList();
//        expected = "belief in supernatural power";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//    
//    @Test
//    public void testSelectEdgesForEgg() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("My sister loathes eggs.")).get(0);
//        assertEquals("egg", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
////        List<String> replies = Lists.newArrayList();
////        String expected = "is part of egg oval reproductive body of fowl use as food";
////        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
////        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//        
//        // Verify that no unwanted responses are included.
//        List<String> replies = Lists.newArrayList();
//        String expected = "is part of egg oval reproductive body of fowl use as food";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//
//        replies = Lists.newArrayList();
//        expected = "is derived from egg oval reproductive body of fowl use as food";
//        found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//    
//    @Test
//    public void testSelectEdgesForDead() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("The WTO is dead!")).get(0);
//        assertEquals("dead", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that no unwanted responses are included.
//        List<String> replies = Lists.newArrayList();
//        String expected = "alive possess life";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertFalse("'" + expected + "' not expected to be in: " + replies.toString(), found);       
//    }
//    
//    /**
//     * This test verifies that, if a lookup on a compound noun fails, we do a lookup on the root of the noun phrase.
//     */
//    @Test
//    public void testSelectEdgesForSoldierGirl() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("She was a soldier girl.")).get(0);
//        assertEquals("soldier_girl", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "a girl can wear a dress";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//    }
//    
//    @Test
//    public void testSelectEdgesForStatueOfLiberty() {
//        String selectedWord = conceptNetServiceBase.selectQueryStrings(getParseReaderOfInput("I saw the Statue of Liberty.")).get(0);
//        assertEquals("statue_of_liberty", selectedWord);
//        ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//        
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, ConceptNetServiceBase.DEFAULT_UNWANTED_RELATIONS);
//        
//        // Verify that the results include expected answers.
//        List<String> replies = Lists.newArrayList();
//        String expected = "is very tall";
//        boolean found = containsAtLeastOneOf(expected, actualEdges, replies, conceptNetServiceBase);
//        assertTrue("'" + expected + "' not in: " + replies.toString(), found);       
//    }
    
}
