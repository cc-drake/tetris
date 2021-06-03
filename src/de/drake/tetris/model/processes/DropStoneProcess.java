package de.drake.tetris.model.processes;

import java.util.HashSet;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.spielfeld.Spielfeld;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.util.Position;

public class DropStoneProcess extends Process {
	
	public DropStoneProcess(final Player player) {
		super(player);

		Asset.SOUND_DROP.play();
	}
	
	@Override
	protected long getDuration() {
		return 0l;
	}
	
	@Override
	protected void update() {
	}
	
	@Override
	protected void processCompleted() {
		Player player = super.getPlayer();
		Spielfeld spielfeld = player.getSpielfeld();
		Stone stone = super.getPlayer().getStone();
		
		// Stein in Blöcke verwandeln
		spielfeld.addBlocks(stone.getPositionen(), stone.getTexture(), false);
		player.destroyStone();
		
		// Liste potentiell fertiger Reihen erstellen
		HashSet<Integer> stoneRows = new HashSet<Integer>();
		for (Position position : stone.getPositionen()) {
			stoneRows.add(position.getY());
		}
		
		// Fertige Reihen ermitteln
		HashSet<Integer> fullRows = new HashSet<Integer>();
		for (int row : stoneRows) {
			if (spielfeld.rowIsComplete(row)) {
				fullRows.add(row);
			}
		}
		
		// Prozess zum Entfernen der vollen Reihen starten
		player.startProcess(new ClearRowProcess(player, fullRows));
	}
	
}