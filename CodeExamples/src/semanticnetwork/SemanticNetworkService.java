/**
 * 
 */
package semanticnetwork;

import java.util.List;

import semanticnetwork.ConceptNetQuery.Edge;
import semanticnetwork.ConceptNetQuery.Relation;

/**
 * Allows us to make use of semantic networks such as ConceptNetServiceBase.
 */
public interface SemanticNetworkService {
    
    /**
     * Return a reply for the given user input.
     * @param tree  
     *      the SRUAnalysis corresponding to the input string
     * @param input1
     *      the user input for which we want a reply
     * @return
     */
//    public ConceptNetReturnVal getReply(ParseReader parseReader);

    /**
     * Given user input, select the word or words which will be used for the semantic network lookup.
     * @param sentence
     * @return
     *      a list of strings, the first item of which is the primary keyword for the semantic network lookup, and whose optional
     *      additional items allow you to filter out undesirable replies
     */
//    public List<String> selectQueryStrings(ParseReader parseReader);

    /**
     * Filter undesirable edges from those found by the given RottenTomatoesQuery.
     * @param query
     * @param unwantedRelations
     * @return
     */
    public List<Edge> selectEdges(ConceptNetQuery query, List<Relation> unwantedRelations);


}
