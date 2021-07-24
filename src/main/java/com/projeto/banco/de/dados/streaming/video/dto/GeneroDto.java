package com.projeto.banco.de.dados.streaming.video.dto;

import com.projeto.banco.de.dados.streaming.video.entity.Genero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneroDto {

	private Long idGenero;

	private String genero;

	public GeneroDto(Genero genero) {
		this.idGenero = genero.getIdGenero();
		this.genero = genero.getGenero();
	}
}
