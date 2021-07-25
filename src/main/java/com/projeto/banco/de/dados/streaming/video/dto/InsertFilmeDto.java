package com.projeto.banco.de.dados.streaming.video.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsertFilmeDto {

	private Long idFilme;

	private String titulo;

	private String sinopse;

	private int ano;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataLancamento;

	private String listaGenero;

}
