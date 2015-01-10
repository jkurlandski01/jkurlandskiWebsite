package conceptnet;

public enum Dataset {
    ConceptNet, DBPedia, GlobalMind, JmDict, ReVerb, Verbosity, Wiktionary, WordNet, Umbel;
    
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
        else if(tempStr.matches(".*umbel.*"))    {
            return Dataset.Umbel;
        }
        
        throw new IllegalArgumentException("Dataset not found for input: " + tempStr);
    }
}
