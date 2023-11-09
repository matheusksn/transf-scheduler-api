	package com.matheusksn.transfschedulerapi.exception;
	
	import lombok.AllArgsConstructor;
import lombok.Data;
	
	@AllArgsConstructor
	@Data
	public class ErrorDetail {
	    private String title;
	    private String detail;
	
	}
