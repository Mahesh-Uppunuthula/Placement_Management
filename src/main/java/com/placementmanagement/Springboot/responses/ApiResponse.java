package com.placementmanagement.Springboot.responses;

import lombok.Data;

@Data
public class ApiResponse {
	private final boolean success;
	private final String message;
	private final short status_code;
	private final Object payload;
}