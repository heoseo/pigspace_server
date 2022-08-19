package com.pigplace.common.vo;

public class ControllerSupport {
	
	/**
	 * <h1>�����ڵ�� �޼����� �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(int code, String message, T data){
		ResponseEntity<T> res = new ResponseEntity<T>(code, message, data);
		return res;
	}

	
	
	
	//���� ���� ����(default StatusCode : 200)
	/**
	 * <h1>data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(){
		return this.getOkResponse(200, Status.StatusCode.get(200), null);
	}
	/**
	 * <h1>data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(T data) {
		return this.getOkResponse(200, Status.StatusCode.get(200), data);
	}
	/**
	 * <h1>�����ڵ带 �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(int code){
		return this.getOkResponse(code, Status.StatusCode.get(code), null);
	}
	/**
	 * <h1>�޼����� �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(String message){
		return this.getOkResponse(200, message, null);
	}
	/**
	 * <h1>�����ڵ�� �޼����� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(int code, String message){
		return this.getOkResponse(code, message, null);
	}
	/**
	 * <h1>�޼����� �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(String message, T data){
		return this.getOkResponse(200, message, data);
	}

	
	
	
	//���� ���� ����(default StatusCode : 500)
	/**
	 * <h1>data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(){
		return this.getOkResponse(500, Status.StatusCode.get(500), null);
	}
	/**
	 * <h1>data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(T data) {
		return this.getOkResponse(500, Status.StatusCode.get(500), data);
	}
	/**
	 * <h1>�����ڵ带 �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(int code){
		return this.getOkResponse(code, Status.StatusCode.get(code), null);
	}
	/**
	 * <h1>�޼����� �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(String message){
		return this.getOkResponse(500, message, null);
	}
	/**
	 * <h1>�����ڵ�� �޼����� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(int code, String message){
		return this.getOkResponse(code, message, null);
	}
	/**
	 * <h1>�޼����� �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(String message, T data){
		return this.getOkResponse(200, message, data);
	}
	/**
	 * <h1>�����ڵ�� �޼����� �����ϰ� data�� ���� Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(int code, String message, T data){
		ResponseEntity<T> res = new ResponseEntity<T>(code, Status.StatusCode.get(code), data);
		return res;
	}
}
