package com.projeto.banco.de.dados.streaming.video.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.banco.de.dados.streaming.video.entity.Serie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SerieDto {

	private String titulo;

	private String sinopse;

	private int ano;

	private byte numeroTemporada;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate anoFim;

	private List<GeneroDto> listaGenero;

	List<EpisodioDto> listaEpisodio;

	public SerieDto(Serie serie) {
		this.titulo = serie.getTitulo();
		this.sinopse = serie.getSinopse();
		this.ano = serie.getAno();
		this.numeroTemporada = serie.getNumeroTemporada();
		this.anoFim = serie.getAnoFim();
		this.listaGenero = serie.getGeneros().stream()
				.map(genero -> new GeneroDto(genero))
				.collect(Collectors.toList());
		this.listaEpisodio = serie.getEpisodios().stream().map(episodio -> new EpisodioDto(episodio))
				.collect(Collectors.toList());
	}
}
