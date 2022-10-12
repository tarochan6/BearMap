package com.example.app.domain;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Bear {
	private Integer id;
	@NotNull
	private Date date;
	private String time;
	@NotBlank
	private String address;
	private Integer adult;
	private Integer child;
	private Integer unknown;
	private Integer total;
	private Integer typeId;
	private String typeName;
}
