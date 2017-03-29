package lab1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class StableMarriageTest {

	@Test
	public void testBBT() {
		testInOut("bbt");
	}
	
	@Test
	public void testIlliad() {
		testInOut("illiad");
	}
	
	@Test
	public void testKTPage4() {
		testInOut("kt-p-4");
	}
	
	@Test
	public void testKTPage5() {
		testInOut("kt-p-5");
	}
	
	@Test
	public void testRandom5() {
		testInOut("random-5");
	}
	
	@Test
	public void testRandom50() {
		testInOut("random-50");
	}
	
	@Test
	public void testRandom500() {
		testInOut("random-500");
	}
	
	@Test
	public void testWorst5() {
		testInOut("worst-5");
	}
	
	@Test
	public void testWorst50() {
		testInOut("worst-50");
	}
	
	@Test
	public void testWorst500() {
		testInOut("worst-500");
	}

	@Test
	public void testFriends() {
		testInOut("friends");
	}

	private void testInOut(String name) {
		try {
			File inFile = new File("sm-" + name + "-in.txt");
			StableMarriage sm = new StableMarriage(new FileInputStream(inFile));
			StableMarriage.Man[] men = sm.getMen();
			File outFile = new File("sm-" + name + "-out.txt");

			Scanner sc = new Scanner(outFile);
			for (int i = 0; i < men.length; i++) {
				assertEquals("At line: " + i, sc.next(), men[i].getName());
				sc.next();
				assertEquals("At line: " + i, sc.next(), men[i].getWifeName());
			}
			sc.close();
		} catch (IOException e) {
			fail(e.toString());
		}
	}

}
