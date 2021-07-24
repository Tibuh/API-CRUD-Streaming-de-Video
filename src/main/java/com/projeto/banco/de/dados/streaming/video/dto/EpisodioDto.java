package com.projeto.banco.de.dados.streaming.video.dto;

import com.projeto.banco.de.dados.streaming.video.entity.Episodio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodioDto {

	private Long idEpisodio;

	private int numero;

	private String titulo;

	private String sinopse;

	private int temporada;

	public EpisodioDto(Episodio episodio) {
		this.idEpisodio = episodio.getIdEpisodio();
		this.numero = episodio.getNumero();
		this.titulo = episodio.getTitulo();
		this.sinopse = episodio.getSinopse();
		this.temporada = episodio.getTemporada();
	}
}
