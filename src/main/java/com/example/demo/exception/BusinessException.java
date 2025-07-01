package com.example.demo.exception;

public class BusinessException extends RuntimeException {
	public BusinessException(String msg) {
		super(msg);
	}
}