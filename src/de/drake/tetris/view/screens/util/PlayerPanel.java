package de.drake.tetris.view.screens.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Map.Entry;

import javax.swing.JPanel;

import de.drake.tetris.assets.Asset;
import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.model.Block;
import de.drake.tetris.model.Game;
import de.drake.tetris.model.Player;
import de.drake.tetris.model.Spielfeld;
import de.drake.tetris.model.stones.Stone;
import de.drake.tetris.model.util.Position;

/**
 * Ein Panel, welches das Spielfeld eines Spielers anzeigt.
 */
public class PlayerPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	/**
	 * Der Spieler, dessen Spielfeld hier angezeigt wird.
	 */
	private final Player spieler;
	
	/**
	 * Die Höhe eines Tetris-Feldes in Pixeln.
	 */
	private int höhe_feld;
	
	/**
	 * Die Breite eines Tetris-Feldes in Pixeln.
	 */
	private int breite_feld;
	
	/**
	 * X-Koordinate des ersten Tetris-Spielfeldes links oben
	 */
	private int offsetX_sf;
	
	/**
	 * Y-Koordinate des ersten Tetris-Spielfeldes links oben
	 */
	private int offsetY_sf;
	
	/**
	 * X-Koordinate des ersten Preview-Spielfeldes links oben
	 */
	private int offsetX_p;
	
	/**
	 * Y-Koordinate des ersten Preview-Spielfeldes links oben
	 */
	private int offsetY_p;
	
	/**
	 * X-Koordinate des Info-Bereichs links oben
	 */
	private int offsetX_i;
	
	/**
	 * Y-Koordinate des Info-Bereichs links oben
	 */
	private int offsetY_i;
	
	/**
	 * Anzahl der Preview-Zeilen bzw. Spalten
	 */
	private final int previewfelder = Config.getMaxSteinSize() + 2;
	
	/**
	 * Erzeugt einen neuen PlayerScreen
	 * 
	 * @param spieler
	 * 		Der Spieler, dessen Spielfeld angezeigt werden soll.
	 */
	public PlayerPanel(final Game game, final Player spieler) {
		this.game = game;
		this.spieler = spieler;
		this.setBackground(Color.black);
		this.setFocusable(false);
	}
	
	/**
	 * Zeichnet den Screen.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.berechneSpielfeldaufteilung();
		this.paintSpielfeld(g);
		this.paintPreview(g);
		this.paintInfos(g);
		g.dispose();
	}

	/**
	 * Berechnet die Aufteilung des Spielfelds. Die Ergebnisse werden in den Attributen dieser
	 * Klasse zwischengespeichert.
	 * 
	 * @param g Das Graphics, mit dem gezeichnet wird
	 * @param breite_feld Die Breite eines Tetris-Feldes
	 * @param höhe_feld Die Höhe eines Tetris-Feldes
	 */
	private void berechneSpielfeldaufteilung() {
		
		//Höhe und Breite eines Tetrisfeldes auf Basis des zur Verfügung stehenden Platzes ermitteln
		this.höhe_feld = (this.getHeight() - 4) / Config.hoehe;
		this.breite_feld = (this.getWidth() - 5) / (Config.breite + this.previewfelder + 1);
		double seitenverhaeltnis = ((double) Asset.SPRITE_WIDTH) / ((double) Asset.SPRITE_HEIGHT);
		if (((double) this.breite_feld) / this.höhe_feld < seitenverhaeltnis) {
			this.höhe_feld = (int) (this.breite_feld / seitenverhaeltnis);
		} else {
			this.breite_feld = (int) (this.höhe_feld * seitenverhaeltnis);
		}
		
		//Höhe und Breite des für das Spielfeld zur Verfügung stehenden Platzes ermitteln
		int höhe_sf = this.getHeight() - 4;
		int breite_sf = this.getWidth() - 5 - (this.previewfelder + 1) * breite_feld; 
		
		//Offset-Koordinaten ermitteln, bei denen die Tetris-Felder des Spielfeldes beginnen
		this.offsetX_sf = (int) ((breite_sf - this.breite_feld * Config.breite) / 2);
		this.offsetY_sf = (int) ((höhe_sf - this.höhe_feld * Config.hoehe) / 2);
		
		//Offset-Koordinaten ermitteln, bei denen die Tetris-Felder des Previewfeldes beginnen
		this.offsetX_p = breite_sf + 3 + this.breite_feld;
		this.offsetY_p = this.offsetY_sf;
		
		//Offset-Koordinaten ermitteln, bei denen der Info-Bereich beginnt
		this.offsetX_i = this.offsetX_p;
		this.offsetY_i = this.offsetY_p + this.höhe_feld * this.previewfelder + 10;
	}

	/**
	 * Zeichnet das Spielfeld.
	 * 
	 * @param g Das Graphics, mit dem gezeichnet wird
	 */
	private void paintSpielfeld(final Graphics g) {
		
		Spielfeld spielfeld = this.spieler.getSpielfeld();

		//Farbe definieren
		g.setColor(Color.getHSBColor(0f, 0f, 0.1f));
		
		//Rahmen um Spielfeld zeichnen
		g.drawRect(this.offsetX_sf - 1, this.offsetY_sf - 1,
				Config.breite * this.breite_feld + 1, Config.hoehe * this.höhe_feld + 1);
		
		//Hintergrund ausmalen
		for (int spalte = 0; spalte < Config.breite; spalte++) {
			for (int zeile = 0; zeile < Config.hoehe; zeile++) {
				if ((spalte + zeile) % 2 == 0) {
					g.fillRect(
							this.offsetX_sf + spalte * this.breite_feld,
							this.offsetY_sf + zeile * this.höhe_feld,
							this.breite_feld, this.höhe_feld);
				}
			}
		}
		
		//Blöcke einzeichnen
		Position position;
		Block block;
		for (Entry<Position, Block> entry : spielfeld.getBlocks().entrySet()) {
			if (entry.getKey().getY() < 0)
				continue;
			position = entry.getKey();
			block = entry.getValue();
			g.drawImage(block.getTexture().getSpielfeldTexture(),
					this.offsetX_sf + position.getX() * this.breite_feld,
					this.offsetY_sf + (int) ((position.getY() + block.getVerticalShift()) * this.höhe_feld),
					this.breite_feld, this.höhe_feld, null);
		}
		
		//Aktuellen Stein einzeichnen
		int zeile, spalte;
		Stone stein = this.spieler.getStone();
		if (stein != null) {
			for (Position pos : stein.getPositionen()) {
				zeile = pos.getY();
				spalte = pos.getX();
				if (zeile < 0)
					continue;
				g.drawImage(stein.getTexture().getStoneTexture(),
						this.offsetX_sf + spalte * this.breite_feld,
						this.offsetY_sf + zeile * this.höhe_feld,
						this.breite_feld, this.höhe_feld, null);
			}
		}
		
		//Status schreiben
		g.setColor(Color.lightGray);
		int fontsize = 3 * this.höhe_feld;
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		switch (this.game.getStatus()) {
		case PREPARED:
			g.drawString("READY?", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 4),
					this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
			break;
		case QUIT:
			g.drawString("BEENDEN?", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 5),
					this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
			break;
		case PAUSED:
			g.drawString("PAUSE", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 3),
					this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
			break;
		default:
			switch (this.spieler.getStatus()) {
			case WINNER:
				g.drawString("WINNER", 
						this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 4),
						this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
				break;
			case LOSER:
				g.drawString("LOSER", 
						this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 3),
						this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
				break;
			default:
				break;
			}
		}
		
	}
	
	/**
	 * Zeichnet das Preview-Fenster.
	 * @param g Das Graphics, mit dem gezeichnet wird
	 */
	private void paintPreview(final Graphics g) {
		
		//Farbe definieren
		g.setColor(Color.getHSBColor((float) 0, (float) 0, (float) .1));
		
		//Rahmen um Previewfeld zeichnen
		g.drawRect(this.offsetX_p - 1, this.offsetY_p - 1,
				previewfelder * this.breite_feld + 1, previewfelder * this.höhe_feld + 1);
		
		//Felder ausmalen
		for (int spalte = 0; spalte < previewfelder; spalte++) {
			for (int zeile = 0; zeile < previewfelder; zeile++) {
				if ((spalte + zeile) % 2 == 0) {
					g.fillRect(
							this.offsetX_p + spalte * this.breite_feld,
							this.offsetY_p + zeile * this.höhe_feld,
							this.breite_feld, this.höhe_feld);
				}
			}
		}
		
		//Preview-Stein einzeichnen
		int zeile, spalte;
		Stone stein = this.spieler.getNextStein();
		for (Position position : stein.getDefaultRelativePositions()) {
			zeile = previewfelder / 2 + position.getY() - 1;
			spalte = previewfelder / 2 + position.getX();
			g.drawImage(stein.getTexture().getStoneTexture(),
					this.offsetX_p + spalte * this.breite_feld,
					this.offsetY_p + zeile * this.höhe_feld,
					this.breite_feld, this.höhe_feld, null);
		}
	}
	
	/**
	 * Zeichnet die Informationen rechts des Spielfensters ein.
	 */
	private void paintInfos(Graphics g) {
		
		//Farbe definieren
		g.setColor(Color.magenta);
		
		//Schreiben
		int fontsize = this.höhe_feld;
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		g.drawString(this.spieler.getName(), 
				this.offsetX_i, this.offsetY_i + 1 * fontsize);
		if (GameMode.getTimeLimit() == 0) {
			g.drawString("Zeit: " + PlayerPanel.getTime(this.spieler.getVergangeneZeitSec()),
					this.offsetX_i, this.offsetY_i + 3 * fontsize);
		} else {
			g.drawString("Zeit: " + PlayerPanel.getTime(this.spieler.getVerbleibendeZeitSec()),
					this.offsetX_i, this.offsetY_i + 3 * fontsize);
		}
				
		g.drawString("Steine: " + this.spieler.getSpawnedStones(),
				this.offsetX_i, this.offsetY_i + 5 * fontsize);
		if (GameMode.is(GameMode.RACE)) {
			g.drawString("Reihen: " + this.spieler.getRemainingRaceRows(),
					this.offsetX_i, this.offsetY_i + 7 * fontsize);
		} else if (GameMode.is(GameMode.CHEESE)){
			g.drawString("Reihen: " + this.spieler.getRemainingCheeseRows(),
					this.offsetX_i, this.offsetY_i + 7 * fontsize);
		} else {
			g.drawString("Reihen: " + this.spieler.getClearedRows(),
					this.offsetX_i, this.offsetY_i + 7 * fontsize);
		}
		if (this.spieler.getPendingRows() > 0) {
			g.drawString("+" + this.spieler.getPendingRows(),
					this.offsetX_i, this.offsetY_i + 9 * fontsize);
		}
		
	}
	
	private static String getTime(int seconds) {
		int hours = seconds / 60 / 60;
		int minutes = seconds / 60 % 60;
		seconds = seconds % 60;
		
		String result = "";
		if (hours > 0) {
			result += hours + ":";
		}
		if (minutes < 10) {
			result += "0" + minutes + ":";
		} else {
			result += minutes + ":";
		}
		if (seconds < 10) {
			result += "0" + seconds;
		} else {
			result += seconds;
		}
		return result;
	}
}