package com.example.demo.exception;

public class InvalidOrderStateException extends RuntimeException {
	public InvalidOrderStateException(String msg) {
		super(msg);
	}
}