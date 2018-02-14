package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ResultInterface extends JFrame {

	private JPanel contentPane;
	private JFrame gameWindow;
	private boolean result;
	private boolean restart;

	public ResultInterface(int xPos, int yPos, boolean result, JFrame gameWindow) {
		this.gameWindow = gameWindow;
		this.result = result;
		this.restart = false;
		generateFrame(xPos, yPos);
	}
	
	public boolean getRestart() 
	{
		return restart;
	}

	private void generateFrame(int xPos, int yPos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel resultLabel = new JLabel();
		if (this.result) {
			resultLabel.setText("You Win!");
		} else if (!this.result) {
			resultLabel.setText("You Lost!");
		}
		resultLabel.setBounds(152, 41, 108, 28);
		contentPane.add(resultLabel);

		JButton exit = new JButton("Exit");
		exit.setBackground(Color.LIGHT_GRAY);
		exit.setBounds(33, 93, 89, 23);
		contentPane.add(exit);

		JButton btnRestart = new JButton("Restart");
		btnRestart.setBackground(Color.LIGHT_GRAY);
		btnRestart.setBounds(132, 93, 89, 23);
		contentPane.add(btnRestart);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart = true;
			}
		});

		JButton btnSettings = new JButton("Settings");
		btnSettings.setBackground(Color.LIGHT_GRAY);
		btnSettings.setBounds(231, 93, 89, 23);
		contentPane.add(btnSettings);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				gameWindow.dispose();
			}
		});
	}
	
	public void close() 
	{
		this.dispose();
	}
}
