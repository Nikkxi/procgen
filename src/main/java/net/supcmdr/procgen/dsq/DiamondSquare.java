package net.supcmdr.procgen.dsq;

import java.util.Random;

public class DiamondSquare {
	
	private int heightMapSize;
	private int range;
	private int roughness;

	private int[][] heightMap;
	
	public DiamondSquare() {
		heightMapSize = (int)Math.pow(2, 6) + 1;
		System.out.println("heightMapSize: " + heightMapSize);
		System.out.println();
		range = 16;
		roughness = 4;
		
		heightMap = new int[heightMapSize][heightMapSize];
	}
	
	public DiamondSquare(int power) {
		this.heightMapSize = (int)Math.pow(2, power)+1;
		range = 16;
		roughness = 4;
		
		heightMap = new int[heightMapSize][heightMapSize];
	}
	
	public DiamondSquare(int power, int range, int roughness) {
		this.heightMapSize = (int)Math.pow(2, power)+1;
		this.range = range;
		this.roughness = roughness;
		
		heightMap = new int[heightMapSize][heightMapSize];
	}
	
	public int[][] doDiamondSquare() {
		randomizeFourCorners();
		
		int chunkSize = heightMapSize -1;
		
		while(chunkSize > 1) {
			int half = chunkSize/2;
			
			doSquareStep(chunkSize, half);
			doDiamondStep(chunkSize, half);
			
			chunkSize /= 2;
			roughness -= 4;
			if(roughness < 1) roughness = 1;
		}
		
		return heightMap;
	}
	
	private void randomizeFourCorners() {
		Random r = new Random();
		heightMap[0][0] = r.nextInt(range);
		heightMap[0][heightMapSize-1] = r.nextInt(range);
		heightMap[heightMapSize-1][0] = r.nextInt(range);
		heightMap[heightMapSize-1][heightMapSize-1] = r.nextInt(range);
	}
	
	private void doSquareStep(int chunkSize, int half) {
		Random r = new Random();
		int flip = r.nextInt(1);
		for (int x=half; x <= heightMap.length-1; x+= chunkSize) {
			for(int y=half; y <= heightMap[0].length-1; y+= chunkSize) {
				int sum = 
						heightMap[x-half][y-half] +
						heightMap[x-half][y+half] +
						heightMap[x+half][y-half] +
						heightMap[x+half][y+half] ;
				sum /= 4;
				
				if(flip==1) heightMap[x][y] = sum - r.nextInt(roughness);
				else heightMap[x][y] = sum + r.nextInt(roughness);
				
				if(heightMap[x][y] < 0) heightMap[x][y] = 0;
				if(heightMap[x][y] > range) heightMap[x][y] = range;
			}
		}
	}
	
	private void doDiamondStep(int chunkSize, int half) {
		Random r = new Random();
		for (int x=0; x <= heightMap.length-1; x+= half) {
			
			int decalage;
			if(x % chunkSize == 0) decalage = half;
			else decalage = 0;
			
			for(int y=decalage; y <= heightMap[0].length-1; y+= chunkSize) {
				int sum = 0;
				int count = 0;
				
				if(x>=half) {
					sum += heightMap[x-half][y];
					count++;
				}
				if(x+half < heightMapSize-1) {
					sum += heightMap[x+half][y];
					count++;
				}
				if(y>=half) {
					sum+= heightMap[x][y-half];
					count++;
				}
				if(y+half < heightMapSize-1) {
					sum+= heightMap[x][y+half];
					count++;
				}
				heightMap[x][y] = sum / count + r.nextInt(roughness);
				
				if(heightMap[x][y] < 0) heightMap[x][y] = 0;
				if(heightMap[x][y] > range) heightMap[x][y] = range;
			}
		}
	}
}
