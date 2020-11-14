package de.drake.tetris.screens.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.GameMode;
import de.drake.tetris.gfx.Assets;
import de.drake.tetris.model.PlayerController;
import de.drake.tetris.model.Spielfeld;
import de.drake.tetris.model.Stein;
import de.drake.tetris.model.util.Position;
import de.drake.tetris.states.GameState;

/**
 * Ein Panel, welches das Spielfeld eines Spielers anzeigt.
 */
public class PlayerPanel extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private GameState gameState;
	
	/**
	 * Der Spieler, dessen Spielfeld hier angezeigt wird.
	 */
	private final PlayerController spieler;
	
	/**
	 * Die H�he eines Tetris-Feldes in Pixeln.
	 */
	private int h�he_feld;
	
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
	public PlayerPanel(final GameState gameState, final PlayerController spieler) {
		this.gameState = gameState;
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
	 * @param h�he_feld Die H�he eines Tetris-Feldes
	 */
	private void berechneSpielfeldaufteilung() {
		
		//H�he und Breite eines Tetrisfeldes auf Basis des zur Verf�gung stehenden Platzes ermitteln
		this.h�he_feld = (this.getHeight() - 4) / Config.hoehe;
		this.breite_feld = (this.getWidth() - 5) / (Config.breite + this.previewfelder + 1);
		if (((double) this.breite_feld) / this.h�he_feld < Config.FELD_SEITENVERHAELTNIS) {
			this.h�he_feld = (int) (this.breite_feld / Config.FELD_SEITENVERHAELTNIS);
		} else {
			this.breite_feld = (int) (this.h�he_feld * Config.FELD_SEITENVERHAELTNIS);
		}
		
		//H�he und Breite des f�r das Spielfeld zur Verf�gung stehenden Platzes ermitteln
		int h�he_sf = this.getHeight() - 4;
		int breite_sf = this.getWidth() - 5 - (this.previewfelder + 1) * breite_feld; 
		
		//Offset-Koordinaten ermitteln, bei denen die Tetris-Felder des Spielfeldes beginnen
		this.offsetX_sf = (int) ((breite_sf - this.breite_feld * Config.breite) / 2);
		this.offsetY_sf = (int) ((h�he_sf - this.h�he_feld * Config.hoehe) / 2);
		
		//Offset-Koordinaten ermitteln, bei denen die Tetris-Felder des Previewfeldes beginnen
		this.offsetX_p = breite_sf + 3 + this.breite_feld;
		this.offsetY_p = this.offsetY_sf;
		
		//Offset-Koordinaten ermitteln, bei denen der Info-Bereich beginnt
		this.offsetX_i = this.offsetX_p;
		this.offsetY_i = this.offsetY_p + this.h�he_feld * this.previewfelder + 10;
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
				Config.breite * this.breite_feld + 1, Config.hoehe * this.h�he_feld + 1);
		
		//Felder ausmalen
		for (int spalte = 0; spalte < Config.breite; spalte++) {
			for (int zeile = 0; zeile < Config.hoehe; zeile++) {
				Position position = new Position(spalte, zeile);
				if (spielfeld.isBlocked(position)) {
					g.drawImage(Assets.getAsset(spielfeld.getStoneType(position), false),
							this.offsetX_sf + spalte * this.breite_feld,
							this.offsetY_sf + zeile * this.h�he_feld,
							this.breite_feld, this.h�he_feld, null);
				} else if ((spalte + zeile) % 2 == 0) {
					g.fillRect(
							this.offsetX_sf + spalte * this.breite_feld,
							this.offsetY_sf + zeile * this.h�he_feld,
							this.breite_feld, this.h�he_feld);
				}
			}
		}
		
		//Aktuellen Stein einzeichnen
		int zeile, spalte;
		Stein stein = this.spieler.getStein();
		for (Position position : stein.getPositionen()) {
			zeile = position.getY();
			spalte = position.getX();
			if (zeile < 0)
				continue;
			g.drawImage(Assets.getAsset(stein.getType(), true),
					this.offsetX_sf + spalte * this.breite_feld,
					this.offsetY_sf + zeile * this.h�he_feld,
					this.breite_feld, this.h�he_feld, null);
		}
		
		//Status schreiben
		g.setColor(Color.lightGray);
		int fontsize = 3 * this.h�he_feld;
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		switch (this.gameState.getState()) {
		case GameState.PREPARED:
			g.drawString("READY?", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 4),
					this.offsetY_sf + this.h�he_feld * (Config.hoehe / 2));
			break;
		case GameState.QUIT:
			g.drawString("BEENDEN?", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 5),
					this.offsetY_sf + this.h�he_feld * (Config.hoehe / 2));
			break;
		case GameState.PAUSED:
			g.drawString("PAUSE", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 3),
					this.offsetY_sf + this.h�he_feld * (Config.hoehe / 2));
			break;
		default:
			switch (this.spieler.getState()) {
			case PlayerController.WINNER:
				g.drawString("WINNER", 
						this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 4),
						this.offsetY_sf + this.h�he_feld * (Config.hoehe / 2));
				break;
			case PlayerController.LOSER:
				g.drawString("LOSER", 
						this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 3),
						this.offsetY_sf + this.h�he_feld * (Config.hoehe / 2));
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
				previewfelder * this.breite_feld + 1, previewfelder * this.h�he_feld + 1);
		
		//Felder ausmalen
		for (int spalte = 0; spalte < previewfelder; spalte++) {
			for (int zeile = 0; zeile < previewfelder; zeile++) {
				if ((spalte + zeile) % 2 == 0) {
					g.fillRect(
							this.offsetX_p + spalte * this.breite_feld,
							this.offsetY_p + zeile * this.h�he_feld,
							this.breite_feld, this.h�he_feld);
				}
			}
		}
		
		//Preview-Stein einzeichnen
		int zeile, spalte;
		Stein stein = this.spieler.getNextStein();
		for (Position position : stein.getRelativkoordinaten()) {
			zeile = previewfelder / 2 + position.getY() - 1;
			spalte = previewfelder / 2 + position.getX();
			g.drawImage(Assets.getAsset(stein.getType(), true),
					this.offsetX_p + spalte * this.breite_feld,
					this.offsetY_p + zeile * this.h�he_feld,
					this.breite_feld, this.h�he_feld, null);
		}
	}
	
	/**
	 * Zeichnet die Informationen rechts des Spielfensters ein.
	 */
	private void paintInfos(Graphics g) {
		
		//Farbe definieren
		g.setColor(Color.magenta);
		
		//Schreiben
		int fontsize = this.h�he_feld;
		g.setFont(new Font(Font.SERIF, Font.BOLD, fontsize));
		g.drawString(this.spieler.getName(), 
				this.offsetX_i, this.offsetY_i + 1 * fontsize);
		if (GameMode.timeLimit == 0) {
			g.drawString("Zeit: " + PlayerPanel.getTime(this.spieler.getVergangeneZeitSec()),
					this.offsetX_i, this.offsetY_i + 3 * fontsize);
		} else {
			g.drawString("Zeit: " + PlayerPanel.getTime(this.spieler.getVerbleibendeZeitSec()),
					this.offsetX_i, this.offsetY_i + 3 * fontsize);
		}
				
		g.drawString("Steine: " + this.spieler.getAnzahlSteine(),
				this.offsetX_i, this.offsetY_i + 5 * fontsize);
		if (GameMode.gameMode == GameMode.RACE) {
			g.drawString("Reihen: " + this.spieler.getVerbleibendeReihen(),
					this.offsetX_i, this.offsetY_i + 7 * fontsize);
		} else if (GameMode.gameMode == GameMode.CHEESE){
			g.drawString("Reihen: " + this.spieler.getCheeseReihen(),
					this.offsetX_i, this.offsetY_i + 7 * fontsize);
		} else {
			g.drawString("Reihen: " + this.spieler.getFertigeReihen(),
					this.offsetX_i, this.offsetY_i + 7 * fontsize);
		}
		if (this.spieler.getWartendeReihen() > 0) {
			g.drawString("+" + this.spieler.getWartendeReihen(),
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