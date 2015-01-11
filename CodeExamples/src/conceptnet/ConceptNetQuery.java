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
    
    private static final String CONCEPTNET_URI = "http://conceptnet5.media.mit.edu/data/5.3/c/en/";
    private static final String NBR_TO_RETRIEVE = "100";

    // Strings identifying properties in the JSON string.
    private static final String EDGES = "edges";
    private static final String NUM_FOUND = "numFound";

    // The item searched on.
    protected String input;
   
    // Data on the response for a ConceptNet lookup.
    private List<Edge> edges = Lists.newArrayList();
    private int numFound = 0;
    
    public ConceptNetQuery(String in)    {
        input = in;
        try {
            String qStr = CONCEPTNET_URI + input + "?limit=" + NBR_TO_RETRIEVE;
            String json = getJsonString(qStr);
            JSONTokener jsonTokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            numFound = jsonObject.getInt(NUM_FOUND);

            // Each JSONArray element contains data on one edge of the many edges returned. 
            JSONArray resultArray = jsonObject.getJSONArray(EDGES);
            for (int i = 0; i < resultArray.length(); i++) {
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
        BufferedReader reader = getReaderForQuery(queryURI);
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
    
    public BufferedReader getReaderForQuery(String query) throws IOException	 {
        URL url = new URL(query);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader;
    }

//    public List<Edge> getFilteredEdges(Relation... relsToBeRemoved) {
//        List<Relation> relationsToRemove = Lists.newArrayList(relsToBeRemoved);
//        List<Edge> retList = Lists.newArrayList();
//        for(Edge edge : edges)  {
//            if(! relationsToRemove.contains(edge.getRelation()))   {
//                retList.add(edge);
//            }
//        }
//        return retList;
//    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNumFound() {
        return numFound;
    }

    public String getInput() {
        return input;
    }
}
