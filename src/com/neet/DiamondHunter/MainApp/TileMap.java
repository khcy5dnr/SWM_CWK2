package com.neet.DiamondHunter.MainApp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileMap {
	
	
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private Image tile;
	
	public TileMap(int size){
		this.tileSize = size;
	}
	
	public void loadMap(String path){
		try {			
			InputStream in = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw_Image(GraphicsContext gc){
		tile = new Image("/Tilesets/testtileset.gif");
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				int originalPoint = map[row][col];
				int yOSH = 0;
				
				if(originalPoint >= (tile.getWidth()/tileSize)){
					yOSH++;
					originalPoint = (int) (originalPoint - tile.getWidth()/tileSize);
				}

				gc.drawImage(tile, originalPoint*tileSize, yOSH*tileSize, tileSize, tileSize, col*tileSize, row*tileSize, tileSize, tileSize);
			}
		}
	}

}

