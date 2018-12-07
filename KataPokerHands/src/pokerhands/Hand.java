package pokerhands;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import pokerhands.HandType;
import pokerhands.CardType;
import pokerhands.Card;
import pokerhands.Deal;

public class Hand {

	private String[] cardStrings;
	private Card[] cards = new Card[5];
	private HandType handType;
	
	private Integer[] decendingCardValues = new Integer[5];
	private int[] matchingCardValues = new int [4];
	private int matches = 0;
	
	private int fullHouseThreeOfKindValue;  // A full house is the only case where the highest match or highest card is not the first condition for winning a tie
	private String fullHouseType;
	
	private boolean kickerNeeded = false;
	private String kicker;
	
	public Card[] getCards() {
		return cards;
	}
	
	public HandType getHandType() {
		return handType;
	}
	
	public String[] getCardStrings() {
		return cardStrings;
	}
	
	public Integer[] getDecendingCardValues() {
		return decendingCardValues;
	}
	
	public int[] getMatchingCardValues() {
		return matchingCardValues;
	}
	
	public void setKickerNeeded() {
		kickerNeeded = true;
	}
	
	public boolean isKickerNeeded() {
		return kickerNeeded;
	}
	
	public String getKicker() {
		return kicker;
	}
	
	public void setKicker(String kicker) {
		this.kicker = kicker;
	}

	
	public Hand(String playerStrings) {
		cardStrings = (playerStrings.split(" "));
		for (int i = 0; i < 5; i++) {
			Card card = new Card(cardStrings[i]);
			cards[i] = card;
		}
		
		orderCardsDecending();
		
		boolean flush = isFlush();
		boolean straight = isStraight();
		if (straight == false && flush == false) {
			findMatches();
		}
		
		handType = rankHand(flush, straight, matchingCardValues);
	}

	private boolean isStraight() {
		if ((decendingCardValues[0]) == (decendingCardValues[1] + 1) && (decendingCardValues[0]) == (decendingCardValues[2] + 2) && (decendingCardValues[0]) == (decendingCardValues[3] + 3)
		&& (decendingCardValues[0] == (decendingCardValues[4] + 4))) {
			return true;
		}else if (decendingCardValues[0] == 14 && (decendingCardValues[1] == 5) && (decendingCardValues[2] == 4) && (decendingCardValues[3] == 3) && (decendingCardValues[4] == 2)) {
			return true;
		} else {
			return false;
		}
	}

	private void orderCardsDecending() {
		
		for (int i = 0; i < 5; i++) {
			decendingCardValues[i] = cards[i].getCardValue();
		}
		Arrays.sort(decendingCardValues, Collections.reverseOrder());
	}

	private void findMatches() {
		for (int i = 0; i < 4; i++) {
			if (decendingCardValues[i].equals(decendingCardValues[i+1])) {
				matchingCardValues[matches] = decendingCardValues[i];
				matches++;
			}
		}
	}

	
	private boolean isFlush() {
		boolean flush = true;
		
		for (Card card : cards) {
			flush = ((card.getCardType() == cards[1].getCardType()) && flush == true);
		}
		return flush;
	}
	
	
	private HandType matchesHandType(int[] matches) {
		
		if (matches[0] == 0) {
			return HandType.HIGHCARD;
		} else if (matches[1] == 0){
			return HandType.PAIR;
		} else if (matches[0] != matches[1] && matches[1] == 0) {
			return HandType.TWOPAIR;
		} else if (matches[0] == matches[1] && matches[2] == 0) {
			return HandType.THREEOFAKIND;
		} else if (isFullHouse(matches)) {
			return HandType.FULLHOUSE;
		} else {
			return HandType.FOUROFAKIND;
		}
	}
	
	
	private boolean isFullHouse(int[] matches) {
		if (matches[0] == matches[1] && matches[1] != matches[2]) {
			fullHouseThreeOfKindValue = matches[1];
			fullHouseType = matches[0] + " over " + matches[2];
			return true;
		} else if (matches[0] != matches[1] && matches[1] == matches[2]) {
			fullHouseThreeOfKindValue = matches[2];
			fullHouseType = matches[1] + " over " + matches[0];
			return true;
		} else {
			return false;
		}
	}
	
	
	private HandType rankHand(boolean flush, boolean straight, int[] matches) {
		
		if (flush == false && straight == false) {
			return matchesHandType(matches);
		} else if (flush == true && straight == false) {
			return HandType.FLUSH;
		} else if (flush == false && straight == true) {
			return HandType.STRAIGHT;
		} else {
			return HandType.STRAIGHTFLUSH;
		}
	}
	
	
	public int getFullHouseThreeOfKindValue() {
		return fullHouseThreeOfKindValue;
	}
	
	
	public String getWinReason(Player player, HandType handType) {
		switch (handType) {
		case THREEOFAKIND:
		case PAIR:
		case FOUROFAKIND: return Card.cardValueToString(matchingCardValues[0]);
		case TWOPAIR: return Card.cardValueToString(matchingCardValues[0]) + " over " + Card.cardValueToString(matchingCardValues[1]);
		case FULLHOUSE: return fullHouseType;
		default: return "";
		}
	}
	
}
