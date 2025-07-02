package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DriverDTO;
import com.example.demo.dto.mapper.DriverMapper;
import com.example.demo.entity.Driver;
import com.example.demo.repository.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverService {
	@Autowired
	DriverRepository driverRepo;
	@Autowired
	DriverMapper mapper;

	public DriverDTO createDriver(DriverDTO dto) {
		Driver driver = mapper.toEntity(dto);
		return mapper.toDto(driverRepo.save(driver));
	}

	public List<DriverDTO> listActiveDrivers() {
		return driverRepo.findByActiveTrue().stream().map(mapper::toDto).toList();
	}
}
