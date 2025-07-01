package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "order_attachments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderAttachment {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@NotBlank
	private String fileName;

	@NotBlank
	private String fileType;

	@Lob
	@NotNull
	private byte[] data;
}
