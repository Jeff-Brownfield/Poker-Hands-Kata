package pokerhands;

public class Player {

	private Hand hand;
	
	public Player(String playerOneStrings){
		Hand hand = new Hand(playerOneStrings);
		this.hand = hand;
	}
	
	public Hand getHand() {
		return this.hand;
	}
}
