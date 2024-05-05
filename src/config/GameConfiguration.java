package config;

public class GameConfiguration {
	
	public static int WIDTH = 1000;
    public static int HEIGHT = 600;
    public static int LINE_COUNT = 10;
    public static int COLUMN_COUNT = 10;
    public static int BLOCK_SIZE = 800; 
    public static int getBlockSizeX() {
        return WIDTH / COLUMN_COUNT;
    }

    public static int getBlockSizeY() {
        return HEIGHT / LINE_COUNT;
    }
	
	public static int INTRU_COUNT = 3;
	public static int GARDIEN_COUNT = 1;
	
	public static int SIZE_VISION = 2;
	
	public static int TIMER_SPAWN_INTRU = 25;
	public static int TIMER_COMMUNICATION_GARDIEN = 5;
	
	public static final int GAME_SPEED = 900;
	
	public static double CHANCE_FOR_WATER_BLOCK = 0.1;
	public static double CHANCE_FOR_TREE_BLOCK = 0.1;
	public static double CHANCE_FOR_ROCK_BLOCK = 0.1;
	
}
