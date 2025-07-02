package com.example.demo.dto;

import com.example.demo.entity.Order.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderUpdateStatusDTO {
	@NotNull
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}