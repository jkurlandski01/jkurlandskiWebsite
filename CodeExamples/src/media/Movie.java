package media;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import media.MovieUtil.Genre;
import media.MovieUtil.Rating;

import com.google.common.collect.Lists;

public class Movie {
    String title = "";
    
    List<Genre> genres = Lists.newArrayList();
    
    Rating rating = Rating.Unknown;
    
    int runtime = 0;
    
    double criticScore = 0.0;
    double audienceScore = 0.0;
    String qualityFreeText = "";
    
    String synopsis = "";
    
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String summary) {
        this.synopsis = summary;
    }

    List<String> cast = Lists.newArrayList();
    
    List<String> directors = Lists.newArrayList();
    
    public Movie()  { }
    
    public Movie(String title, List<Genre> genres, Rating rating, int runtime, double criticScore, double audienceScore, List<String> cast, List<String> directors)  {
        this.title = title;
        this.genres.addAll(genres);
        this.rating = rating;
        this.runtime = runtime;
        this.criticScore = criticScore;
        this.audienceScore = audienceScore;
        this.cast.addAll(cast);
        this.directors.addAll(directors);
    }

    public Movie(String title)  {
        //this.title = title.toLowerCase();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String datum) {
        this.title = datum;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void addGenres(List<Genre> genres) {
        this.genres.addAll(genres);
    }
    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }
    /**
     * For each item in the input list, first convert it to a Genre enum type, then insert it into the genres object.
     * If the string can't be converted, we insert Genre.Unknown.
     * @param genreStrs
     *      list of genre strings to insert
     *      ArrayList instead of List to avoid the "erasure" conflict with addGenres(List<Genre> genres)
     */
    public void addGenres(ArrayList<String> genreStrs) {
        for(String str : genreStrs)    {
            Genre genre = null;
            try {
                genre = Genre.valueOf(str);
            } catch (IllegalArgumentException e) {
                // The string isn't in our Genre enum. No problem--we deal with this below.
            }
            if(genre == null) {
                genre = Genre.Unknown;
            }
            
            this.genres.add(genre);
        }
        
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
    public void setRating(String ratingStr) {
        Rating rating = null;
        try {
            rating = Rating.getByName(ratingStr);
        } catch (IllegalArgumentException e) {
            // The string isn't in our Rating enum. No problem--we deal with this below.
        }
        if(rating == null) {
            rating = Rating.Unknown;
        }
        
        this.rating = rating;
    }

    public String getRuntime() {
        return String.valueOf(runtime);
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public void setRuntime(String runtime) {
        try {
            Integer integer = new Integer(runtime);
            this.runtime = integer.intValue();
        }
        catch(NumberFormatException ex) {
            // Do nothing.
        }
        
    }

    public String getCriticScore() {
        return String.valueOf(criticScore);
    }

    public void setCriticScore(double criticScore) {
        this.criticScore = criticScore;
    }

    public String getAudienceScore() {
        return String.valueOf(audienceScore);
    }

    public void setAudienceScore(double audienceScore) {
        this.audienceScore = audienceScore;
    }

    public List<String> getCast() {
        return cast;
    }

    public void addCast(List<String> cast) {
        this.cast.addAll(cast);
    }
    public void addCastMember(String castMember)    {
        this.cast.add(castMember);
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void addDirectors(List<String> directors) {
        this.directors.addAll(directors);
    }
    
    public void addDirector(String director)    {
        this.directors.add(director);
    }

    public String paraphraseSummary() {
        StringBuilder paraphrase = new StringBuilder();
        
        List<String> actorCharacters = getActorPlaysCharacter();
        if(! actorCharacters.isEmpty()) {
            paraphrase.append(actorCharacters.get(0));
        }
        
        if(paraphrase.length() == 0)    {
            paraphrase.append(synopsis);
        }
        
        return paraphrase.toString();
    }

    /**
     * @param paraphrase
     */
    List<String> getActorPlaysCharacter( ) {
        List<String> actorCharacters = Lists.newArrayList();
        
        // Look for CapitalizedWords (CapitalizedWords) pattern, e.g. John Smith (Adam Bent)
        // FIXME: Doesn't work for unicode, as in "Chloë Grace Moretz". 
        String capitalizedWords = "[A-Z][A-Za-z'][A-Za-z']*( [A-Z][A-Za-z'][A-Za-z']*)?( [A-Z][A-Za-z'][A-Za-z']*)?";
        Pattern pattern = Pattern.compile(capitalizedWords + " \\("+ capitalizedWords);
        Matcher m = pattern.matcher(synopsis);
        while (m.find()) {
            String s = m.group(0);
            // s now contains CapitalizedWords (CapitalizedWords). Split it.
            String[] words = s.split("\\(");
            String character = words[0].trim();
            // For the end, split on any non-word character.
            words = words[1].split("[;:,)]");
            String actor = words[0].trim();
            
            String val = actor + " plays " + character + ".";
            actorCharacters.add(val);
        }
        
        // Look for a/an + word + word (CapitalizedWords), as in "an elderly gentleman (Art Carney)
        String nounPhraseRegex = " an?( [a-z][a-z]+)( [a-z][a-z]+)?";
        pattern = Pattern.compile(nounPhraseRegex + " \\("+ capitalizedWords);
        m = pattern.matcher(synopsis);
        while (m.find()) {
            String s = m.group(0);
            String[] words = s.split("\\(");
            String nounPhrase = words[0].trim();
            words = words[1].split("[;:,)]");
            String actor = words[0].trim();
            
            String val = actor + " plays " + nounPhrase + ".";
            actorCharacters.add(val);
        }
        
        return actorCharacters;
    }

    public void setQualityFreeText(String datum) {
        qualityFreeText = datum;
    }

    public Object getQualityFreeText() {
        return qualityFreeText;
    }
}
