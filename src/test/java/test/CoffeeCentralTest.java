package test;

import coffee.Answer;
import coffee.Main;
import coffee.Town;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CoffeeCentralTest {

	@Test
	public void checkEndProgram() {
		List<String> input = Main.getTownCaseFromFile("input.txt");
		List<String> mustBe = new ArrayList<>();
		mustBe.add("4 4 5 3\n1 1\n1 2\n3 3\n4 4\n2 4\n1\n2\n4\n");


		assertThat("No end line find!", mustBe, is(input));
	}

	
	@Test
	public void checkResultValues() {
		List<String> input = new ArrayList<>();
		input.add("4 4 5 3\n1 1\n1 2\n3 3\n4 4\n2 4\n1\n2\n4\n0 0 0 0");
		
		List<Answer> mustBe = new ArrayList<>();
		mustBe.add(new Answer(3, 3, 4));
		mustBe.add(new Answer(4, 2, 2));
		mustBe.add(new Answer(5, 3, 1));
		
		assertThat("Different result!", mustBe, is(Main.getResult(input, false)));
	}
	
	@Test
	public void checkDistanceCalculating() {

		int x1 = 15, y1 = 15;
		int x2 = 25, y2 = 5;
		int distance = 20;

		assertEquals("Different result!", distance, Town.getDistance(x1, y1, x2, y2));
	}

}
