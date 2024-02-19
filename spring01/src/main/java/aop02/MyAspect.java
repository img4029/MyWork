package aop02;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
	
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
