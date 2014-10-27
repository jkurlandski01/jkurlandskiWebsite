/**
 * 
 */
package media;

import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Performs ConceptNet queries for a given string.
 */
public class RottenTomatoesQuery {
    
    private static Log LOG = LogFactory.getLog(RottenTomatoesQuery.class);
    
    protected String input;
    
    // User: jkurlandski
    // login password: Amelia2014!
    private static final String API_KEY = "urx69p52heyuepm5sna8e6nd";
    
    private static final String OPENING_MOVIES_URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey=" + API_KEY;
    
    private static final String INCOMPLETE_MOVIE_DETAIL_URL = "http://api.rottentomatoes.com/api/public/v1.0/movies";
    
    public class MovieNode {
        private static final String ID = "id";
        String id = "";
        private static final String TITLE = "title";
        String title = "";
        private static final String RATING = "mpaa_rating";
        String rating = "";
        private static final String RUNTIME = "runtime";
        int runtime = 0;
        
        private static final String CRITICAL_RATINGS = "ratings";
        private static final String CRITIC_SCORE = "critics_score";
        double criticScore = 0.0;
        private static final String AUDIENCE_SCORE = "audience_score";
        double audienceScore = 0.0;
        
        private static final String SYNOPSIS = "synopsis";
        String synopsis = "";
        
        private static final String CAST = "abridged_cast";
        private static final String NAME = "name";
        List<String> cast = Lists.newArrayList();
        
        private static final String GENRES = "genres";
        List<String> genres = Lists.newArrayList();

        private static final String DIRECTORS = "abridged_directors";
        List<String> directors = Lists.newArrayList();

        public MovieNode()   {
        }

        public MovieNode(JSONObject jsonObj) {
            id = readJsonObjectForString(jsonObj, ID);
            title = readJsonObjectForString(jsonObj, TITLE);
            rating = readJsonObjectForString(jsonObj, RATING);

            try {
                runtime = new Integer(readJsonObjectForString(jsonObj, RUNTIME));
            } catch (NumberFormatException e1) {
                // Do nothing.
            }

            // Get critical ratings.
            try {
                JSONObject ratingsObj = jsonObj.getJSONObject(CRITICAL_RATINGS);
                try {
                    criticScore = new Double(readJsonObjectForString(ratingsObj, CRITIC_SCORE));
                } catch (NumberFormatException e) {
                    // Do nothing.
                }
                try {
                    audienceScore = new Double(readJsonObjectForString(ratingsObj, AUDIENCE_SCORE));
                } catch (NumberFormatException e) {
                    // Do nothing.
                }
            } catch (JSONException e) {
                LOG.info("JSONException: Can't retrieve message:" + e.getMessage());
            }

            synopsis = readJsonObjectForString(jsonObj, SYNOPSIS);

            // Get actors.
            try {
                JSONArray actorResultArray = jsonObj.getJSONArray(CAST);
                for (int i = 0; i < actorResultArray.length(); i++) {
                    JSONObject jsonObject = actorResultArray.getJSONObject(i);
                    String actor = readJsonObjectForString(jsonObject, NAME);
                    cast.add(actor);
                }
            } catch (JSONException e) {
                LOG.info("JSONException: Can't retrieve message:" + e.getMessage());
            }

            // Get genres.
            try {
                JSONArray genreResultArray = jsonObj.getJSONArray(GENRES);
                for (int i = 0; i < genreResultArray.length(); i++) {
                    String genre = genreResultArray.getString(i);
                    genres.add(genre);
                }
            } catch (JSONException e) {
                LOG.info("JSONException: Can't retrieve message:" + e.getMessage());
            }


            // Get directors.
            try {
                JSONArray directorResultArray = jsonObj.getJSONArray(DIRECTORS);
                for (int i = 0; i < directorResultArray.length(); i++) {
                    JSONObject jsonObject = directorResultArray.getJSONObject(i);
                    String director = jsonObject.getString(NAME);
                    directors.add(director);
                }
            } catch (JSONException e) {
                LOG.info("JSONException: Can't retrieve message:" + e.getMessage());
            }

        }


        private String readJsonObjectForString(JSONObject jsonObj, String idStr) {
            try {
                return jsonObj.getString(idStr);
            } catch (JSONException e) {
                LOG.info("JSONException: Can't retrieve message:" + e.getMessage());
            }
            return "";
       }

        public void getDetails() {
            try {
                String qStr = RottenTomatoesQuery.constructSingleMovieLookupURI(id);

                String json = getJsonString(qStr);
                JSONTokener jsonTokener = new JSONTokener(json);
                JSONObject jsonObject = new JSONObject(jsonTokener);

                MovieNode movie = new MovieNode(jsonObject);
                
                // Get genres.
                this.genres = Lists.newArrayList(movie.genres);
                
                // Get directors.
                this.directors = Lists.newArrayList(movie.directors);
                
            } catch (JSONException e) {
                LOG.warn("JSONException: Can't retrieve message:" + e.getMessage());
            } catch (IOException e) {
                LOG.warn("IOException: Can't retrieve message:" + e.getMessage());
            }
        }

    }

    private static final String MOVIES = "movies";
    protected List<MovieNode> movies = Lists.newArrayList();
    private MovieNode currMovie;
    protected int currMovieIndex = -1;
    int getCurrMovieIndex() { return currMovieIndex; }

    private int limit = 1000;
    
    public RottenTomatoesQuery()    {
        
    }
    
    /**
     * Create a query of a limited number of movie elements.
     * @param limit
     */
    public RottenTomatoesQuery(int limit) {
        this.limit = limit;
    }

    public void getOpeningMovies()    {
        try {
            String qStr = OPENING_MOVIES_URL;
            String json = getJsonString(qStr);
            JSONTokener jsonTokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            JSONArray resultArray = jsonObject.getJSONArray(MOVIES);
            for (int i = 0; i < resultArray.length(); i++) {               
                if(i + 1 >= limit)  {
                    break;
                }
                
                JSONObject result = resultArray.getJSONObject(i);
                MovieNode node = new MovieNode(result);
                movies.add(node);
            }
            
            // Shuffle the query, so that a different movie can come up first in the queue.
            Collections.shuffle(movies);
            if(! movies.isEmpty())  {
                currMovieIndex = 0;
            }
            
        } catch (JSONException e) {
            LOG.warn("JSONException: Can't retrieve message:" + e.getMessage());
        } catch (IOException e) {
            LOG.warn("IOException: Can't retrieve message:" + e.getMessage());
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

    public List<MovieNode> getMovies() {
        // Defensive copy.
        return Lists.newArrayList(movies);
    }

    public static String constructSingleMovieLookupURI(String movieID) {
        String str = INCOMPLETE_MOVIE_DETAIL_URL + "/" + movieID + ".json?apikey=" + API_KEY;
        return str;
    }

    /**
     * Get details for the first nbr elements in the movie list.
     * @param nbr
     */
    @Deprecated
    public void getDetailsForFirstN(int nbr) {
        for(int idx = 0; idx < movies.size() && idx < nbr; idx++)  {
            MovieNode movie = movies.get(idx);
            movie.getDetails();
        } 
    }
    
    /**
     * Returns the next movie of the movies returned by the API query.
     * Iterates through the movie list until the end, then starts back from the beginning.
     * @return
     *      the next movie, or null if the movie list is empty
     */
    public MovieNode getNextMovieDetails()  {
        if(! movies.isEmpty())   {
            currMovie = movies.get(currMovieIndex++);
            if(currMovieIndex > movies.size() - 1)  {
                currMovieIndex = 0;
            }
            currMovie.getDetails();
            return currMovie;
        }
        return null;
    }

    public void getByTitle(String string) {
        try {
            String title = string.replaceAll(" ", "+");
            String qStr = INCOMPLETE_MOVIE_DETAIL_URL + ".json?apikey=" + API_KEY + "&q=" + title + "&page_limit=1";
            String json = getJsonString(qStr);
            JSONTokener jsonTokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(jsonTokener);

            JSONArray resultArray = jsonObject.getJSONArray(MOVIES);
            for (int i = 0; i < resultArray.length(); i++) {               
                if(i + 1 >= limit)  {
                    break;
                }
                
                JSONObject result = resultArray.getJSONObject(i);
                MovieNode node = new MovieNode(result);
                movies.add(node);
            }

            if(! movies.isEmpty())  {
                currMovieIndex = 0;
            }
            
        } catch (JSONException e) {
            LOG.warn("JSONException: Can't retrieve message:" + e.getMessage());
        } catch (IOException e) {
            LOG.warn("IOException: Can't retrieve message:" + e.getMessage());
        }
    }
    
}
