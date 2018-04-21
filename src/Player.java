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
		
		// Distribute the collected stones in the holes
		while(stonesCollected > 0) {
			// Check if the hole is not the other player's mancala
			if(holes[holeIndex].getClass() == mancala.getClass()) {
				if(holes[holeIndex] != mancala) {
					holeIndex++;
				}
			}
			// Add a stone and move on to the next hole
			holes[holeIndex].incrementStones();
			holeIndex++;
			stonesCollected--;
		}
		holeIndex--;
		
		// If the last hole was one of the player's pit
		if (Arrays.asList(pits).contains(holes[holeIndex])) {
			if (holes[holeIndex].getStones() == 1) {
				holes[holeIndex].decrementStones();
				stonesCollected = holes[(holeIndex + 7)%14].removeAllStones() + 1;
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