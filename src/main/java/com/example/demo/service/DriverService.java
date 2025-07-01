package com.example.demo.service;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entity.Driver;
import com.example.demo.dto.mapper.DriverMapper;
import com.example.demo.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
	private final DriverRepository driverRepo;
	private final DriverMapper mapper;

	public DriverDTO createDriver(DriverDTO dto) {
		Driver driver = mapper.toEntity(dto);
		return mapper.toDto(driverRepo.save(driver));
	}

	public List<DriverDTO> listActiveDrivers() {
		return driverRepo.findByActiveTrue().stream().map(mapper::toDto).toList();
	}
}
