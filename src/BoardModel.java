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
	private Player[] players;
	private PlayerEnum playerOne;
	private PlayerEnum playerTwo;
	private UndoStructure us;
	
	/**
	 * Default no-args ctor for the BoardModel class. Initializes all underlying data structures except
	 * for the underlying BoardView. BoardView MUST be initialized separately and then enclosed in
	 * this BoardModel using setBoardView(BoardView view).
	 */
	public BoardModel() {
		// Create the mancalas.
		Mancala playerOneMancala = new Mancala();
		Mancala playerTwoMancala = new Mancala();
		
		// Create the player pits.
		Pit[] playerOnePits = new Pit[6];
		Pit[] playerTwoPits = new Pit[6];
		
		for (int i = 0; i < playerOnePits.length; i = i + 1) {
			playerOnePits[i] = new Pit();
			playerTwoPits[i] = new Pit();
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
		playerTwo = PlayerEnum.PLAYER_A;
		
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
	 * Method which contains the logic for the game. Initiates a new game
	 * when called.
	 */
	public void play() {
		if (us.getWhoseTurn() == playerOne) {
			// TODO: Player 1 logic
		} else {
			// TODO: Player 2 logic
		}
		view.isNotified();
	}
	
	/**
	 * Mutator for the BoardView associated with this BoardModel. Attachs a mouse listener
	 * to the view. The listener acts as the controller in the model/view/controller design
	 * strategy.
	 * 
	 * @param view the BoardView for this BoardModel
	 */
	public void setBoardView(BoardView view) {
		final Shape[] shapes = view.getShapes();
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				int clickedPitIndex = -1;
				for (int i = 0; i < shapes.length; i = i + 1) {
					if (shapes[i].contains(p)) {
						clickedPitIndex = clickedPitIndex + i + 1;
					}
				}
				System.out.println(clickedPitIndex);
			}
		});
		this.view = view;
	}
}