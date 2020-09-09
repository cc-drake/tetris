package de.drake.tetris.states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.drake.tetris.view.LogoScreen;

/**
 * Der MainState besteht aus dem Startbildschirm.
 */
public class MainState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final JPanel screen;
	
	private final static String newGame = "Neues Spiel";
	private final static String endGame = "Beenden";
	
	/**
	 * Erstellt einen neuen MainState.
	 */
	MainState() {
		
		this.screen = new JPanel();
		this.screen.setBackground(Color.black);
		this.screen.setLayout(new BorderLayout());
		this.screen.add(new LogoScreen(), BorderLayout.CENTER);
		
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.black);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
				JButton start = new JButton(MainState.newGame);
				start.addActionListener(this);
				buttonPanel.add(start);
				
				JButton end = new JButton(MainState.endGame);
				end.addActionListener(this);
				buttonPanel.add(end);
				
			this.screen.add(buttonPanel, BorderLayout.SOUTH);
			
	}
	
	@Override
	public void tick() {
	}

	@Override
	public JPanel getScreen() {
		return this.screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == MainState.newGame) {
			State.setCurrentState(new GameState());
		} else if (e.getActionCommand() == MainState.endGame) {
			System.exit(0);
		}
	}
}