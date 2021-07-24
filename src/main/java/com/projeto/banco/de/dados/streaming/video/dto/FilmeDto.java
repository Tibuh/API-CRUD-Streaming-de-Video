package com.projeto.banco.de.dados.streaming.video.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.banco.de.dados.streaming.video.entity.Filme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDto {

	private String titulo;

	private String sinopse;

	private int ano;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataLancamento;

	private List<GeneroDto> listaGenero;

	public FilmeDto(Filme filme) {
		this.titulo = filme.getTitulo();
		this.sinopse = filme.getSinopse();
		this.ano = filme.getAno();
		this.dataLancamento = filme.getDataLancamento();
		this.listaGenero = filme.getGeneros().stream().map(genero -> new GeneroDto(genero))
				.collect(Collectors.toList());
	}
}
