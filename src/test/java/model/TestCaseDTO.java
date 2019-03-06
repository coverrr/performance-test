package model;
import java.time.LocalDateTime;
import java.util.List;

public class TestCaseDTO {
	private String testClass;
	private String testMethod;
	private List<TestRunDTO> testRuns;
	
	private LocalDateTime startDateCurrent;
	private LocalDateTime endDateCurrent;
	
	public TestCaseDTO(String testClass, String testMethod) {
		super();
		this.testClass = testClass;
		this.testMethod = testMethod;
	}
	
	public TestCaseDTO(String testClass, String testMethod, List<TestRunDTO> testRuns) {
		this.testClass = testClass;
		this.testMethod = testMethod;
		this.testRuns = testRuns;
	}
	
	
	
	public TestCaseDTO(String testClass, String testMethod, LocalDateTime startDateCurrent,
			LocalDateTime endDateCurrent) {
		this.testClass = testClass;
		this.testMethod = testMethod;
		this.startDateCurrent = startDateCurrent;
		this.endDateCurrent = endDateCurrent;
	}

	public LocalDateTime getStartDateCurrent() {
		return startDateCurrent;
	}

	public void setStartDateCurrent(LocalDateTime startDateCurrent) {
		this.startDateCurrent = startDateCurrent;
	}

	public LocalDateTime getEndDateCurrent() {
		return endDateCurrent;
	}

	public void setEndDateCurrent(LocalDateTime endDateCurrent) {
		this.endDateCurrent = endDateCurrent;
	}

	public String getTestClass() {
		return testClass;
	}

	public void setTestClass(String testClass) {
		this.testClass = testClass;
	}

	public String getTestMethod() {
		return testMethod;
	}

	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	public List<TestRunDTO> getTestRuns() {
		return testRuns;
	}

	public void setTestRuns(List<TestRunDTO> testRuns) {
		this.testRuns = testRuns;
	}

	@Override
	public String toString() {
		return "TestCaseDTO [testClass=" + testClass + ", testMethod=" + testMethod + ", testRuns=" + testRuns + "]";
	}

}
