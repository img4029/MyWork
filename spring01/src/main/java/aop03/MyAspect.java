package aop03;

import org.aspectj.lang.ProceedingJoinPoint;

//** xml 방식의 공통적 관심 사항 구현 2.
//=> pointcut : 매개변수, return 값 없음  
//=> 개별 advice 메서드를 구현 
//  Before, After_returning, After_throwing, After 

//** 용어정리
//Target: 핵심사항(Core concerns) 가 구현된 객체 : Boy, Girl
//JoinPoint: 클라이언트가 호출하는 모든 비즈니스 메서드    
//       ( 공통관심사항이 적용될수있는 지점, ex:메소드 호출시, 객체생성시 등 )
//Pointcut: JoinPoint 중 실제 공통적 관심사항이 적용될 대상 : doStudying()  
//Advice : 공통관심사항(Cross-Cutting) 구현 코드 + 적용시점
//     : 시점별 메서드들 ( myBefore() .... )
//     : 적용시점 (핵심로직 실행 전, 후, 정상 종료 후, 비정상 종료 후 등 )
//Aspect : Advice + Pointcut

public class MyAspect {
	
	// ** Before
	public void myBefore() {
		System.out.println("프로젝트 과제를 합니다 => Before");
	}
		
	// ** After_returning: 핵심적 관심사항(기능)의 정상종료
	public void myAfter_returning() {
		System.out.println("~~ 200 OK: 작업이 잘 완료되었습니다.");
	}
	
	// ** After_throwing: 핵심적 관심사항(기능)의 비정상종료 
	public void myAfter_throwing() {
		System.out.println("** 밤새도록 수정 작업 => 예외발생으로 핵심적 관심사항 비정상종료");
	}
	
	// ** After: 정상/비성장 관계없이 무조건 시행
	public void myAfter() {
		System.out.println("** finally : 무조건 제줄합니다 => 무조건 종료(After)");
	}
	
	public void myAround(ProceedingJoinPoint joinPoint) {
		// ** Before
		System.out.println("프로젝트 과제를 합니다 => Before");
		try {
			
			// ** 핵심기능 수행 (pointCut 전달)
	         // => Around 메서드의 매개변수(ProceedingJoinPoint Type) 를 통해 전달받아 수행
			joinPoint.proceed();			
			// => Throwable 로 예외처리를 해야함
	        //    Throwable - Exception - RuntimeException (UnChecked), IOException (Checked)
	        //              - Error (개발자가 대응못하는 시스템적 오류)
			
			// ** 핵심적 관심사항의 정상종료 : After_returning
			System.out.println("~~ 200 OK: 작업이 잘 완료되었습니다.");

		} catch (Throwable e) {
			
			// ** 핵심적 관심사항의 비정상종료 : After_throwing
			System.out.println("** 밤새도록 수정 작업 => 예외발생으로 핵심적 관심사항 비정상종료");
			
	        // => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test)
	        // => main으로 전달하려면 예외발생시켜주면됨.
	        // 	  throw new Exception(e) ;  // Exception 은 Checked -> try~catch 처리 해야함 
			throw new RuntimeException(e);
			
		} finally {
			System.out.println("** finally : 무조건 제줄합니다 => 무조건 종료(After)");
		} //fry,catch,finally
		
	} //doStudying
	
} //class
