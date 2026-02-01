package com.project.pjt.restaurant.stores.restaurant.custom;

/**
 * 소지금
 */
public class NotEnoughMoneyException extends Exception {
	public NotEnoughMoneyException(String message) {
		super(message);
	}
}
