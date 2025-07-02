package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.OrderCreateDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.mapper.OrderMapper;
import com.example.demo.entity.Driver;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderAttachment;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.InvalidOrderStateException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.OrderAttachmentRepository;
import com.example.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	@Autowired
	DriverRepository driverRepo;
	@Autowired
	OrderAttachmentRepository attachmentRepo;
	@Autowired
	OrderMapper orderMapper;

//	public OrderDTO createOrder(OrderCreateDTO dto) {
//		Order order = Order.builder().origin(dto.getOrigin()).destination(dto.getDestination())
//				.status(Order.Status.CREATED).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
//		return orderMapper.toDto(orderRepo.save(order));
//	}
	
	public OrderDTO createOrder(OrderCreateDTO dto) {
	    Order order = new Order();
	    order.setOrigin(dto.getOrigin());
	    order.setDestination(dto.getDestination());
	    order.setStatus(Order.Status.CREATED);
	    order.setCreatedAt(LocalDateTime.now());
	    order.setUpdatedAt(LocalDateTime.now());
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

//	public void addAttachment(UUID orderId, MultipartFile file) throws Exception {
//		Order order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//		String type = file.getContentType();
//		if (!(type.equals("application/pdf") || type.equals("image/png") || type.equals("image/jpeg")))
//			throw new BusinessException("Invalid file type");
//
//		OrderAttachment attach = OrderAttachment.builder().order(order).fileName(file.getOriginalFilename())
//				.fileType(type).data(file.getBytes()).build();
//		attachmentRepo.save(attach);
//	}
	
	public void addAttachment(UUID orderId, MultipartFile file) throws Exception {
	    Order order = orderRepo.findById(orderId)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
	    String type = file.getContentType();
	    if (!(type.equals("application/pdf") || type.equals("image/png") || type.equals("image/jpeg")))
	        throw new BusinessException("Invalid file type");

	    OrderAttachment attach = new OrderAttachment();
	    attach.setOrder(order);
	    attach.setFileName(file.getOriginalFilename());
	    attach.setFileType(type);
	    attach.setData(file.getBytes());

	    attachmentRepo.save(attach);
	}
}