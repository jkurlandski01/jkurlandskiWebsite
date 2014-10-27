package media;

import static org.junit.Assert.*;
import media.RottenTomatoesQuery.MovieNode;

import org.junit.*;

public class RottenTomatoesQueryTest {
    
    private class RottenTomatoesQueryMock extends RottenTomatoesQuery   {
        private class MovieNodeMock extends MovieNode   {
            @Override
            public void getDetails() {
                // Do nothing.
            }
        }
        
        @Override
        public void getOpeningMovies()  {
            MovieNodeMock node1 = new MovieNodeMock();
            node1.title = "Node 1";
            node1.id = "1";
            movies.add(node1);
            MovieNodeMock node2 = new MovieNodeMock();
            node2.title = "Node 2";
            node2.id = "2";
            movies.add(node2);
            MovieNodeMock node3 = new MovieNodeMock();
            node3.title = "Node 3";
            node3.id = "3";
            movies.add(node3);
            currMovieIndex = 0;
        }
    }

    /**
     * Test all fields for a single movie whose Rotten Tomatoes query is known to have all the essential fields.
     * Then 
     */
    @Test
    public void testMovieSilverLiningsPlaybook() {
        RottenTomatoesQuery query = new RottenTomatoesQuery();
        query.getByTitle("Silver Linings Playbook");
        assertTrue(query.getMovies().size() > 0);
        
        MovieNode movieNode = query.getMovies().get(0);
        // If the value is not going to change, test on that. Otherwise, make sure that the object doesn't have the initialized values.
        assertEquals("Silver Linings Playbook", movieNode.title);
        assertNotEquals("", movieNode.id);
        assertEquals("R", movieNode.rating);
        assertNotEquals(0, movieNode.runtime);
        assertNotEquals(0.0, movieNode.criticScore);
        assertNotEquals(0.0, movieNode.audienceScore);
        assertNotEquals("", movieNode.synopsis);
        assertNotEquals(0, movieNode.cast.size());
        assertTrue(movieNode.cast.contains("Jennifer Lawrence"));
        // Verify that genres and directors are empty--for now.
        assertEquals(0, movieNode.genres.size());
        assertEquals(0, movieNode.directors.size());
        
        // Now update the movie, and make sure it has the remaining info.
        movieNode = query.getNextMovieDetails();
        assertNotEquals(0, movieNode.genres.size());
        assertTrue(movieNode.genres.contains("Comedy"));
        assertNotEquals(0, movieNode.directors.size());
        assertTrue(movieNode.directors.contains("David O. Russell"));
    }

    /**
     * Test the opening movies functionality. The returned info does not contain all MovieNode data; but those tested
     * here seem to be consistently returned for all movies.
     */
    @Test
    public void testOpeningMovies() {
        RottenTomatoesQuery query = new RottenTomatoesQuery();
        query.getOpeningMovies();
        assertTrue(query.getMovies().size() > 0);
        
        MovieNode movieNode = query.getMovies().get(0);
        // Make sure that the object doesn't have the initialized values.
        assertNotEquals("", movieNode.title);
        assertNotEquals(movieNode.title + ": ", "", movieNode.id);
        assertNotEquals(movieNode.title + ": ", "", movieNode.rating);
        // This isn't always known or available: assertNotEquals(movieNode.title + ": ", 0, movieNode.cast.size());
        assertNotEquals(movieNode.title + ": ", "", movieNode.synopsis);
        // This isn't always known or available: assertNotEquals(movieNode.title + ": ", 0.0, movieNode.criticScore);
        // This isn't always known or available: assertNotEquals(movieNode.title + ": ", 0.0, movieNode.audienceScore);
        // This isn't always known or available: assertNotEquals(movieNode.title + ": ", 0, movieNode.runtime);
    }

    @Test
    public void testOpeningMoviesLimit0() {
        RottenTomatoesQuery query = new RottenTomatoesQuery(0);
        query.getOpeningMovies();
        assertEquals(0, query.getMovies().size());
    }

    @Test
    public void testGetNextMovieDetails() {
        RottenTomatoesQueryMock query = new RottenTomatoesQueryMock();
        query.getOpeningMovies();

        MovieNode node1 = query.getNextMovieDetails();
        MovieNode nodeLast = query.getNextMovieDetails();
        assertNotEquals(node1.title, nodeLast.title);
        assertNotEquals(node1.id, nodeLast.id);

        // Verify that getMovieDetails() can iterate through the list multiple times by calling the method > list.size() times.
        for(int idx = query.getCurrMovieIndex(); idx < query.getMovies().size() + 1; idx++)     {
            System.out.println(query.getCurrMovieIndex());
            MovieNode nodeCurr = query.getNextMovieDetails();
            assertNotEquals(nodeLast.title, nodeCurr.title);
            assertNotEquals(nodeLast.id, nodeCurr.id);
            nodeLast = nodeCurr;
        }

        // Now make sure we have looped all the way through.
        System.out.println(query.getCurrMovieIndex());
        assertEquals(node1.title, nodeLast.title);
        assertEquals(node1.id, nodeLast.id);
    }
}
