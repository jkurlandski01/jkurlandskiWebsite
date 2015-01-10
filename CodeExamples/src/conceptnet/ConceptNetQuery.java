/**
 * 
 */
package conceptnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.common.collect.Lists;

/**
 * Performs ConceptNet queries for a given string.
 */
public class ConceptNetQuery {
    
    protected String input;
    
    private static final String CONCEPTNET_URI = "http://conceptnet5.media.mit.edu/data/5.3/c/en/";
    
    // These fields represent the JSON string returned by ConceptNet.
    private static final String EDGES = "edges";
    protected List<Edge> edges = Lists.newArrayList();
    private static final String NUM_FOUND = "numFound";

    private static final String NBR_TO_RETRIEVE = "100";
    private int numFound = 0;
    
    public class Edge {
        private String lookupStr;
        
        private static final String RELATION = "rel";
        private String relationString = "";
        private Relation relation = Relation.Other;
        private static final String WEIGHT = "weight";
        private double weight = 0.0;
        private static final String SURFACE_TEXT = "surfaceText";
        private String surfaceText = "";
        private static final String DATASET = "dataset";
        private Dataset dataset;
        
        private static final String START = "start";
        private String startNode = "";
        private static final String END = "end";
        private String endNode = "";
        
        public Edge()   {
        }
        
        public Edge(String lookupString, JSONObject jsonObj)  {
            lookupStr = lookupString;
            
            try {
            	relation = setRelation(cleanRelation(jsonObj.getString(RELATION)));
            	weight = jsonObj.getDouble(WEIGHT);
            	startNode = jsonObj.getString(START);
            	endNode = jsonObj.getString(END);
            	dataset = Dataset.getDataset(jsonObj.getString(DATASET));       
                surfaceText = cleanSurfaceText(jsonObj.getString(SURFACE_TEXT));
            } catch (JSONException e) {
                System.out.println("JSONException in Edge constructor for string: " + lookupString);
            }

        }

        public Edge(String relation, String score, String weight, String startNode, String endNode, String dataset, String surfaceText)   {
            this.relation = setRelation(relation);
            this.weight = new Double(weight);
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
        	return cleanNodes(startNode);
        }
        public String getEndNode() {
        	return cleanNodes(endNode);
        }
        
        private String cleanNodes(String in)	{
        	// TODO: figure out why sometimes the end string ends in an apparently meaningless "/n".
        	in = in.replaceAll("/n$", "");
        	
        	String[] start = in.split("/");
        	String retString = start[start.length - 1].replaceAll("_", " ");
            return retString;        	
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
            numFound = jsonObject.getInt(NUM_FOUND);
            JSONArray resultArray = jsonObject.getJSONArray(EDGES);
            for (int i = 0; i < resultArray.length(); i++) {
                // TODO: go into this object for finer-grained parsing: 
                JSONObject result = resultArray.getJSONObject(i);
                Edge edge = new Edge(input, result);
                edges.add(edge);
            }

        } catch (IOException e) {
            System.out.println("IOException: Can't retrieve message for: " + in);
        } catch (JSONException e) {
            System.out.println("JSONException: Can't retrieve message for: " + in);
        }
    }

    public String getJsonString(String queryURI) throws IOException	{
        BufferedReader reader = getReaderforQuery(queryURI);
        if(reader == null)	{
        	return "";
        }
        
        StringBuffer buffer = new StringBuffer();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1)	{
            buffer.append(chars, 0, read);  
        }
        return buffer.toString();
    }
    
    public BufferedReader getReaderforQuery(String query) throws IOException	 {
        URL url = new URL(query);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNumFound() {
        return numFound;
    }

    public String getInput() {
        return input;
    }
    
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
}
