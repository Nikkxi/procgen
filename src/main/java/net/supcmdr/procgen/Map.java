package net.supcmdr.procgen;

import net.supcmdr.procgen.perlin.PerlinNoise;

public class Map {
	int[] map;

	public Map(int width, int height) {
		map = new int[width*height];
	}
	
	public void generatePerlinMap() {
		PerlinNoise noiseGen = new PerlinNoise();
	}
}
