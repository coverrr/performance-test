package listeners;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;

import model.DatabasePersistenceStrategy;
import model.ExcelPersistenceStrategy;
import model.PersistenceStrategy;
import model.TestCaseDTO;
import model.TestRunDTO;

public class PerformanceListener implements ISuiteListener {

	private PersistenceStrategy persistenceStrategy;
	
	private double factor;

	// onStartSuite
	public void onStart(ISuite suite) {
		String persistenceType = suite.getParameter("persistenceType").toUpperCase();

		switch (persistenceType) {
		case "EXCEL":
			this.persistenceStrategy = new ExcelPersistenceStrategy();
			break;
		case "DATABASE":
			this.persistenceStrategy = new DatabasePersistenceStrategy();
			break;
		default:
			throw new IllegalArgumentException("Parameter darf nur 'EXCEL' oder 'DATABASE' sein.");
		}
	}

	// finishSuite
	public void onFinish(ISuite suite) {
		System.out.println("--------------ON FINISH SUITE--------------");

		// hole Test-Performances
		List<TestCaseDTO> performanceTestCases = suite.getAllInvokedMethods().stream()	// hole aktuelle Testergebnisse
				.filter(IInvokedMethod::isTestMethod)
				.map(this::bildeAbAufTestCase)
				.map(this::holeVorherigePerformanceDaten)
				.collect(Collectors.toList());

		// ueberpruefe auf Anomalien
		performanceTestCases.stream()
			.forEach(this::checkForAnomalies);

		// persistiere Test-Faelle
		performanceTestCases.stream()
			.forEach(this.persistenceStrategy::persistiereTestCase);
	}

	public void checkForAnomalies(TestCaseDTO tc) {
		double average = buildAverage(tc).orElseThrow(() -> new ArithmeticException("Der Durchschnitt konnte nicht ermittelt werden"));
		
		long timeTakenCurrentTest = ChronoUnit.MILLIS.between(tc.getStartDateCurrent(), tc.getEndDateCurrent());
		
		if(timeTakenCurrentTest > average)
			Assert.fail(String.format("%s#%s hat %d ms in Anspruche genommen; Durchschnitt ist %s", tc.getTestClass(), tc.getTestMethod(),
					timeTakenCurrentTest, average));

	}

	public OptionalDouble buildAverage(TestCaseDTO tc) {
		return tc.getTestRuns().stream()
			.mapToLong(r -> ChronoUnit.MILLIS.between(r.getStartDate(), r.getEndDate()))
			.average();
	}

	private TestCaseDTO holeVorherigePerformanceDaten(TestCaseDTO tc) {
		List<TestRunDTO> runs = this.persistenceStrategy.holeTestRuns(tc);
		tc.setTestRuns(runs);
		
		return tc;
	}

	private TestCaseDTO bildeAbAufTestCase(IInvokedMethod t) {
		ITestResult result = t.getTestResult();

		String testClassName = result.getTestClass().getName();
		String testClassMethodName = result.getMethod().getMethodName();
		LocalDateTime testStartedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getStartMillis()),
				ZoneId.systemDefault());
		LocalDateTime testFinishedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(result.getEndMillis()),
				ZoneId.systemDefault());
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
		// HH:mm:ss");

		return new TestCaseDTO(testClassName, testClassMethodName, testStartedAt, testFinishedAt);
	}

}
