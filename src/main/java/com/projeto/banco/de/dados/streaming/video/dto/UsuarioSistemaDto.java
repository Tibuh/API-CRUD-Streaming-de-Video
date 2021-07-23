package com.projeto.banco.de.dados.streaming.video.dto;

import com.projeto.banco.de.dados.streaming.video.entity.UsuarioSistema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSistemaDto {

	private String login;

	private String nome;

	private String senha;

	private boolean administrador;
	
	public UsuarioSistemaDto(UsuarioSistema usuarioSistema) {
		this.login = usuarioSistema.getLogin();
		this.nome = usuarioSistema.getNome();
		this.senha = usuarioSistema.getSenha();
		this.administrador = usuarioSistema.isAdministrador();
	}
}
