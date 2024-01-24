package iocDI05_LCAll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// ** Bean(객체) 의 LifeCycle 
// => 객체생성 -> 사용 -> 소멸
// => 해당되는 특정시점에 자동실행 : xml, @(어노테이션), interface

// ** Test4. ALL
// => 실행순서 
// 	  생성 -> @(어노테이션) -> interface -> xml
// 	  컨테이너 close, 객체소멸 시작 -> @(어노테이션) -> interface -> xml

class LifeCycleTest implements InitializingBean, DisposableBean {
	public LifeCycleTest() { System.out.println("~~ LifeCycleTest All 생성자 ~~"); }
	
	// 1. xml 적용 메서드
	public void begin() { System.out.println("~~ xml All begin() ~~"); }
	public void end() { System.out.println("~~ xml All end() ~~"); }
	
	// 2. @ 적용메서드 
	@PostConstruct
	public void beginA() { System.out.println("~~ 어노테이션 All PostConstruct ~~"); }
	@PreDestroy
	public void endA() { System.out.println("~~ 어노테이션 All PreDestroy ~~"); }
	
	// 3. interface
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("~~ interface All afterPropertiesSet() ~~");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ interface All destroy() ~~");
	}
	
	public void login() { System.out.println("~~ LifeCycleTest All login() ~~"); }
	public void list() { System.out.println("~~ LifeCycleTest All list() ~~"); }
} //LifeCycleTest

public class LC04_All {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDI05_LCAll/lc_All.xml");
		LifeCycleTest lc = (LifeCycleTest)sc.getBean("lc");
		lc.login();
		lc.list();
		sc.close();
	} //main

} //class
