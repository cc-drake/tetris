package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.util.Position;

public class SquareBombProcess extends Process {
	
	private final Position mittelpunkt;

	public SquareBombProcess(final Player player, final Position mittelpunkt) {
		super(player);
		this.mittelpunkt = mittelpunkt;
		
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
		player.getSpielfeld().clear3x3(this.mittelpunkt);
		
		HashSet<Integer> rowsToRemove = new HashSet<Integer>();
		rowsToRemove.add(this.mittelpunkt.getY() - 1);
		rowsToRemove.add(this.mittelpunkt.getY());
		if (this.mittelpunkt.getY() < Config.hoehe - 1)
			rowsToRemove.add(this.mittelpunkt.getY() + 1);
		player.startProcess(new FallingRowsProcess(
				player, rowsToRemove, this.mittelpunkt.getX() - 1, this.mittelpunkt.getX() + 1));
	}
	
}