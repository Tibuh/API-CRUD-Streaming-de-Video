package com.projeto.banco.de.dados.streaming.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class AtualizarUsuarioSistemaDto {

	private Long idUsuarioSistema;

	private String login;

	private String nome;

	private String senha;

	private boolean administrador;
}
