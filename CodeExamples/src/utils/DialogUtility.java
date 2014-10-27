package utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DialogUtility {

    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String EMPTY_STRING = "";
    public static final String UTF8_ENCODING = "UTF-8";
    
    private Log LOG = LogFactory.getLog(DialogUtility.class);

    public static List<String> COMMON_VERBS_1_TO_22 = ImmutableList.of(
    "ask", "be", "call", "come", "do", "find", "get", "give", "go", "have", "know", "let", "look", "make", "mean", "need", "put", "say", "take", "tell", "think", "want");
    
    /**
     * A list of common words which cause problems at different points in the process.
     */
    // FIXME: It might be more economical to create lists of problem parts of speech.
    public static List<String> PROBLEM_KEYWORDS = ImmutableList.of(
            "he", "her", "him", "i", "it", "me", "she", "them", "they", "us", "we", "you",
            "how", "what", "when", "where", "who", "whom", "why",
            "am", "are", "be", "do", "did", "does", "go", "goes", "had", "has", "have", "is", "made", "make", "makes", "was", "went", "were",
            "anyone", "anything", "nothing", "someone", "something",
            "this", "that", "these", "those",
            "at", "in", "on", "with");
    
//    @Autowired
//    private ElizaNLPService elizaNLPService;
//    
//    public ElizaNLPService getElizaNLPService()	{ return elizaNLPService; }
    
    /**
     * Get the original input string of this SRUAnalysis.
     * @param sru
     * @return
     */
//    public String sruAnalysisToString(SRUAnalysis sru)    {
//        String sruAnalysisString;
//        try{
//            sruAnalysisString = elizaNLPService.toSentenceString(sru.getDepTree(), true);
//        }   catch(Exception e) {
//            // See #6788
//            sruAnalysisString = sru.toStringRaw().trim();
//            LOG.info("elizaNLPService.toSentenceString() can't return original string. This string is returned by toStringRaw(): " + sruAnalysisString);
//        }
//        return sruAnalysisString;
//    }
//
//	public SRUAnalysis stringToSruAnalysis(String input) {
//		return elizaNLPService.parseSentence(input);
//	}
//
//    public List<SRUAnalysis> convertToSRUAnalysisList(List<String> inputStrs) {
//        List<SRUAnalysis> returnList = Lists.newArrayList();
//        for (String str : inputStrs) {
//            returnList.add(stringToSruAnalysis(str));
//        }
//        return returnList;
//    }

    /**
     * Trim the input string to the desired length if it is longer than that length.
     * @param in
     * @param intendedLen
     * @param fromLeft
     *      trim the string from the left if true, else from the right
     * @return
     */
    public String trimToNCharacters(String in, int intendedLen, boolean fromLeft)  {
        if(in.length() <= intendedLen)  {
            return in;
        }

        String returnString = "";

        if(fromLeft)    {
            returnString = in.substring(in.length() - intendedLen);
        }
        else    {
            returnString = in.substring(0, in.length() - intendedLen - 1);
        }

        return returnString;
    }

    /**
     * Extract the last windowSz lines from the dialog.
     * @param dialog
     * @param windowSize
     * @return
     */
//    public List<String> getConversationWindowAsString(DialogSentences dialog, int windowSize) {
//        List<String> chatList = Lists.newArrayList();
//
//        for(DialogLine line : dialog.getLines())  {
//            chatList.add(line.utterance);
//        }
//
//        int retListSize = windowSize < chatList.size() ? windowSize: chatList.size();
//        List<String> retList = Lists.newArrayList(chatList.subList(chatList.size() - retListSize, chatList.size()));
//
//        return retList;
//    }

    /**
     * Insert dummy speaker names at the start of each line of a dialog between two people.
     * @param lines
     * @return
     */
    public List<String> insertSpeakerNames(List<String> lines) {
        List<String> newLines = Lists.newArrayList();
        for(int ct = 0; ct < lines.size(); ct++)    {
            String name = String.valueOf(ct % 2);
            newLines.add(name + ": " + lines.get(ct));
        }
        return newLines;
    }

    /**
     * Return the contents of the given file as a string.
     * @param file
     * @return
     * @throws IOException
     */
    public String getFileAsString(File file) throws IOException  {
        String fileContents = FileUtils.readFileToString(file, UTF8_ENCODING);

        return fileContents;
    }

    /**
     * Randomly pick and return one element of the given list.
     * Returns IllegalArgumentException if the collection is null or empty.
     * @param coll
     * @return
     */
    public <T> T pickRandomListMember(List<T> coll) {
        Preconditions.checkArgument(coll != null && !coll.isEmpty(), "Input is null or empty.");
        return coll.get(RandomUtils.nextInt(coll.size()));
    }



}
