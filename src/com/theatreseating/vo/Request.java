package com.theatreseating.vo;

public class Request {

	private String name;
	private long ticketsRequested;
	private boolean requestSatisfied;
	private long rowNumber;
	private long sectionNumber;
	private String reason;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTicketsRequested() {
		return ticketsRequested;
	}
	public void setTicketsRequested(long ticketsRequested) {
		this.ticketsRequested = ticketsRequested;
	}
	public boolean isRequestSatisfied() {
		return requestSatisfied;
	}
	public void setRequestSatisfied(boolean requestSatisfied) {
		this.requestSatisfied = requestSatisfied;
	}
	public long getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(long rowNumber) {
		this.rowNumber = rowNumber;
	}
	public long getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(long sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
