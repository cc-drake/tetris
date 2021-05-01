package de.drake.tetris.view.screens;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.drake.tetris.config.Config;
import de.drake.tetris.controller.states.StartState;
import de.drake.tetris.view.screens.util.ComponentFactory;
import de.drake.tetris.view.screens.util.LogoPanel;

public class StartScreen extends JScrollPane {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public StartScreen(final ActionListener listener) {
		JPanel contentPanel = new JPanel();
		super.setViewportView(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
			contentPanel.add(new LogoPanel(), BorderLayout.CENTER);
		
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Config.COLOR_BACKGROUND);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			contentPanel.add(buttonPanel, BorderLayout.SOUTH);
			
				buttonPanel.add(ComponentFactory.createButton
						(StartState.NEW_GAME, listener));
				buttonPanel.add(ComponentFactory.createButton
						(StartState.QUIT_GAME, listener));
		
	}

}
