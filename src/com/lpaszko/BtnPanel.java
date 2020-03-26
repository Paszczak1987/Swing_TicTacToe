package com.lpaszko;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BtnPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton[] buttons = new JButton[9];
	private MainFrame parent = null;

	public BtnPanel(MainFrame parent) {
		
		this.parent = parent;
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].addActionListener(this);// dla for each nie dzia³a addActionListener
			buttons[i].setFont(new Font("Arial", Font.PLAIN, 45));
			add(buttons[i]);
		}

		setPreferredSize(new Dimension(300, 300));
		setSize(getPreferredSize().width, getPreferredSize().height);
		setLayout(new GridLayout(3, 3));
	
	}

	private void setSymbol(JButton button) {
		boolean isOTurn = parent.getGame().isOTurn();
		if (button.getText().equals("")) {
			if (isOTurn)
				button.setText("O");
			else
				button.setText("X");
			parent.getGame().setOTurn(!isOTurn);
		}
	}

	public void resetButtons() {
		for (JButton button : buttons)
			button.setText("");
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		for (JButton button : buttons) {
			if (e.getSource().equals(button)) {
				setSymbol(button);
			}
		}
		parent.getGame().stateUpdate(buttons);
	}

}// BtnPanel
