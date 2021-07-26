package com.projeto.banco.de.dados.streaming.video.dto;

import com.projeto.banco.de.dados.streaming.video.entity.Episodio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListagemEpisodioDto {

	private Long idEpisodio;

	private int numero;

	private String titulo;

	private String sinopse;

	private int temporada;

	private Long idSerie;

	public ListagemEpisodioDto(Episodio episodio) {
		this.idEpisodio = episodio.getIdEpisodio();
		this.numero = episodio.getNumero();
		this.titulo = episodio.getTitulo();
		this.sinopse = episodio.getSinopse();
		this.temporada = episodio.getTemporada();
		this.idEpisodio = episodio.getSerie().getIdTitulo();
	}

}
