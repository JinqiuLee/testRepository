package com.atguigu.crowd.funding.util;

import javax.servlet.http.HttpServletRequest;

public class CrowdFundingUtils {
	/**
	 * 用于判断一个请求是否是异步请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkAsyncRequest(HttpServletRequest request) {

		// 1.获取相应请求消息头
		String accept = request.getHeader("Accept");
		String xRequested = request.getHeader("X-Requested-With");

		// 2.判断请求消息头数据中是否包含目标特征
		if ((stringEffective(accept) && accept.contains("application/json"))
				|| (stringEffective(xRequested) && xRequested
						.contains("XMLHttpRequest"))) {
			return true;
		}

		return false;
	}
	
	/**
	 * 判断字符串是否有效
	 * @param source 待验证字符串
	 * @return true表示有效，false表示无效
	 */
	public static boolean stringEffective(String source) {
		
		return source != null && source.length() > 0;
	}

}
