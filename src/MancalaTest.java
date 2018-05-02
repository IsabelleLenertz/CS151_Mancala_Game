
/**
 * Class containing the main method
 * 
 *
 */
public class MancalaTest {

	public static void main(String[] args) {
		int stones;
		BoardStrategy selectedStragegy;
		BoardModel boardModel = new BoardModel();
		BoardView boardView = new BoardView(1000);
		boardView.setBoardModel(boardModel);
		boardModel.setBoardView(boardView);
		StartWindow sw = new StartWindow();
		
	}

}
