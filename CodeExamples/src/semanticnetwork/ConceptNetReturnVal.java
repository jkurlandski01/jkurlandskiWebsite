/**
 * 
 */
package semanticnetwork;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Container for the pieces of a ConceptNet return value.
 */
public class ConceptNetReturnVal {
    private String reply;
    private List<String> lookupStrings = Lists.newArrayList();
    private String properties;
    
    public ConceptNetReturnVal(String answer, List<String> lookup, String props)    {
        reply = answer;
        lookupStrings = lookup;
        properties = props;
    }

    public String getReply() {
        return reply;
    }

    public List<String> getLookupStrings() {
        return lookupStrings;
    }

    public String getProperties() {
        return properties;
    }
}
