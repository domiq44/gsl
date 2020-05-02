package lexer;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

/*  =========================================================================
    ZsLex                        - No title

    =========================================================================
*/
public class ZsLex {

	/*
	 * @header Description of class for man page.
	 *
	 * @discuss Detailed discussion of the class, if any.
	 *
	 * @end
	 */

	// Structure of our class

	private final ZsLexFsm fsm; // Our finite state machine
	private final Map<Character, Event> events = new HashMap<>(); // Map characters to events
	private String input; // Line of text we're parsing
	private int inputPtr; // Next character to process
	private Token type; // Token type
	private StringBuilder token; // Current token
	private char current; // Current character

	// ---------------------------------------------------------------------------
	// Create a new ZsLex.

	public ZsLex() {
		fsm = new ZsLexFsm(this);
		events.put((char) 0, Event.FINISHED_EVENT);
		for (int charNbr = 1; charNbr < 256; charNbr++) {
			events.put((char) charNbr, Event.OTHER_EVENT);
		}
		// There are two ways to do this; either we define character
		// classes that produce generic events depending on the current
		// state (e.g. hyphen_event in function names, or minus_event in
		// numbers), or else we define lower level events that the FSM
		// sorts out. I've chosen the second design so decisions stay in
		// the FSM.
		setEvents("ABCDEFGHIJKLMNOPQRSTUVWXYZ", Event.LETTER_EVENT);
		setEvents("abcdefghijklmnopqrstuvwxyz", Event.LETTER_EVENT);
		setEvents("0123456789", Event.DIGIT_EVENT);
		setEvents("-", Event.HYPHEN_EVENT);
		setEvents("+", Event.PLUS_EVENT);
		setEvents("/", Event.SLASH_EVENT);
		setEvents("_", Event.UNDERSCORE_EVENT);
		setEvents(".", Event.PERIOD_EVENT);
		setEvents(",", Event.COMMA_EVENT);
		setEvents(":", Event.COLON_EVENT);
		setEvents("*", Event.ASTERISK_EVENT);
		setEvents("^", Event.CARET_EVENT);
		setEvents("%", Event.PERCENT_EVENT);
		setEvents("<", Event.OPEN_QUOTE_EVENT);
		setEvents(">", Event.CLOSE_QUOTE_EVENT);
		setEvents("(", Event.OPEN_LIST_EVENT);
		setEvents(")", Event.CLOSE_LIST_EVENT);
		setEvents(" \t", Event.WHITESPACE_EVENT);
		setEvents("\n", Event.NEWLINE_EVENT);
	}

	private void setEvents(String chars, Event event) {
		for (final char c : chars.toCharArray()) {
			events.put(c, event);
		}
	}

	// ---------------------------------------------------------------------------
	// Enable verbose tracing of lexer

	public void verbose(boolean verbose) {
		fsm.setAnimate(verbose);
	}

	// ---------------------------------------------------------------------------
	// Return number of processing cycles used so far

	public int getCycles() {
		return fsm.getCycles();
	}

	// ---------------------------------------------------------------------------
	// Start parsing buffer, return type of first token

	public Token first(final String input) {
		this.input = input;
		inputPtr = 0;
		return next();
	}

	// ---------------------------------------------------------------------------
	// Continue parsing buffer, return type of next token

	public Token next() {
		parseNextCharacter();
		fsm.execute();
		return type;
	}

	// ---------------------------------------------------------------------------
	// Return actual token value, if any

	private String token() {
		return token.toString();
	}

	// ---------------------------------------------------------------------------
	// Selftest

	public void zsLexTest(boolean verbose) {
		System.out.print(" * ZsLex: ");
		if (verbose) {
			System.out.println();
		}

		// @selftest
		final ZsLex zsLex = new ZsLex();
		zsLex.verbose(verbose);

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("1234"));
		assertEquals(Token.NULL_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("1234 4567"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.STRING_TOKEN, zsLex.first("<Hello, World>"));
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.STRING_TOKEN, zsLex.first("<Hello,>\n<World>"));
		assertEquals(Token.STRING_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NULL_TOKEN, zsLex.first("<Here is a long string"));
		assertEquals(Token.STRING_TOKEN, zsLex.first(" which continues over two lines>"));
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.COMPOSE_TOKEN, zsLex.first("pi: ( 22/7 )"));
		assertEquals(Token.OPEN_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.CLOSE_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.COMPOSE_TOKEN, zsLex.first("twopi:( pi 2 times )"));
		assertEquals(Token.OPEN_TOKEN, zsLex.next());
		assertEquals(Token.FUNCTION_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.FUNCTION_TOKEN, zsLex.next());
		assertEquals(Token.CLOSE_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.FUNCTION_TOKEN, zsLex.first("something(22/7*2)"));
		assertEquals(Token.OPEN_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.CLOSE_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("1 +1 -1 .1 0.1"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("3.141592653589793238462643383279502884197169"));
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("1/2 1:2 1024*1024 10^10 1v2 99:70"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("1E10 3.14e+000 1,000,000"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("2k 2M 2G 2T 2P 2E 2Z 2Y"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("2Ki 2Mi 2Gi 2Ti 2Pi 2Ei"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("2d 2c 2m 2u 2n 2p 2f 2a 2z 2y"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		assertEquals(Token.NUMBER_TOKEN, zsLex.first("2*3 2^64-1"));
		assertEquals(Token.NUMBER_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());

		// Test various invalid tokens
		assertEquals(Token.INVALID_TOKEN, zsLex.first("[Hello, World>"));
		assertEquals(Token.STRING_TOKEN, zsLex.first("<Hello,>?<World>"));
		assertEquals(Token.INVALID_TOKEN, zsLex.next());
		assertEquals(Token.FUNCTION_TOKEN, zsLex.first("echo ( some text }"));
		assertEquals(Token.OPEN_TOKEN, zsLex.next());
		assertEquals(Token.FUNCTION_TOKEN, zsLex.next());
		assertEquals(Token.FUNCTION_TOKEN, zsLex.next());
		assertEquals(Token.INVALID_TOKEN, zsLex.next());
		assertEquals(Token.NULL_TOKEN, zsLex.next());
		assertEquals(Token.INVALID_TOKEN, zsLex.first(",1"));
		assertEquals(Token.INVALID_TOKEN, zsLex.first("1?2"));

		// @end
		System.out.println("OK");
	}

	// ************************* Finite State Machine *************************
	// These actions are called from the generated FSM code.

	// ---------------------------------------------------------------------------
	// startNewToken
	//

	public void startNewToken() {
		token = new StringBuilder();
		type = Token.NULL_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// storeTheCharacter
	//

	public void storeTheCharacter() {
		token.append(current);
	}

	// ---------------------------------------------------------------------------
	// parseNextCharacter
	//

	public void parseNextCharacter() {
		try {
			current = input.charAt(inputPtr);
		} catch (final StringIndexOutOfBoundsException e) {
			current = 0;
			inputPtr = 0;
			input = "";
		}

		if (current != 0) {
			inputPtr++; // Don't advance past end of input
		}
		fsm.setNextEvent(events.get(current));
	}

	// ---------------------------------------------------------------------------
	// haveOpenToken
	//

	public void haveOpenToken() {
		type = Token.OPEN_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// haveCloseToken
	//

	public void haveCloseToken() {
		type = Token.CLOSE_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// haveNullToken
	//

	public void haveNullToken() {
		type = Token.NULL_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// haveFunctionToken
	//

	public void haveFunctionToken() {
		type = Token.FUNCTION_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// haveComposeToken
	//

	public void haveComposeToken() {
		type = Token.COMPOSE_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// pushBackToPrevious
	//

	public void pushBackToPrevious() {
		// This lets us handle tokens that are glued together
		if (inputPtr > 0) {
			inputPtr--;
		}
	}

	// ---------------------------------------------------------------------------
	// haveNumberToken
	//

	public void haveNumberToken() {
		type = Token.NUMBER_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// haveStringToken
	//

	public void haveStringToken() {
		type = Token.STRING_TOKEN;
	}

	// ---------------------------------------------------------------------------
	// storeNewlineCharacter
	//

	public void storeNewlineCharacter() {
		current = '\n';
		storeTheCharacter();
	}

	// ---------------------------------------------------------------------------
	// haveInvalidToken
	//

	public void haveInvalidToken() {
		type = Token.INVALID_TOKEN;
	}
}
