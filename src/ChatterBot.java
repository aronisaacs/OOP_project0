import java.util.*;

/**
 A bot that responds to a given statement, for legal/illegal requests. If the statement begins with the
 word "echo", then
 the bot will respond with the rest of the statement. If the statement begins with the correct
 request statement, currently the string constant "say", the bot will respond with one of its legal reply
 patterns,
 chosen at random ,inserting the statement into the pattern. Otherwise, it will do the same with one of its
 illegal responses
 @see Chat
 * @author Aron Isaacs
 */
class ChatterBot {

	/**
	 * a statement is considered legal if it starts with the following prefix:
	 */
	static final String REQUEST_PREFIX = "say ";

	/**
	 * an empty string constant:
	 */
	static final String EMPTY_STRING = "";

	/**
	 * the placeholder expression the bot replaces with the statement it is given, when the statement is
	 * LEGAL:
	 */
	static final String PLACEHOLDER_FOR_REQUESTED_PHRASE = "<phrase>";

	/**
	 * the placeholder expression the bot replaces with the statement it is given, when the statement is
	 * ILLEGAL:
	 */
	static final String PLACEHOLDER_FOR_ILLEGAL_REQUEST = "<request>";

	/**
	 * an array of potential illegal responses:
	 */
	String[] illegalRequestsReplies;

	/**
	 * an array of potential illegal responses:
	 */
	String[] legalRequestsReplies;

	/**
	 * the name of the bot:
	 */
	String name;

	/**
	 * the prefix that tells the bot to simply repeat the statement (without said prefix):
	 */
	static final String REPEAT_PREFIX = "echo ";

	/**
	 * the bot's random object:
	 */
	Random rand = new Random();


	/**
	 * constructs the chatterbot.
	 * @param name the name of the bot
	 * @param legalRequestsReplies an array of legal requests
	 * @param illegalRequestsReplies an array of illegal requests
	 */
	ChatterBot(String name, String[] legalRequestsReplies, String[] illegalRequestsReplies) {
		this.name = name;
		this.legalRequestsReplies = new String[legalRequestsReplies.length];
		// manual array copies:
		for (int i = 0 ; i < legalRequestsReplies.length ; i = i+1) {
			this.legalRequestsReplies[i] = legalRequestsReplies[i];
		}
		this.illegalRequestsReplies = new String[illegalRequestsReplies.length];
		for (int i = 0 ; i < illegalRequestsReplies.length ; i = i+1) {
			this.illegalRequestsReplies[i] = illegalRequestsReplies[i];
		}
	}

	/**
	 * getter for the name of the bot
	 * @return the name of the bot
	 */
	String getName() {
		return name;
	}

	/**
	 * causes the bot to reply to the given statement, legal, illegal, or echoed:
	 * @param statement the statement the bot is replying to
	 * @return the reply
	 */
	String replyTo(String statement) {
		if (statement.startsWith(REPEAT_PREFIX)) {
			return statement.replaceFirst(REPEAT_PREFIX, EMPTY_STRING);
		}
		if(statement.startsWith(REQUEST_PREFIX)) {
			//we don’t repeat the request prefix, so delete it from the reply
			statement = statement.replaceFirst(REQUEST_PREFIX, EMPTY_STRING);
			return replyToLegalRequest(statement);
		}
		//otherwise, the statement is illegal:
		return replyToIllegalRequest(statement);
	}

	/**
	 * subroutine for handling illegal requests
	 * @param statement the illegal statement
	 * @return the bot's response
	 */
	String replyToIllegalRequest(String statement) {
		return replacePlaceholderInARandomPattern(illegalRequestsReplies, PLACEHOLDER_FOR_ILLEGAL_REQUEST,
				statement);
	}

	/**
	 * subroutine for handling legal requests
	 * @param statement the legal statement
	 * @return the bot's response
	 */
	String replyToLegalRequest(String statement) {
		return replacePlaceholderInARandomPattern(legalRequestsReplies, PLACEHOLDER_FOR_REQUESTED_PHRASE,
				statement);
	}

	/**
	 * picks a response at random from the given array, inserts the users statement to replace any
	 * placeholders. Used by both the legal illegal subroutines
	 * @param patterns the array of general patterns the bot chooses from
	 * @param placeholder the string the bot will replace with the statement
	 * @param replacement the statement that replaces the placeholders
	 * @return the bot's reply
	 */
	String replacePlaceholderInARandomPattern(String[] patterns,
											  String placeholder,
											  String replacement){
		int randomIndex = rand.nextInt(patterns.length);
		String responsePattern = patterns[randomIndex];
		return responsePattern.replaceAll(placeholder, replacement);
	}
}
