package gui;

import java.awt.Graphics;

import javax.swing.JPanel;
import process.Element; // Assurez-vous que le chemin est correct

import mobile.Gardien;
import mobile.Intru;

import carte.Carte;




public class GameDisplay extends JPanel {
	private static final long serialVersionUID = 1L;
	private Carte map;
	private Element manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	

	public GameDisplay(Carte map, Element manager) {
		this.map = map;
		this.manager = manager;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		paintStrategy.paintMap(map, g);
		
		for (Gardien gardien : manager.getGardiens()) {
			if (gardien.etatPoursuite == true) {
				paintStrategy.paintGardienPoursuite(gardien, g);		
			}else {
				paintStrategy.paintGardien(gardien, g);				
			}
			paintStrategy.paintVision(gardien, g,map);
		}	

		for (Intru intru : manager.getIntrus()) {
			paintStrategy.paintIntru(intru, g);
			paintStrategy.paintVision(intru, g,map);
		}		
	}

	public Element getManager() {
		return this.manager;
	}
}
