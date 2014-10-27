package semanticnetwork;

import static org.junit.Assert.*;

import java.util.List;

import semanticnetwork.ConceptNetQuery;
import semanticnetwork.ConceptNetQuery.Dataset;
import semanticnetwork.ConceptNetQuery.Edge;
import semanticnetwork.ConceptNetQuery.Relation;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ConceptNetQueryTest {

    /**
     * This query returns no edges because it is in uppercase.
     */
    @Test
    public void testEmptyReturn() {
        ConceptNetQuery dict = new ConceptNetQuery("Florida");
        assertEquals(0.0, dict.getMaxScore(), 0.0);
        assertEquals(0, dict.getNumFound());
        assertEquals(Lists.newArrayList(), dict.getEdges());
    }

    @Test
    public void testStarryStarryNight() {
        ConceptNetQuery dict = new ConceptNetQuery("starry_starry_night");
        assertEquals(13.6, dict.getMaxScore(), 1.0);
        assertEquals(4, dict.getNumFound());
        
        List<Edge> edges = dict.getEdges();
        assertEquals(4, edges.size());
        
        Edge edge = edges.get(0);
        assertEquals(Relation.IsA, edge.getRelation());
        assertEquals("starry starry night", edge.getStartNode());
        assertEquals("song about picasso", edge.getEndNode());
        assertEquals("starry starry night is a song about picasso", edge.getText());
        assertEquals(13.6, edge.getScore(), 1.0);
        assertEquals(1.58, edge.getWeight(), 0.1);
        assertEquals(Dataset.ConceptNet, edge.getDataset());
        
        edge = edges.get(1);
        assertEquals(Relation.IsA, edge.getRelation());
        assertEquals("starry starry night album", edge.getStartNode());
        assertEquals("album", edge.getEndNode());
        assertEquals("", edge.getText());
        assertEquals(4.88, edge.getScore(), 1.0);
        assertEquals(0.58, edge.getWeight(), 0.1);
        assertEquals(Dataset.DBPedia, edge.getDataset());
        
        edge = edges.get(2);
        assertEquals(Relation.IsA, edge.getRelation());
        assertEquals("starry starry night album", edge.getStartNode());
        assertEquals("musical work", edge.getEndNode());
        assertEquals("", edge.getText());
        assertEquals(4.88, edge.getScore(), 1.0);
        assertEquals(0.58, edge.getWeight(), 0.1);
        assertEquals(Dataset.DBPedia, edge.getDataset());
        
        edge = edges.get(3);
        assertEquals(Relation.IsA, edge.getRelation());
        assertEquals("starry starry night album", edge.getStartNode());
        assertEquals("live album", edge.getEndNode());
        assertEquals("", edge.getText());
        assertEquals(4.88, edge.getScore(), 1.0);
        assertEquals(0.58, edge.getWeight(), 0.1);
        assertEquals(Dataset.DBPedia, edge.getDataset());
    }

    @Test
    public void testFloridaFiltered() {
        ConceptNetQuery dict = new ConceptNetQuery("florida");
        assertEquals(16.3, dict.getMaxScore(), 1.0);
        assertEquals(11285, dict.getNumFound());
        
        List<Edge> edges = dict.getEdges();
        assertEquals(100, edges.size());
        
        // Assert before filtering.
        Edge edge = edges.get(0);
        assertEquals(Relation.IsA, edge.getRelation());

        // Filter one Relation type and test.
        edges = dict.getFilteredEdges(Relation.IsA);      
        edge = edges.get(0);
        assertEquals(Relation.AtLocation, edge.getRelation());
        
        // Filter multiple Relation types and test.
        edges = dict.getFilteredEdges(Relation.IsA, Relation.AtLocation, Relation.HasA);      
        edge = edges.get(0);
        assertEquals(Relation.PartOf, edge.getRelation());
        assertEquals("florida", edge.getStartNode());
        assertEquals("unite state", edge.getEndNode());
        assertEquals("Florida is part of the United States", edge.getText());
        assertEquals(11.6, edge.getScore(), 1.0);
        assertEquals(2.32, edge.getWeight(), 0.1);
    }
    
    @Test
    public void testRelationGlosses()   {
        assertEquals("is a", Relation.IsA.toString());
        assertEquals("does not have the property of", Relation.NotHasProperty.toString());
    }
    
    
    
    @Test(expected=IllegalArgumentException.class)
    public void testUnknownDataset() {
        Dataset.getDataset("blah/blah");
    }

    @Test
    public void testConceptNet() {
        Dataset dataset = Dataset.getDataset("/d/conceptnet/4/en");
        assertEquals(Dataset.ConceptNet, dataset);
    }

    @Test
    public void testDbPedia() {
        Dataset dataset = Dataset.getDataset("/d/dbpedia/en");
        assertEquals(Dataset.DBPedia, dataset);
    }

    @Test
    public void testGlobalMind() {
        Dataset dataset = Dataset.getDataset("/D/GLOBALMIND");
        assertEquals(Dataset.GlobalMind, dataset);
    }

    @Test
    public void testJmDict() {
        Dataset dataset = Dataset.getDataset("/d/jmdict");
        assertEquals(Dataset.JmDict, dataset);
    }

    @Test
    public void testVerbosity() {
        Dataset dataset = Dataset.getDataset("/d/verbosity");
        assertEquals(Dataset.Verbosity, dataset);
    }

    @Test
    public void testWiktionary1() {
        Dataset dataset = Dataset.getDataset("/d/wiktionary/en/be");
        assertEquals(Dataset.Wiktionary, dataset);
    }

    @Test
    public void testWiktionary2() {
        Dataset dataset = Dataset.getDataset("/d/wiktionary/en/tk");
        assertEquals(Dataset.Wiktionary, dataset);
    }

    @Test
    public void testWordNet() {
        Dataset dataset = Dataset.getDataset("/d/wordnet/3.0");
        assertEquals(Dataset.WordNet, dataset);
    }

    @Test
    public void testReVerb() {
        Dataset dataset = Dataset.getDataset("/blah/reverb/blah");
        assertEquals(Dataset.ReVerb, dataset);
    }

    @Test
    public void testGetRelationString() {
        ConceptNetQuery dict = new ConceptNetQuery();
        Edge edge = dict.new Edge("NonexistentRelation", "1.0", "2.0", "aaa", "bbb", "/d/globalmind", "ddd");
        
        assertEquals(Relation.Other, edge.getRelation());
        String actualStr = edge.getRelationString();
        assertEquals("NonexistentRelation", actualStr);
    }
}
