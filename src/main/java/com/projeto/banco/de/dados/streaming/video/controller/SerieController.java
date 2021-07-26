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

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarEpisodioDto;
import com.projeto.banco.de.dados.streaming.video.dto.AtualizarSerieDto;
import com.projeto.banco.de.dados.streaming.video.dto.EpisodioDto;
import com.projeto.banco.de.dados.streaming.video.dto.InformacaoEpisodioDto;
import com.projeto.banco.de.dados.streaming.video.dto.InsertSerieDto;
import com.projeto.banco.de.dados.streaming.video.dto.SerieDto;
import com.projeto.banco.de.dados.streaming.video.service.SerieService;

@RestController
@RequestMapping(value = "/serie")
public class SerieController {

	@Autowired
	private SerieService serieService;

	@GetMapping("/find-all")
	public ResponseEntity<List<SerieDto>> findAll() {
		return ResponseEntity.ok().body(serieService.findAll());
	}

	@GetMapping("/find-by-id/{idSerie}")
	public ResponseEntity<SerieDto> findById(@PathVariable Long idSerie) {
		return ResponseEntity.ok().body(serieService.findById(idSerie));
	}

	@GetMapping("/find-episodio-by-id/{idEpisodio}")
	public ResponseEntity<EpisodioDto> findEpisodioById(@PathVariable Long idEpisodio) {
		return ResponseEntity.ok().body(serieService.findEpisodioById(idEpisodio));
	}

	@GetMapping("/find-episodio-by-id-serie/{idSerie}")
	public ResponseEntity<List<EpisodioDto>> findEpisodioByIdSerie(@PathVariable Long idSerie) {
		return ResponseEntity.ok().body(serieService.findEpisodioByIdSerie(idSerie));
	}

	@PostMapping("/insert-serie")
	public ResponseEntity<Long> insertSerie(@RequestBody InsertSerieDto serieDtoDto) {
		return ResponseEntity.ok().body(serieService.insertSerie(serieDtoDto));
	}

	@PostMapping("/insert-episodio")
	public ResponseEntity<Long> insertEpisodio(@RequestBody InformacaoEpisodioDto InformacaoEpisodioDtoDtoDto) {
		return ResponseEntity.ok().body(serieService.insertEpisodio(InformacaoEpisodioDtoDtoDto));
	}

	@PostMapping("/update-serie")
	public ResponseEntity<Long> updteSerie(@RequestBody AtualizarSerieDto atualizarSerieDto) {
		return ResponseEntity.ok().body(serieService.updateSerie(atualizarSerieDto));
	}

	@PostMapping("/update-episodio")
	public ResponseEntity<Long> updateEpisodio(@RequestBody AtualizarEpisodioDto atualizarEpisodioDto) {
		return ResponseEntity.ok().body(serieService.updateEpisodio(atualizarEpisodioDto));
	}

	@PostMapping("/delete-serie/{idSerie}")
	public ResponseEntity<String> deleteFilme(@PathVariable Long idSerie) {
		return ResponseEntity.ok().body(serieService.deleteSerie(idSerie));
	}

	@PostMapping("/delete-episodio/{idEpisodio}")
	public ResponseEntity<String> deleteEpisodio(@PathVariable Long idEpisodio) {
		return ResponseEntity.ok().body(serieService.deleteEpisodio(idEpisodio));
	}
}
