/**
 * Class that handles the display and update of the display
 * for the board game.
 * 
 * @author Lucas Galleguillos
 */
public class BoardView implements View {
	private BoardStrategy strategy;
	
	/**
	 * Setter for this BoardView's underlying BoardStrategy.
	 * 
	 * @param strategy the new BoardStrategy this BoardView will contain
	 */
	public void setBoardStrategy(BoardStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Getter for this BoardView's underlying BoardStrategy. 
	 * 
	 * @return the BoardStrategy contained by this BoardView
	 */
	public BoardStrategy getBoardStrategy() {
		return strategy;
	}
	
	/**
	 * Update the display of the board using the method of the BoardStrategy interface
	 * To be left private and called by isNotified()
	 */
	private void drawBoard() {
		// TODO Auto-generated method stub
		
	}
	
	public void isNotified() {
		
	}
}
