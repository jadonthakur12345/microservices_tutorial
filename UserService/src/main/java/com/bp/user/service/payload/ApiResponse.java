package com.bp.user.service.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
	
	public ApiResponse(String message2, boolean b, HttpStatus notFound) {
		// TODO Auto-generated constructor stub
	}
	private String message;
	private boolean success;
	private HttpStatus httpStatus;
}
