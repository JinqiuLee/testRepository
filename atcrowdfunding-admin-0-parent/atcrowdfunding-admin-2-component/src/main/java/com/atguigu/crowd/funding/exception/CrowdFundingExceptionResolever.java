package com.atguigu.crowd.funding.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.crowd.funding.entity.ResultEntity;
import com.atguigu.crowd.funding.util.CrowdFundingConstant;
import com.atguigu.crowd.funding.util.CrowdFundingUtils;
import com.google.gson.Gson;

@ControllerAdvice
public class CrowdFundingExceptionResolever {
	@ExceptionHandler(value = Exception.class)
	public ModelAndView catchException(Exception exception,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 1.�Ե�ǰ������м��
		boolean checkAsyncRequestResult = CrowdFundingUtils
				.checkAsyncRequest(request);

		// 2.������첽����
		if (checkAsyncRequestResult) {

			// 3.����ResultEntity����
			ResultEntity<String> resultEntity = ResultEntity
					.failed(CrowdFundingConstant.UNKNOW_ERROR);

			// 4.��resultEntityת��ΪJSON��ʽ
			Gson gson = new Gson();
			String json = gson.toJson(resultEntity);

			// 5.��json��Ϊ��Ӧ���ݷ��ظ������
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(json);

			return null;
		}

		ModelAndView mav = new ModelAndView();

		mav.addObject("exception", exception);

		mav.setViewName("system-error");

		return mav;
	}

}
