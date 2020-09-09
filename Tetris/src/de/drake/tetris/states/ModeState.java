package de.drake.tetris.states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Der ModeState erlaubt die Auswahl des Spielmodus
 */
public class ModeState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final JPanel screen;
	
	private final static String solitaer = "Solitär";
	private final static String race = "Race";
	private final static String combat = "Combat";
	private final static String cheese = "Cheese";
	private final static String back = "Zurück";
	
	/**
	 * Erstellt einen neuen ModeState.
	 */
	ModeState() {
		
		this.screen = new JPanel();
		this.screen.setBackground(Color.black);
		this.screen.setLayout(new BorderLayout());
		
			JPanel topPanel = new JPanel();
			topPanel.setBackground(Color.black);
			topPanel.setLayout(new GridLayout(1, 4));
			this.screen.add(new JScrollPane(topPanel), BorderLayout.CENTER);
			
				Box settingsBox = new Box(BoxLayout.Y_AXIS);
				settingsBox.setBackground(Color.black);
				topPanel.add(settingsBox);
				
					JPanel titlePanel = new JPanel();
					titlePanel.setBackground(Color.black);
					titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
					titlePanel.setLayout(new GridLayout(1, 4));
					settingsBox.add(titlePanel);
					
						JLabel solitaerLabel = new JLabel(ModeState.solitaer);
				
					JButton start = new JButton(ModeState.solitaer);
					start.setAlignmentX(Component.CENTER_ALIGNMENT);
					start.addActionListener(this);
					settingsBox.add(start);
					
					JButton start2 = new JButton(ModeState.solitaer);
					start2.setAlignmentX(Component.CENTER_ALIGNMENT);
					start2.addActionListener(this);
					settingsBox.add(start2);
					
					JButton start3 = new JButton(ModeState.solitaer);
					start3.setAlignmentX(Component.CENTER_ALIGNMENT);
					start3.addActionListener(this);
					settingsBox.add(start3);
			
		
			JPanel bottomPanel = new JPanel();
			bottomPanel.setBackground(Color.black);
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
				JButton back = new JButton(ModeState.back);
				back.addActionListener(this);
				bottomPanel.add(back);
				
			this.screen.add(bottomPanel, BorderLayout.SOUTH);
			
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
		if (e.getActionCommand() == ModeState.back)
			State.setCurrentState(State.startState);
	}
}