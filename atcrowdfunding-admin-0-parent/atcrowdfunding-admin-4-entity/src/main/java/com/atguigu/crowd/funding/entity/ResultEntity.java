package com.atguigu.crowd.funding.entity;

/**
 * 统一整个项目中所有Ajax请求的响应格式，作为项目的一个开发规范
 * 
 * @author Lenovo
 * 
 */
public class ResultEntity<T> {
	public static final String NO_MESSAGE = "NO_MESSAGE";
	public static final String FAILED = "FAILED";
	public static final String SUCCESS = "SUCCESS";
	public static final String NO_DATA = "NO_DATA";
	public static final String NO_UPDATE = "NO_UPDATE";
	public T data;
	public String result;
	public String message;

	public ResultEntity(T data, String result, String message) {
		super();
		this.data = data;
		this.result = result;
		this.message = message;
	}

	public ResultEntity() {
		super();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultEntity [data=" + data + ", result=" + result
				+ ", message=" + message + "]";
	}

	// 执行成功返回数据及消息
	public static <E> ResultEntity<E> successWithData(E data) {
		return new ResultEntity<E>(data, SUCCESS, NO_MESSAGE);
	}

	// 执行成功不返回数据及消息
	public static ResultEntity<String> successWithoutData() {
		return new ResultEntity<String>(NO_DATA, SUCCESS, NO_MESSAGE);
	}
	
	//执行成功但数据库受影响的行数为0
	public static ResultEntity<String> successWithoutUpdate(String message) {
		return new ResultEntity<String>(NO_DATA, SUCCESS, message);
	}
	
	// 执行失败返回失败消息及数据
	public static <E> ResultEntity<E> failedWithData(E data, String message) {
		return new ResultEntity<E>(data, FAILED, message);
	}

	public static ResultEntity<String> failed(String message) {
		// TODO Auto-generated method stub
		return new ResultEntity<String>(NO_DATA, FAILED, message);
	}

}
