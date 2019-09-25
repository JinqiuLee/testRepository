package com.atguigu.crowd.funding.util;

import javax.servlet.http.HttpServletRequest;

public class CrowdFundingUtils {
	/**
	 * �����ж�һ�������Ƿ����첽����
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkAsyncRequest(HttpServletRequest request) {

		// 1.��ȡ��Ӧ������Ϣͷ
		String accept = request.getHeader("Accept");
		String xRequested = request.getHeader("X-Requested-With");

		// 2.�ж�������Ϣͷ�������Ƿ����Ŀ������
		if ((stringEffective(accept) && accept.contains("application/json"))
				|| (stringEffective(xRequested) && xRequested
						.contains("XMLHttpRequest"))) {
			return true;
		}

		return false;
	}
	
	/**
	 * �ж��ַ����Ƿ���Ч
	 * @param source ����֤�ַ���
	 * @return true��ʾ��Ч��false��ʾ��Ч
	 */
	public static boolean stringEffective(String source) {
		
		return source != null && source.length() > 0;
	}

}
