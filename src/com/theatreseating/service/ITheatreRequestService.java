package com.theatreseating.service;

import java.util.List;

import com.theatreseating.vo.Layout;
import com.theatreseating.vo.Request;

public interface ITheatreRequestService {

	List<Request> getRequest(String inputRequest);
	
	Layout getLayout(String inputLayout);
	
	void processTickets(Layout layout, List<Request> request);
	
	
}
