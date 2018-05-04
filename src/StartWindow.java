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
	int numberOfStone;
	BoardStrategy selectedStrategy;
/**
 * ctor that creates five buttons, add listeners to the buttons
 */
	public StartWindow() {
		super("Mancala");
		this.setSize(500,400);
		threeStone = new JButton("3 stones");
		threeStone.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    numberOfStone = 3;
			    System.out.println(3);
			  } 
			} );
		
		fourStone = new JButton("4 stones");
		fourStone.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    numberOfStone = 4;
			    System.out.println(4);
			  } 
			} );
		squareStrategy = new JButton("SquareStrategy");
		squareStrategy.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    selectedStrategy =  new SquareBoard();
			    System.out.println("square");
			  } 
			} );
		roundStrategy = new JButton("CircleStrategy");
		roundStrategy.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    selectedStrategy =  new RoundBoard();
			    System.out.println("round");
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
		box.add(Box.createVerticalStrut(100));
		box.add(themeBox);
		box.add(Box.createVerticalStrut(100));
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
		BoardView boardView = new BoardView(1000);
		boardView.setBoardModel(boardModel);
		boardModel.setBoardView(boardView);
		boardView.setBoardStrategy(selectedStrategy);
		JFrame gameFrame = new JFrame();
		gameFrame.setSize(1000,500);
		gameFrame.add(boardView);
		gameFrame.setVisible(true);
		System.out.println("reached start game");
	}
	
}
