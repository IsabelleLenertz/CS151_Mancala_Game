import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
/**
 * Strategy used to display a board with round pits
 * Used by the BoardView to define the shape and colors of the board
 * @author Isabelle Delmas
 * @date 05-26-2018
 * @revision 					Reason:
 *
 */
public class RoundBoard implements BoardStrategy {

	// Constants
	private static final int STONES_PER_ROW = 3;
	private static final int STONES_PER_COL = 3;
	private static final int STONES_PER_ROW_M = 3;
	private static final int STONES_PER_COL_M = 10;
	private static final int CHAR_HEIGHT_OFFSET = 13;
	private static final int WIDTH_ARC = 10;
	private static final int HEIGHT_ARC = WIDTH_ARC;
	private static final Color BACKGROUND_COLOR = new Color(0, 196, 117);
	private static final Color MANCALA_COLOR = new Color(119, 253, 199);
	private static final Color PIT_COLOR = new Color(255, 255, 120);
	private static final Color STONE_COLOR = new Color(78, 60, 145);
	private static final Color BORDER_COLOR = new Color(0, 0, 0);
	private static final Color TEXT_COLOR = new Color(0, 6, 4);
	private final static float DASH1[] = {10.0f};
    private final static BasicStroke DASHED_STROKE = new BasicStroke(4.0f ,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, DASH1, 0.0f);
    private final static BasicStroke BASIC_STROKE = new BasicStroke(1.0f ,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	
	/**
	 * Display the score of the player horizontally on the board view
	 * "Score: [score]"
	 * @param score score to display
	 * @param xPosition x position of the score (upper left corner)
	 * @param yPosition y position of the score (upper left corner)
	 * @param g2 graphical context
	 */
	public void displayScore(int score, int xPosition, int yPosition, Graphics2D g2) {
		yPosition += CHAR_HEIGHT_OFFSET;
		g2.setColor(TEXT_COLOR);
		g2.drawString("Score: " + score, xPosition, yPosition);
		
	}
	
	/**
	 * Display a label on the board view
	 * @param label string to display
	 * @param xPosition x position of the label (upper left corner)
	 * @param yPosition y position of the label (upper left corner)
	 * @param g2 graphical context
	 */
	public void displayPitLabel(String label, int xPosition, int yPosition, Graphics2D g2) {
		yPosition += CHAR_HEIGHT_OFFSET;
		g2.setColor(TEXT_COLOR);
		g2.drawString(label, xPosition, yPosition);

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
	public void displayPlayer(String playerName, int xPosition, int yPosition, Graphics2D g2) {
		g2.setColor(TEXT_COLOR);
		yPosition += CHAR_HEIGHT_OFFSET;
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
	public void drawBoardBackground( int xPosition, int yPosition, int height, int width, Graphics2D g2) {
		// Draw the background
		g2.setColor(BACKGROUND_COLOR);
		g2.fillRoundRect(xPosition, yPosition, width, height, WIDTH_ARC, HEIGHT_ARC);

		// Draw the boarder
		g2.setColor(BORDER_COLOR);
		g2.setStroke(DASHED_STROKE);
		g2.drawRoundRect(xPosition, yPosition, width, height, WIDTH_ARC, HEIGHT_ARC);
		
		// Reset the stroke
		g2.setStroke(new BasicStroke());
	}
	
	/**
	 * Draw a pit on the board view
	 * @param xPosition x position of the pit (upper left corner)
	 * @param yPosition y position of the pit (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 * @return reference to the shape that represents the Pit
	 */
	public Shape drawPit(int numberOfStones, int xPosition, int yPosition, int height, int width, Graphics2D g2) {
		// Set the stroke to round borders
		g2.setStroke(BASIC_STROKE);

		
		// Create and draw the rectangle for the pit
		Shape hole = new Ellipse2D.Double(xPosition, yPosition, width, height);
		g2.setColor(PIT_COLOR);
		g2.fill(hole);
		g2.setColor(BORDER_COLOR);
		g2.draw(hole);
		
		// Create and draw all the stones if room enough to display them		
		if (numberOfStones <= 4) {
			int stoneWidth = width/(STONES_PER_ROW + 1);
			int stoneHeight = height/(STONES_PER_COL + 1);
			int widthInterval = stoneWidth/(STONES_PER_ROW + 1);
			int heightInterval = stoneHeight/(STONES_PER_COL + 1);
			int stoneInitialX = xPosition + widthInterval;
			int stoneInitialY = yPosition + heightInterval;
			int stoneX = stoneInitialX;
			int stoneY = stoneInitialY;
			for(int i = 0; i < numberOfStones; ) {
				if (stoneX+stoneWidth > (xPosition+width)) {
					stoneX = stoneInitialX;
					stoneY += stoneHeight + heightInterval;
				}
				Ellipse2D.Double stone = new Ellipse2D.Double(stoneX, stoneY, stoneHeight, stoneWidth);
				if(hole.contains(stoneX,  stoneY,  stoneWidth,  stoneHeight)) {
					g2.setColor(STONE_COLOR);
					g2.fill(stone);
					g2.setColor(BORDER_COLOR);
					g2.draw(stone);
					i++;
				}
				stoneX += stoneWidth + widthInterval;

			}
		// Create a string if there are more stones
		} else {
			g2.setColor(STONE_COLOR);
			g2.drawString("" + numberOfStones, xPosition+(width)/2, yPosition + height/2);
		}
		
		// Reset the stroke
		g2.setStroke(new BasicStroke());
		
		return hole;
	}

	/**
	 * Draw a elliptical mancala with elliptical stones on the board view
	 * @param xPosition x position of the pit (upper left corner)
	 * @param yPosition y position of the pit (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 * @return reference to the shape that represents the Mancala
	 */
	public Shape drawMancala(int numberOfStones, int xPosition, int yPosition, int width, int height, Graphics2D g2) {
		// Set the stroke to round borders
		g2.setStroke(BASIC_STROKE);
		
		// Create and draw the rectangle for the pit
		Shape hole = new RoundRectangle2D.Double(xPosition, yPosition, width, height, WIDTH_ARC, WIDTH_ARC );
		g2.setColor(MANCALA_COLOR);
		g2.fill(hole);
		g2.setColor(BORDER_COLOR);
		g2.draw(hole);
		
		// Create and draw all the stones if room enough to display them		
		if (numberOfStones <= (STONES_PER_ROW*STONES_PER_COL)) {
			int stoneWidth = width/(STONES_PER_ROW_M + 1);
			int stoneHeight = height/(STONES_PER_COL_M + 3);
			int widthInterval = stoneWidth/(STONES_PER_ROW_M + 1);
			int heightInterval = stoneHeight/2;
			int stoneInitialX = xPosition + widthInterval;
			int stoneInitialY = yPosition + heightInterval;
			int stoneX = stoneInitialX;
			int stoneY = stoneInitialY;
			for(int i = 0; i < numberOfStones; i++) {
				if (stoneX+stoneWidth > (xPosition+width)) {
					stoneX = stoneInitialX;
					stoneY += stoneHeight + heightInterval;
				}
				Ellipse2D.Double stone = new Ellipse2D.Double(stoneX, stoneY, stoneHeight, stoneWidth);
				g2.setColor(STONE_COLOR);
				g2.fill(stone);
				g2.setColor(BORDER_COLOR);
				g2.draw(stone);
				stoneX += stoneWidth + widthInterval;

			}
		// Create a string if there are more stones
		} else {
			g2.setColor(STONE_COLOR);
			g2.drawString("" + numberOfStones, xPosition+(width)/3, yPosition + height/2);
		}
		
		// Reset the stroke
		g2.setStroke(new BasicStroke());	
		
		return hole;
	}

}
