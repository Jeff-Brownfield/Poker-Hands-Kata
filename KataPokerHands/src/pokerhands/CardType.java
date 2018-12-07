package pokerhands;

public enum CardType {
	
	SPADES, HEARTS, DIAMONDS, CLUBS;
	
	public static CardType getCardType(String cardString) {
		
		switch (cardString) {
			case "C": return CLUBS;
			case "S": return SPADES;
			case "H": return HEARTS;
			default: return DIAMONDS;
		}
	}
	
}
