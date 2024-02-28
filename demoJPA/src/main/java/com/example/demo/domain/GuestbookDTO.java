package com.example.demo.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GuestbookDTO {
	
	private Long gno; //Auto_increment
	private String title;
	private String content;
	private String writer;
	
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}
