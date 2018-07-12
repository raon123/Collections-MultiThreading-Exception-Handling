package com.theatreseating.vo;

import java.util.List;

public class Layout {

	private long layoutcapacity;
	private long avaialableLayoutCapacity;
	private List<Section> sectionList;
	
	public long getLayoutcapacity() {
		return layoutcapacity;
	}
	public void setLayoutcapacity(long layoutcapacity) {
		this.layoutcapacity = layoutcapacity;
	}
	public long getAvaialableLayoutCapacity() {
		return avaialableLayoutCapacity;
	}
	public void setAvaialableLayoutCapacity(long avaialableLayoutCapacity) {
		this.avaialableLayoutCapacity = avaialableLayoutCapacity;
	}
	public List<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
	
	
	
}
