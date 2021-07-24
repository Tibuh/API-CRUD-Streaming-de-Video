package com.projeto.banco.de.dados.streaming.video.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario_sistema")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSistema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_sistema")
	private Long idUsuarioSistema;

	private String login;

	private String nome;

	private String senha;

	private boolean administrador;
}
