package com.example.demo.dto.mapper;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entity.Driver;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DriverMapper {
	DriverDTO toDto(Driver driver);

	Driver toEntity(DriverDTO dto);
}