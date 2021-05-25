package de.drake.tetris.model.processes;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;

public class VerticalBombProcess extends Process {

	private final int column;

	public VerticalBombProcess(final Player player, final int column) {
		super(player);
		this.column = column;
		
		player.destroyStone();
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
		Player player = super.getPlayer();
		player.getSpielfeld().clearColumn(this.column);
		player.startProcess(new AddRowsProcess(player));
	}
	
}