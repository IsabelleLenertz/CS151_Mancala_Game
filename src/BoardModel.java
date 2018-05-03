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
 */
public class BoardModel {
	private BoardView view;
	private Hole[] holes;
	private int initialStoneCount;
	private Player[] players;
	private PlayerEnum playerOne;
	private PlayerEnum playerTwo;
	private UndoStructure us;
	
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
		
		// Player one goes first. Save to the undo structure.
		us.setWhoseTurn(playerOne);
		
		// Save the initial holes set up to the undo structure.
		us.setHoles(holes);
	}
	
	/**
	 * Method for updating the appearance of the game for the user after
	 * changes have been made to the underlying data.
	 */
	public void notifyView() {
		view.isNotified();
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
	 * Method which contains the main logic for the game.
	 */
	public void play(int index) {
		us.setHoles(holes); // Save the holes in case of undo
		
		boolean validTurn = false; // Flag is set to true when model is changed.
		
		if (us.getWhoseTurn() == playerOne) { // Player 1's turn
			if (0 <= index && index <= 5) {   // Check if a valid player 1 pit was selected
				players[0].play(index);       // Player 1 takes their turn
				us.setWhoseTurn(playerTwo);   // Player 2 is next
				validTurn = true;             // Valid turn, set the flag.
			}
		} else {                             // Player 2's turn
			if (7 <= index && index <= 12) { // Check if a valid player 2 pit was selected
				players[1].play(index);      // Player 2 takes their turn
				us.setWhoseTurn(playerOne);  // Player 1 is next
				view.isNotified();           // Update the view
				validTurn = true;            // Valid turn, set the flag.
			}
		}
		
		if (validTurn) {       // Check to see if the model was changed.
			view.isNotified(); // Update the view to reflect the changes to the model.
		}
	}
	
	/**
	 * Mutator for the BoardView associated with this BoardModel. Attachs a mouse listener
	 * to the view. The listener acts as the controller in the model/view/controller design
	 * strategy.
	 * 
	 * @param view the BoardView for this BoardModel
	 */
	public void setBoardView(BoardView view) {
		// Get the shapes created during the first display of the view.
		final Shape[] shapes = view.getShapes();
		
		// Attach a listener to the view for clicks.
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// When a click on the view is detected, we will check to see if
				// it was a on a pit.
				Point p = e.getPoint(); // The location of the click.
				
				// The index of the clicked hole. -1 if it there is no clicked hole
				int clickedHoleIndex = -1;
				
				// Loop through the hole shapes and see if the click was in one
				// of them.
				for (int i = 0; i < shapes.length; i = i + 1) {
					if (shapes[i].contains(p)) { // A valid click.
						clickedHoleIndex = clickedHoleIndex + i + 1; // Save the index of hole clicked.
					}
				}
				
				if (-1 < clickedHoleIndex) { // Can play the game.
					play(clickedHoleIndex);  // Play the game.
				}
			}
		});
		
		// Enclose the modified view in this BoardModel.
		this.view = view;
	}
}