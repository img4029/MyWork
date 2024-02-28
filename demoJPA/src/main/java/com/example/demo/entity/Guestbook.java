package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Table(name="guestbook") // 클래스 이름과 동일한 경우 생략가능

@Getter
@Builder //복잡한 객체의 생성 과정과 표현 방법을 분리하여 다양한 구성의 인스턴스를 만드는 생성 패턴. 출처: https://inpa.tistory.com/entry/GOF-💠-빌더Builder-패턴-끝판왕-정리
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 생성(DB가 생성방식 결정)
	private Long gno; //Auto_increment
	
	@Column(length = 100, nullable = false) //길이 100글자 제한, not null
	private String title;
	@Column(length = 2000, nullable = false) //길이 2000글자 제한, not null
	private String content;
	@Column(length = 50, nullable = false) //길이 50글자 제한, not null
	private String writer;
	
	public void changTitle(String title) {
		this.title = title;
	}
	public void changContent(String content) {
		this.content = content;
	}
	
}
