
/**
 * Interface for the View in the MVC pattern
 * Used by the change Listener to draw the board when a change occurs in the model
 * @author Isabelle Delmas
 * @date 04-31-18
 * @revision				Reason:
 *
 */
public interface View {
	
	/**
	 * Set the strategy used to display the board
	 * @param strategy strategy to be used to display the board
	 */
	public void setBoardStrategy(BoardStrategy strategy);
	
	/**
	 * get a reference to the strategy used to display the board
	 * @return reference to the current strategy
	 */
	public BoardStrategy getBoardStrategy();
	
	/**
	 * Update the display of the board using the method of the BoardStrategy interface
	 * To be left private and called by isNotified()
	 */
	void drawBoard();
	
	/**
	 * Notify the View to update the display
	 * Called by the model (ie, BoardModel)
	 * This is makes the view interface our change listener.
	 */
	public void isNotified();
}