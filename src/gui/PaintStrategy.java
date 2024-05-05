package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import carte.Obstacle;
import carte.Carte;
import carte.Carte;
import carte.Obstacle;
import config.GameConfiguration;
import mobile.Gardien;
import mobile.Intru;

public class PaintStrategy {
	
	    public void paintMap(Carte map, Graphics graphics) {
	        int blockSizeX = GameConfiguration.getBlockSizeX(); 
	        int blockSizeY = GameConfiguration.getBlockSizeY(); 
	        Obstacle[][] blocks = map.getBlocks();

	        for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
	            for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
	                Obstacle block = blocks[lineIndex][columnIndex];

	                if (map.isWall(block)) {
	                    Image image = SimulationUtility.loadImage("herbre.png");
	                    graphics.drawImage(image, block.getColumn() * blockSizeX, block.getLine() * blockSizeY, blockSizeX, blockSizeY, null);
	                } else if (map.isWater(block)) {
	                    Image image = SimulationUtility.loadImage("eau.png");
	                    graphics.drawImage(image, block.getColumn() * blockSizeX, block.getLine() * blockSizeY, blockSizeX, blockSizeY, null);
	                } else if (map.isTree(block)) {
	                    Image image = SimulationUtility.loadImage("arbre.jpeg");
	                    graphics.drawImage(image, block.getColumn() * blockSizeX, block.getLine() * blockSizeY, blockSizeX, blockSizeY, null);
	                } else if (map.isRock(block)) {
	                    Image image = SimulationUtility.loadImage("roche.jpeg");
	                    graphics.drawImage(image, block.getColumn() * blockSizeX, block.getLine() * blockSizeY, blockSizeX, blockSizeY, null);
	                } else {
	                    Image image = SimulationUtility.loadImage("herbre.png");
	                    graphics.drawImage(image, block.getColumn() * blockSizeX, block.getLine() * blockSizeY, blockSizeX, blockSizeY, null);
	                }
	            }
	        }
	    }
	    
	    public void paintGardien(Gardien gardien, Graphics graphics) {
	        Obstacle position = gardien.getPosition();
	        int blockSizeX = GameConfiguration.getBlockSizeX(); 
	        int blockSizeY = GameConfiguration.getBlockSizeY(); 

	        int y = position.getLine();
	        int x = position.getColumn();

	        Image image = SimulationUtility.loadImage("gardien.png");
	        graphics.drawImage(image, x * blockSizeX, y * blockSizeY, blockSizeX, blockSizeY, null);
	    }


	    public void paintIntru(Intru intru, Graphics graphics) {
	        Obstacle position = intru.getPosition();
	        int blockSizeX = GameConfiguration.getBlockSizeX(); 
	        int blockSizeY = GameConfiguration.getBlockSizeY(); 

	        int y = position.getLine();
	        int x = position.getColumn();

	        Image image = SimulationUtility.loadImage("intru.png");
	        graphics.drawImage(image, x * blockSizeX, y * blockSizeY, blockSizeX, blockSizeY, null);
	    }
	    
	    public void paintGardienPoursuite(Gardien gardien, Graphics graphics) {
	        Obstacle position = gardien.getPosition();
	        int blockSizeX = GameConfiguration.getBlockSizeX(); 
	        int blockSizeY = GameConfiguration.getBlockSizeY(); 

	        int y = position.getLine();
	        int x = position.getColumn();

	        Image image = SimulationUtility.loadImage("poursuite.png");
	        graphics.drawImage(image, x * blockSizeX, y * blockSizeY, blockSizeX, blockSizeY, null);
	    }
	    
	
	
	public void paintVision(Gardien gardien, Graphics graphics,Carte map) {
	    List<Obstacle> zone = gardien.getZone(gardien,map);

	    Graphics2D g2d = (Graphics2D) graphics;
	    AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	    g2d.setComposite(alpha);

	    for (Obstacle block : zone) {
	        graphics.setColor(Color.white); 
	        graphics.fillRect(block.getColumn() * GameConfiguration.BLOCK_SIZE, block.getLine() * GameConfiguration.BLOCK_SIZE, GameConfiguration.BLOCK_SIZE, GameConfiguration.BLOCK_SIZE);
	    }
	}
	
	public void paintVision(Intru intru, Graphics graphics, Carte map) {
	    List<Obstacle> zone = intru.getZone(intru,map);

	    Graphics2D g2d = (Graphics2D) graphics;
	    AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	    
	    g2d.setComposite(alpha);

	    for (Obstacle block : zone) {
	        graphics.setColor(Color.yellow); 
	        graphics.fillRect(block.getColumn() * GameConfiguration.BLOCK_SIZE, block.getLine() * GameConfiguration.BLOCK_SIZE, GameConfiguration.BLOCK_SIZE, GameConfiguration.BLOCK_SIZE);
	    }
	}


	
}