package org.hello.algorithm.bean;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-02-09 18:25
 **/
//@Repository
public class Hello {

	private Dog dog;

	private User u = new User();



	public Hello() {
		System.out.println("Hello noArgs");
	}

	public void hello(){
		System.out.println("Hello,spring!");
	}
}
