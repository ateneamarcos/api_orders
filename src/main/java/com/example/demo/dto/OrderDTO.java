package com.example.demo.dto;

import com.example.demo.entity.Order.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDTO {
	private UUID id;
	private Status status;
	private String origin;
	private String destination;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private DriverDTO driver;
}