package semanticnetwork;

import java.util.ArrayList;
import java.util.List;

import utils.DialogUtility;
import semanticnetwork.ConceptNetQuery.Dataset;
import semanticnetwork.ConceptNetQuery.Edge;
import semanticnetwork.ConceptNetQuery.Relation;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


public class ConceptNetServiceBase implements SemanticNetworkService {
    
    public static final List<Relation> DEFAULT_UNWANTED_RELATIONS = Lists.newArrayList(Relation.Antonym, Relation.NotAntonym, 
            Relation.AtLocation, Relation.NotAtLocation, Relation.DerivedFrom, Relation.NotDerivedFrom, 
            Relation.HasA, Relation.NotHasA, Relation.HasContext, Relation.NotHasContext, Relation.IsA, Relation.NotIsA, 
            Relation.RelatedTo, Relation.NotRelatedTo,
            Relation.SimilarTo, Relation.NotSimilarTo, Relation.TranslationOf, Relation.NotTranslationOf);

    public static final List<Dataset> DEFAULT_UNWANTED_DATASETS = Lists.newArrayList(Dataset.WordNet);
    
    /**
     * ConceptNet replies containing these strings have been deemed to cause more trouble than they're worth.
     */
    public static final List<String> DEFAULT_UNWANTED_CONTENT_STRINGS = Lists.newArrayList("helps answer the question");
    
    public static final double minWeight = 1.5;

    // This particular implementation does not use additional query words. Subclasses may.
    protected List<String> addlQueryWords = Lists.newArrayList();
    // Method used exclusively for unit tests.
    void setAdditionalQueryWords(List<String> words)  {
        addlQueryWords.addAll(words);
    }
    
//    @Override
//    public ConceptNetReturnVal getReply(ParseReader pReader) {
//        ConceptNetReturnVal reply = null;
//        
//        boolean acceptableSType = isSentenceTypeAcceptable(pReader.getSRUTree());
//        if(!acceptableSType)    {
//            return null;
//        }
//
//        List<String> origKeywords = selectQueryStrings(pReader);
//        List<String> keywords = Lists.newArrayList(origKeywords);
//        if(! keywords.isEmpty())  {
//        
//            String selectedWord = keywords.remove(0);
//
//            addlQueryWords = keywords;
//
//            ConceptNetQuery query = new ConceptNetQuery(selectedWord);
//            List<Edge> selectedEdges = selectEdges(query, getUnwantedRelations());
//
//            if(! selectedEdges.isEmpty())   {
//                Edge selectedEdge = pickOneEdge(selectedEdges);
//                String answer = formulateReply(selectedEdge);
//                String props = getProperties(selectedEdge);
//                reply = new ConceptNetReturnVal(answer, origKeywords, props);
//            }
//        }
//        
//        return reply;
//    }

//    protected boolean isSentenceTypeAcceptable(@SuppressWarnings("unused") SRUAnalysis tree) {
//        return true;
//    }

    public static String getProperties(Edge edge) {
        String delimiter = ";  ";
        StringBuilder sBuild = new StringBuilder();
        sBuild.append("DataSource: ").append(edge.getDataset().name().trim()).append(delimiter);
        
        if(edge.getRelation().equals(Relation.Other))   {
            sBuild.append("Relation: ").append(edge.getRelation().name().trim());
            sBuild.append(" (").append(edge.getRelationString()).append(") ").append(delimiter);
        }
        else    {
            sBuild.append("Relation: ").append(edge.getRelation().name().trim()).append(delimiter);
        }
        
        sBuild.append("Score: ").append(edge.getScore()).append(delimiter);
        sBuild.append("Weight: ").append(edge.getWeight()).append(delimiter);
        
        return sBuild.toString();
    }

    protected List<Relation> getUnwantedRelations() {
        return MultiwordQueryConceptNetService.DEFAULT_UNWANTED_RELATIONS;
    }

    public static String formulateReply(Edge edge) {
        String text = edge.getText();
        if(StringUtils.isBlank(text))   {
            text = edge.getStartNode() + " " + edge.getRelation() + " " + edge.getEndNode();
        }
        return prettify(text);
    }
    
    private Edge pickOneEdge(List<Edge> selectedEdges) {
        Preconditions.checkArgument(selectedEdges != null && !selectedEdges.isEmpty(), "Input is null or empty.");
        Edge pickedEdge = selectedEdges.get(RandomUtils.nextInt(selectedEdges.size()));

        return pickedEdge;
    }

    @Override
    public List<Edge> selectEdges(ConceptNetQuery query, List<Relation> unwantedRelations) {
        // If the query was performed on a "problem keyword," return nothing.
        if(DialogUtility.PROBLEM_KEYWORDS.contains(query.getInput())) {
            return new ArrayList<Edge>();
        }
        
        List<Edge> edges = performSelectEdgesSteps(query, unwantedRelations);
        
        // If the query was done on a compound noun and returned nothing, try a new query based on the main part of the compound noun.
        if(query.input.contains("_") && edges.isEmpty())     {
            // Get the new query string.
            int idx = query.input.lastIndexOf("_");
            String newQueryInput = query.input.substring(idx+1);
            // Make sure it's not a problem keyword.
            List<String> queryList = Lists.newArrayList(newQueryInput);
            String keyword = selectKeyword(queryList);
            if(! keyword.isEmpty())     {
                // Create a new query.
                query =  new ConceptNetQuery(keyword);
                if(DialogUtility.PROBLEM_KEYWORDS.contains(query.getInput())) {
                    return new ArrayList<Edge>();
                }
                
                edges = performSelectEdgesSteps(query, unwantedRelations);
            }
        }
        
        return edges;
    }
    
    private List<Edge> performSelectEdgesSteps(ConceptNetQuery query, List<Relation> unwantedRelations) {
        List<Edge> edges = query.getEdges();
        edges = filterByRelation(edges, unwantedRelations);
        
        edges = filterByDataset(edges);
        
        // Filter by weight.
        edges = filterByWeight(edges);
        
        // Filter by the reply's content.
        edges = filterByContent(query, edges);
        
        return edges;
    }

    private List<Edge> filterByDataset(List<Edge> edges) {
        List<Edge> retList = Lists.newArrayList();
        for(Edge edge : edges)  {
            if(! DEFAULT_UNWANTED_DATASETS.contains(edge.getDataset()))   {
                retList.add(edge);
            }
        }
        return retList;
    }

    protected List<Edge> filterByRelation(List<Edge> edges, List<Relation> unwantedRelations)     {
//        List<Edge> edges = query.getEdges();
        List<Edge> retList = Lists.newArrayList();
        for(Edge edge : edges)  {
            if(! unwantedRelations.contains(edge.getRelation()))   {
                retList.add(edge);
            }
        }
        return retList;
    }

    /**
     * Filter out those edges whose reply does not contain the original query keyword.
     * With the exception of the most common plural forms, the edge's reply must contain
     * the keyword as a whole-word match, not merely a substring.
     * @param query
     * @param edges
     * @return
     *      a filtered version of the original edges
     */
    protected List<Edge> filterByContent(ConceptNetQuery query, List<Edge> edges) {
        String input = query.getInput().toLowerCase();
        input = input.replaceAll("_", " ");
        
        // Note whole-word match (\b) that also allows for simple plural forms ((es|s)?).
        String inputRegex = ".*\\b" + input + "(es|s)?\\b.*";

        List<Edge> retEdges = Lists.newArrayList();
        
        outer: for(Edge edge : edges)  {
            String reply = formulateReply(edge).toLowerCase();
            for(String unwanted : DEFAULT_UNWANTED_CONTENT_STRINGS) {
                if(reply.contains(unwanted))    {
                    continue outer;
                }
            }
            if(reply.matches(inputRegex))   {
                retEdges.add(edge);
            }
        }
        
        return retEdges;
    }

    private List<Edge> filterByWeight(List<Edge> edges) {
        List<Edge> retEdges = Lists.newArrayList();
        
        for(Edge edge : edges)  {
            if(edge.getWeight() >= minWeight)   {
                retEdges.add(edge);
            }
        }
        
        return retEdges;
    }
    
    /**
     * Given a list of possible keywords, find the first one that is good for a lookup.
     * Returns empty string if input list is null or empty.
     * @param list
     * @return
     *      a good lookup keyword, or empty string if none found
     */
    private String selectKeyword(List<String> list) {
        if(list == null || list.isEmpty())  {
            return "";
        }
        
        for(String candidate : list)    {
            if(! DialogUtility.PROBLEM_KEYWORDS.contains(candidate))  {
                return candidate;
            }
        }
        
        return "";
    }

//    @Override
//    public List<String> selectQueryStrings(ParseReader parseReader) {
//        Preconditions.checkArgument(parseReader != null, "Input is null or empty.");
//
//        // TODO: This code will have to be rewritten if ParseReader ever loads all the direct objects or indirect objects--right
//        // now it only finds the first.
//        
//        // Create a list of keyword candidates to try out, in descending order of preference.
//        // Prefer direct object over indirect object over verb. Prefer compound nouns over the head of the noun phrase by itself.
//        List<String> keywordCandidates = Lists.newArrayList();
//        SRUNode focusNode = parseReader.getFocusNode();
//        if(focusNode != null)  {
//            keywordCandidates.add(ParseReader.getLemmaOrCompoundNoun(focusNode));
//            keywordCandidates.add(focusNode.getLemma().toLowerCase());
//        }
//        
//        if(! parseReader.getFocusDirectObjects().isEmpty())  {
//            SRUNode directObjectNode = parseReader.getFocusDirectObjects().get(0);
//            keywordCandidates.add(ParseReader.getLemmaOrCompoundNoun(directObjectNode));
//            keywordCandidates.add(directObjectNode.getLemma().toLowerCase());
//        }
//
//        if(! parseReader.getFocusIndirectObjects().isEmpty())  {
//            SRUNode indirectObjectNode = parseReader.getFocusIndirectObjects().get(0);
//            keywordCandidates.add(ParseReader.getLemmaOrCompoundNoun(indirectObjectNode));
//            keywordCandidates.add(indirectObjectNode.getLemma().toLowerCase());
//        }
//
//        if(parseReader.getMainVerb() != null)   {
//            keywordCandidates.add(parseReader.getMainVerb().getLemma().toLowerCase());
//        }
//        
//        String keyword = selectKeyword(keywordCandidates);
//        
//              
//        List<String> retList = Lists.newArrayList();
//        if(! keyword.isEmpty())   {
//            retList.add(keyword);
//        }
//
//        // If for some reason the input couldn't be parsed, use the input string itself.
//        if(retList.isEmpty())   {
//            String input = parseReader.getInputText();
//            input = input.replaceAll("[?,.]", "");
//            input = input.replaceAll(" ", "_");
//            retList.add(input);
//        }
//           
//        return retList;
//    }

    /**
     * Readies the input for presentation to the user.
     * @param in
     * @return
     */
    public static String prettify(String in)    {
        return StringUtils.capitalize(in) + ".";
    }

}
