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
public class AtualizarSerieDto {

	private Long idSerie;

	private String titulo;

	private String sinopse;

	private int ano;

	private byte numeroTemporada;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate anoFim;

	private List<GeneroDto> listaGenero;
}
