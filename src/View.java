import java.awt.Shape;
import java.awt.event.MouseListener;


/**
 * Interface for the View in the MVC pattern
 * Used by the change Listener to draw the board when a change occurs in the model
 * @author Isabelle Delmas
 * @date 04-31-18
 * @revision 05-05-18				Reason: added getShape(), needed by the controller
 *
 */
public interface View{
	
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
	 * Notify the View to update the display
	 * Called by the model (ie, BoardModel)
	 * This makes the view interface our change listener.
	 */
	public void isNotified();
	
	/**
	 * Get a reference to the shape display information about each holes (+ undo button)
	 * Can be called by the controller to determine location of a click and call the Model's mutator
	 * @return
	 */
	public Shape[] getShapes();
	
	/**
	 * Set the Model used to get the data to display.
	 * 
	 * @param model the BoardModel associated with this BoardView
	 */
	public void setBoardModel(BoardModel model) ;
	
	/**
	 * Add a mouseListener to the view (make sure the view extends Component, which will take care of the implementation of that method)
	 * @param ml mouse listener to add
	 */
	public void addMouseListener(MouseListener ml);
	
}
