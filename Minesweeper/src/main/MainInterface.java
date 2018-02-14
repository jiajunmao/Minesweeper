package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainInterface {

	private JFrame frame;
	private int row = 9;
	private int column = 9;

	private int mineNum = 10;
	private boolean isOver;

	private MineList mineList;

	public MainInterface(int screenWidth, int screenHeight) {
		mineList = new MineList(row, column, mineNum);
		isOver = false;
		generateFrame(screenWidth, screenHeight);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public boolean getGameStat() {
		//System.out.println(isOver);
		return isOver;
	}
	
	public void setGameStat(boolean isOver) {
		this.isOver = isOver;
	}

	public MineList getMineList() {
		return mineList;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getTotalField() {
		return row * column;
	}

	public int getMineNum() {
		return mineNum;
	}

	public void detectClick() {
		JPanel tempPanel = (JPanel) frame.getContentPane();
		for (int i = 0; i < row * column; i++) {
			MineButton temp = (MineButton) tempPanel.getComponent(i);
			temp.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (temp.contains(new Point(e.getX(), e.getY()))) {
							if (temp.isMine()) {
								temp.setBackground(Color.red);
								isOver = true;
							} else {
								temp.detectRange();
							}
						}
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						if (!temp.isMarked()) {
							temp.setBackground(Color.ORANGE);
							temp.setSkip(true);
							temp.setMarked(true);
						} else {
							temp.setBackground(Color.LIGHT_GRAY);
							temp.setSkip(false);
							temp.setMarked(false);
						}
					}
				}
			});
		}

	}

	private void generateFrame(int width, int height) {
		int enlargeRatio = (int) (width / 1000);

		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(0, 0, (row * 50 + 110), (column * 50 + 160));
		frame.setSize((row * 50 * enlargeRatio + 110 * enlargeRatio),
				(column * 50 * enlargeRatio + 160 * enlargeRatio));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				MineButton tempButton = mineList.getMineList()[i][j];
				tempButton.setBounds(i * 50 * enlargeRatio + 50 * enlargeRatio,
						j * 50 * enlargeRatio + 50 * enlargeRatio, 50 * enlargeRatio, 50 * enlargeRatio);
				tempButton.setBackground(Color.LIGHT_GRAY);
				frame.add(tempButton);
			}
		}

		JLabel remainMine = new JLabel();
		remainMine.setText("" + mineNum);
		remainMine.setBackground(Color.BLACK);
		remainMine.setForeground(Color.WHITE);
		remainMine.setBounds(0,0,50,50);
		frame.add(remainMine);
	}
	
	public void close() 
	{
		frame.dispose();
	}
}
