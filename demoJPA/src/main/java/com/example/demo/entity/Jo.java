package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity //javax.persistence.Entity
@Table(name="jo")
public class Jo {
	
	@Id
	private int jno;
	private String jname;
	private String captain;
	
	@Column(updatable = false)
	private String name;
	
	private String project;
	private String slogan;
}
