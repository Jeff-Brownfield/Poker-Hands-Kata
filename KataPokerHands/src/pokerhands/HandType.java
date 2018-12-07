package pokerhands;

public enum HandType {
	
	STRAIGHTFLUSH ("straight flush: "),
	FOUROFAKIND ("four of a kind: "),
	FULLHOUSE ("full house: "),
	FLUSH ("flush: "),
	STRAIGHT ("straight: "),
	THREEOFAKIND ("three of a kind: "),
	TWOPAIR ("two pair: "),
	PAIR ("pair: "),
	HIGHCARD ("high card: ");
	
	private String outputText;
	
	private HandType(String outputText) {
		this.outputText = outputText;
	}
	
	public String getOutputString() {
		return outputText;
	}
	
	public static int getHandScore(HandType handType) {
		switch (handType) {
		case HIGHCARD: return 0;
		case PAIR: return 1;
		case TWOPAIR: return 2;
		case THREEOFAKIND: return 3;
		case STRAIGHT: return 4;
		case FLUSH: return 5;
		case FULLHOUSE: return 6;
		case FOUROFAKIND: return 7;
		default: return 8;
		}
	}
}
