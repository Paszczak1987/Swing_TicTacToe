package com.lpaszko;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int DEFAULT_WIDTH = 320;
	private final int DEFAULT_HEIGHT = 374;
	private BtnPanel btnPanel = null;
	private JTextArea display = null;
	private Game game = null;

	public MainFrame() {

		setTitle("Kó³ko i krzy¿yk");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int windowLocationY = toolkit.getScreenSize().height / 2 - getSize().height / 2;
		int windowLocationX = toolkit.getScreenSize().width / 2 - getSize().width / 2;
		setLocation(windowLocationX, windowLocationY);

		btnPanel = new BtnPanel(this);

		display = new JTextArea();
		display.setPreferredSize(new Dimension(250, 24));
		display.setFont(new Font("Arial", Font.PLAIN, 17));
		display.setEditable(false);

		game = new Game(this);

		//add to content container
		add(display);
		add(btnPanel);

		setLayout(new FlowLayout());

	}

	public void setDisplayMessage(String message) {
		display.setText(message);
	}

	public Game getGame() {
		return game;
	}

	public BtnPanel getBtnPanel() {
		return btnPanel;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				var frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}// main

}// MainFrame
