package com.projeto.banco.de.dados.streaming.video.service;

import org.springframework.stereotype.Service;

import com.projeto.banco.de.dados.streaming.video.dto.UsuarioAutenticarDto;

@Service
public class UsuarioService {

	public UsuarioAutenticarDto autenticar(UsuarioAutenticarDto usuarioDto) {
		return usuarioDto;
	}
}
