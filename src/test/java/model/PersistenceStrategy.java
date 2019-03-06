package model;

import java.util.List;

public interface PersistenceStrategy {
	List<TestRunDTO> holeTestRuns(TestCaseDTO testCase);
	
	void persistiereTestCase(TestCaseDTO testCase);
}
