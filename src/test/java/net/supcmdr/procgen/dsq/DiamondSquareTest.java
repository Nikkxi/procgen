package net.supcmdr.procgen.dsq;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

public class DiamondSquareTest {
	
	public DiamondSquareTest() {
		
	}
	
	@Test
	public void RunTest() {
		DiamondSquare ds = new DiamondSquare(12, 256, 32);
		
		int[][] map = ds.doDiamondSquare();
		
		assertNotNull(map);
		
		//printIntArrayToConsole(map);
		//printIntArrayToScreenshot(map);
	}
	
	private void printIntArrayToConsole(int[][] map) {
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[0].length; y++) {
				int value = map[x][y];
				
				if(value < 10)
					System.out.print(map[x][y] + "  ");
				else
					System.out.print(map[x][y] + " ");
			}
			System.out.println();
		}
	}
	
	private void printIntArrayToScreenshot(int[][] map) {
		BufferedImage img = new BufferedImage(map.length, map[0].length, BufferedImage.TYPE_3BYTE_BGR);
		
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[0].length; y++) {
				int rgb = (int)map[x][y]<<16 | (int)map[x][y]<<8 | (int)map[x][y];
				img.setRGB(x, y, rgb);
			}
		}
		
		try {
			ImageIO.write(img, "jpg", new File("P:\\intarray.jpg"));
		}catch(Exception e) {
			System.out.println("Failed to write image to file.");
			e.printStackTrace();
		}
	}

}
