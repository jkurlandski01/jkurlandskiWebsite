/**
 * 
 */
package media;

import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


/**
 * Holds enums, etc. used by movie functionality.
 */
public class MovieUtil {

    public enum Genre { 
        Action_And_Adventure("Action & Adventure", "an action movie", Lists.newArrayList("action", "adventure", "marvel", "super-hero")), 
        Animation("Animation", "an animated movie", Lists.newArrayList("animated", "animation", "cartoon")), 
        Art_House_And_International("Art House & International", "an art or international movie", Lists.newArrayList("art", "foreign", "indie", "international")), 
        Classic("Classics", "a classic", Lists.newArrayList("classic", "classics")),
        Comedy("Comedy", "a comedy", Lists.newArrayList("comedy", "spoof")),
        Cult("Cult Movies", "a cult classic", Lists.newArrayList("cult")), 
        Documentary("Documentary", "a documentary", Lists.newArrayList("documentary", "non-fiction")),
        Drama("Drama", "a drama", Lists.newArrayList("drama", "tear-jerker")), 
        History("History", "a history", Lists.newArrayList("biographical", "biography", "history")),    // don't use "historical" because of "historical romance"
        Horror("Horror", "a horror flick", Lists.newArrayList("chain-saw", "horror")),
        Kids_And_Family("Kids & Family", "a family movie", Lists.newArrayList("children", "family", "kids")), 
        Musical("Musical & Performing Arts", "a musical", Lists.newArrayList("broadway", "music", "musical")), 
        Mystery_And_Suspense("Mystery & Suspense", "a mystery-and-suspense movie", Lists.newArrayList("crime", "mystery", "noir", "suspense", "whodunit")), 
        Romance("Romance", "a romance", Lists.newArrayList("romance", "romantic")), 
        SciFi_And_Fantasy("Science Fiction & Fantasy", "a sci-fi/fantasy movie", Lists.newArrayList("fantasy", "scifi", "science fiction")), 
        Sports("Sports", "a sports movie", Lists.newArrayList("baseball", "football", "sport", "sports")), 
        SpecialInterest("Special Interest", "a special interest film", Lists.newArrayList("action", "adventure")), 
        Television("Television", "a made-for-television movie", Lists.newArrayList("television", "tv")), 
        Thriller("Thriller", "a thriller", Lists.newArrayList("thriller")), 
        Unknown("Unknown", "Unknown", Lists.newArrayList("unknown")), 
        Western("Western", "a western", Lists.newArrayList("western"));
    
        private String key;
        private String description;
        private List<String> keywords = Lists.newArrayList();
        
        Genre(String keyStr, String descriptionStr, List<String> keywordList)   {
            key = keyStr;
            description = descriptionStr;
            keywords.addAll(keywordList);
        }
    
        @Override
        public String toString()    {
            return description;
        }
        
        public static Genre getByName(String in)    {
            String name = in.trim().toLowerCase();
            for(Genre current: Genre.values()){
                if(current.key.equalsIgnoreCase(name))  {
                    return current;
                }
            }
            for(Genre current: Genre.values()){
                for(String keyword : current.keywords)  {
                    if(name.contains(keyword))     {
                        return current;
                    }
                }
            }
            return Genre.Unknown;
        }
    }


    public enum Rating {
        G("G"), PG ("PG"), PG_13 ("PG-13"), R ("R"), NC_17 ("NC-17"), X ("X"), NR ("NR"), Unknown("Unknown"); 
        private String gloss;
        
        Rating(String str)   {
            gloss = str;
        }
        
        @Override
        public String toString()    {
            return gloss;
        }
        
        public static Rating getByName(String in)    {
            String name = in.trim().toUpperCase();
            for(Rating current: Rating.values()){
                if(current.gloss.equalsIgnoreCase(name)){
                    return current;
                }
            }
            if(name.equals("UNRATED"))  {
                return Rating.NR;
            }
            return Rating.Unknown;
        }
    }

    /**
     * Randomly pick and return one element of the given list.
     * Returns IllegalArgumentException if the collection is null or empty.
     * @param coll
     * @return
     */
    public static <T> T pickRandomListMember(List<T> coll) {
        Preconditions.checkArgument(coll != null && !coll.isEmpty(), "Input is null or empty.");
        return coll.get(RandomUtils.nextInt(coll.size()));
    }
}

