package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.OrderCreateDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderUpdateStatusDTO;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderCreateDTO dto) {
		return ResponseEntity.ok(orderService.createOrder(dto));
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<OrderDTO> updateStatus(@PathVariable UUID id, @Valid @RequestBody OrderUpdateStatusDTO dto) {
		return ResponseEntity.ok(orderService.updateStatus(id, dto.getStatus()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@PutMapping("/{id}/assign-driver/{driverId}")
	public ResponseEntity<OrderDTO> assignDriver(@PathVariable UUID id, @PathVariable UUID driverId) {
		return ResponseEntity.ok(orderService.assignDriver(id, driverId));
	}

	@PostMapping("/{id}/attachments")
	public ResponseEntity<?> addAttachment(@PathVariable UUID id, @RequestParam("file") MultipartFile file)
			throws Exception {
		orderService.addAttachment(id, file);
		return ResponseEntity.ok().build();
	}
}