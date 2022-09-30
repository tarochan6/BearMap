package com.example.app.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Bear {

	private Integer id;
	private Date date;
	private String time;
	private String address;
	private Integer adult;
	private Integer child;
	private Integer unknown;
	private Integer total;
	private Integer info;
}
