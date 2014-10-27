/**
 * 
 */
package semanticnetwork;

import java.util.List;

import semanticnetwork.ConceptNetQuery.Edge;
import semanticnetwork.ConceptNetQuery.Relation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * Given a set of keywords to look up, this class allows the user to filter out the results by Relation.
 */
public class ConceptNetExplorer {
    
    public static final List<Relation> WANTED_RELATIONS = Lists.newArrayList(Relation.RelatedTo);
    
    public static final List<String> REGULAR_NOUNS = ImmutableList.of(
            "arachnid", "brother", "chair", "cheese", "curiosity", "devil", "dog", "drawer", "egg", "eyeball", "flower", "fusion", "grammar", "jazz", "leaf", "mom", "music", "news", 
            "pizza", "privation", "pressure", "retirement", "rhino", "season", "servant", 
            "shrub", "shuttle", "skipper", "soccer", "spaghetti", "spider", "star",
            "statement", "strep_throat", "sun", "system", "wildfowl", "wing", "wine", "wish");

    public static final List<String> PROPER_NOUNS = ImmutableList.of(
            "america", "asia", "bogota", "christmas", "december", "don_quixote", "easter", "florida", "france", "hemingway", "january", "kyoto", "mark_twain", "mcdonalds", 
            "microsoft", "paris", "picasso", 
            "spain", "starry_starry_night", "summer", "van_gogh", "washington");

    public static final List<String> ADJECTIVES = ImmutableList.of(
            "curious", "dazed", "clean", "cold", "dead", "dirty", "eastern", "fervent", "flawless", "frisky", "hot", "modern", "precise", "rich", "smelly", "spanish", "torrid");

    public static final List<String> VERBS = ImmutableList.of(
            "beat", "dance", "dine", "eat", "edit", "feed", "forget", "include", "learn", "like", "sleep", "sniff", "think", "translate", "vivify");
    
    private List<String> listToUse = Lists.newArrayList();
    
    public ConceptNetExplorer() {
        listToUse.addAll(REGULAR_NOUNS);
        listToUse.addAll(PROPER_NOUNS);
        listToUse.addAll(ADJECTIVES);
        listToUse.addAll(VERBS);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConceptNetExplorer explorer = new ConceptNetExplorer();
        List<Edge> edges = explorer.doLookup();
        List<Edge> filteredEdges = explorer.filterRelations(edges);
        printEdges(filteredEdges);
    }

    private static void printEdges(List<Edge> filteredEdges) {
        for(Edge edge : filteredEdges)     {
            String text = ConceptNetServiceBase.formulateReply(edge);
            
            System.out.println(edge.getLookupStr() + ": " + text);
            String info = ConceptNetServiceBase.getProperties(edge);
            System.out.println("   " + info);
        }
    }

    private List<Edge> filterRelations(List<Edge> edgesIn) {
        List<Edge> edgesOut = Lists.newArrayList();
        
        for(Edge edge : edgesIn)     {
            if(WANTED_RELATIONS.contains(edge.getRelation()))   {
                edgesOut.add(edge);
            }
        }
        
        return edgesOut;
    }

    private List<Edge> doLookup() {
        List<Edge> edges = Lists.newArrayList();
        
        for(String str : listToUse)     {
            ConceptNetQuery dict = new ConceptNetQuery(str);
            edges.addAll(dict.getEdges());
        }
        
        return edges;
    }

}
