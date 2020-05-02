package lexer;

import java.util.List;

/*  =========================================================================
    ZsLexFsm - No title state machine engine

    ** WARNING *************************************************************
    THIS SOURCE FILE IS 100% GENERATED. If you edit this file, you will lose
    your changes at the next build cycle. This is great for temporary printf
    statements. DO NOT MAKE ANY CHANGES YOU WISH TO KEEP. The correct places
    for commits are:

     * The XML model used for this code generation: pipo_lex.xml, or
     * The code generation script that built this file: fsm_java
    ************************************************************************
    =========================================================================
*/
public class ZsLexFsm {

	// Names for state machine logging and error reporting
	private final List<String> stateNames = State.getStateNames();
	private final List<String> eventNames = Event.getEventNames();

	// This is the context block for a FSM thread; use the setter
	// methods to set the FSM properties.

	private final ZsLex parent; // Parent class
	private boolean animate; // Animate state machine
	private State state; // Current state
	private Event event; // Current event
	private Event nextEvent; // The next event
	private Event exception; // Exception event, if any
	private int cycles; // Track the work done

	public ZsLexFsm(ZsLex parent) {
		this.parent = parent;
		state = State.EXPECTING_TOKEN_STATE;
		event = Event.NULL_EVENT;
		nextEvent = Event.NULL_EVENT;
	}

	public void setNextEvent(Event nextEvent) {
		this.nextEvent = nextEvent;
	}

	public void setException(Event exception) {
		this.exception = exception;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public int getCycles() {
		return cycles;
	}

	// Execute state machine until it has no next event. Before calling this
	// you must have set the next event using fsm_set_next_event(). Ends when
	// there is no next event set.

	public void execute() {
		while (nextEvent != Event.NULL_EVENT) {
			cycles++;
			event = nextEvent;
			nextEvent = Event.NULL_EVENT;
			exception = Event.NULL_EVENT;
			if (animate) {
				System.out.println("ZsLex: " + stateNames.get(state.getValue()) + ':');
				System.out.println("ZsLex:                        " + eventNames.get(event.getValue()));
			}
			if (state == State.EXPECTING_TOKEN_STATE) {
				if (event == Event.LETTER_EVENT) {
					if (exception != null) {
						// start_new_token
						if (animate) {
							System.out.println("ZsLex:                            $ start_new_token");
						}
						parent.startNewToken();
					}
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
					if (exception != null) {
						state = State.READING_FUNCTION_STATE;
					}
				} else if (event == Event.HYPHEN_EVENT) {
					if (exception != null) {
						// start_new_token
						if (animate) {
							System.out.println("ZsLex:                            $ start_new_token");
						}
						parent.startNewToken();
					}
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
					if (exception != null) {
						state = State.READING_NUMBER_STATE;
					}
				} else if (event == Event.PLUS_EVENT) {
					if (exception != null) {
						// start_new_token
						if (animate) {
							System.out.println("ZsLex:                            $ start_new_token");
						}
						parent.startNewToken();
					}
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
					if (exception != null) {
						state = State.READING_NUMBER_STATE;
					}
				} else if (event == Event.PERIOD_EVENT) {
					if (exception != null) {
						// start_new_token
						if (animate) {
							System.out.println("ZsLex:                            $ start_new_token");
						}
						parent.startNewToken();
					}
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
					if (exception != null) {
						state = State.READING_NUMBER_STATE;
					}
				} else if (event == Event.DIGIT_EVENT) {
					if (exception != null) {
						// start_new_token
						if (animate) {
							System.out.println("ZsLex:                            $ start_new_token");
						}
						parent.startNewToken();
					}
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
					if (exception != null) {
						state = State.READING_NUMBER_STATE;
					}
				} else if (event == Event.OPEN_QUOTE_EVENT) {
					if (exception != null) {
						// start_new_token
						if (animate) {
							System.out.println("ZsLex:                            $ start_new_token");
						}
						parent.startNewToken();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
					if (exception != null) {
						state = State.READING_STRING_STATE;
					}
				} else if (event == Event.WHITESPACE_EVENT) {
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.NEWLINE_EVENT) {
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.OPEN_LIST_EVENT) {
					if (exception != null) {
						// have_open_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_open_token");
						}
						parent.haveOpenToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.CLOSE_LIST_EVENT) {
					if (exception != null) {
						// have_close_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_close_token");
						}
						parent.haveCloseToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.FINISHED_EVENT) {
					if (exception != null) {
						// have_null_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_null_token");
						}
						parent.haveNullToken();
					}
				} else if (event == Event.OTHER_EVENT) {
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else {
					// Handle all other events
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				}
			} else if (state == State.READING_FUNCTION_STATE) {
				if (event == Event.LETTER_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.DIGIT_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.HYPHEN_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.PERIOD_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.SLASH_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.UNDERSCORE_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.WHITESPACE_EVENT) {
					if (exception != null) {
						// have_function_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_function_token");
						}
						parent.haveFunctionToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.COLON_EVENT) {
					if (exception != null) {
						// have_compose_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_compose_token");
						}
						parent.haveComposeToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.OPEN_LIST_EVENT) {
					if (exception != null) {
						// have_function_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_function_token");
						}
						parent.haveFunctionToken();
					}
					if (exception != null) {
						// push_back_to_previous
						if (animate) {
							System.out.println("ZsLex:                            $ push_back_to_previous");
						}
						parent.pushBackToPrevious();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.CLOSE_LIST_EVENT) {
					if (exception != null) {
						// have_function_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_function_token");
						}
						parent.haveFunctionToken();
					}
					if (exception != null) {
						// push_back_to_previous
						if (animate) {
							System.out.println("ZsLex:                            $ push_back_to_previous");
						}
						parent.pushBackToPrevious();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.NEWLINE_EVENT) {
					if (exception != null) {
						// have_function_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_function_token");
						}
						parent.haveFunctionToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.FINISHED_EVENT) {
					if (exception != null) {
						// have_function_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_function_token");
						}
						parent.haveFunctionToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.OTHER_EVENT) {
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else {
					// Handle all other events
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				}
			} else if (state == State.READING_NUMBER_STATE) {
				if (event == Event.DIGIT_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.LETTER_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.HYPHEN_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.PLUS_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.SLASH_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.PERIOD_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.COMMA_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.COLON_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.ASTERISK_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.CARET_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.PERCENT_EVENT) {
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else if (event == Event.CLOSE_LIST_EVENT) {
					if (exception != null) {
						// have_number_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_number_token");
						}
						parent.haveNumberToken();
					}
					if (exception != null) {
						// push_back_to_previous
						if (animate) {
							System.out.println("ZsLex:                            $ push_back_to_previous");
						}
						parent.pushBackToPrevious();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.WHITESPACE_EVENT) {
					if (exception != null) {
						// have_number_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_number_token");
						}
						parent.haveNumberToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.NEWLINE_EVENT) {
					if (exception != null) {
						// have_number_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_number_token");
						}
						parent.haveNumberToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.FINISHED_EVENT) {
					if (exception != null) {
						// have_number_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_number_token");
						}
						parent.haveNumberToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.OTHER_EVENT) {
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else {
					// Handle all other events
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				}
			} else if (state == State.READING_STRING_STATE) {
				if (event == Event.CLOSE_QUOTE_EVENT) {
					if (exception != null) {
						// have_string_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_string_token");
						}
						parent.haveStringToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else if (event == Event.FINISHED_EVENT) {
					if (exception != null) {
						// store_newline_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_newline_character");
						}
						parent.storeNewlineCharacter();
					}
				} else if (event == Event.NEWLINE_EVENT) {
					if (exception != null) {
						// store_newline_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_newline_character");
						}
						parent.storeNewlineCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				} else {
					// Handle all other events
					if (exception != null) {
						// store_the_character
						if (animate) {
							System.out.println("ZsLex:                            $ store_the_character");
						}
						parent.storeTheCharacter();
					}
					if (exception != null) {
						// parse_next_character
						if (animate) {
							System.out.println("ZsLex:                            $ parse_next_character");
						}
						parent.parseNextCharacter();
					}
				}
			} else if (state == State.DEFAULTS_STATE) {
				if (event == Event.OTHER_EVENT) {
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				} else {
					// Handle all other events
					if (exception != null) {
						// have_invalid_token
						if (animate) {
							System.out.println("ZsLex:                            $ have_invalid_token");
						}
						parent.haveInvalidToken();
					}
					if (exception != null) {
						state = State.EXPECTING_TOKEN_STATE;
					}
				}
			}
			// If we had an exception event, interrupt normal programming
			if (exception == null) {
				if (animate) {
					System.out.println("ZsLex:                            ! " + eventNames.get(exception.getValue()));
				}
				nextEvent = exception;
			} else if (animate) {
				System.out.println("ZsLex:                            > " + stateNames.get(state.getValue()));
			}
		}
	}
}
