package tests;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import org.testng.Assert;
import org.testng.annotations.Test;

import listeners.PerformanceListener;
import model.TestCaseDTO;
import model.TestRunDTO;

public class PerformanceListenerTest {

	@Test
	public void buildAverageTest() {
		PerformanceListener pl = new PerformanceListener();

		TestCaseDTO tc = new TestCaseDTO("TestKlasse", "TestMethods");

		LocalDateTime sampleDate = LocalDateTime.ofEpochSecond(1000, 0, ZoneOffset.UTC);
		List<TestRunDTO> runs = Arrays.asList(
				new TestRunDTO(sampleDate, sampleDate.plus(1000, ChronoUnit.MILLIS), true),
				new TestRunDTO(sampleDate, sampleDate.plus(300, ChronoUnit.MILLIS), true),
				new TestRunDTO(sampleDate, sampleDate.plus(1700, ChronoUnit.MILLIS), true));
		tc.setTestRuns(runs);
		// (1000 + 1300 + 1700) / 3 = 1000
		OptionalDouble average = pl.buildAverage(tc);

		Assert.assertEquals(average.getAsDouble(), 1000.);
	}

	@Test
	public void checkForAnomaliesTest_valid() {
		PerformanceListener pl = new PerformanceListener();

		TestCaseDTO tc = new TestCaseDTO("TestKlasse", "TestMethods");

		LocalDateTime sampleDate = LocalDateTime.ofEpochSecond(1000, 0, ZoneOffset.UTC);
		List<TestRunDTO> runs = Arrays.asList(
				new TestRunDTO(sampleDate, sampleDate.plus(1000, ChronoUnit.MILLIS), true),
				new TestRunDTO(sampleDate, sampleDate.plus(300, ChronoUnit.MILLIS), true),
				new TestRunDTO(sampleDate, sampleDate.plus(1700, ChronoUnit.MILLIS), true));
		tc.setTestRuns(runs);
		tc.setStartDateCurrent(sampleDate);
		tc.setEndDateCurrent(sampleDate.plus(900, ChronoUnit.MILLIS));

		pl.checkForAnomalies(tc);
	}
	
	@Test(expectedExceptions = AssertionError.class)
	public void checkForAnomaliesTest_invalid() {
		PerformanceListener pl = new PerformanceListener();

		TestCaseDTO tc = new TestCaseDTO("TestKlasse", "TestMethods");

		LocalDateTime sampleDate = LocalDateTime.ofEpochSecond(1000, 0, ZoneOffset.UTC);
		List<TestRunDTO> runs = Arrays.asList(
				new TestRunDTO(sampleDate, sampleDate.plus(1000, ChronoUnit.MILLIS), true),
				new TestRunDTO(sampleDate, sampleDate.plus(300, ChronoUnit.MILLIS), true),
				new TestRunDTO(sampleDate, sampleDate.plus(1700, ChronoUnit.MILLIS), true));
		tc.setTestRuns(runs);
		tc.setStartDateCurrent(sampleDate);
		tc.setEndDateCurrent(sampleDate.plus(1100, ChronoUnit.MILLIS));

		pl.checkForAnomalies(tc);
	}
}
