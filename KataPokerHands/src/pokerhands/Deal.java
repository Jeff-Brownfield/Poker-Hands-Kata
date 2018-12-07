package pokerhands;
import pokerhands.HandType;
import pokerhands.Hand;

public class Deal {

	private Player Black;
	private Player White;
	private Hand blackHand;
	private Hand whiteHand;
	private String comparisonResult;
	
	public Deal(String playerOneStrings, String playerTwoStrings) {
		Player Black = new Player(playerOneStrings);
		Player White = new Player(playerTwoStrings);
		
		comparisonResult = compareHands(Black, White);
	}
	
	public String getComparisonResult() {
		return comparisonResult;
	}
	
	private String compareHands(Player Black, Player White) {
		blackHand = Black.getHand();
		whiteHand = White.getHand();
		HandType blackHandType = blackHand.getHandType();
		HandType whiteHandType= whiteHand.getHandType();
		int blackHandScore = HandType.getHandScore(blackHandType);
		int whiteHandScore = HandType.getHandScore(whiteHandType);
		
		if (blackHandScore > whiteHandScore) {
			return blackWinString(blackHandType);
		} else if (whiteHandScore > blackHandScore) {
			return whiteWinString(whiteHandType);
		} else {
			return solveTie(blackHandType);
		}
	}


	private String solveTie(HandType handType) {
		switch (handType) {
		case FOUROFAKIND:
		case THREEOFAKIND:
		case TWOPAIR:
		case PAIR: return compareHighestMatch(handType); 
		case FULLHOUSE: return getWinningFullHouse(handType);
		default:
			return compareHighestCard(handType);
		}
	}

	
	private String getWinningFullHouse(HandType handType) {
		if (blackHand.getFullHouseThreeOfKindValue() > whiteHand.getFullHouseThreeOfKindValue()) {
			return blackWinString(handType);
		} else {
			return whiteWinString(handType);
		}
	}
	
	
	private String compareHighestCard(HandType handType) {
		for (int i = 0; i < blackHand.getDecendingCardValues().length; i++) {
			
			if (blackHand.getDecendingCardValues()[i] > whiteHand.getDecendingCardValues()[i]) {
				setKickerString(blackHand, handType, i);
				blackHand.setKickerNeeded();
				return blackWinString(handType);
				
			} else if (blackHand.getDecendingCardValues()[i] < whiteHand.getDecendingCardValues()[i]) {
				setKickerString(whiteHand, handType, i);
				whiteHand.setKickerNeeded();
				return whiteWinString(handType);
			}
		}
		return "Tie.";
	}

	
	private void setKickerString(Hand playerHand, HandType handType, int i) {
		if (handType == HandType.HIGHCARD) {
			playerHand.setKicker(Card.cardValueToString(playerHand.getDecendingCardValues()[i]));
		} else {
			playerHand.setKicker(Card.cardValueToString(playerHand.getDecendingCardValues()[i]) + " high");
		}
	}

	
	private String compareHighestMatch(HandType handType) {
		for (int i = 0; i < blackHand.getMatchingCardValues().length; i++) {
			if (blackHand.getMatchingCardValues()[i] > whiteHand.getMatchingCardValues()[i]) {
				return blackWinString(handType);
			} else if (blackHand.getMatchingCardValues()[i] < whiteHand.getMatchingCardValues()[i]) {
				return whiteWinString(handType);
			}
		}
		return compareHighestCard(handType);
	}
	
	
	private String blackWinString(HandType blackHandType) {
		return "Black wins. - with " + blackHandType.getOutputString() + blackHand.getWinReason(Black, blackHandType)
			+ (blackHand.isKickerNeeded()? blackHand.getKicker(): "");
	}
	
	
	private String whiteWinString(HandType whiteHandType) {
		return "White wins. - with " + whiteHandType.getOutputString() + whiteHand.getWinReason(White, whiteHandType)
			+ (whiteHand.isKickerNeeded()? whiteHand.getKicker(): "");
	}

}