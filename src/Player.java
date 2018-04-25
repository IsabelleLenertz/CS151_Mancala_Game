import java.util.Arrays;

/**
 * Part of a group project
 * Player in a mancala game
 * @author Isabelle Delmas
 * @Date 04-21-2018
 * @Revision:				Reason:
 *
 */
public class Player {
	Mancala mancala;
	Pit[] pits;
	Hole[] holes;
	boolean turn;
	
	/**
	 * Create a Player with turn = false
	 * @param pMancala reference to the player's mancala
	 * @param pPits reference to the player pits (size = 6)
	 * @param pHoles reference to all the holes (size = 14)
	 * precondition: pPits is an array of size 6 and oHoles is an array of size 14
	 */
	public Player(Mancala pMancala, Pit[] pPits, Hole[] pHoles) {
		mancala = pMancala;
		pits = pPits;
		holes = pHoles;
		turn = false;
	}
	
	/**
	 * Get the player's current score
	 * @return current score
	 */
	public int getScore() {
		return mancala.getStones();
	}
	
	/**
	 * Check if the player can play
	 * @return true if the player can play
	 */
	public boolean isTurn() {
		return this.turn;
	}
	
	/**
	 * End the player's turn
	 */
	private void endTurn() {
		this.turn = false;
	}
	
	/**
	 * Start the player's turn
	 */
	public void startTurn() {
		this.turn = true;
	}
	
	/**
	 * play the player's turn with a specific hole number
	 * @param holeIndex hole selected by the player
	 * @return current score
	 * precondition: the hole has to be on the player's side: up to the caller to check
	 * postcondition: modifies the content of the holes, up to the caller to notify the views of the change
	 */
	public int play(int holeIndex) {
		
		int stonesCollected = holes[holeIndex].removeAllStones();
		holeIndex++;
		// Distribute the collected stones in the holes
		while(stonesCollected > 0) {
			// Check if the hole is not the other player's mancala
			if(holes[holeIndex].getClass() == mancala.getClass()) {
				if(holes[holeIndex] != mancala) {
					holeIndex = (holeIndex + 1)%13;
				}
			}
			// Add a stone and move on to the next hole
			holes[holeIndex].incrementStones();
			holeIndex = (holeIndex + 1)%13;
			stonesCollected--;
		}
		// Make sure the index is not out of bound when decreasing to be at the last hole a stone was added too.
		if (holeIndex == 0) {
			holeIndex = 13;
		} else {
			holeIndex--;
		}
		
		// If the last hole was one of the player's pit
		if (Arrays.asList(pits).contains(holes[holeIndex])) {
			if (holes[holeIndex].getStones() == 1) {
				int indexToSteal = 0;
				switch(holeIndex) {
				case 0:	indexToSteal = 12;
						break;
				case 1: indexToSteal = 11;
						break;				
				case 2:	indexToSteal = 10;
						break;
				case 3: indexToSteal = 9;
						break;
				case 4:	indexToSteal = 8;
						break;
				case 5: indexToSteal = 7;
						break;
				case 7: indexToSteal = 5;
						break;				
				case 8:	indexToSteal = 4;
						break;
				case 9: indexToSteal = 3;
						break;
				case 10:indexToSteal = 2;
						break;
				case 11:indexToSteal = 1;
						break;
				case 12:indexToSteal = 0;
						break;
				}
				holes[holeIndex].decrementStones();
				stonesCollected = holes[indexToSteal].removeAllStones() + 1;
				mancala.addStones(stonesCollected);
			}
		}
		
		// If the last hole was not the player's mancala, end the turn
		if(holes[holeIndex] != mancala) {
			endTurn();
		}
			
		return getScore();
	}
}
