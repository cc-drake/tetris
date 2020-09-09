package de.drake.tetris.states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.drake.tetris.view.LogoScreen;

/**
 * Der StartState besteht aus dem Startbildschirm.
 */
public class StartState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final JPanel screen;
	
	private final static String newGame = "Neues Spiel";
	private final static String run = "Run (Remove later)";//TODO
	private final static String endGame = "Beenden";
	
	/**
	 * Erstellt einen neuen StartState.
	 */
	StartState() {
		
		this.screen = new JPanel();
		this.screen.setBackground(Color.black);
		this.screen.setLayout(new BorderLayout());
		this.screen.add(new JScrollPane(new LogoScreen()), BorderLayout.CENTER);
		
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.black);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
				JButton start = new JButton(StartState.newGame);
				start.addActionListener(this);
				buttonPanel.add(start);
				
				JButton run = new JButton(StartState.run);//TODO
				run.addActionListener(this);
				buttonPanel.add(run);
				
				JButton end = new JButton(StartState.endGame);
				end.addActionListener(this);
				buttonPanel.add(end);
				
			this.screen.add(buttonPanel, BorderLayout.SOUTH);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == StartState.newGame)
			State.setCurrentState(State.modeState);
		if (e.getActionCommand() == StartState.run)//TODO
			State.setCurrentState(new GameState());
		if (e.getActionCommand() == StartState.endGame)
			System.exit(0);
	}

	@Override
	public void tick() {
	}

	@Override
	public JPanel getScreen() {
		return this.screen;
	}
		
}