package model;

import java.util.LinkedList;
import java.util.List;

public class ExcelPersistenceStrategy implements PersistenceStrategy {

	@Override
	public List<TestRunDTO> holeTestRuns(TestCaseDTO testCase) {
		System.out.println("Hole TestRuns via Excel-Tabelle");

		return new LinkedList<>();
	}

	@Override
	public void persistiereTestCase(TestCaseDTO testCase) {
		System.out.println("Persistiere TestCase via Excel-Tabelle");

	}

}
