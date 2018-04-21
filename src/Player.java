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
		return 0;
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
	 * @param holeNumber hole selected by the player
	 * @return current score
	 * precondition: the hole has to be on the player's side: up to the caller to check
	 * postcondition: modifies the content of the holes, up to the caller to notify the views of the change
	 */
	public int play(int holeNumber) {
		return 0;
	}
}
