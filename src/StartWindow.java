import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * start window is for users to choose initial stones and theme
 * @author yilinzhao
 *@date 05/02/18
 */
public class StartWindow extends JFrame{
	JButton threeStone;
	JButton fourStone;
	JButton squareStrategy;
	JButton roundStrategy;
	JButton startNow;
	final int START_WINDOW_DIMENSION = 400;
	final int GAME_BOARD_WIDTH = 1000;
	final int GAME_BOARD_HEIGHT = 500;
	final int VERTICAL_DISTANCE_BETWEEN_BUTTONS = 100;
	int numberOfStone;
	BoardStrategy selectedStrategy;
/**
 * ctor that creates five buttons, add listeners to the buttons
 */
	public StartWindow() {
		super("Mancala");
		this.setSize(START_WINDOW_DIMENSION,START_WINDOW_DIMENSION);
		threeStone = new JButton("3 stones");
		threeStone.setOpaque(true);
		threeStone.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    numberOfStone = 3;
			    System.out.println(3);
			    threeStone.setBackground(Color.BLUE);
			    fourStone.setBackground(Color.WHITE);
			  } 
			} );
		
		fourStone = new JButton("4 stones");
	    fourStone.setOpaque(true);
		fourStone.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    numberOfStone = 4;
			    System.out.println(4);
			    fourStone.setBackground(Color.BLUE);
			    threeStone.setBackground(Color.WHITE);
			  } 
			} );
		squareStrategy = new JButton("SquareStrategy");
	    squareStrategy.setOpaque(true);
		squareStrategy.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    selectedStrategy =  new SquareBoard();
			    System.out.println("square");
			    squareStrategy.setBackground(Color.BLUE);
			    roundStrategy.setBackground(Color.WHITE);
			  } 
			} );
		roundStrategy = new JButton("CircleStrategy");
		roundStrategy.setOpaque(true);
		roundStrategy.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    selectedStrategy =  new RoundBoard();
			    System.out.println("round");
			    roundStrategy.setBackground(Color.BLUE);
			    squareStrategy.setBackground(Color.WHITE);
			  } 
			} );
		startNow = new JButton("Start Now!");
		startNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		Box stoneBox = Box.createHorizontalBox();
		Box themeBox = Box.createHorizontalBox();
		Box startBox = Box.createHorizontalBox();
		stoneBox.add(threeStone);
		stoneBox.add(fourStone);
		themeBox.add(squareStrategy);
		themeBox.add(roundStrategy);
		startBox.add(startNow);
		Box box = Box.createVerticalBox();
		box.add(stoneBox);
		box.add(Box.createVerticalStrut(VERTICAL_DISTANCE_BETWEEN_BUTTONS));
		box.add(themeBox);
		box.add(Box.createVerticalStrut(VERTICAL_DISTANCE_BETWEEN_BUTTONS));
		box.add(startBox);
		this.add(box);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public int getNumberOfStone() {
		return numberOfStone;
	}
	public BoardStrategy getStragtegy() {
		return selectedStrategy;
	}
	/**
	 * start main game screen
	 */
	public void startGame() {
		BoardModel boardModel = new BoardModel(numberOfStone);
		BoardView boardView = new BoardView(GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
		boardView.setBoardModel(boardModel);
		boardModel.setBoardView(boardView);
		boardView.setBoardStrategy(selectedStrategy);
		JFrame gameFrame = new JFrame();
		gameFrame.setSize(GAME_BOARD_WIDTH,GAME_BOARD_HEIGHT);
		gameFrame.add(boardView);
		gameFrame.setVisible(true);
		System.out.println("reached start game");
	}
	
}
