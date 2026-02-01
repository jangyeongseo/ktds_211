package com.project.pjt.restaurant.stores.restaurant.custom;

/**
 * 재고
 */
public class OutOfStockException extends Exception {
	public OutOfStockException(String message) {
		super(message);
	}
}
