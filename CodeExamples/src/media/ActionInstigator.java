package media;

public class ActionInstigator {
    public enum ConversationAction { SwitchTopic, SwitchMovie, Ambiguous, None;  }
    
    String inputStr;
    ConversationAction conversationAction;

    public ActionInstigator(String input, ConversationAction theAction) {
        inputStr = input;
        conversationAction = theAction;
    }

}
