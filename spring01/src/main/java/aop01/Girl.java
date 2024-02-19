package aop01;

import java.util.Random;

public class Girl implements Programmer{

	@Override
	public void doStudying() {
		System.out.println("프로젝트 과제를 합니다 => Before");
		try {
			System.out.println("열심히 게시판을 만듭니다 => 핵심적 관심사항");
			if (new Random().nextBoolean()) {
				// 실패 
				throw new Exception("~~ Error 500*100 => 예외발생");
			} else {
				System.out.println("~~ 게시판 이상없이 작동 => 핵심적 관심사항 정상 종료");
			}
		} catch (Exception e) {
			System.out.println("** Girl Exception => " + e.toString());
			System.out.println("** 밤새도록 수정 작업 => 예외발생으로 핵심적 관심사항 비정상 종료");
		} finally {
			System.out.println("** finally : 무조건 제줄합니다 => 무조건 종료(After)");
		} //fry,catch,finally
		
	} //doStudying
	
} //class
