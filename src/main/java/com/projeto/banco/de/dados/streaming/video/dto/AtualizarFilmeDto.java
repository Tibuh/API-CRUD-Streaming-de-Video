package com.projeto.banco.de.dados.streaming.video.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarFilmeDto {

	private Long idTitulo;

	private String titulo;

	private String sinopse;

	private int ano;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataLancamento;

	private List<GeneroDto> listaGenero;
}