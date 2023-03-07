package com.texo.goldenraspberryawards.domain.exception;

import java.io.Serial;

public class LoadDataErrorException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -8756409016046349432L;

	public LoadDataErrorException(String message) {
		super(message);
	}

}
