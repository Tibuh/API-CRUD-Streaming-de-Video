package com.projeto.banco.de.dados.streaming.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.banco.de.dados.streaming.video.dto.UsuarioAutenticarDto;
import com.projeto.banco.de.dados.streaming.video.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServico;

	@PostMapping("/autenticar")
	public ResponseEntity<UsuarioAutenticarDto> autenticarUsuario(@RequestBody UsuarioAutenticarDto usuarioDto) {
		return ResponseEntity.ok().body(usuarioServico.autenticar(usuarioDto));
	}
}
