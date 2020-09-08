package de.drake.tetris.states;

public class GameStateManager {
	
	public final static GameState playState = new PlayState();
	public final static GameState mainState = new MainState();
	
	private static GameState currentState;
	
	public static void setState(final GameState state) {
		GameStateManager.currentState = state;
	}
	
	public static GameState getState() {
		return GameStateManager.currentState;
	}
	
}