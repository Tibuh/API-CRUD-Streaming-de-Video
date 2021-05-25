package com.projeto.banco.de.dados.streaming.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class UsuarioAutenticarDto {
	
	private String email;
	
	private String senha;
	
}
