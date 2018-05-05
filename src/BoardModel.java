import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents the model object which stores
 * references to all high level data structures used to
 * play the game of mancala.
 * 
 * @author Lucas Galleguillos
 * updated on 05/05/2018		by Isabelle Delmas		reason: made the code compliant with MVC pattern
 */
public class BoardModel {
	private View view;
	private Hole[] holes;
	private int initialStoneCount;
	private Player[] players;
	private PlayerEnum playerOne;
	private PlayerEnum playerTwo;
	private UndoStructure us;
	private int[] endingRowTotals;
	
	/**
	 * Default singe-args ctor for the BoardModel class. Initializes all underlying data structures except
	 * for the underlying BoardView. BoardView MUST be initialized separately and then enclosed in
	 * this BoardModel using setBoardView(BoardView view).
	 * 
	 * @param int initialStoneCount the number of stones in each pit at the start of the game
	 */
	public BoardModel(int initialStoneCount) {
		this.initialStoneCount = initialStoneCount;
		
		// Create the mancalas.
		Mancala playerOneMancala = new Mancala();
		Mancala playerTwoMancala = new Mancala();
		
		// Create the player pits.
		Pit[] playerOnePits = new Pit[6];
		Pit[] playerTwoPits = new Pit[6];
		
		for (int i = 0; i < playerOnePits.length; i = i + 1) {
			// Player 1's pits
			playerOnePits[i] = new Pit();
			playerOnePits[i].stoneMutator(initialStoneCount);
			
			// Player 2's pits
			playerTwoPits[i] = new Pit();
			playerTwoPits[i].stoneMutator(initialStoneCount);
		}
		
		// Create the master hole data structure.
		holes = new Hole[14]; // holes[0] to holes[5] are player 1's pits
		                      // holes[6] is player 1 mancala
		                      // holes[7] to holes[12] are player 2's pits
		                      // holes[13] is player 2 mancala
		
		// Load the mancalas into holes.
		holes[6] = playerOneMancala;
		holes[13] = playerTwoMancala;
		
		// Load the pits into holes.
		for (int j = 0; j < 6; j = j + 1) {
			holes[j] = playerOnePits[j];
			holes[j + 7] = playerTwoPits[j];
		}
		
		// Create the players data structure.
		players = new Player[2];
		
		// Load the players.
		players[0] = new Player(playerOneMancala, playerOnePits, holes); // Player 1
		players[1] = new Player(playerTwoMancala, playerTwoPits, holes); // Player 2
		
		// Create the undo data structure.
		us = new UndoStructure();
		
		// Create enum's for the player's
		playerOne = PlayerEnum.PLAYER_A;
		playerTwo = PlayerEnum.PLAYER_B;
		
		// Player one goes first.
		players[0].startTurn();
		
		// Save to the undo structure.
		us.setWhoseTurn(playerOne);
		
		// Save the initial holes set up to the undo structure.
		us.setHoles(holes);
				
		endingRowTotals = new int[2];
	}
	
	/**
	 * Checks to see if the game has ended.
	 * 
	 * @return boolean denoting the status of the game's completion.
	 */
	private void gameOver() {

	}
	
	/**
	 * Allows the current state of the holes to be accessed by other classes.
	 * 
	 * @return the array of hole objects stored in this BoardModel
	 */
	public Hole[] getHoles() {
		return holes;
	}
	
	/**
	 * Allows the current state of the players to be accessed by other classes.
	 * 
	 * @return the array of player objects stored in this BoardModel
	 */
	public Player[] getPlayers() {
		return players;
	}
	
	/**
	 * Method for updating the appearance of the game for the user after
	 * changes have been made to the underlying data.
	 */
	public void notifyView() {
		view.isNotified();
	}
	
	/**
	 * Method which contains the main logic for the game.
	 * @param index hole selected to play
	 */
	public void play(int index) {
		us.setHoles(holes); // Save the holes in case of undo
		
		boolean modelUpdated = false; // Flag used to trigger view notification.
		
		if (players[0].isTurn()) {                    // Check Player 1 can take their turn.
			if (0 <= index && index <= 5) {           // Check a Player 1 pit was selected.
				if (us.getWhoseTurn() == playerTwo) { // Check who last player was.
					us.resetUndoCount();              // Last player was not player 1. Reset the undo count.
				}
				players[0].play(index);               // Player 1 takes their turn.
				players[1].startTurn();               // Player 1's turn is over. Player 2 can go next.
				us.setWhoseTurn(playerOne);           // Player 1 is now the last player to take a turn.
				                                      // Thus, the UndoStructure is updated.
				modelUpdated = true;                  // The view must be updated.
			}
		} else if (players[1].isTurn()) {             // Check Player 2 can take their turn.
			if (7 <= index && index <= 12) {          // Check a Player 2 pit was selected.
				if (us.getWhoseTurn() == playerOne) { // Check who last player was.
					us.resetUndoCount();              // Last player was not player 2. Reset the undo count.
				}
				players[1].play(index);               // Player 2 takes their turn.
				players[0].startTurn();               // Player 2's turn is over. Player 1 can go next.
				us.setWhoseTurn(playerTwo);           // Player 2 is now the last player to take a turn.
				                                      // Thus, the UndoStructure is updated.
				modelUpdated = true;                  // The view must be updated.
			}
		}
		
		if (modelUpdated) {    // Check if model has been updated.
			view.isNotified(); // Notify the view to update as well.
			gameOver();
			
			if (gameIsOver()) {
				holes[6].stoneMutator(endingRowTotals[0] + holes[6].getStones());
				holes[13].stoneMutator(endingRowTotals[1] + holes[13].getStones());
				
				endingRowTotals[0] = 0;
				endingRowTotals[1] = 0;
				
				for (int i = 0; i < 6; i = i + 1) {
					holes[i].stoneMutator(0);
					holes[i + 7].stoneMutator(0);
				}
				
				view.isNotified();
			}
		}
	}

	/**
	 * Restore the state of the board to the previous state, if allowed by the rules
	 */
	public void undo() {
		if(us.getUndoCount() < 3) {      // Undo pressed. Check that undo has not been exceeded.
			for(int i = 0;i<14;i++) {
				holes[i].stoneMutator(us.getHoles()[i]); // Set stones from UndoStructure.
			}
			
			// Return to the previous turn.
			if (us.getWhoseTurn() == playerOne) {
				players[0].startTurn();
				players[1].endTurn();
			} else {
				players[0].endTurn();
				players[1].startTurn();
			}
			
			
			// Update view.
			view.isNotified();
			
			// Increase the undo counter.
			us.incrementCount();
		}
	}
	
	/**
	 * Check if the game has reached the end
	 * @return true if the game ended, false otherwise
	 */
	public boolean gameIsOver() {
		int firstRowCount = 0;
		int secondRowCount = 0;
		
		for (int i = 0; i < 6; i = i + 1) {
			firstRowCount = firstRowCount + holes[i].getStones();
			secondRowCount = secondRowCount + holes[i + 7].getStones();
		}
		
		endingRowTotals[0] = firstRowCount;
		endingRowTotals[1] = secondRowCount;
		
		if (firstRowCount == 0) {
			return true;
		}
		
		if (secondRowCount == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Mutator for the BoardView associated with this BoardModel. Attachs a mouse listener
	 * to the view. The listener acts as the controller in the model/view/controller design
	 * strategy.
	 * 
	 * @param newView the BoardView for this BoardModel
	 */
	public void setBoardView(View newView) {
		// Enclose the modified view in this BoardModel.
		view = newView;
	}
}