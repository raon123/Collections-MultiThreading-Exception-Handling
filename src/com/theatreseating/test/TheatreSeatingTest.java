package com.theatreseating.test;

import java.util.List;
import java.util.Scanner;

import com.theatreseating.service.ITheatreRequestService;
import com.theatreseating.service.TheatreRequestServiceImpl;
import com.theatreseating.vo.Layout;
import com.theatreseating.vo.Request;

public class TheatreSeatingTest {

	public static void main(String[] args) {
		
		String line;
		StringBuilder layout = new StringBuilder();
		StringBuilder request = new StringBuilder();
		boolean layoutCompleted = false;
		System.out.println("Enter Layout and Ticket Request Details , Type enter and 'completed' once input is completed \n");
		
		Scanner sc = new Scanner(System.in);
		
		while((line = sc.nextLine()) != null && !line.equalsIgnoreCase("completed")){
			
			if(line.length() == 0){
				layoutCompleted = true;
				continue;
			}
			
			if(!layoutCompleted){
				layout.append(line+"\n");
			}else{
				request.append(line+System.lineSeparator());
			}
		}
		sc.close();
		
		ITheatreRequestService requestService = new TheatreRequestServiceImpl();
		Layout outputLayout = requestService.getLayout(layout.toString());
		List<Request> outputRequest = requestService.getRequest(request.toString());
		requestService.processTickets(outputLayout, outputRequest);
		
		for(Request r : outputRequest){
			
			if(r.getReason() != null){
				System.out.println(r.getName()+" "+r.getReason());
			}else{
				System.out.println(r.getName()+" "+"Row"+" "+r.getRowNumber()+" "+"Section"+" "+r.getSectionNumber());
			}
		}
		
	}
}
