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

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarGeneroDto;
import com.projeto.banco.de.dados.streaming.video.dto.GeneroDto;
import com.projeto.banco.de.dados.streaming.video.service.GeneroService;

@RestController
@RequestMapping(value = "/genero")
public class GeneroController {

	@Autowired
	private GeneroService generoService;

	@GetMapping("/find-all")
	public ResponseEntity<List<GeneroDto>> findAll() {
		return ResponseEntity.ok().body(generoService.findAll());
	}

	@GetMapping("/find-by-id/{idGenero}")
	public ResponseEntity<GeneroDto> findById(@PathVariable Long idGenero) {
		return ResponseEntity.ok().body(generoService.findById(idGenero));
	}

	@PostMapping("/insert")
	public ResponseEntity<Long> insertFilme(@RequestBody GeneroDto generoDto) {
		return ResponseEntity.ok().body(generoService.insertGenero(generoDto));
	}

	@PostMapping("/update")
	public ResponseEntity<Long> updteFilme(@RequestBody AtualizarGeneroDto atualizarGeneroDto) {
		return ResponseEntity.ok().body(generoService.updateGenero(atualizarGeneroDto));
	}

	@PostMapping("/delete/{idGenero}")
	public ResponseEntity<String> deleteFilme(@PathVariable Long idGenero) {
		return ResponseEntity.ok().body(generoService.deleteGenero(idGenero));
	}
}
