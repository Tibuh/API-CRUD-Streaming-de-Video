package com.projeto.banco.de.dados.streaming.video.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "serie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="id_titulo")
public class Serie extends Titulo {

	private static final long serialVersionUID = 1L;
	
	private byte numeroTemporada;

	private LocalDate anoFim;
	
	@OneToMany(mappedBy="serie", fetch=FetchType.EAGER)
	private List<Episodio> episodios;
}
