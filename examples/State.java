package lexer;

import java.util.Arrays;
import java.util.List;

public enum State {

    // ---------------------------------------------------------------------------
    // State machine constants

    EXPECTING_TOKEN_STATE(1),
    READING_FUNCTION_STATE(2),
    READING_NUMBER_STATE(3),
    READING_STRING_STATE(4),
    DEFAULTS_STATE(5);

    private final int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // Names for state machine logging and error reporting

    public static List<String> getStateNames() {
        return Arrays.asList(
                "(NONE)",
                "expecting_token",
                "reading_function",
                "reading_number",
                "reading_string",
                "defaults");
    }

}
