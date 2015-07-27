package com.demo.exception;

@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	public enum ExceptionType {
		GENERAL_ERROR, RECORD_NOT_FOUND, FILE_NOT_FOUND, INVALID_PARAMETER, INVALID_PARAMETER_COUNT, UPDATE_FAILURE, IO_ERROR, RECORD_ALREADY_EXISTS, INVALID_CONFIG, RECORD_NOT_FOUND2, RECORD_NOT_FOUND3
	}

	private ExceptionType exceptionType;

	public ApplicationException(ExceptionType exceptionType) {
		super();
		this.exceptionType = exceptionType;
	}

	public ApplicationException(ExceptionType exceptionType, String message,
			Throwable cause) {
		super(message, cause);
		this.exceptionType = exceptionType;
	}

	public ApplicationException(ExceptionType exceptionType, String message) {
		super(message);
		this.exceptionType = exceptionType;
	}

	public ApplicationException(ExceptionType exceptionType, Throwable cause) {
		super(cause);
		this.exceptionType = exceptionType;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}
}