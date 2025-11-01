import java.util.Scanner;

/**
 * the Chat object, containing main, which allows two bots to reply one to the other
 * @author Aron Isaacs
 */
class Chat {
	/**
	 * legal requests for the first bot:
	 */
	public static final String[] FIRST_LEGAL_REQUESTS =
			{"okay " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE + " ",
			"okay!!" + " " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE, "whats up " +
			ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE};

	/** legal requests for the second bot:
	 */
	public static final String[] SECOND_LEGAL_REQUESTS = {"fine "
			+ ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE,
			"fine!! " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE + " "};

	/** illegal requests for the first bot:
	 */
	public static final String[] FIRST_ILLEGAL_REQUESTS = {"what",
			"say I should say " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST + " "};

	/** illegal requests for the second bot:
	 */
	public static final String[] SECOND_ILLEGAL_REQUESTS = {"whaaat ",
			"say say " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST + " "};
	/**
	 * the opening statement of the conversation:
	 */
	public static final String OPENING_STATEMENT = "say hello world";

	/**
	 * the name of the first bot:
	 */
	public static final String FIRST_BOT_NAME = "Sammy";

	/**
	 * the name of the second bot:
	 */
	public static final String SECOND_BOT_NAME = "Ruthy";

	/**
	 * a constant for ": ":
	 */
	public static final String SEMI_COLON_SPACE = ": ";

	/**
	 * creates two bots, each with their own legal and illegal responses,and initiates an unending
	 * conversation between them. to get the next response, the user should hit the "enter" key.
	 * @param args unused
	 */
	public static void main(String[] args) {

		ChatterBot[] bots = new ChatterBot[2];




		bots[0] = new ChatterBot(FIRST_BOT_NAME, FIRST_LEGAL_REQUESTS, FIRST_ILLEGAL_REQUESTS);
		bots[1] = new ChatterBot(SECOND_BOT_NAME, SECOND_LEGAL_REQUESTS, SECOND_ILLEGAL_REQUESTS);
		//initial "seed" statement:
		String statement = OPENING_STATEMENT;
		Scanner scanner = new Scanner(System.in);
		//infinite chat.
		for(int i = 0 ; ; i = i+1) {
			String currentBotName = bots[i % bots.length].getName() + SEMI_COLON_SPACE;
			statement = bots[i % bots.length].replyTo(statement);
			System.out.print(currentBotName + statement);
			scanner.nextLine();
		}

	}
}
