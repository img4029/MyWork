package pageTest;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.ToString;

//** PageMaker : View 에 필요한 값을 완성
//=> 전체Row 갯수 (전체 Page수 계산위해 필요)
//=> 1Page당 표시할 pageNo갯수
//=> view 에 표시할 첫 PageNo
//=> view 에 표시할 끝 PageNo
//=> 출력 가능한 마지막 PageNo (totalRowsCount, rowPerPage 로 계산)
//=> 이전 PageBlock 으로
//=> 다음 PageBlock 으로

//=> Criteria 를 이용해서..

@Getter
@ToString
public class PageMaker {
	
	private int totalRowsCount; // 출력대상 전체 Row 갯수: from DB
	private int displayPageNo = 3; // 1page 당 표시할 PageNo 갯수
	private int spageNo; // View에 표시할 첫 pageNo: 계산
	private int epageNo; // View에 표시할 끝 pageNo: 계산
	// ※ 주의 필드명이 ePage 처럼 두번째 알파벳이 대문자인 경우
	//    => setter, getter 는 setsPageNo , getsPageNo 형태로 만들어지기때문에
	//       Lombok.. 등등 과 규칙이다르므로 사용시 불편 
	//         -> 그러므로 대.소문자 섞어사용시 주의. 
	private int lastPageNo; // 출력가능한 마지막 PageNo
	private boolean prev; // 이전 PageBlock 으로
	private boolean next; // 다음 PageBlock 으로
	
	Criteria cri;
	
	// ** 필요값 계산
	// 1) Criteria
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	// 2) totalRowsCount
	// => 전체 Rows 갯수 : Read from DB
	// => 이 값을 이용해서 나머지 필요한값 계산
	public void setTotalRowsCount(int totalRowsCount) {
		this.totalRowsCount = totalRowsCount;
		calcData();
	}
	// 3) 나머지 필요한값 계산
	public void calcData() {
		// 3.1) spageNo, epageNo
	    // => currPage가 속한 PageBlock 의 spageNo, epageNo 를 계산 
	      
	    // => pageNo를 n개씩 출력한다고 가정하면 epageNo 는 항상 n의 배수
	    //     displayPageNo=3 이면 3, 6, 9, 12......
	    // => ex) 17 page 요청 , displayPageNo=3, 일때 17이 몇번째 그룹 인지 계산하려면,
	    //        17/3 -> 5 나머지 2 결론은 정수 나누기 후 나머지가 있으면 +1 이 필요함
	    //    -> Math.ceil(17/3) -> Math.ceil(5.73..) -> 6.0 -> 6번쨰 그룹 16,17,18
	    // 즉, 17이 몇번째 그룹 인지 계산하고, 여기에 * displayPageNo 를 하면 됨.   
	      
	    // ** Math.ceil(c) : 매개변수 c 보다 크면서 같은 가장 작은 정수를 double 형태로 반환 
	    //                   ceil -> 천장, 예) 11/3=3.666..  -> 4
	    // => Math.ceil(12.345) => 13.0      
		this.epageNo = (int)Math.ceil(cri.getCurrPage() / (double)displayPageNo) * displayPageNo;
		this.spageNo = this.epageNo - displayPageNo + 1;
		
		// 3.2) lastpageNo 계산 & epageNo 의 적합성
		this.lastPageNo = (int)Math.ceil(this.totalRowsCount / (double)cri.getRowPerPage());
		if(this.epageNo > this.lastPageNo) this.epageNo = this.lastPageNo;
		
		// 3.3) prev, next
		//prev = this.spageNo > 1 ;
		prev = this.spageNo==1 ? false : true;
		next = this.epageNo==this.lastPageNo ? false : true;
		
	} //calcData
	
	// 4) QueryString 자동 만들기
	// ** 패키지 org.springframework.web.util
	// => 웹개발에 필요한 많은 유틸리티 클래스 제공
	// => UriComponents , UriComponentsBuilder
	//    Uri를 동적으로 생성해주는 클래스,
	//    파라미터가 조합된 uri를 손쉽게 만들어줌
	// => ?currPage=7&rowsPerPage=10 이것을 만들어줌
	//    ? 부터 만들어지므로 jsp Code에서 ? 포함하지 않도록 주의    
   
	// ** ver01
	// => QueryString 자동생성 
	//    ?currPage=4&rowsPerPage=3
	public String makeQuery(int currPage) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currPage", currPage)
				.queryParam("rowPerPage", cri.getRowPerPage())
				.build();
		
		return uriComponents.toString();
	} //makeQuery
	
	
	
} //class