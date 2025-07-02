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
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public DriverDTO getDriver() {
		return driver;
	}
	public void setDriver(DriverDTO driver) {
		this.driver = driver;
	}
	
	
}