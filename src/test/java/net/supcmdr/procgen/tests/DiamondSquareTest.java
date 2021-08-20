package net.supcmdr.procgen.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import net.supcmdr.procgen.dsq.DiamondSquare;
import net.supcmdr.procgen.utilities.TestUtils;

public class DiamondSquareTest {
	
	private TestUtils util;
	
	public DiamondSquareTest(TestUtils util) {
		this.util = util;
	}
	
	@Test
	public void RunTest() {
		DiamondSquare ds = new DiamondSquare(12, 256, 32);
		
		int[][] map = ds.doDiamondSquare();
		
		assertNotNull(map);
		
		util.printIntArrayToConsole(map);
		util.printIntArrayToScreenshot(map);
	}
	
	

}
