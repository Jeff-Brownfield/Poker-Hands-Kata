package pokerhands;
import pokerhands.CardType;

public class Card {
	
	private CardType cardType;
	private int cardValue;
		
	public CardType getCardType() {
		return cardType;
	}
	
	public int getCardValue() {
		return cardValue;
	}
	
	
	public Card(String cardString) {
		String[] cardStrings = (cardString.split(""));
		
		this.cardValue = cardValueToInt(cardStrings[0]);
		this.cardType = CardType.getCardType(cardStrings[1]);
	}

	private int cardValueToInt(String string) {
		switch (string) {
		case "T": return 10;
		case "J": return 11;
		case "Q": return 12;
		case "K": return 13;
		case "A": return 14;
		default: return  Integer.parseInt(string);
		}
	}
	
	public static String cardValueToString(Integer cardValue) {
		switch (cardValue) {
		case 11: return "Jack";
		case 12: return "Queen";
		case 13: return "King";
		case 14: return "Ace";
		default: return Integer.toString(cardValue);
		}
	}

	public static String cardValueToString(int cardValue) {
		switch (cardValue) {
		case 11: return "Jack";
		case 12: return "Queen";
		case 13: return "King";
		case 14: return "Ace";
		default: return Integer.toString(cardValue);
		}
	}


	
	
}
