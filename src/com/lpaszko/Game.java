package com.lpaszko;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Game {

	private MainFrame parent = null;
	private String status = null;
	private boolean isOTurn = false;
	private JOptionPane endMessage = null;
	private JDialog endDialog = null;

	public Game(MainFrame parent) {

		this.parent = parent;

		endMessage = new JOptionPane();
		endMessage.setIcon(null);
		endMessage.setMessageType(JOptionPane.QUESTION_MESSAGE);
		endMessage.setMessage("Czy chcesz zagraæ jeszcze raz?");
		endMessage.setOptionType(JOptionPane.YES_NO_OPTION);
		endMessage.setOptions(new String[] { "Tak", "Nie" });

		setNewGame();

	}

	private void setNewGame() {
		status = "NEW_GAME";
		isOTurn = true;
		parent.setDisplayMessage("Kolej O");
		parent.getBtnPanel().resetButtons();
	}

	private String[] convert(JButton... btnPanel) {
		String[] strings = new String[btnPanel.length];
		for (int i = 0; i < btnPanel.length; i++)
			strings[i] = btnPanel[i].getText();
		return strings;
	}

	private String checkConditions(JButton... btnPanel) {
		String[] board = convert(btnPanel);
		for (int i = 0; i <= 6; i++) {
			if ((i == 0 || i == 3) || i == 6) {
				if (board[i] == board[i + 1] && board[i] == board[i + 2])
					if (board[i] != "")
						return "WIN";
			}
			if (i == 2) {
				if (board[i] == board[i + 2] && board[i] == board[i + 4])
					if (board[i] != "")
						return "WIN";
			}
			if ((i == 0 || i == 1) || i == 2) {
				if (board[i] == board[i + 3] && board[i] == board[i + 6])
					if (board[i] != "")
						return "WIN";
			}
			if (i == 0) {
				if (board[i] == board[i + 4] && board[i] == board[i + 8])
					if (board[i] != "")
						return "WIN";
			}
		}
		int counter = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i].equals("X") || board[i].equals("O"))
				counter++;
			if (counter == 9)
				return "DRAW";
		}
		return "NOTHING";
	}

	@SuppressWarnings("deprecation")
	private void showEndDialog() {
		endDialog = endMessage.createDialog(parent, "Koniec gry");
		endDialog.show();
		String selectedValue = (String) endMessage.getValue();
		if (selectedValue.equals("Tak")) {
			setNewGame();
		} else
			parent.dispose();
	}
	
	public void setOTurn(boolean value) {
		isOTurn = value;
	}

	public boolean isOTurn() {
		return isOTurn;
	}

	public void stateUpdate(JButton... btnPanel) {
		if (status.equals("NOTHING") || status.equals("NEW_GAME")) {
			parent.setDisplayMessage(isOTurn ? "Kolej O" : "Kolej X");
			status = checkConditions(btnPanel);
			if (status.equals("WIN") || status.equals("DRAW")) {
				if (status.equals("WIN")) {
					parent.setDisplayMessage(isOTurn ? "Wygrywa X" : "Wygrywa O");
					endMessage.setMessage("Wygrywa " + (isOTurn ? "X" : "O") + "\n Czy chcesz graæ ponownie?");
				} else {
					parent.setDisplayMessage("Remis");
					endMessage.setMessage("Remis\n Czy chcesz graæ ponownie?");
				}
				showEndDialog();
			}
		}
	}

}// Game
