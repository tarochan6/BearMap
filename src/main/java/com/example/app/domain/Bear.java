package com.example.app.domain;



import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Bear {
	private Integer id;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	// @JsonFormat(pattern = "yyyy/MM/dd")
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
