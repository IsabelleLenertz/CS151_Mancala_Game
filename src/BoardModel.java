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
	private Player[] players;
	
	/**
	 * Default ctor for the class. Takes a single argument
	 * that changes what the style of the final display will
	 * be for the user.
	 * 
	 * @param view BoardView instance that determines style of board for user
	 */
	public BoardModel(BoardView view) {
		this.view = view;
		holes = new Hole[14];
		players = new Player[2];
		
		//players[0] = new Player(); // Player 1
		//players[1] = new Player(); // Player 2
	}
	
	/**
	 * Method for updating the appearance of the game for the user after
	 * changes have been made to the underlying data.
	 */
	private void notifyView() {
		view.isNotified();
	}
	
	/**
	 * Allows the current state of the stones to be accessed by other classes.
	 * 
	 * @return the array of hole objects stored in this BoardModel
	 */
	public Hole[] getHoles() {
		return holes;
	}
	
	/**
	 * Method which contains the logic for the game. Initiates a new game
	 * when called.
	 */
	public void play() {
		
	}
}