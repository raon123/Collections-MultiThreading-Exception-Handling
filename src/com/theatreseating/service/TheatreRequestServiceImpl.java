package com.theatreseating.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.theatreseating.vo.Layout;
import com.theatreseating.vo.Request;
import com.theatreseating.vo.Section;

public class TheatreRequestServiceImpl implements ITheatreRequestService {

	@Override
	public List<Request> getRequest(String inputRequest) {
		
		Request request;
		List<Request> requestList = new ArrayList<>();
		String[] requestArray = inputRequest.split(System.lineSeparator());
		for(int i=0; i< requestArray.length; i++){
			String[] personRequest = requestArray[i].split(" ");
			request = new Request();
			request.setName(personRequest[0]);
			request.setTicketsRequested(Long.valueOf(personRequest[1]));
			requestList.add(request);
		}
		return requestList;
	}

	@Override
	public Layout getLayout(String inputLayout) {
		
		Layout layout = new Layout();
		Section section;
		List<Section> sectionList = new ArrayList<>();
		long layoutCapacity = 0;
		String[] rows = inputLayout.split("\n");
		
		for(int i=0; i< rows.length ; i++){
			String[] sections = rows[i].split(" ");
				for(int j = 0;j<sections.length; j++){
					layoutCapacity += Long.valueOf(sections[j]);
					section = new Section();
					section.setAvaialbleCapacity(Long.valueOf(sections[j]));
					section.setSectionCapacity(Long.valueOf(sections[j]));
					section.setRow(i+1);
					section.setSectionNumberInRow(j+1);
					sectionList.add(section);
				}
		}
		layout.setLayoutcapacity(layoutCapacity);
		layout.setAvaialableLayoutCapacity(layoutCapacity);
		layout.setSectionList(sectionList);
		return layout;
		
	}

	@Override
	public void processTickets(Layout layout, List<Request> request) {
		int currentRequestProcessing = -1;
		for(Request r : request){
			currentRequestProcessing++;
			if(r.isRequestSatisfied())
				continue;
			if(r.getTicketsRequested() > layout.getLayoutcapacity()){
				r.setRowNumber(-1);
				r.setSectionNumber(-1);
				r.setReason("Sorry,we can't handle your party.");
				continue;
			}
			if(r.getTicketsRequested() > layout.getAvaialableLayoutCapacity()){
				r.setRowNumber(-1);
				r.setSectionNumber(-1);
				r.setReason("Sorry,we can't handle your party.");
				continue;
			}
			
			List<Section> sectionList = layout.getSectionList();
			for(Section section : sectionList){
				if(r.getTicketsRequested() == section.getAvaialbleCapacity()){
					r.setRequestSatisfied(true);
					r.setRowNumber(section.getRow());
					r.setSectionNumber(section.getSectionNumberInRow());
					layout.setAvaialableLayoutCapacity(layout.getAvaialableLayoutCapacity() - r.getTicketsRequested());
					section.setAvaialbleCapacity(section.getAvaialbleCapacity() - r.getTicketsRequested());
					break;
				}else if(r.getTicketsRequested() < section.getAvaialbleCapacity()){
					
					int requestNo = findRequestForRemainingSectionSpace(request, section.getAvaialbleCapacity() - r.getTicketsRequested(), 
								currentRequestProcessing);
					if(requestNo != -1){
						Request requestComplment = request.get(requestNo);
						
						requestComplment.setRequestSatisfied(true);
						requestComplment.setRowNumber(section.getRow());
						requestComplment.setSectionNumber(section.getSectionNumberInRow());
						layout.setAvaialableLayoutCapacity(layout.getAvaialableLayoutCapacity() - r.getTicketsRequested());
						section.setAvaialbleCapacity(section.getAvaialbleCapacity() - r.getTicketsRequested());
						
						r.setRequestSatisfied(true);
						r.setRowNumber(section.getRow());
						r.setSectionNumber(section.getSectionNumberInRow());
						layout.setAvaialableLayoutCapacity(layout.getAvaialableLayoutCapacity() - r.getTicketsRequested());
						section.setAvaialbleCapacity(section.getAvaialbleCapacity() - r.getTicketsRequested());
						break;
					}else{
						
						int sectionNo = findPerfectSectionForRequest(sectionList, r.getTicketsRequested());
						
						if(sectionNo >0){
							Section section2 = sectionList.get(sectionNo);
							r.setRequestSatisfied(true);
							r.setRowNumber(section2.getRow());
							r.setSectionNumber(section2.getSectionNumberInRow());
							section2.setAvaialbleCapacity(section2.getAvaialbleCapacity() - r.getTicketsRequested());
							layout.setAvaialableLayoutCapacity(layout.getAvaialableLayoutCapacity() - r.getTicketsRequested());
							break;
						}else{
							r.setRowNumber(section.getRow());
							r.setSectionNumber(section.getSectionNumberInRow());
							r.setRequestSatisfied(true);
							section.setAvaialbleCapacity(section.getAvaialbleCapacity() - r.getTicketsRequested());
							layout.setAvaialableLayoutCapacity(layout.getAvaialableLayoutCapacity() - r.getTicketsRequested());
							break;
						}
					}
				}
			}
			
			if(!r.isRequestSatisfied()){
				r.setReason("Call to split party");
			}
				
		}
		
	}

	private int findPerfectSectionForRequest(List<Section> sections, long availableSeats) {
		
		 int i=0;
	        Section section = new Section();
	        section.setAvaialbleCapacity(availableSeats);
	        
	        Collections.sort(sections);
	        
	        Comparator<Section> byAvailableSeats = new Comparator<Section>() {
	            
	            @Override
	            public int compare(Section o1, Section o2) {
	                
	                return (int)(o1.getSectionCapacity() - o2.getSectionCapacity());
	                
	            }
	        };
	        
	        int sectionNo = Collections.binarySearch(sections, section, byAvailableSeats);
	        
	        if(sectionNo > 0){
	            
	            for(i=sectionNo-1 ; i>=0 ; i--){
	                
	                Section s = sections.get(i);
	                
	                if(s.getAvaialbleCapacity() != availableSeats) break;
	                
	            }
	            
	            sectionNo = i + 1;
	            
	        }
	        
	        return sectionNo;
	}

	private int findRequestForRemainingSectionSpace(List<Request> request, long l, int currentRequestProcessing) {
		int requestNo = -1;
		for(int i = currentRequestProcessing+1; i< request.size() ; i++){
			Request request2 = request.get(i);
			if(!request2.isRequestSatisfied() && request2.getTicketsRequested() == l){
				requestNo = i;
				break;
			}
		}
		return requestNo;
	}

	
	
}
