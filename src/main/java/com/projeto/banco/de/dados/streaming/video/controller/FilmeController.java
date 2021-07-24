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

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarFilmeDto;
import com.projeto.banco.de.dados.streaming.video.dto.FilmeDto;
import com.projeto.banco.de.dados.streaming.video.service.FilmeService;

@RestController
@RequestMapping(value = "/filme")
public class FilmeController {

	@Autowired
	private FilmeService filmeService;

	@GetMapping("/find-all")
	public ResponseEntity<List<FilmeDto>> findAll() {
		return ResponseEntity.ok().body(filmeService.findAll());
	}

	@GetMapping("/find-by-id/{idTitulo}")
	public ResponseEntity<FilmeDto> findById(@PathVariable Long idTitulo) {
		return ResponseEntity.ok().body(filmeService.findById(idTitulo));
	}

	@PostMapping("/insert")
	public ResponseEntity<Long> insertFilme(@RequestBody FilmeDto filmeDto) {
		return ResponseEntity.ok().body(filmeService.insertFilme(filmeDto));
	}

	@PostMapping("/update")
	public ResponseEntity<Long> updteFilme(@RequestBody AtualizarFilmeDto atualizarFilmeDto) {
		return ResponseEntity.ok().body(filmeService.updateFilme(atualizarFilmeDto));
	}

	@PostMapping("/delete/{idTitulo}")
	public ResponseEntity<String> deleteFilme(@PathVariable Long idTitulo) {
		return ResponseEntity.ok().body(filmeService.deleteFilme(idTitulo));
	}
}
