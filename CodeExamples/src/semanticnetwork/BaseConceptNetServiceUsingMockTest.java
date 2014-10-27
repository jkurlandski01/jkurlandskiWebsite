package semanticnetwork;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import semanticnetwork.ConceptNetQuery.Edge;
//import semanticnetwork.ConceptNetServiceTestBase;

//public class BaseConceptNetServiceUsingMockTest extends ConceptNetServiceTestBase {
	public class BaseConceptNetServiceUsingMockTest  {

//    @Autowired
//    ConceptNetServiceBase conceptNetServiceBase;
    
    ConceptNetQuery emptyQuery = new ConceptNetQuery();
    Edge emptyEdge = emptyQuery.new Edge();
    List<Edge> mockEdgeList = Lists.newArrayList(emptyEdge);
    
    /**
     * This mock allows us to set the string to be looked up, but without actually
     * running the look-up.
     */
    private class ConceptNetQueryMock extends ConceptNetQuery  {

        public ConceptNetQueryMock(String in) {
            input = in;
            emptyEdge.setText(in);
            emptyEdge.setWeight(ConceptNetServiceBase.minWeight);
            edges = mockEdgeList;
        }
    }

    
    /**
     * Lookups on pronouns should always fail. 
     */
//    @Test
//    public void testSelectEdgesForSubjectivePronouns() {
//        ConceptNetQuery query = new ConceptNetQueryMock("i");
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("you");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("he");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("she");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("it");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("we");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQuery("they");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//}
//
//    /**
//     * Lookups on pronouns should always fail. 
//     */
//    @Test
//    public void testSelectEdgesForObjectivePronouns() {
//        ConceptNetQuery query = new ConceptNetQueryMock("me");
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("you");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("him");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("her");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("it");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("us");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("them");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//    }
//
//    @Test
//    public void testSelectEdgesForQuestionWords() {
//        ConceptNetQuery query = new ConceptNetQueryMock("how");
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("what");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("when");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("where");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("who");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("whom");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("why");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//    }
//
//    @Test
//    public void testSelectEdgesForCommonVerbs() {
//        ConceptNetQuery query = new ConceptNetQueryMock("am");
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("are");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("be");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("go");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("goes");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("had");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("has");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("have");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("is");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("makes");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("was");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("went");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("were");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//    }
//
//    @Test
//    public void testSelectEdgesForOther() {
//        ConceptNetQuery query = new ConceptNetQueryMock("in");
//        List<Edge> actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("that");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("this");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("anyone");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("someone");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("anything");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("something");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//
//        query = new ConceptNetQueryMock("nothing");
//        actualEdges = conceptNetServiceBase.selectEdges(query, conceptNetServiceBase.getUnwantedRelations());
//        assertEquals(0, actualEdges.size());
//    }

}
