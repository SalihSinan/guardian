package mobile;

import carte.Obstacle;
import carte.Carte;

public class Gardien extends MobileElement {

	public Gardien(Obstacle position, Carte map) {
		super(position, map);
	}

	public boolean etatPoursuite;
}