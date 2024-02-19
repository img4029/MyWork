package aop02;

public class Girl implements Programmer{

	@Override
	public void doStudying() throws Exception {
		
		System.out.println("열심히 게시판을 만듭니다 => 핵심적 관심사항");
		
		// ** Test 를 위해 늘 성공으로 처리
		// => 항상 true 가 되도록 
		//if (new Random().nextBoolean()) {
		Boolean bl = true;
		if (bl) {
			throw new Exception("~~ Error 500*100 => 예외발생");
		} else {
			System.out.println("~~ 게시판 이상없이 작동 => 핵심적 관심사항 정상 종료");
		}
		
	} //doStudying
	
} //class
