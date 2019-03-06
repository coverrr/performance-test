package model;

import java.time.LocalDateTime;

public class TestRunDTO {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private boolean successful;
	
	public TestRunDTO(LocalDateTime startDate, LocalDateTime endDate, boolean successful) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.successful = successful;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	@Override
	public String toString() {
		return "TestRunDTO [startDate=" + startDate + ", endDate=" + endDate + ", successful=" + successful + "]";
	}
	
	
}
