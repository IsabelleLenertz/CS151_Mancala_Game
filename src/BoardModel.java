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
	private UndoStructure us;
	
	/**
	 * Default ctor for the class. Takes a single argument
	 * that changes what the style of the final display will
	 * be for the user.
	 * 
	 * @param view BoardView instance that determines style of board for user
	 */
	public BoardModel() {
		Mancala playerOneMancala = new Mancala();
		Mancala playerTwoMancala = new Mancala();
		
		Pit[] playerOnePits = new Pit[6];
		Pit[] playerTwoPits = new Pit[6];
		
		for (int i = 0; i < playerOnePits.length; i = i + 1) {
			playerOnePits[i] = new Pit();
			playerTwoPits[i] = new Pit();
		}
		
		holes = new Hole[14]; // holes[13] is player 2 mancala
		                      // holes[0] to holes[5] are player 1's pits
		                      // holes[6] is player 1 mancala
		                      // holes[7] to holes[12] are player 2's pits
		
		holes[6] = playerOneMancala;
		holes[13] = playerTwoMancala;
		
		for (int j = 0; j < 6; j = j + 1) {
			holes[j] = playerOnePits[j];
			holes[j + 7] = playerTwoPits[j];
		}
		
		players = new Player[2];
		us = new UndoStructure();
		
		players[0] = new Player(playerOneMancala, playerOnePits, holes); // Player 1
		players[1] = new Player(playerTwoMancala, playerTwoPits, holes); // Player 2
	}
	
	/**
	 * Method for updating the appearance of the game for the user after
	 * changes have been made to the underlying data.
	 */
	private void notifyView() {
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
	 * Method which contains the logic for the game. Initiates a new game
	 * when called.
	 */
	public void play() {
		PlayerEnum playerOne = PlayerEnum.PLAYER_A;
		PlayerEnum playerTwo = PlayerEnum.PLAYER_A;
		
		
	}
	
	/**
	 * Mutator for the BoardView associated with this BoardModel.
	 * 
	 * @param view the BoardView for this BoardModel
	 */
	public void setBoardView(BoardView view) {
		this.view = view;
	}
}