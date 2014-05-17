package model.utils;

import java.io.IOException;

public class MalformedBoardException extends IOException {
	
	private static final long serialVersionUID = 1L;
	
	public MalformedBoardException() {
		super();
	}
	
	public MalformedBoardException(String error) {
		super(error);
	}

}
