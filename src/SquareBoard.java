import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;i
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Strategy used to display a board with square pits
 * Used by the BoardView to define the shape and colors of the board
 * @author Isabelle Delmas
 * @date 05-25-2018
 * @revision				Reason:
 *
 */
public class SquareBoard implements BoardStrategy {

	private static final int STONES_PER_ROW = 3;
	private static final int STONES_PER_COL = 3;
	private static final int NUMBER_STONE_OFFSET = 5;
	private static final int CHAR_HEIGHT_OFFSET = 10;
	private static final int WIDTH_ARC = 10;
	private static final int HEIGHT_ARC = WIDTH_ARC;
	private static final Color BACKGROUND_COLOR = new Color(53, 0, 252);
	private static final Color MANCALA_COLOR = new Color(0, 251, 233);
	private static final Color PIT_COLOR = new Color(193, 182, 236);
	private static final Color STONE_COLOR = new Color(78, 60, 145);
	private static final Color BORDER_COLOR = new Color(0, 0, 0);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);
	final static float DASH1[] = {10.0f};
    final static BasicStroke DASHED_STROKE = new BasicStroke(1.0f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, DASH1, 0.0f);

	/**
	 * Draw a rectangular pit with elliptical stones on the board view
	 * @param xPosition x position of the pit (upper left corner)
	 * @param yPosition y position of the pit (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 */
	@Override
	public void drawPit(int numberOfStones, int xPosition, int yPosition, int height, int width, Graphics2D g2) {	
		drawHole(numberOfStones, xPosition, yPosition, height, width, g2, PIT_COLOR);
	}
	
	/**
	 * Draw a rectangular hole with elliptical stones on the board view
	 * @param xPosition x position of the pit (upper left corner)
	 * @param yPosition y position of the pit (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 * @param holeColor color of the hole
	 */
	private void drawHole(int numberOfStones, int xPosition, int yPosition, int height, int width, Graphics2D g2, Color holeColor) {
		// Create and draw the rectangle for the pit
		Rectangle hole = new Rectangle(xPosition, yPosition, height, width);
		g2.setColor(holeColor);
		g2.fill(hole);
		g2.setColor(BORDER_COLOR);
		g2.draw(hole);
		
		// Create and draw all the stones if room enough to display them		
		ArrayList<Ellipse2D.Double> stones = null;
		if (numberOfStones <= (STONES_PER_ROW*STONES_PER_COL)) {
			stones = new ArrayList<Ellipse2D.Double>();
			int stoneWidth = width/10;
			int stoneHeight = height/10;
			int widthInterval = (width/10)/(STONES_PER_ROW + 1);
			int heightInterval = (height/10)/(STONES_PER_COL + 1);
			int stoneInitialX = xPosition + widthInterval;
			int stoneInitialY = xPosition + widthInterval;
			int stoneX = stoneInitialX;
			int stoneY = stoneInitialY;
			for(Ellipse2D.Double stone : stones) {
				stone = new Ellipse2D.Double(stoneX, stoneY, stoneWidth, stoneHeight);
				stoneX += widthInterval;
				if (stoneX > (xPosition+width)) {
					stoneX = stoneInitialX;
					stoneY += heightInterval;
				}
				g2.setColor(STONE_COLOR);
				g2.fill(stone);
				g2.setColor(BORDER_COLOR);
				g2.draw(stone);
			}
		// Create a string if there are more stones
		} else {
			g2.setColor(TEXT_COLOR);
			g2.drawString("" + numberOfStones, xPosition+NUMBER_STONE_OFFSET, yPosition + NUMBER_STONE_OFFSET);
		}
	}
	
	/**
	 * Draw a rectangular pit with elliptical stones on the board view
	 * @param xPosition x position of the pit (upper left corner)
	 * @param yPosition y position of the pit (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 */
	@Override
	public void drawMancala(int numberOfStones, int xPosition, int yPosition, int height, int width, Graphics2D g2) {
		drawHole(numberOfStones, xPosition, yPosition, height, width, g2, MANCALA_COLOR);		
	}

	/**
	 * Display the score of the player horizontally on the board view
	 * "Score: [score]"
	 * @param score score to display
	 * @param xPosition x position of the score (upper left corner)
	 * @param yPosition y position of the score (upper left corner)
	 * @param g2 graphical context
	 */
	@Override
	public void displayScrore(int score, int xPosition, int yPosition, Graphics2D g2) {
		g2.setColor(TEXT_COLOR);
		g2.drawString("Score: ", xPosition, yPosition);
	}

	/**
	 * Display the name of the player vertically on the board view
	 * [p]
	 * [l]
	 * [a]
	 * [y]
	 * [e]
	 * [r]
	 * [N]
	 * [a]
	 * [m]
	 * [e]
	 * @param playerName name of the player
	 * @param xPosition x position of the player (upper left corner)
	 * @param yPosition y position of the player (upper left corner)
	 * @param g2 graphical context
	 */
	@Override
	public void displayPlayer(String playerName, int xPosition, int yPosition, Graphics2D g2) {
		g2.setColor(TEXT_COLOR);
		for(char c : playerName.toCharArray()) {
			g2.drawString("" + c, xPosition, yPosition);
			yPosition += CHAR_HEIGHT_OFFSET;
		}
		
	}

	/**
	 * Display the boarder and background of the board on the board view
	 * @param height height of the board view
	 * @param width width of the board view
	 * @param g2 graphical context
	 */
	@Override
	public void drawBoardBackground(int height, int width, Graphics2D g2) {
		// Draw the background
		g2.setColor(BACKGROUND_COLOR);
		g2.fillRoundRect(0, 0, width, height, WIDTH_ARC, HEIGHT_ARC);

		// Draw the boarder
		g2.setColor(BORDER_COLOR);
		g2.setStroke(DASHED_STROKE);
		g2.drawRoundRect(0, 0, width, height, WIDTH_ARC, HEIGHT_ARC);
	}


}
