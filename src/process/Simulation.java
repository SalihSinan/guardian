package process;

import carte.Obstacle;
import carte.Carte;
import config.GameConfiguration;
import mobile.Gardien;
import mobile.Intru;

public class Simulation {

	private static Carte map = new Carte(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);

	public static Carte buildMap() {
		Obstacle[][] blocks = map.getBlocks();

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Obstacle block = blocks[lineIndex][columnIndex];
				map.isWall(block);
				map.isWater(block);
				map.isTree(block);
				map.isRock(block);
				map.isFloor(block);
			}
		}
		return map;
	}

	public static Element buildInitMobile(Carte map) {
		Element manager = new Element(map);

		initGardien(map, manager);
		initIntru(map, manager);

		return manager;
	}

	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}

	private static void initGardien(Carte map, Element manager) {
		int gardienCount = GameConfiguration.GARDIEN_COUNT;
		while (gardienCount != 0) {

			Obstacle block = map.getBlock(getRandomNumber(1, GameConfiguration.LINE_COUNT - 2),
					getRandomNumber(1, GameConfiguration.COLUMN_COUNT - 2));
			Gardien gardien = new Gardien(block, map);
			manager.add(gardien);
			gardienCount--;
		}
	}

	private static void initIntru(Carte map, Element manager) {
		int intrusCount = GameConfiguration.INTRU_COUNT;
		while (intrusCount != 0) {

			Obstacle block = map.getBlock(getRandomNumber(1, GameConfiguration.LINE_COUNT - 2),
					getRandomNumber(1, GameConfiguration.COLUMN_COUNT - 2));
			Intru intru = new Intru(block, map);
			manager.add(intru);
			intrusCount--;
		}
	}

}
