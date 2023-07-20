package com.placementmanagement.Springboot.responses;

import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
public class ApiResponseBuilder<T> {
	private Optional<T> payload;
	private List<T> payloadList;
	private PayloadType payloadType = PayloadType.NONE;
	private final String successMessage;
	private final String errorMessage;

	/**
	 * payloadType is an indicator of type of payload is used payloadType.
	 * 
	 * payload types are NONE, SINGLE, LIST.
	 * 
	 * VALUE = NONE : type is NOT DECIDED. NO object is created yet.
	 * 
	 * VALUE = SINGLE : type is DECIDED i.e.., object created is of type
	 * 'Optional<T>'.
	 * 
	 * VALUE = LIST : type is DECIDED i.e.., object created is of type 'List<T>'
	 */

	public ApiResponseBuilder(Optional<T> payload, String successMessage, String errorMessage) {
		this.payload = payload;
		this.successMessage = successMessage;
		this.errorMessage = errorMessage;
		this.payloadType = PayloadType.SINGLE;
	}

	public ApiResponseBuilder(List<T> payloadList, String successMessage, String errorMessage) {
		this.payloadList = payloadList;
		this.successMessage = successMessage;
		this.errorMessage = errorMessage;
		this.payloadType = PayloadType.LIST;
	}

	private boolean getSuccessStatus() {

		boolean status = false;
		// performing validation individually
		if (payloadType.equals(PayloadType.LIST)) {
			status = (payloadList != null && ! payloadList.isEmpty());
		}
		
		if (payloadType.equals(PayloadType.SINGLE)) {
			status = (payload != null && ! payload.isEmpty());
		}
		
		
		return status;
	}

	private String getMessage() {
		return getSuccessStatus() ? successMessage : errorMessage;
	}

	private short getStatusCode() {
		return (short) (getSuccessStatus() ? 200 : 204);
	}

	public ApiResponse getResponseInstance() {
		return new ApiResponse(getSuccessStatus(), getMessage(), getStatusCode(), getData());
	}

	private Object getData() {
		System.out.println("in getData()");

		// returning List<T>
		if (payloadType.equals(PayloadType.LIST)) {
			return payloadList;
		}

		// returning Optional<T>
		if (payloadType.equals(PayloadType.SINGLE)) {
			return payload;
		}

		// when PAYLOADLIST & PAYLOAD are NULL
		return Optional.empty();
	}

}