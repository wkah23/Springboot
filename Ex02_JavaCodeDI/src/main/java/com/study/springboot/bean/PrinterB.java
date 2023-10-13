package com.study.springboot.bean;

public class PrinterB implements Printer{
	public void print(String message) {
		System.out.println("Printer B : " + message);
	}
}
