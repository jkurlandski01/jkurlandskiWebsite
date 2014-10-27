/**
 * 
 */
package semanticnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.common.collect.Lists;

/**
 * Performs ConceptNet queries for a given string.
 */
public class ConceptNetQuery {
    
    private static Log LOG = LogFactory.getLog(ConceptNetQuery.class);
    
    protected String input;
    
    private static final String CONCEPTNET_URI = "http://conceptnet5.media.mit.edu/data/5.2/c/en/";
    
    // These fields represent the JSON string returned by ConceptNet.
    private static final String EDGES = "edges";
    protected List<Edge> edges = Lists.newArrayList();
    private static final String MAX_SCORE = "maxScore";
    private double maxScore = 0.0;
    private static final String NUM_FOUND = "numFound";

    private static final String NBR_TO_RETRIEVE = "100";
    private int numFound = 0;
    
    public enum Relation {Other("is somehow related to"), 
        Antonym("is the opposite of"), NotAntonym("is not the opposite of"), 
        AtLocation("is at"), NotAtLocation("is not at"), CapableOf("is capable of"), NotCapableOf("is not capable of"), 
        Causes("causes"), NotCauses("does not cause"), DefinedAs("is defined as"), NotDefinedAs("is not defined as"), 
        DerivedFrom("is derived from"), NotDerivedFrom("is not derived from"),
        HasA("has a"), NotHasA("doesn't have a"), HasContext("occurs in the context of"), NotHasContext("does not occur in the context of"), 
        HasPrerequisite("has a prerequisite of"), NotHasPrerequisite("does not have a prerequisite of"), 
        HasProperty("has the property of"), NotHasProperty("does not have the property of"), HasSubevent("has a subevent of"), NotHasSubevent("does not have a subevent of"), 
        IsA("is a"), NotIsA("is not a"), MemberOf("is a member of"), NotMemberOf("is not a member of"), PartOf("is part of"), NotPartOf("is not part of"), 
        RelatedTo("is related to"), NotRelatedTo("is not related to"), SimilarTo("is similar to"), NotSimilarTo("is not similar to"), 
        TranslationOf("is a translation of"), NotTranslationOf("is not a translation of"), 
        UsedFor("is used for"), NotUsedFor("is not used for");
    
        private String gloss;
    
        Relation(String str)   {
            gloss = str;
        }
        
        @Override
        public String toString()    {
            return gloss;
        }
    }
    
    public enum Dataset {
        ConceptNet, DBPedia, GlobalMind, JmDict, ReVerb, Verbosity, Wiktionary, WordNet;
        
        public static Dataset getDataset(String input)  {
            String tempStr = input.toLowerCase();
            if(tempStr.matches(".*conceptnet.*"))    {
                return Dataset.ConceptNet;
            }
            else if(tempStr.matches(".*dbpedia.*"))    {
                return Dataset.DBPedia;
            }
            else if(tempStr.matches(".*globalmind.*"))    {
                return Dataset.GlobalMind;
            }
            else if(tempStr.matches(".*jmdict.*"))    {
                return Dataset.JmDict;
            }
            else if(tempStr.matches(".*reverb.*"))    {
                return Dataset.ReVerb;
            }
            else if(tempStr.matches(".*verbosity.*"))    {
                return Dataset.Verbosity;
            }
            else if(tempStr.matches(".*wiktionary.*"))    {
                return Dataset.Wiktionary;
            }
            else if(tempStr.matches(".*wordnet.*"))    {
                return Dataset.WordNet;
            }
            
            throw new IllegalArgumentException("Dataset not found for input: " + tempStr);
        }
    }
    
    public class Edge {
        private String lookupStr;
        
        private static final String RELATION = "rel";
        private String relationString = "";
        private Relation relation = Relation.Other;
        private static final String SCORE = "score";
        private double score = 0.0;
        private static final String WEIGHT = "weight";
        private double weight = 0.0;
        private static final String SURFACE_TEXT = "surfaceText";
        private String surfaceText = "";
        private static final String DATASET = "dataset";
        private Dataset dataset;
        
        private static final String START = "startLemmas";
        private String startNode = "";
        private static final String END = "endLemmas";
        private String endNode = "";
        
        public Edge()   {
        }
        
        public Edge(String lookupString, JSONObject jsonObj) throws JSONException   {
            lookupStr = lookupString;
            
            relation = setRelation(cleanRelation(jsonObj.getString(RELATION)));
            score = jsonObj.getDouble(SCORE);
            weight = jsonObj.getDouble(WEIGHT);
            startNode = jsonObj.getString(START);
            endNode = jsonObj.getString(END);
            dataset = Dataset.getDataset(jsonObj.getString(DATASET));
            
            try {
                surfaceText = cleanSurfaceText(jsonObj.getString(SURFACE_TEXT));
            } catch (JSONException e) {
                // LOG.info("JSONException: Edge has no " + SURFACE_TEXT + " value: " + jsonObj.toString());
            }

        }

        public Edge(String relation, String score, String weight, String startNode, String endNode, String dataset, String surfaceText)   {
            this.relation = setRelation(relation);
            this.score = new Double(score);
            this.weight = new Double(weight);
            this.startNode = startNode;
            this.endNode = endNode;
            this.dataset = Dataset.getDataset(dataset);
            this.surfaceText = cleanSurfaceText(surfaceText);
        }
        
       public String getLookupStr() {
            return lookupStr;
        }

        public void setLookupStr(String lookupStr) {
            this.lookupStr = lookupStr;
        }

        private Relation setRelation(String relationStr) {
            relationString = relationStr;
            Relation rel = null;
            try {
                rel = Relation.valueOf(relationStr);
            } catch (IllegalArgumentException e) {
                // The relation isn't in our Relation enum. No problem--we deal with this below.
            }
            if(rel == null) {
                rel = Relation.Other;
            }
            return rel;
        }

        private String cleanRelation(String string) {
            return string.replaceFirst("/r/", "");
        }

        private String cleanSurfaceText(String string) {
            String TO_REMOVE = "(\"|\\[|\\])";
            return string.replaceAll(TO_REMOVE, "");
        }

        public Relation getRelation() {
            return relation;
        }

        public String getRelationString() {
            return relationString;
        }

        public Dataset getDataset() {
            return dataset;
        }

        public String getStartNode() {
            return startNode;
        }
        public String getEndNode() {
            return endNode;
        }

        public double getScore() {
           return score;
        }

        public String getText() {
            return surfaceText;
        }
        public void setText(String txt)   {
            surfaceText = txt;
        }

        public double getWeight() {
            return weight;
        }
        public void setWeight(double w) {
            weight = w;
        }
    }
    
    public ConceptNetQuery()    {
        
    }
    
    public ConceptNetQuery(String in)    {
        input = in;
        try {
            String qStr = CONCEPTNET_URI + input + "?limit=" + NBR_TO_RETRIEVE;
            String json = getJsonString(qStr);
            JSONTokener jsonTokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            maxScore = jsonObject.getDouble(MAX_SCORE);
            numFound = jsonObject.getInt(NUM_FOUND);
            JSONArray resultArray = jsonObject.getJSONArray(EDGES);
            for (int i = 0; i < resultArray.length(); i++) {
                // TODO: go into this object for finer-grained parsing: 
                JSONObject result = resultArray.getJSONObject(i);
                Edge edge = new Edge(input, result);
                edges.add(edge);
            }


        } catch (IOException e) {
            LOG.warn("IOException: Can't retrieve message for:" + in);
        } catch (JSONException e) {
            LOG.warn("JSONException: Can't retrieve message for:" + in);
        }
    }

    public static String getJsonString(String queryURI) throws IOException{
        BufferedReader reader = null;
        URL url = new URL(queryURI);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer buffer = new StringBuffer();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1){
            buffer.append(chars, 0, read);  
        }
        return buffer.toString();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public int getNumFound() {
        return numFound;
    }

    /**
     * Remove from this query's result the given relations.
     * @param relsToBeRemoved
     *  the relations to be removed
     * @return
     *  a new query result with the given relations removed
     */
    // FIXME: This work has been moved to ConceptNetServiceBase. Perhaps delete.
    //@Deprecated
    public List<Edge> getFilteredEdges(Relation... relsToBeRemoved) {
        List<Relation> relationsToRemove = Lists.newArrayList(relsToBeRemoved);
        List<Edge> retList = Lists.newArrayList();
        for(Edge edge : edges)  {
            if(! relationsToRemove.contains(edge.getRelation()))   {
                retList.add(edge);
            }
        }
        return retList;
    }

    public String getInput() {
        return input;
    }
    
}
