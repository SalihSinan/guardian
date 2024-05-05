package process;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import carte.Obstacle;
import carte.Carte;
import config.GameConfiguration;
import gui.Scoreboard;
import mobile.Gardien;
import mobile.Intru;
import mobile.MobileElement;

public class Element {

	private Carte map;

	private List<Gardien> gardiens = new ArrayList<Gardien>();
	private List<Intru> intrus = new ArrayList<Intru>();
	private Scoreboard scoreBoard;
	private int intruCaught;

	int timerSpawn = GameConfiguration.TIMER_SPAWN_INTRU;
	int timerCom = GameConfiguration.TIMER_COMMUNICATION_GARDIEN;

	public Element(Carte map) {
		this.map = map;
	}

	public void moveUp(Gardien gardien) {
		Obstacle position = gardien.getPosition();
		if (position.getLine() > 1) {
			Obstacle newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				gardien.setPosition(newPosition);
			}
		}
	}

	public void moveDown(Gardien gardien) {
		Obstacle position = gardien.getPosition();
		if (position.getLine() < GameConfiguration.LINE_COUNT - 1) {
			Obstacle newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				gardien.setPosition(newPosition);
			}
		}
	}

	public void moveLeft(Gardien gardien) {
		Obstacle position = gardien.getPosition();
		if (position.getColumn() > 1) {
			Obstacle newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				gardien.setPosition(newPosition);
			}
		}
	}

	public void moveRight(Gardien gardien) {
		Obstacle position = gardien.getPosition();
		if (position.getColumn() < GameConfiguration.COLUMN_COUNT - 1) {
			Obstacle newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);

			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				gardien.setPosition(newPosition);
			}
		}
	}

	public void moveUp(Intru intru) {
		Obstacle position = intru.getPosition();
		if (position.getLine() > 1) {
			Obstacle newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				intru.setPosition(newPosition);
			}
		}
	}

	public void moveDown(Intru intru) {
		Obstacle position = intru.getPosition();
		if (position.getLine() < GameConfiguration.LINE_COUNT - 1) {
			Obstacle newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				intru.setPosition(newPosition);
			}
		}
	}

	public void moveLeft(Intru intru) {
		Obstacle position = intru.getPosition();
		if (position.getColumn() > 1) {
			Obstacle newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				intru.setPosition(newPosition);
			}
		}
	}

	public void moveRight(Intru intru) {
		Obstacle position = intru.getPosition();
		if (position.getColumn() < GameConfiguration.COLUMN_COUNT - 1) {
			Obstacle newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
			if (map.isTree(newPosition) || map.isFloor(newPosition)) {
				intru.setPosition(newPosition);
			}
		}
	}

	public void RandomMoveGardiens(Gardien gardien) {

		int direction = getRandomNumber(1, 4);

		if (direction == 1) {
			moveDown(gardien);
		}

		if (direction == 2) {
			moveUp(gardien);
		}

		if (direction == 3) {
			moveRight(gardien);
		}

		if (direction == 4) {
			moveLeft(gardien);
		}

	}

	public void RandomMoveIntrus(Intru intru) {

		int direction = getRandomNumber(1, 4);

		if (direction == 1) {
			moveDown(intru);
		}

		if (direction == 2) {
			moveUp(intru);
		}

		if (direction == 3) {
			moveRight(intru);
		}

		if (direction == 4) {
			moveLeft(intru);
		}

	}

	


	public void communication(Obstacle posIntru) {
	    for (Gardien gardien : gardiens) {
	        Obstacle posGardien = gardien.getPosition();
	        List<Obstacle> vision = gardien.getZone(gardien, map);
	        if (!vision.contains(posIntru)) {
	            gardien.etatPoursuite = true;
	            if (posGardien.getLine() > posIntru.getLine()) {
	                moveUp(gardien);
	            } else if (posGardien.getLine() < posIntru.getLine()) {
	                moveDown(gardien);
	            } else if (posGardien.getColumn() < posIntru.getColumn()) {
	                moveRight(gardien);
	            } else if (posGardien.getColumn() > posIntru.getColumn()) {
	                moveLeft(gardien);
	            }
	        }
	    }
	}

	public void poursuiteGardien() {
	    for (Gardien gardien : gardiens) {
	        Obstacle posGardien = gardien.getPosition();
	        List<Obstacle> vision = gardien.getZone(gardien, map);
	        Intru intruProche = null;
	        double closestDistance = Double.POSITIVE_INFINITY;
	        for (Intru intru : intrus) {
	            Obstacle posIntru = intru.getPosition();
	            if (vision.contains(posIntru)) {
	                double distance = Math.sqrt(Math.pow(posIntru.getLine() - posGardien.getLine(), 2) + Math.pow(posIntru.getColumn() - posGardien.getColumn(), 2));
	                if (distance < closestDistance) {
	                    intruProche = intru;
	                    closestDistance = distance;
	                }
	            }
	        }
	        if (intruProche != null) {
	            gardien.etatPoursuite = true;
	            Obstacle posIntruProche = intruProche.getPosition();
	            if (posGardien.getLine() > posIntruProche.getLine()) {
	                moveUp(gardien);
	            } else if (posGardien.getLine() < posIntruProche.getLine()) {
	                moveDown(gardien);
	            } else if (posGardien.getColumn() < posIntruProche.getColumn()) {
	                moveRight(gardien);
	            } else if (posGardien.getColumn() > posIntruProche.getColumn()) {
	                moveLeft(gardien);
	            }
	            if (timerCom == 0) {
	                communication(posIntruProche);
	            } else {
	                timerCom--;
	            }
	        } else {
	            RandomMoveGardiens(gardien);
	            gardien.etatPoursuite = false;
	        }
	    }
	}


	public void fuiteIntru() {

		for (Intru intru : intrus) {
			Obstacle posIntru = intru.getPosition();
			List<Obstacle> vision = intru.getZone(intru, map);
			Gardien gardienProche = null;
			double closestDistance = Double.POSITIVE_INFINITY;
			for (Gardien gardien : gardiens) {
				Obstacle posGardien = gardien.getPosition();
				if (vision.contains(posGardien)) {
					double distance = Math.sqrt(Math.pow(posGardien.getLine() - posIntru.getLine(), 2)
							+ Math.pow(posGardien.getColumn() - posIntru.getColumn(), 2));
					if (distance < closestDistance) {
						gardienProche = gardien;
						closestDistance = distance;
					}
				}
			}
			if (gardienProche != null) {
				Obstacle posGardienProche = gardienProche.getPosition();
				if (posIntru.getLine() < posGardienProche.getLine()) {
					moveUp(intru);
				} else if (posIntru.getLine() > posGardienProche.getLine()) {
					moveDown(intru);
				} else if (posIntru.getColumn() > posGardienProche.getColumn()) {
					moveRight(intru);
				} else if (posIntru.getColumn() < posGardienProche.getColumn()) {
					moveLeft(intru);
				}
			} else {
				RandomMoveIntrus(intru);
			}
		}
	}

	public void eliminerIntrus() {

		for (Gardien gardien : gardiens) {

			Obstacle positionGardien = gardien.getPosition();
			for (Iterator<Intru> iterator = intrus.iterator(); iterator.hasNext();) {

				Intru intru = iterator.next();
				Obstacle positionIntrus = intru.getPosition();
				if (positionGardien.equals(positionIntrus)) {
					iterator.remove();
					intruCaught++;
					timerCom = GameConfiguration.TIMER_COMMUNICATION_GARDIEN;
				}
			}
		}
		scoreBoard.updateintrusCaughtCount(intruCaught);
		updateScoreBoard();
	}
	
	

	public void spawnRandomIntru() {

		if (timerSpawn != 0) {
			timerSpawn--;
		} else {

			int line = getRandomNumber(2, GameConfiguration.LINE_COUNT - 2);
			int column = getRandomNumber(2, GameConfiguration.COLUMN_COUNT - 2);
			Obstacle position = new Obstacle(line, column);
			if (position.isFloor() || position.isTree()) {
				Intru intru = new Intru(position, map);
				add(intru);
				timerSpawn = GameConfiguration.TIMER_SPAWN_INTRU;
			}
		}
		updateScoreBoard();
	}

	public void spawnIntru() {
		int line = getRandomNumber(2, GameConfiguration.LINE_COUNT - 2);
		int column = getRandomNumber(2, GameConfiguration.COLUMN_COUNT - 2);
		Obstacle position = new Obstacle(line, column);
		if (position.isFloor() || position.isTree()) {
			Intru intru = new Intru(position, map);
			add(intru);
		}
		updateScoreBoard();
	}

	public void nextRound() {
		poursuiteGardien();
		fuiteIntru();
		eliminerIntrus();
		spawnRandomIntru();
	}

	public List<Intru> getIntrus() {
		updateScoreBoard();
		return intrus;
	}

	public List<Gardien> getGardiens() {
		updateScoreBoard();
		return gardiens;
	}

	public void add(Intru intru) {
		intrus.add(intru);
		updateScoreBoard();
	}

	public void suppr(Intru intru) {
		intrus.remove(intru);
		updateScoreBoard();
	}

	public void add(Gardien gardien) {
		gardiens.add(gardien);
		updateScoreBoard();
	}

	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}

	public void setScoreBoard(Scoreboard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public void updateScoreBoard() {
		if (scoreBoard != null) {
			this.scoreBoard.updateguardiensCount(gardiens.size());
			this.scoreBoard.updateintrusCount(intrus.size());
			this.scoreBoard.setPercentageBlocks(map.getBlockPercentage());
		}
	}
}