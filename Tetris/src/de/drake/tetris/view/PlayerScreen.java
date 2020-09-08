package de.drake.tetris.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.drake.tetris.config.Config;
import de.drake.tetris.gfx.Assets;
import de.drake.tetris.model.Spieler;
import de.drake.tetris.model.Spielfeld;
import de.drake.tetris.model.Stein;
import de.drake.tetris.states.GameState;
import de.drake.tetris.util.Position;

/**
 * Ein Panel, welches das Spielfeld eines Spielers anzeigt.
 */
public class PlayerScreen extends JPanel {
	
	/**
	 * Die default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private GameState gameState;
	
	/**
	 * Der Spieler, dessen Spielfeld hier angezeigt wird.
	 */
	private final Spieler spieler;
	
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
	public PlayerScreen(final GameState gameState, final Spieler spieler) {
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
	 * @param höhe_feld Die Höhe eines Tetris-Feldes
	 */
	private void berechneSpielfeldaufteilung() {
		
		//Höhe und Breite eines Tetrisfeldes auf Basis des zur Verfügung stehenden Platzes ermitteln
		this.höhe_feld = (this.getHeight() - 4) / Config.hoehe;
		this.breite_feld = (this.getWidth() - 5) / (Config.breite + this.previewfelder + 1);
		if (((double) this.breite_feld) / this.höhe_feld < Config.feld_seitenverhaeltnis) {
			this.höhe_feld = (int) (this.breite_feld / Config.feld_seitenverhaeltnis);
		} else {
			this.breite_feld = (int) (this.höhe_feld * Config.feld_seitenverhaeltnis);
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
		g.setColor(Color.getHSBColor((float) 0, (float) 0, (float) .1));
		
		//Rahmen um Spielfeld zeichnen
		g.drawRect(this.offsetX_sf - 1, this.offsetY_sf - 1,
				Config.breite * this.breite_feld + 1, Config.hoehe * this.höhe_feld + 1);
		
		//Felder ausmalen
		for (int spalte = 0; spalte < Config.breite; spalte++) {
			for (int zeile = 0; zeile < Config.hoehe; zeile++) {
				Position position = new Position(spalte, zeile);
				if (spielfeld.isBlocked(position)) {
					g.drawImage(Assets.getAsset(spielfeld.getColor(position), false),
							this.offsetX_sf + spalte * this.breite_feld,
							this.offsetY_sf + zeile * this.höhe_feld,
							this.breite_feld, this.höhe_feld, null);
				} else if ((spalte + zeile) % 2 == 0) {
					g.fillRect(
							this.offsetX_sf + spalte * this.breite_feld,
							this.offsetY_sf + zeile * this.höhe_feld,
							this.breite_feld, this.höhe_feld);
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
			g.drawImage(Assets.getAsset(stein.getColor(), true),
					this.offsetX_sf + spalte * this.breite_feld,
					this.offsetY_sf + zeile * this.höhe_feld,
					this.breite_feld, this.höhe_feld, null);
		}
		
		//Status schreiben
		g.setColor(Color.lightGray);
		int fontsize = 3 * this.höhe_feld;
		g.setFont(new Font("Serif", Font.BOLD, fontsize));
		switch (this.gameState.getState()) {
		case GameState.PREPARED:
			g.drawString("READY?", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 4),
					this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
			break;
		case GameState.QUIT:
			g.drawString("BEENDEN?", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 5),
					this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
			break;
		case GameState.PAUSED:
			g.drawString("PAUSE", 
					this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 3),
					this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
			break;
		default:
			switch (this.spieler.getState()) {
			case Spieler.WINNER:
				g.drawString("WINNER", 
						this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 4),
						this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
				break;
			case Spieler.LOSER:
				g.drawString("LOSER", 
						this.offsetX_sf + this.breite_feld * (Config.breite / 2 - 3),
						this.offsetY_sf + this.höhe_feld * (Config.hoehe / 2));
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
		Stein stein = this.spieler.getNextStein();
		for (Position position : stein.getRelativkoordinaten()) {
			zeile = previewfelder / 2 + position.getY() - 1;
			spalte = previewfelder / 2 + position.getX();
			g.drawImage(Assets.getAsset(stein.getColor(), true),
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
		g.setFont(new Font("Serif", Font.BOLD, fontsize));
		g.drawString(this.spieler.getName(), 
				this.offsetX_i, this.offsetY_i + fontsize);
		g.drawString("Steine: " + this.spieler.getAnzahlSteine(),
				this.offsetX_i, this.offsetY_i + 3 * fontsize);
		g.drawString("Reihen: " + this.spieler.getFertigeReihen(), 
				this.offsetX_i, this.offsetY_i + 5 * fontsize);
			
		
	}
}