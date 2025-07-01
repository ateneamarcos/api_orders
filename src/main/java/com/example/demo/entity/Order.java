package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@NotBlank
	@Size(max = 255)
	@Column(nullable = false)
	private String origin;

	@NotBlank
	@Size(max = 255)
	@Column(nullable = false)
	private String destination;

	@NotNull
	private LocalDateTime createdAt;

	@NotNull
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "driver_id")
	@Valid
	private Driver driver;

	public enum Status {
		CREATED, IN_TRANSIT, DELIVERED, CANCELLED
	}
}