package org.hello.algorithm.bean;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-02-09 20:23
 **/
public class User {
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public User() {
		System.out.println("User noArgsConstructor");
	}

	public User(String value) {
		System.out.println("User ArgsConstructor："+value);
		this.value = value;
	}

	public void init(){
		System.out.println("init...");
	}

}
