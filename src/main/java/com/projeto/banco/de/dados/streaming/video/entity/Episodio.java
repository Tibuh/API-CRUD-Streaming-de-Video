package com.projeto.banco.de.dados.streaming.video.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "episodio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Episodio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_episodio")
	private Long idVideo;

	private int numero;

	private String titulo;

	private String sinopse;

	private int temporada;

	@OneToOne
	@JoinColumn(name = "id_video", unique = true, nullable = true)
	private Video video;

	@ManyToOne
	private Serie serie;
}
