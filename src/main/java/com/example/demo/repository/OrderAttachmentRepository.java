package com.example.demo.repository;

import com.example.demo.entity.OrderAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderAttachmentRepository extends JpaRepository<OrderAttachment, UUID> {
	
}