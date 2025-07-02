package com.example.demo.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class OrderCreateDTO {
	@NotBlank
	@Size(max = 255)
	private String origin;

	@NotBlank
	@Size(max = 255)
	private String destination;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}