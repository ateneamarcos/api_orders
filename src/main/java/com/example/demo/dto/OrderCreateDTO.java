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
}