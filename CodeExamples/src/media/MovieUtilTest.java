package media;

import static org.junit.Assert.*;

import media.MovieUtil.Genre;
import media.MovieUtil.Rating;

import org.junit.Test;

public class MovieUtilTest {

    @Test
    public void testRatings() {
        Rating rating = Rating.getByName("PG-13");        
        assertEquals("PG_13", rating.name());
        assertEquals("PG-13", rating.toString());
        
        rating = Rating.getByName("NC-17");        
        assertEquals("NC_17", rating.name());
        assertEquals("NC-17", rating.toString());
        
        rating = Rating.getByName("Unrated");        
        assertEquals("NR", rating.name());
        assertEquals("NR", rating.toString());

    }
    
    @Test
    public void testAddGenresRottenTomatoes() {
        Genre genre = Genre.getByName("Action & Adventure");
        assertEquals("an action movie", genre.toString());
        
        genre = Genre.getByName("Animation");
        assertEquals("an animated movie", genre.toString());
        
        genre = Genre.getByName("Art House & International");
        assertEquals("an art or international movie", genre.toString());
        
        genre = Genre.getByName("Comedy");
        assertEquals("a comedy", genre.toString());
        
        genre = Genre.getByName("Classics");
        assertEquals("a classic", genre.toString());

        genre = Genre.getByName("Cult Movies");
        assertEquals("a cult classic", genre.toString());

        genre = Genre.getByName("Documentary");
        assertEquals("a documentary", genre.toString());

        genre = Genre.getByName("Drama");
        assertEquals("a drama", genre.toString());

        genre = Genre.getByName("Horror");
        assertEquals("a horror flick", genre.toString());

        genre = Genre.getByName("Kids & Family");
        assertEquals("a family movie", genre.toString());
        
        genre = Genre.getByName("Musical & Performing Arts");
        assertEquals("a musical", genre.toString());
        
        genre = Genre.getByName("Mystery & Suspense");
        assertEquals("a mystery-and-suspense movie", genre.toString());
        
        genre = Genre.getByName("Romance");
        assertEquals("a romance", genre.toString());
        
        genre = Genre.getByName("Science Fiction & Fantasy");
        assertEquals("a sci-fi/fantasy movie", genre.toString());
        
        genre = Genre.getByName("Special Interest");
        assertEquals("a special interest film", genre.toString());
        
        genre = Genre.getByName("Television");
        assertEquals("a made-for-television movie", genre.toString());
        
        genre = Genre.getByName("Thriller");
        assertEquals("a thriller", genre.toString());
        
        genre = Genre.getByName("Western");
        assertEquals("a western", genre.toString());
    }

    /**
     * These genres are on the Roger Ebert website but not on the Rotten Tomatoes website.
     */
    @Test
    public void testAddGenresRogerEbert() {
        Genre genre = Genre.getByName("Action");
        assertEquals("an action movie", genre.toString());
        
        genre = Genre.getByName("Adventure");
        assertEquals("an action movie", genre.toString());
        
        genre = Genre.getByName("Crime");
        assertEquals("a mystery-and-suspense movie", genre.toString());
        
        genre = Genre.getByName("Family");
        assertEquals("a family movie", genre.toString());
        
        genre = Genre.getByName("Fantasy");
        assertEquals("a sci-fi/fantasy movie", genre.toString());
        
        genre = Genre.getByName("Foreign");
        assertEquals("an art or international movie", genre.toString());
        
        genre = Genre.getByName("History");
        assertEquals("a history", genre.toString());
        
        genre = Genre.getByName("Indie");
        assertEquals("an art or international movie", genre.toString());
        
        genre = Genre.getByName("Music");
        assertEquals("a musical", genre.toString());
        
        genre = Genre.getByName("Musical");
        assertEquals("a musical", genre.toString());
        
        genre = Genre.getByName("Mystery");
        assertEquals("a mystery-and-suspense movie", genre.toString());
        
        genre = Genre.getByName("Science Fiction");
        assertEquals("a sci-fi/fantasy movie", genre.toString());
        
        genre = Genre.getByName("Sports Film");
        assertEquals("a sports movie", genre.toString());
        
        genre = Genre.getByName("Thriller");
        assertEquals("a thriller", genre.toString());
}



}
