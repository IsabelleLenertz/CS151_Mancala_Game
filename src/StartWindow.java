import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartWindow extends JFrame{
	JButton threeStone;
	JButton fourStone;
	JButton squareStrategy;
	JButton roundStrategy;
	JButton startNow;
	int numberOfStone;
	BoardStrategy selectedStrategy;

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
	
}