package lexer;

import java.util.Arrays;
import java.util.List;

public enum Event {

    NULL_EVENT(0),
    LETTER_EVENT(1),
    HYPHEN_EVENT(2),
    PLUS_EVENT(3),
    PERIOD_EVENT(4),
    DIGIT_EVENT(5),
    OPEN_QUOTE_EVENT(6),
    WHITESPACE_EVENT(7),
    NEWLINE_EVENT(8),
    OPEN_LIST_EVENT(9),
    CLOSE_LIST_EVENT(10),
    FINISHED_EVENT(11),
    SLASH_EVENT(12),
    UNDERSCORE_EVENT(13),
    COLON_EVENT(14),
    COMMA_EVENT(15),
    ASTERISK_EVENT(16),
    CARET_EVENT(17),
    PERCENT_EVENT(18),
    CLOSE_QUOTE_EVENT(19),
    OTHER_EVENT(20);

    private final int value;

    private Event(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<String> getEventNames() {
        return Arrays.asList(
                "(NONE)",
                "letter",
                "hyphen",
                "plus",
                "period",
                "digit",
                "open_quote",
                "whitespace",
                "newline",
                "open_list",
                "close_list",
                "finished",
                "slash",
                "underscore",
                "colon",
                "comma",
                "asterisk",
                "caret",
                "percent",
                "close_quote",
                "other");
    }

}
