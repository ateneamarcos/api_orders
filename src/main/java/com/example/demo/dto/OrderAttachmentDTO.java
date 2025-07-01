package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderAttachmentDTO {
	private String fileName;
	private String fileType;
	private byte[] data;
}