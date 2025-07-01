package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.dto.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepo;
	private final DriverRepository driverRepo;
	private final OrderAttachmentRepository attachmentRepo;
	private final OrderMapper orderMapper;

	public OrderDTO createOrder(OrderCreateDTO dto) {
		Order order = Order.builder().origin(dto.getOrigin()).destination(dto.getDestination())
				.status(Order.Status.CREATED).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
		return orderMapper.toDto(orderRepo.save(order));
	}

	public OrderDTO updateStatus(UUID orderId, Order.Status newStatus) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		validateStatusTransition(order.getStatus(), newStatus);
		order.setStatus(newStatus);
		order.setUpdatedAt(LocalDateTime.now());
		return orderMapper.toDto(orderRepo.save(order));
	}

	private void validateStatusTransition(Order.Status current, Order.Status next) {
		if (current == Order.Status.CREATED && next == Order.Status.IN_TRANSIT)
			return;
		if (current == Order.Status.IN_TRANSIT && (next == Order.Status.DELIVERED || next == Order.Status.CANCELLED))
			return;
		throw new InvalidOrderStateException("Invalid status transition");
	}

	public OrderDTO getOrderById(UUID id) {
		return orderMapper
				.toDto(orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found")));
	}

	public OrderDTO assignDriver(UUID orderId, UUID driverId) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		if (order.getStatus() != Order.Status.CREATED)
			throw new BusinessException("Only orders in CREATED status can be assigned");

		Driver driver = driverRepo.findById(driverId)
				.orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
		if (!driver.getActive())
			throw new BusinessException("Driver is not active");

		order.setDriver(driver);
		order.setUpdatedAt(LocalDateTime.now());
		return orderMapper.toDto(orderRepo.save(order));
	}

	public void addAttachment(UUID orderId, MultipartFile file) throws Exception {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		String type = file.getContentType();
		if (!(type.equals("application/pdf") || type.equals("image/png") || type.equals("image/jpeg")))
			throw new BusinessException("Invalid file type");

		OrderAttachment attach = OrderAttachment.builder().order(order).fileName(file.getOriginalFilename())
				.fileType(type).data(file.getBytes()).build();
		attachmentRepo.save(attach);
	}
}