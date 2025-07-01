package com.example.demo.dto.mapper;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { DriverMapper.class })
public interface OrderMapper {
	OrderDTO toDto(Order order);

	Order toEntity(OrderDTO dto);
}