package com.projeto.banco.de.dados.streaming.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarUsuarioSistemaDto;
import com.projeto.banco.de.dados.streaming.video.dto.UsuarioAutenticarDto;
import com.projeto.banco.de.dados.streaming.video.dto.UsuarioSistemaDto;
import com.projeto.banco.de.dados.streaming.video.service.UsuarioSistemaService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioSistemaController {

	@Autowired
	private UsuarioSistemaService usuarioServico;

	@GetMapping("/find-all")
	public ResponseEntity<List<UsuarioSistemaDto>> findAll() {
		return ResponseEntity.ok().body(usuarioServico.findAll());
	}

	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<UsuarioSistemaDto> findById(@PathVariable Long idUsuarioSistema) {
		return ResponseEntity.ok().body(usuarioServico.findById(idUsuarioSistema));
	}

	@PostMapping("/insert-user")
	public ResponseEntity<Long> insertUser(@RequestBody UsuarioSistemaDto usuarioSistemaDto) {
		return ResponseEntity.ok().body(usuarioServico.insertUser(usuarioSistemaDto));
	}

	@PostMapping("/update-user")
	public ResponseEntity<Long> updateUser(@RequestBody AtualizarUsuarioSistemaDto atualilzarUsuarioSistemaDto) {
		return ResponseEntity.ok().body(usuarioServico.updateUser(atualilzarUsuarioSistemaDto));
	}

	@PostMapping("/delete-user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long idUsuarioSistema) {
		return ResponseEntity.ok().body(usuarioServico.deleteUser(idUsuarioSistema));
	}

	@PostMapping("/autenticar")
	public ResponseEntity<String> autenticarUsuario(@RequestBody UsuarioAutenticarDto usuarioDto) {
		return ResponseEntity.ok(usuarioServico.autenticar(usuarioDto));
	}
}
