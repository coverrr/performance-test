package model;

import java.util.Collections;
import java.util.List;

public class DatabasePersistenceStrategy implements PersistenceStrategy{

	@Override
	public List<TestRunDTO> holeTestRuns(TestCaseDTO testCase) {
		System.out.println("Hole TestRuns via Database");
		
		return Collections.emptyList();
	}

	@Override
	public void persistiereTestCase(TestCaseDTO testCase) {
		System.out.println("Persistiere TestCase in Database");
		
	}

}
