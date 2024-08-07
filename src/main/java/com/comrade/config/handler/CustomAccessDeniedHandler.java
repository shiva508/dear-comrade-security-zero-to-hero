package com.comrade.config.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import com.comrade.model.HttpResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException, ServletException {
		HttpResponse httpResponse =HttpResponse.builder()
											   .httpStatusCode(HttpStatus.UNAUTHORIZED.value())
											   .httpStatus(HttpStatus.UNAUTHORIZED)
											   .message("ACCESS DENAIED")
											   .reason(HttpStatus.UNAUTHORIZED.getReasonPhrase())
											   .timeStamp(new Date()).build();
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		OutputStream outputStream=response.getOutputStream();
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.writeValue(outputStream, httpResponse);
		outputStream.flush();

	}

}
