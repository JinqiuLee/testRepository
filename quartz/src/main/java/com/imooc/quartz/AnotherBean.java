package com.imooc.quartz;

import org.springframework.stereotype.Component;

@Component("anotherBean")
public class AnotherBean {

	public void printAnotherMessage() {
		System.out.println("AnotherMessage");
	}

}
