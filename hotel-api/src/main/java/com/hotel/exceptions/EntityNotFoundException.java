package com.hotel.exceptions;

public class EntityNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -4704961559013873788L;

	public EntityNotFoundException(String msg) {
		super(msg);
	}
}
