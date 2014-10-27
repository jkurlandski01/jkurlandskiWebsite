package media;

import static org.junit.Assert.*;
import media.MovieUtil.*;
import media.ActionInstigator.ConversationAction;
import media.MovieConversation.MovieTopic;
import media.MovieConversation.ConversationState;
//import net.ipsoft.eliza.dm.test.DMFunctional;
//import net.ipsoft.eliza.sru.ss.SSScorerWS4J;

import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

//public class MovieConversationTest extends DMFunctional {
	public class MovieConversationTest {

//    @Autowired
//    private SSScorerWS4J semanticSimilarityScorer;
    
//    @Test
//    public void testUserAskingQuestions() {
//        Movie movie = new Movie("Toy Story 3", 
//                Lists.newArrayList(Genre.Animation, Genre.Comedy, Genre.SciFi_And_Fantasy),
//                Rating.G,
//                103,
//                0.99,
//                0.89,
//                Lists.newArrayList("Tom Hanks", "Tim Allen", "Joan Cusack", "Don Rickles"),
//                Lists.newArrayList("Lee Unkrich"));
//        
//        MovieConversation conversation = new MovieConversation(movie, semanticSimilarityScorer, ConversationState.UserAsk);
//        
//        String reply = conversation.respond("What's it rated?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("rated"));
//        assertTrue(reply + "doesn't contain: ", reply.contains(Rating.G.name()));
//        assertFalse(reply + "doesn't contain: ", reply.contains(Rating.PG.name()));
//        
//        reply = conversation.respond("Who stars in it?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("Tom Hanks"));
//        
//        reply = conversation.respond("Who directed it?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("Lee Unkrich"));
//        
//        reply = conversation.respond("What kind of movie is it?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("comedy"));
//        
//        reply = conversation.respond("How long is it?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("103"));
//        
//        reply = conversation.respond("Is it good?");
//        assertTrue(reply + "doesn't contain: ", reply.contains(".99"));
//        
//        reply = conversation.respond("What's its title again?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("Toy Story 3"));
//        
//        reply = conversation.respond("What happens?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("Sorry, but I can't remember what it's about."));
//        
//        // Now try setting the synopsis.
//        movie.setSynopsis("\"Toy Story 3\" welcomes Woody, Buzz and the whole gang back to the big screen as Andy prepares to depart for college and his loyal toys find themselves in... daycare! These untamed tots with their sticky little fingers do not play nice, so it's all for one and one for all as plans for the great escape get underway. A few new faces-some plastic, some plush-join the adventure, including iconic swinging bachelor and Barbie's counterpart Ken, a thespian hedgehog named Mr. Pricklepants and a pink, strawberry-scented teddy bear called Lots-o'-Huggin' Bear.");
//        reply = conversation.respond("What's it about?");
//        assertTrue(reply + "doesn't contain: ", reply.contains("welcomes Woody, Buzz and the whole gang back"));
//    }
//    
//    @Test
//    public void testSetMovieDatumForMovieTopic()    {
//        Movie movie = new Movie();
//        MovieConversation conversation = new MovieConversation(movie, semanticSimilarityScorer, ConversationState.AmeliaAsk);
//        
//        assertEquals("", movie.getTitle());
//        conversation.setMovieDatumForMovieTopic("Untitled", MovieTopic.Title);
//        assertEquals("Untitled", movie.getTitle());
//        
//        assertEquals("0", movie.getRuntime());
//        conversation.setMovieDatumForMovieTopic("100", MovieTopic.Runtime);
//        assertEquals("100", movie.getRuntime());
//        
//        assertEquals(Rating.Unknown, movie.getRating());
//        conversation.setMovieDatumForMovieTopic("NC-17", MovieTopic.Rating);
//        assertEquals(Rating.NC_17, movie.getRating());
//        
//        assertEquals("", movie.getQualityFreeText());
//        conversation.setMovieDatumForMovieTopic("It stinks!", MovieTopic.Quality);
//        assertEquals("It stinks!", movie.getQualityFreeText());
//        
//        assertEquals("", movie.getSynopsis());
//        conversation.setMovieDatumForMovieTopic("Boy meets girl.", MovieTopic.Synopsis);
//        assertEquals("Boy meets girl.", movie.getSynopsis());
//        
//        assertEquals(0, movie.getGenres().size());
//        conversation.setMovieDatumForMovieTopic("Still_Life", MovieTopic.Genre);
//        assertEquals(Genre.Unknown, movie.getGenres().get(0));
//        conversation.setMovieDatumForMovieTopic("Comedy", MovieTopic.Genre);
//        assertEquals(2, movie.getGenres().size());
//        assertEquals(Genre.Unknown, movie.getGenres().get(0));
//        assertEquals(Genre.Comedy, movie.getGenres().get(1));
//        
//        assertEquals(0, movie.getDirectors().size());
//        conversation.setMovieDatumForMovieTopic("Big Foot.", MovieTopic.Director);
//        assertEquals("Big Foot", movie.getDirectors().get(0));
//        
//        assertEquals(0, movie.getCast().size());
//        conversation.setMovieDatumForMovieTopic("Nessie.", MovieTopic.Cast);
//        assertEquals("Nessie", movie.getCast().get(0));
//    }
//    
//    @Test
//    public void testGetActionForInput() {
//        Movie movie = new Movie();
//        MovieConversation conversation = new MovieConversation(movie, semanticSimilarityScorer, ConversationState.AmeliaAsk);
//        
//        ConversationAction action = conversation.getActionForInput("I want to talk about a different movie.", false);
//        assertEquals(ConversationAction.SwitchMovie, action);
//        
//        action = conversation.getActionForInput("I want to talk about a different topic.", false);
//        assertEquals(ConversationAction.SwitchTopic, action);
//        
//        action = conversation.getActionForInput("I want to talk about something different.", false);
//        assertEquals(ConversationAction.Ambiguous, action);
//        
//        action = conversation.getActionForInput("a different movie.", true);
//        assertEquals(ConversationAction.SwitchMovie, action);
//        
//        action = conversation.getActionForInput("a different topic.", true);
//        assertEquals(ConversationAction.SwitchTopic, action);
//        
//        action = conversation.getActionForInput("something different.", true);
//        assertEquals(ConversationAction.Ambiguous, action);
//    }

}
