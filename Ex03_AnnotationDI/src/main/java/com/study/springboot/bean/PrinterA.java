package com.study.springboot.bean;

import org.springframework.stereotype.Component;

/*
 	이름을 지정하여 빈을 생성한다. PrinterA 타입의 PrinterA빈이 생성된다.
 */
@Component("printerA")
public class PrinterA implements Printer {

	@Override
	public void print(String message) {
		System.out.println("Printer A : " + message);
	}
}
