package com.projeto.banco.de.dados.streaming.video.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "filme")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="id_titulo")
public class Filme extends Titulo {

	private static final long serialVersionUID = 1L;
	
	private LocalDate dataLancamento;
	
	@OneToOne
    @JoinColumn(name="id_video", unique= true, nullable=true)
	private Video video;

}
