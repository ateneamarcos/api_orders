package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotBlank
	@Size(max = 100)
	@Column(nullable = false)
	private String name;

	@NotBlank
	@Size(max = 50)
	@Column(nullable = false, unique = true)
	private String licenseNumber;

	@NotNull
	@Column(nullable = false)
	private Boolean active;
}
