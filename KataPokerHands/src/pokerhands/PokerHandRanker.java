package pokerhands;
import java.util.Scanner;

public class PokerHandRanker {
	
	private static String playerOneStrings;
	private static String playerTwoStrings;
	
	public static void main(String[] args) {
		String handInput = readInput();
		splitStrings(handInput);
		
		Deal deal = new Deal(playerOneStrings, playerTwoStrings);
		System.out.println(deal.getComparisonResult());
	}
	
	private static String readInput() {
	    try(java.util.Scanner s = new Scanner(System.in)) {
	    	System.out.println("Please enter hand information:");
	    	String input = s.nextLine();
	    	return input;
	    }
	}
		
	private static void splitStrings(String handInput) {
		String[] splitHandInput = handInput.split("(?= White)");
		playerOneStrings = splitHandInput[0].substring(7, 21);
		playerTwoStrings = splitHandInput[1].substring(8, 22);
	}
	
}
