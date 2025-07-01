package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class DriverDTO {
	private UUID id;

	@NotBlank
	@Size(max = 100)
	private String name;

	@NotBlank
	@Size(max = 50)
	private String licenseNumber;

	@NotNull
	private Boolean active;
}