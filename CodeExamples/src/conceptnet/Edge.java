package conceptnet;

import org.json.JSONException;
import org.json.JSONObject;

public class Edge {
    private String lookupStr;
    
    // Strings identifying the edge properties in the JSON string.
    private static final String RELATION = "rel";
    private static final String WEIGHT = "weight";
    private static final String SURFACE_TEXT = "surfaceText";
    private static final String DATASET = "dataset";
    private static final String START = "start";
    private static final String END = "end";
    
    // This Edge's properties.
    private String relationString = "";
    private Relation relation = Relation.Other;
    private double weight = 0.0;
    private String surfaceText = "";
    private Dataset dataset;
    private String startNode = "";
    private String endNode = "";
    
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

    // Surface text often appears with square brackets, e.g. "[[Strep throat]] is [[painful]]"
    private String cleanSurfaceText(String string) {
        String TO_REMOVE = "(\"|\\[|\\])";
        return string.replaceAll(TO_REMOVE, "");
    }

    public Relation getRelation() {
        return relation;
    }

   public String getLookupStr() {
        return lookupStr;
    }

    public void setLookupStr(String lookupStr) {
        this.lookupStr = lookupStr;
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