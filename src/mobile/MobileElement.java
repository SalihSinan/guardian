package mobile;

import java.util.ArrayList;
import java.util.List;

import carte.Obstacle;
import carte.Carte;
import config.GameConfiguration;

public abstract class MobileElement {

	private Obstacle position;

	public MobileElement(Obstacle position, Carte map) {
		this.position = position;
	}

	public List<Obstacle> getZone(Gardien gardien, Carte map) {
		Obstacle position = gardien.getPosition();
		List<Obstacle> zone = new ArrayList<>();

		for (int i = 0; i < GameConfiguration.LINE_COUNT; i++) {

			for (int j = 0; j < GameConfiguration.COLUMN_COUNT; j++) {

				if (i != position.getLine() || j != position.getColumn()) { 
																			

					int rayon = Math.abs(position.getLine() - i) + Math.abs(position.getColumn() - j);
					if (rayon <= GameConfiguration.SIZE_VISION) {
						Obstacle newPos = map.getBlock(i, j);
						if (newPos.isFloor() || newPos.isWater()) {
							zone.add(newPos);
						}

					}

				}
			}
		}

		return zone;
	}

	public List<Obstacle> getZone(Intru intru, Carte map) {
		Obstacle position = intru.getPosition();
		List<Obstacle> zone = new ArrayList<>();

		for (int i = 0; i < GameConfiguration.LINE_COUNT; i++) {

			for (int j = 0; j < GameConfiguration.COLUMN_COUNT; j++) {

				if (i != position.getLine() || j != position.getColumn()) { 

					int rayon = Math.abs(position.getLine() - i) + Math.abs(position.getColumn() - j);
					if (rayon <= GameConfiguration.SIZE_VISION) {
						Obstacle newPos = map.getBlock(i, j);
						if (newPos.isFloor() || newPos.isWater()) {
							zone.add(newPos);
						}

					}

				}
			}
		}

		return zone;
	}

	public Obstacle getPosition() {
		return position;
	}

	public void setPosition(Obstacle position) {
		this.position = position;
	}
}
