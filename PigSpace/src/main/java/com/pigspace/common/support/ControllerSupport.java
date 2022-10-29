package com.pigspace.common.support;

public class ControllerSupport {

	/**
	 * <h1>응답코드와 메세지를 지정하고 data를 담은 Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(int code, String message, T data){
		ResponseEntity<T> res = new ResponseEntity<T>(code, message, data);
		return res;
	}




	//성공 응답 리턴(default StatusCode : 200)
	/**
	 * <h1>data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(){
		return this.getOkResponse(200, Status.StatusCode.get(200), null);
	}
	/**
	 * <h1>data를 담은 Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(T data) {
		return this.getOkResponse(200, Status.StatusCode.get(200), data);
	}
	/**
	 * <h1>응답코드를 지정하고 data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(int code){
		return this.getOkResponse(code, Status.StatusCode.get(code), null);
	}
	/**
	 * <h1>메세지를 지정하고 data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(String message){
		return this.getOkResponse(200, message, null);
	}
	/**
	 * <h1>응답코드와 메세지를 data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(int code, String message){
		return this.getOkResponse(code, message, null);
	}
	/**
	 * <h1>메세지를 지정하고 data를 담은 Response</h1>
	 */
	public <T> ResponseEntity<T> getOkResponse(String message, T data){
		return this.getOkResponse(200, message, data);
	}




	//실패 응답 리턴(default StatusCode : 500)
	/**
	 * <h1>data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(){
		return this.getOkResponse(500, Status.StatusCode.get(500), null);
	}
	/**
	 * <h1>data를 담은 Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(T data) {
		return this.getOkResponse(500, Status.StatusCode.get(500), data);
	}
	/**
	 * <h1>응답코드를 지정하고 data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(int code){
		return this.getOkResponse(code, Status.StatusCode.get(code), null);
	}
	/**
	 * <h1>메세지를 지정하고 data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(String message){
		return this.getOkResponse(500, message, null);
	}
	/**
	 * <h1>응답코드와 메세지를 data가 없는 Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(int code, String message){
		return this.getOkResponse(code, message, null);
	}
	/**
	 * <h1>메세지를 지정하고 data를 담은 Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(String message, T data){
		return this.getOkResponse(200, message, data);
	}
	/**
	 * <h1>응답코드와 메세지를 지정하고 data를 담은 Response</h1>
	 */
	public <T> ResponseEntity<T> getFailResponse(int code, String message, T data){
		ResponseEntity<T> res = new ResponseEntity<T>(code, Status.StatusCode.get(code), data);
		return res;
	}
}
