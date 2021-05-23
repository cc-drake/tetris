package de.drake.tetris.model.processes;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;

public class VerticalBombProcess extends Process {

	private final int column;

	public VerticalBombProcess(final Player player, final int column) {
		super(player);
		this.column = column;
		
		this.player.destroyStone();
		Asset.SOUND_BOOM.play();
	}
	
	@Override
	protected long getDuration() {
		return 1000000000l;
	}
	
	@Override
	protected void update() {
	}

	@Override
	protected void processCompleted() {
		this.player.getSpielfeld().clearColumn(this.column);
		
		this.player.startProcess(new AddRowsProcess(this.player));
	}
	
}