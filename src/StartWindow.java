import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartWindow extends JFrame{
	JButton threeStone;
	JButton fourStone;
	JButton squareStrategy;
	JButton circleStrategy;
	JButton startNow;

	public StartWindow() {
		super("Mancala");
		this.setSize(500,400);
		threeStone = new JButton("3 stones");
		fourStone = new JButton("4 stones");
		squareStrategy = new JButton("SquareStrategy");
		circleStrategy = new JButton("CircleStrategy");
		startNow = new JButton("Start Now!");
		Box stoneBox = Box.createHorizontalBox();
		Box themeBox = Box.createHorizontalBox();
		Box startBox = Box.createHorizontalBox();
		stoneBox.add(threeStone);
		stoneBox.add(fourStone);
		themeBox.add(squareStrategy);
		themeBox.add(circleStrategy);
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
