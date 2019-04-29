package com.imooc.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class FirstScheduledJob extends QuartzJobBean {
	private AnotherBean anotherBean;

	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("FirstScheduledJob Executes!");
		this.anotherBean.printAnotherMessage();
	}

}
