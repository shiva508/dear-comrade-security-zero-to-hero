package com.comrade.model;

import java.util.Date;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HttpResponse {
	private Integer httpStatusCode;
	private HttpStatus httpStatus;
	private String reason;
	private String message;
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy hh:mm:ss")
	private Date timeStamp;


}
