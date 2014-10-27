package media;

import static org.junit.Assert.*;

import java.util.List;

import media.MovieUtil.*;

import org.junit.Test;

import com.google.common.collect.Lists;

public class MovieTest {

    @Test
    public void testParaphrase1() {
        Movie movie = new Movie("The Hundred-Foot Journey", 
                Lists.newArrayList(Genre.Drama),
                Rating.G,
                103,
                0.99,
                0.89,
                Lists.newArrayList("Helen Mirren", "Manish Dayal"),
                Lists.newArrayList("Lasse Hallstrom"));
        movie.setSynopsis("In \"The Hundred-Foot Journey,\" Hassan Kadam (Manish Dayal) is a culinary ingénue with the gastronomic equivalent of perfect pitch.");
        
        String actual = movie.getSynopsis();
        assertEquals("In \"The Hundred-Foot Journey,\" Hassan Kadam (Manish Dayal) is a culinary ingénue with the gastronomic equivalent of perfect pitch.", actual);
        
        actual = movie.paraphraseSummary();
        assertEquals("Manish Dayal plays Hassan Kadam.", actual);
    }

    @Test
    public void testParaphrase2() {
        Movie movie = new Movie();
        
        movie.setSynopsis("The haunting story of THE GIVER centers on Jonas (Brenton Thwaites), a young man who lives in a seemingly ideal, if colorless, world of conformity and contentment.");
        String actual = movie.paraphraseSummary();
        assertEquals("Brenton Thwaites plays Jonas.", actual);
        
        movie.setSynopsis("In THE EXPENDABLES 3, Barney (Stallone), Christmas (Statham) and the rest of the team come face-to-face with Conrad Stonebanks (Gibson), who years ago co-founded The Expendables with Barney.");
        actual = movie.paraphraseSummary();
        assertEquals("Stallone plays Barney.", actual);
        
        movie.setSynopsis("19-year-old Eric (Jack O'Connell, star of the upcoming UNBROKEN) is arrogant and ultra-violent.");
        actual = movie.paraphraseSummary();
        assertEquals("Jack O'Connell plays Eric.", actual);
    }

    @Test
    public void testParaphrase3() {
        Movie movie = new Movie();
        
        movie.setSynopsis("More than two decades after catapulting to stardom with The Princess Bride, an aging actress (Robin Wright, playing a version of herself) decides to take her final job: preserving her digital likeness for a future Hollywood.");
        String actual = movie.paraphraseSummary();
        assertEquals("Robin Wright plays an aging actress.", actual);
        
        // FIXME: We don't do this yet.
//        movie.setSummary("He prematurely transferred to the same adult prison facility as his estranged father (Ben Mendelsohn, THE DARK KNIGHT RISES).");
//        actual = movie.paraphraseSummary();
//        assertEquals("Ben Mendelsohn plays his estranged father.", actual);
    }

    @Test
    public void testParaphraseExceptions() {
        Movie movie = new Movie();
        
        movie.setSynopsis("Mia Hall (Chloë Grace Moretz) thought the hardest decision she would ever face would be whether to pursue her musical dreams at Juilliard or follow a different path to be with the love of her life, Adam (Jamie Blackley).");
        String actual = movie.paraphraseSummary();
        assertEquals("Chlo plays Mia Hall.", actual);
        
        movie.setSynopsis("This sentence has no match.");
        actual = movie.paraphraseSummary();
        assertEquals("This sentence has no match.", actual);
    }

    @Test
    public void testGetActorPlaysCharacter1() {
        Movie movie = new Movie();
        
        movie.setSynopsis("In THE EXPENDABLES 3, Barney (Stallone), Christmas (Statham) and the rest of the team come face-to-face with Conrad Stonebanks (Gibson), who years ago co-founded The Expendables with Barney.");
        List<String> actual = movie.getActorPlaysCharacter();
        
        assertEquals(3, actual.size());
        String actorCharacter = actual.get(0);
        assertEquals("Stallone plays Barney.", actorCharacter);
        actorCharacter = actual.get(1);
        assertEquals("Statham plays Christmas.", actorCharacter);
        actorCharacter = actual.get(2);
        assertEquals("Gibson plays Conrad Stonebanks.", actorCharacter);
    }

    @Test
    public void testGetActorPlaysCharacter2() {
        Movie movie = new Movie();
        
        movie.setSynopsis("19-year-old Eric (Jack O'Connell, star of the upcoming UNBROKEN), arrogant and ultra-violent, is prematurely transferred to the same adult prison facility as an estranged father (Ben Mendelsohn, THE DARK KNIGHT RISES).");
        List<String> actual = movie.getActorPlaysCharacter();
        
        assertEquals(2, actual.size());
        String actorCharacter = actual.get(0);
        assertEquals("Jack O'Connell plays Eric.", actorCharacter);
        actorCharacter = actual.get(1);
        assertEquals("Ben Mendelsohn plays an estranged father.", actorCharacter);
    }

}
