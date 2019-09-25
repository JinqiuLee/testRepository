package com.atguigu.crowd.funding.entity;

/**
 * ͳһ������Ŀ������Ajax�������Ӧ��ʽ����Ϊ��Ŀ��һ�������淶
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

	// ִ�гɹ��������ݼ���Ϣ
	public static <E> ResultEntity<E> successWithData(E data) {
		return new ResultEntity<E>(data, SUCCESS, NO_MESSAGE);
	}

	// ִ�гɹ����������ݼ���Ϣ
	public static ResultEntity<String> successWithoutData() {
		return new ResultEntity<String>(NO_DATA, SUCCESS, NO_MESSAGE);
	}
	
	//ִ�гɹ������ݿ���Ӱ�������Ϊ0
	public static ResultEntity<String> successWithoutUpdate(String message) {
		return new ResultEntity<String>(NO_DATA, SUCCESS, message);
	}
	
	// ִ��ʧ�ܷ���ʧ����Ϣ������
	public static <E> ResultEntity<E> failedWithData(E data, String message) {
		return new ResultEntity<E>(data, FAILED, message);
	}

	public static ResultEntity<String> failed(String message) {
		// TODO Auto-generated method stub
		return new ResultEntity<String>(NO_DATA, FAILED, message);
	}

}
