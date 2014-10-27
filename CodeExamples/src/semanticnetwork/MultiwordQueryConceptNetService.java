/**
 * 
 */
package semanticnetwork;

import java.util.List;

import semanticnetwork.ConceptNetQuery.Edge;
import semanticnetwork.ConceptNetQuery.Relation;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Extends ConceptNetServiceBase by using a second keyword for filtering out the results of the initial query.
 */
public class MultiwordQueryConceptNetService extends ConceptNetServiceBase {

    public static final List<Relation> DEFAULT_UNWANTED_RELATIONS = Lists.newArrayList(Relation.Antonym, Relation.NotAntonym, 
            Relation.DerivedFrom, Relation.NotDerivedFrom, 
            Relation.HasContext, Relation.NotHasContext, 
            Relation.RelatedTo, Relation.NotRelatedTo,
            Relation.TranslationOf, Relation.NotTranslationOf);

    private static final List<String> UNWANTED_VERBS = Lists.newArrayList("'m", "am", "'re", "are", "be", "'s", "is");

//    @Override
//    protected boolean isSentenceTypeAcceptable(SRUAnalysis tree) {
//        SentenceType sType = tree.getSentenceType();
//        if(sType.equals(SentenceType.Interrogative)) {
//            return false;
//        }
//        
//        return true;
//    }

    /* 
     * Main focus is on the "object" of the sentence; additional keywords use the verb.
     */
//    @Override
//    public List<String> selectQueryStrings(ParseReader parseReader) {
//        Preconditions.checkArgument(parseReader != null, "ParseReader is null.");
//
//        // The base class gets the object and inserts it into the list.
//        List<String> retList = super.selectQueryStrings(parseReader);
//
//        // Load verb.
//        if(parseReader.getFocusVerb() != null)  {
//            String verbLemma = parseReader.getFocusVerb().getLemma();
//            if(! verbLemma.isEmpty())   {
//                if(! UNWANTED_VERBS.contains(verbLemma))   {
//                    retList.add(verbLemma);
//                }
//            }
//            String verbForm = parseReader.getFocusVerb().getForm();
//            if(! verbForm.isEmpty())   {
//                if(! UNWANTED_VERBS.contains(verbForm))   {
//                    retList.add(verbForm);
//                }
//            }
//        }
//        
//        // If the first and second elements are identical (as would be the case if the first call returned with the verb),
//        // our criteria are not sufficient for a Multiword query.
//        if(retList.size() >= 2 && retList.get(0).equals(retList.get(1)))    {
//            retList.clear();
//        }
//        
//        return retList;
//    }


    /* 
     * Filter out the edges found by the query: (1) by calling base class filterByContent; (2) by requiring that
     * each element in the addlQueryWords field be found in the edge's reply.
     */
    @Override
    protected List<Edge> filterByContent(ConceptNetQuery query, List<Edge> edges) {
        List<Edge> tempEdges = super.filterByContent(query, edges);

        List<Edge> retEdges = Lists.newArrayList();
        
        for(Edge edge : tempEdges)  {
            for(String str : addlQueryWords)    {
                String regex = ".*\\b" + str.toLowerCase() + "\\b.*";
                String reply = formulateReply(edge).toLowerCase();
                if(reply.matches(regex))    {
                    retEdges.add(edge);
                }
            }
        }
        
        return retEdges;
    }

    @Override
    protected List<Relation> getUnwantedRelations() {
        return MultiwordQueryConceptNetService.DEFAULT_UNWANTED_RELATIONS;
    }

}

