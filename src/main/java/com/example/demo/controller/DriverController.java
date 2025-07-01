package com.example.demo.controller;

import com.example.demo.dto.DriverDTO;
import com.example.demo.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
	private final DriverService driverService = new DriverService();

	@PostMapping
	public ResponseEntity<DriverDTO> create(@Valid @RequestBody DriverDTO dto) {
		return ResponseEntity.ok(driverService.createDriver(dto));
	}

	@GetMapping("/active")
	public ResponseEntity<List<DriverDTO>> listActive() {
		return ResponseEntity.ok(driverService.listActiveDrivers());
	}
}