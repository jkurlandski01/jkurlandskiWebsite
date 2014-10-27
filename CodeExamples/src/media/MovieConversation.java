package media;

import com.google.common.collect.Lists;
import media.ActionInstigator.ConversationAction;
import media.MovieUtil.Genre;
import media.MovieUtil.Rating;
import media.RottenTomatoesQuery.MovieNode;
//import net.ipsoft.eliza.sru.ss.SSScorerWS4J;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MovieConversation {
    public enum ConversationState {AmeliaAsk, UserAsk, AmbiguousActionChangeState}
    
    public enum MovieTopic { Cast, Director, Genre, Synopsis, Quality, Rating, Runtime, Title, Unknown }
    
    static private List<ActionInstigator> actionInstigatorList = Lists.newArrayList();
    static  {
        actionInstigatorList.add(new ActionInstigator("Let's talk about a different movie.", ConversationAction.SwitchMovie));
        actionInstigatorList.add(new ActionInstigator("I want to talk about a different movie.", ConversationAction.SwitchMovie));
        actionInstigatorList.add(new ActionInstigator("different movie", ConversationAction.SwitchMovie));
        actionInstigatorList.add(new ActionInstigator("Let's talk about a different topic.", ConversationAction.SwitchTopic));
        actionInstigatorList.add(new ActionInstigator("I want to talk about a different topic.", ConversationAction.SwitchTopic));
        actionInstigatorList.add(new ActionInstigator("different topic", ConversationAction.SwitchTopic));
        actionInstigatorList.add(new ActionInstigator("Let's talk about something different.", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("I want to talk about something different.", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("Let's talk about something else.", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("I want to talk about something else.", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("Let's talk about something new.", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("I want to talk about something new.", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("something different", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("something else", ConversationAction.Ambiguous));
        actionInstigatorList.add(new ActionInstigator("something new", ConversationAction.Ambiguous));
    }
    
    public static class MovieQuery {
        String queryStr;
        MovieTopic topic;
        boolean useForAmeliaQuestion;
        
        public MovieQuery(String theQueryStr, MovieTopic theTopic, boolean theSoundsNatural)    {
            queryStr = theQueryStr;
            topic = theTopic;
            useForAmeliaQuestion = theSoundsNatural;
        }
    }
    
    static private List<MovieQuery> movieQueryList = Lists.newArrayList();
    static  {
        movieQueryList.add(new MovieQuery("What's its title?", MovieTopic.Title, true));
        movieQueryList.add(new MovieQuery("What's the title?", MovieTopic.Title, true));
        movieQueryList.add(new MovieQuery("What is it called?", MovieTopic.Title, true));
        movieQueryList.add(new MovieQuery("What's it called again?", MovieTopic.Title, true));
        
        movieQueryList.add(new MovieQuery("What kind of movie is it?", MovieTopic.Genre, true));
        movieQueryList.add(new MovieQuery("What's its genre?", MovieTopic.Genre, false));
        
        movieQueryList.add(new MovieQuery("What's its rating?", MovieTopic.Rating, false));
        movieQueryList.add(new MovieQuery("What's it rated?", MovieTopic.Rating, true));
        //movieQueryList.add(new MovieQuery("How is it rated?", MovieTopic.Rating, false));
        
        movieQueryList.add(new MovieQuery("What's the runtime?", MovieTopic.Runtime, false));
        movieQueryList.add(new MovieQuery("What's its runtime?", MovieTopic.Runtime, false));
        movieQueryList.add(new MovieQuery("What's its length?", MovieTopic.Runtime, false));
        movieQueryList.add(new MovieQuery("How long is it?", MovieTopic.Runtime, true));
        
        movieQueryList.add(new MovieQuery("Who's in it?", MovieTopic.Cast, true));
        movieQueryList.add(new MovieQuery("Who stars in it?", MovieTopic.Cast, true));
        
        movieQueryList.add(new MovieQuery("Who directed it?", MovieTopic.Director, true));
        
        movieQueryList.add(new MovieQuery("How good is it?", MovieTopic.Quality, true));
        movieQueryList.add(new MovieQuery("Is it any good?", MovieTopic.Quality, true));
        movieQueryList.add(new MovieQuery("Do people like it?", MovieTopic.Quality, false));
        movieQueryList.add(new MovieQuery("What kind of reviews is it getting?", MovieTopic.Quality, false));
        
        movieQueryList.add(new MovieQuery("What's it about?", MovieTopic.Synopsis, true));
        movieQueryList.add(new MovieQuery("What happens in it?", MovieTopic.Synopsis, true));
        movieQueryList.add(new MovieQuery("What's its synopsis?", MovieTopic.Synopsis, false));
        movieQueryList.add(new MovieQuery("Tell me what it's about.", MovieTopic.Synopsis, true));
    }
    
    private static List<String> genericReplies = Lists.newArrayList();
    static {
        genericReplies.add("You leave me speechless.");
        genericReplies.add("Has anyone ever told you that you're a very interesting person?");
        genericReplies.add("Enough about me. Let's talk about you.");
    }
    
    /**
     * Holds the topics asked about or discussed.
     */
    private List<MovieTopic> topicHistory = Lists.newArrayList();
    
    private List<MovieTopic> topicsNotDiscussed = Lists.newArrayList(MovieTopic.values());
    
    RottenTomatoesQuery query;
    
    // FIXME: change to this when you start comparing SRUAnalysis objects   private SSScorer ssScorer;
//    private SSScorerWS4J ssScorer;
    
    private ConversationState conversationState;

    private Movie movie;
    public Movie getMovie() { return movie; }
    
//    public MovieConversation(Movie movie, SSScorerWS4J ssScorer, ConversationState state)   {
//        this.movie = movie;
//        this.ssScorer = ssScorer;
//        this.conversationState = state;
//        
//        setTopicsNotDiscussed(System.nanoTime());
//    }
    
//    public MovieConversation(SSScorerWS4J ssScorer, ConversationState state) {
//        this.ssScorer = ssScorer;
//        this.conversationState = state;
//
//        // Query for opening movies, and pick the first.
//        query = new RottenTomatoesQuery();
//        query.getOpeningMovies();
//        //query.getDetailsForFirstN(1);
//        MovieNode movieNode = query.getNextMovieDetails();
//        
//        loadMovieNodeData(movieNode);
//    }

    /**
     * @param movieNode
     */
    private void loadMovieNodeData(MovieNode movieNode) {
        Movie tempMovie = new Movie(movieNode.title);
        tempMovie.setAudienceScore(movieNode.audienceScore);
        tempMovie.setCriticScore(movieNode.criticScore);
        tempMovie.setRating(Rating.getByName(movieNode.rating));
        tempMovie.setRuntime(movieNode.runtime);
        tempMovie.addCast(movieNode.cast);
        tempMovie.setSynopsis(movieNode.synopsis);
        tempMovie.addDirectors(movieNode.directors);
        tempMovie.addGenres(Lists.newArrayList(movieNode.genres));
        this.movie = tempMovie;
    }

//    private void setTopicsNotDiscussed(long seed) {
//        topicsNotDiscussed.remove(MovieTopic.Unknown);
//        topicsNotDiscussed.remove(MovieTopic.Title);
//        Collections.shuffle(topicsNotDiscussed, new Random(seed));
//        topicsNotDiscussed.add(MovieTopic.Title);
//    }
//
//    public String respond(String input)     {
//        String response = initializeReply();
//        
//        if(conversationState.equals(ConversationState.UserAsk))    {
//            MovieTopic topic = getMovieTopicForInput(input);
//            if(! topic.equals(MovieTopic.Unknown)) {
//                topicHistory.add(topic);
//                response = getResponseForMovieTopic(topic);
//            }
//            else    {
//                // See if user wants to take a ConversationAction.
//                ConversationAction action = getActionForInput(input, false);
//                response = takeAppropriateAction(action);
//            }
//        }
//        else if(conversationState.equals(ConversationState.AmeliaAsk))    {
//            // See if user wants to take a ConversationAction.
//            ConversationAction action = getActionForInput(input, true);
//            if(! action.equals(ConversationAction.None))   {
//                response = takeAppropriateAction(action);
//            }
//            else    {
//                // Set movie with user response.
//                if(! topicsNotDiscussed.isEmpty())  {
//                    MovieTopic topic = topicsNotDiscussed.remove(0);
//                    if(! topic.equals(MovieTopic.Unknown)) {
//                        setMovieDatumForMovieTopic(input, topic);
//                        // Ask next question.
//                        response = askQuestion();
//                    }
//                }
//                else    {
//                    // Amelia has asked all the questions she can about the user's movie.
//                    response = switchMovie();
//                }
//            }
//        }
//        else if(conversationState.equals(ConversationState.AmbiguousActionChangeState))   {
//            ConversationAction action = getActionForInput(input, false);
//            if(action.equals(ConversationAction.Ambiguous))   {
//                // If the user has replied ambiguously a second time, assume s/he wants a complete change of topic.
//                action = ConversationAction.SwitchTopic;
//            }
//            response = takeAppropriateAction(action);
//        }
//        
//        return response;
//    }

    private String takeAppropriateAction(ConversationAction action) {
        String reply = initializeReply();
        
        switch (action) {
        case SwitchTopic: reply = switchTopic();
        break;

        case SwitchMovie: reply = switchMovie();
        break;

        case Ambiguous: 
        default: reply = handleAmbiguousAction();
        break;
        }
        
        return reply;
    }

    private String handleAmbiguousAction() {
        this.conversationState = ConversationState.AmbiguousActionChangeState;
        return "Do you want to talk about a different movie, or something else altogether?";
    }

    private String switchMovie() {
        String reply = initializeReply();
        
        if(query != null)   {
            // Simply ask query to give us another movie.
            MovieNode movieNode = query.getNextMovieDetails();
            loadMovieNodeData(movieNode);
            reply = proposeMovieAsTopic();
            this.conversationState = ConversationState.UserAsk;
        }
        else    {
            // Create a new query.
            query = new RottenTomatoesQuery();
            query.getOpeningMovies();
            MovieNode movieNode = query.getNextMovieDetails();            
            loadMovieNodeData(movieNode);
            reply = proposeMovieAsTopic();
            this.conversationState = ConversationState.UserAsk;
        }

        return reply;
    }

    private String switchTopic() {
        return "For the time being, you have to say, 'Let's not talk about movies.'";
    }

//    ConversationAction getActionForInput(String input, boolean strict) {
//        double minimumScore = 0.4;
//        if(strict)  {
//            minimumScore = 0.7;
//        }
//        
//        ConversationAction conversationAction = ConversationAction.None;
//        
//        ActionInstigator bestInstigator = null;
//        double topScore = 0.0;
//        for(ActionInstigator instigator : actionInstigatorList)   {
//            Double score = ssScorer.getSSScore(input, instigator.inputStr);
//            // FIXME: do it on trees first; then string if > 1
//
//            if(score > topScore)    {
//                topScore = score;
//                bestInstigator = instigator;
//            }
//
//        }
//
//        if(topScore >= minimumScore)     {
//            conversationAction = bestInstigator.conversationAction;
//        }
//        else    {
//            conversationAction = ConversationAction.None;
//        }
//        return conversationAction;
//    }

    public String respond() {
        String reply = initializeReply();
        
        if(conversationState.equals(ConversationState.AmeliaAsk))  {
            reply = askQuestion();
        }
        
        return reply;
    }
    
    /**
     * Set this object's movie property correctly, given a MovieTopic and the value.
     * @param datum
     * @param topic
     */
    void setMovieDatumForMovieTopic(String input, MovieTopic topic)   {
        String datum = input.trim();
        
        switch (topic) {
        case Cast: movie.addCastMember(clean(datum));
        break;

        case Director: movie.addDirector(clean(datum));
        break;

        case Genre: movie.addGenres(Lists.newArrayList(clean(datum)));
        break;

        case Synopsis: movie.setSynopsis(datum);
        break;

        case Quality: movie.setQualityFreeText(datum);
        break;

        case Rating: movie.setRating(clean(datum));
        break;

        case Runtime: movie.setRuntime(clean(datum));
        break;

        case Title: movie.setTitle(clean(datum));
        break;

        default:
        break;
        }
    }
    
    private String clean(String in) {
        String out = in.replaceAll("\\.", "");
        return out;
    }
    
    private String getResponseForMovieTopic(MovieTopic topic) {
        String retString = "";
        
        switch (topic) {
        case Cast: retString = answerCast();
        break;

        case Director: retString = answerDirector();
        break;

        case Genre: retString = answerGenre();
        break;

        case Synopsis: retString = answerPlot();
        break;

        case Quality: retString = answerQuality();
        break;

        case Rating: retString = answerRating();
        break;

        case Runtime: retString = answerRuntime();
        break;

        case Title: retString = answerTitle();
        break;

        case Unknown: 
        default: retString = answerUnknown();
        break;
        }
        
        return retString;
    }

    private String answerPlot() {
        String str = "";
        if(movie.getSynopsis().isEmpty())  {
            str = "Sorry, but I can't remember what it's about.";
        }
        else {
            str = movie.paraphraseSummary();
        }
        return str;
    }

    private String answerTitle() {
        String str = movie.getTitle();
        if(str.isEmpty())  {
            str = "Sorry, but I can't remember what it's called.";
        }
        return str;
    }

    private String answerUnknown() {
        return "I'm not sure--the review didn't say.";
    }

    private String answerRuntime() {
        String str = movie.getRuntime();
        if(str.isEmpty() || str.equals(0))  {
            str = "Sorry, but I can't remember what it's called.";
        }
        return "It's about " + str + " minutes long.";
    }

    private String answerRating() {
        Rating rating = movie.getRating();
        String str = "";
        if(rating.equals(Rating.Unknown))  {
            str = "Sorry, but I can't remember what it's rated.";
        }
        else if(rating.equals(Rating.NR))  {
            str = "For some reason it's unrated. Maybe it's a foreign film.";
        }
        else    {
            str = "It's rated " + rating.toString() + ".";
        }
        return str;
    }

    private String answerQuality() {
        String score = movie.getCriticScore();
        if(score.isEmpty() || score.startsWith("-1")) {
            score = movie.getAudienceScore();
        }
        
        if(score.isEmpty()) {
            return "I'm not sure how good it is.";
        }
        return "Rotten Tomatoes gave it a " + score + " out of a hundred.";
    }

    private String answerGenre() {
        if(movie.getGenres().isEmpty())   {
            return "I'm not sure what kind of movie it is.";
        }

        Genre genre = movie.getGenres().get(0);
        
        // Some of the genres are not how a person would initially describe a movie. If one of the genres is comedy, drama, or scifi--use that.
        for(Genre newGenre : movie.getGenres())     {
            if(newGenre.equals(Genre.Comedy) || newGenre.equals(Genre.Drama) || newGenre.equals(Genre.SciFi_And_Fantasy))   {
                genre = newGenre;
                break;
            }
        }
        
        if(movie.getGenres().get(0).equals(Genre.Unknown))  {
            return "I'm not sure what kind of movie it is.";
        }

        return "It's " + genre.toString() + ".";
    }

    private String answerDirector() {
        String str = "";
        if(movie.getDirectors().isEmpty())  {
            str = "Sorry, but I don't remember who directed it.";
        }
        else    {
            str = "It's directed by " + movie.getDirectors().get(0) + ".";
        }
        return str;
    }

    private String answerCast() {
        String str = "";
        if(movie.getCast().isEmpty())  {
            str = "Sorry, but I don't remember who's in it.";
        }
        else    {
            str = "It stars " + movie.getCast().get(0) + ".";
        }
        return str;
    }

//    private MovieTopic getMovieTopicForInput(String input) {
//        MovieTopic topic = MovieTopic.Unknown;
//
//        if(conversationState.equals(ConversationState.UserAsk))    {       
//            topic = readInputForUserAsk(input);
//        }
//        
//        return topic;
//    }

    /**
     * @param input
     * @param topic
     * @return
     */
//    private MovieTopic readInputForUserAsk(String input) {
//        MovieTopic topic = MovieTopic.Unknown;
//        
//        MovieQuery bestQuery = null;
//        double topScore = 0.0;
//        for(MovieQuery query : movieQueryList)   {
//            Double score = ssScorer.getSSScore(input, query.queryStr);
//            // FIXME: do it on trees first; then string if > 1
//
//            if(score > topScore)    {
//                topScore = score;
//                bestQuery = query;
//            }
//
//        }
//
//        if(topScore >= 0.4)     {
//            topic = bestQuery.topic;
//        }
//        return topic;
//    }
    
    
    /**
     * Ask the user a question about the movie being discussed.
     * @return
     *  Returns a question on a topic that hasn't been discussed, or some appropriate comment if all topics have been covered.
     */
    private String askQuestion() {
        String reply = "";
        
        // Shuffle the movie query list to simulate randomized behavior.
        List<MovieQuery> queryList = Lists.newArrayList(movieQueryList);
        Collections.shuffle(queryList);
        
        if(! topicsNotDiscussed.isEmpty())  {
            MovieTopic nextTopic = topicsNotDiscussed.get(0);
            if(nextTopic.equals(MovieTopic.Title))  {
                reply = "What was its name again?";
            }
            else    {
                for(MovieQuery query : queryList) {
                    if(query.topic.equals(nextTopic) && query.useForAmeliaQuestion)  {
                        reply = query.queryStr;
                    }
                }
            }
        }
        else    {
            reply = "That's interesting. Maybe I'll look for a review of the movie somewhere.";
        }
        
        return reply;
    }

    public String proposeMovieAsTopic() {
        String reply = initializeReply();
        if(movie != null && ! movie.getTitle().isEmpty())   {
            reply = "I read a review about a movie called " + movie.getTitle() + ".";
        }
        return reply;
    }

    private String initializeReply() {
        return MovieUtil.pickRandomListMember(genericReplies);
    }
}
