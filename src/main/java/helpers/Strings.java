package helpers;

import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.message.Message;

public class Strings {


    private static final FormattedMessageFactory formattedMessageFactory = new FormattedMessageFactory();

    public static String f(String pattern, Object... args) {
        Message m = formattedMessageFactory.newMessage(pattern, args);
        return m.getFormattedMessage();
    }

}
