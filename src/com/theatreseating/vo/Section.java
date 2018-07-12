package com.theatreseating.vo;

public class Section implements Comparable<Section>{

	private long sectionNumberInRow;
	private long row;
	private long sectionCapacity;
	private long avaialbleCapacity;
	public long getSectionNumberInRow() {
		return sectionNumberInRow;
	}
	public void setSectionNumberInRow(long sectionNumberInRow) {
		this.sectionNumberInRow = sectionNumberInRow;
	}
	public long getRow() {
		return row;
	}
	public void setRow(long row) {
		this.row = row;
	}
	public long getSectionCapacity() {
		return sectionCapacity;
	}
	public void setSectionCapacity(long sectionCapacity) {
		this.sectionCapacity = sectionCapacity;
	}
	public long getAvaialbleCapacity() {
		return avaialbleCapacity;
	}
	public void setAvaialbleCapacity(long avaialbleCapacity) {
		this.avaialbleCapacity = avaialbleCapacity;
	}
	
	@Override
	public String toString() {
		return "Section [sectionNumberInRow=" + sectionNumberInRow + ", row=" + row + ", sectionCapacity="
				+ sectionCapacity + ", avaialbleCapacity=" + avaialbleCapacity + "]";
	}
	@Override
	public int compareTo(Section o) {
		
		if(this.avaialbleCapacity - o.avaialbleCapacity == 0){
			
			if(this.row - o.row == 0){
				return (int)(this.sectionNumberInRow - o.sectionNumberInRow);
			}else{
				return (int)(this.row - o.row);
			}
		}else{
			return (int)(this.avaialbleCapacity - o.avaialbleCapacity);
		}
		
	}
	
	
	
}
