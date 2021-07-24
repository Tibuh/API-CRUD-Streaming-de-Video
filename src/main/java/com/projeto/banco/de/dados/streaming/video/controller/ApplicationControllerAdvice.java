package com.projeto.banco.de.dados.streaming.video.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.projeto.banco.de.dados.streaming.video.config.ApiErrors;
import com.projeto.banco.de.dados.streaming.video.exception.ExcecaoNegocio;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(ExcecaoNegocio.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(ExcecaoNegocio ex) {
		return new ApiErrors(ex.getMessage());
	}
}

