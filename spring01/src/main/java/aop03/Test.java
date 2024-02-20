package aop03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// ** IOC/DI 적용
	    // => 스프링컨테이너 생성
	    // => 필요한 Bean 을 주입받는다
		
		AbstractApplicationContext sc = new GenericXmlApplicationContext("aop03.xml");
		
		Programmer programmerBoy = (Programmer)sc.getBean("boy");
		Programmer programmerGirl = (Programmer)sc.getBean("girl");
		try {
			System.out.println("** Boy Test **");
			programmerBoy.doStudying();
			System.out.println("\n** Girl Test **");
			programmerGirl.doStudying();
		} catch (Exception e) {
			System.out.println("\n** main Exception => "+e.toString());
		}
		sc.close();	
	} //main

} //class
