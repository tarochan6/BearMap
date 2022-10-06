package com.example.app.domain;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Bear {

	private Integer id;
	@NotBlank
	private Date date;
	private String time;
	@NotBlank
	private String address;
	private Integer adult;
	private Integer child;
	private Integer unknown;
	private Integer total;
	@NotBlank
	private Integer info;
	private String typeName;
}
