package aop01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// ** IOC/DI 적용
	    // => 스프링컨테이너 생성
	    // => 필요한 Bean 을 주입받는다
		
		AbstractApplicationContext sc = new GenericXmlApplicationContext("aop01.xml");
		
		Programmer programmerBoy = (Programmer)sc.getBean("boy");
		Programmer programmerGirl = (Programmer)sc.getBean("girl");
		
		programmerBoy.doStudying();
		System.out.println();
		programmerGirl.doStudying();
		
		sc.close();
	} //main

} //class
