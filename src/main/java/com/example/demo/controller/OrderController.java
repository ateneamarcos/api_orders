package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

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