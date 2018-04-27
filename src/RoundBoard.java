import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class RoundBoard implements BoardStrategy {

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
	final static float DASH1[] = {10.0f};
    final static BasicStroke DASHED_STROKE = new BasicStroke(4.0f ,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, DASH1, 0.0f);
    final static BasicStroke BASIC_STROKE = new BasicStroke(1.0f ,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	
	@Override
	public void displayScrore(int score, int xPosition, int yPosition, Graphics2D g2) {
		yPosition += CHAR_HEIGHT_OFFSET;
		g2.setColor(TEXT_COLOR);
		g2.drawString("Score: " + score, xPosition, yPosition);
		
	}

	@Override
	public void displayPlayer(String playerName, int xPosition, int yPosition, Graphics2D g2) {
		g2.setColor(TEXT_COLOR);
		yPosition += CHAR_HEIGHT_OFFSET;
		for(char c : playerName.toCharArray()) {
			g2.drawString("" + c, xPosition, yPosition);
			yPosition += CHAR_HEIGHT_OFFSET;
		}
		
	}

	@Override
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
	@Override
	public void drawPit(int numberOfStones, int xPosition, int yPosition, int height, int width, Graphics2D g2) {
		// Set the stroke to round borders
		g2.setStroke(BASIC_STROKE);

		
		// Create and draw the rectangle for the pit
		Ellipse2D.Double hole = new Ellipse2D.Double(xPosition, yPosition, width, height);
		g2.setColor(PIT_COLOR);
		g2.fill(hole);
		g2.setColor(BORDER_COLOR);
		g2.draw(hole);
		
		// Create and draw all the stones if room enough to display them		
		if (numberOfStones <= (STONES_PER_ROW*STONES_PER_COL)) {
			int stoneWidth = width/(STONES_PER_ROW + 1);
			int stoneHeight = height/(STONES_PER_COL + 1);
			int widthInterval = stoneWidth/(STONES_PER_ROW + 1);
			int heightInterval = stoneHeight/(STONES_PER_COL + 1);
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
			g2.drawString("" + numberOfStones, xPosition+(width)/2, yPosition + height/2);
		}
		
		// Reset the stroke
		g2.setStroke(new BasicStroke());		
	}

	@Override
	public void drawMancala(int numberOfStones, int xPosition, int yPosition, int width, int height, Graphics2D g2) {
		// Set the stroke to round borders
		g2.setStroke(BASIC_STROKE);
		
		// Create and draw the rectangle for the pit
		Ellipse2D.Double hole = new Ellipse2D.Double(xPosition, yPosition, width, height);
		g2.setColor(MANCALA_COLOR);
		g2.fill(hole);
		g2.setColor(BORDER_COLOR);
		g2.draw(hole);
		
		// Create and draw all the stones if room enough to display them		
		if (numberOfStones <= (27)) {
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
	}

}
