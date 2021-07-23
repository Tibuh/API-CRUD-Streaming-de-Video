package com.projeto.banco.de.dados.streaming.video.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarUsuarioSistemaDto;
import com.projeto.banco.de.dados.streaming.video.dto.UsuarioAutenticarDto;
import com.projeto.banco.de.dados.streaming.video.dto.UsuarioSistemaDto;
import com.projeto.banco.de.dados.streaming.video.entity.UsuarioSistema;
import com.projeto.banco.de.dados.streaming.video.exception.ExcecaoNegocio;
import com.projeto.banco.de.dados.streaming.video.repositoy.UsuarioSistemaRepository;

@Service
public class UsuarioSistemaService {

	@Autowired
	private UsuarioSistemaRepository usuarioSistemaRepository;

	public List<UsuarioSistemaDto> findAll() {
		return usuarioSistemaRepository.findAll().stream().map(usuarioSistema -> new UsuarioSistemaDto(usuarioSistema))
				.collect(Collectors.toList());
	}

	public UsuarioSistemaDto findById(Long idUsuarioSistema) {
		Optional<UsuarioSistema> usuarioSistema = usuarioSistemaRepository.findById(idUsuarioSistema);
		if (usuarioSistema.isPresent()) {
			return new UsuarioSistemaDto(usuarioSistema.get());
		}

		throw new ExcecaoNegocio("Nao foi encontrado nenhum usuario com este id.");
	}

	public Long insertUser(UsuarioSistemaDto usuarioSistemaDto) {

		if (usuarioSistemaDto != null) {
			UsuarioSistema usuarioSistema = new UsuarioSistema();

			usuarioSistema.setLogin(usuarioSistemaDto.getLogin());
			usuarioSistema.setNome(usuarioSistemaDto.getNome());
			usuarioSistema.setSenha(usuarioSistemaDto.getSenha());
			usuarioSistema.setAdministrador(usuarioSistemaDto.isAdministrador());

			return usuarioSistemaRepository.save(usuarioSistema).getIdUsuarioSistema();
		}

		throw new ExcecaoNegocio("É necessário informar um usuario para o cadastro");
	}

	public Long updateUser(AtualizarUsuarioSistemaDto atualizarUsuarioSistemaDto) {

		if (atualizarUsuarioSistemaDto != null) {
			Optional<UsuarioSistema> usuarioSistema = usuarioSistemaRepository
					.findById(atualizarUsuarioSistemaDto.getIdUsuarioSistema());

			if (usuarioSistema.isPresent()) {
				usuarioSistema.get().setLogin(atualizarUsuarioSistemaDto.getLogin());
				usuarioSistema.get().setNome(atualizarUsuarioSistemaDto.getNome());
				usuarioSistema.get().setSenha(atualizarUsuarioSistemaDto.getSenha());
				usuarioSistema.get().setAdministrador(atualizarUsuarioSistemaDto.isAdministrador());

				return usuarioSistemaRepository.save(usuarioSistema.get()).getIdUsuarioSistema();
			}

			throw new ExcecaoNegocio("Usuario nao encontrado para o identificador passado.");
		}

		throw new ExcecaoNegocio("É necessário informar um usuario para atualizar.");
	}

	public String deleteUser(Long idUsuarioSistema) {
		Optional<UsuarioSistema> usuarioSistema = usuarioSistemaRepository.findById(idUsuarioSistema);

		if (usuarioSistema.isPresent()) {
			usuarioSistemaRepository.deleteById(idUsuarioSistema);
			return "Usuario deletado com sucesso";

		}
		throw new ExcecaoNegocio("Usuario nao encontrado para o identificador passado.");
	}

	public String autenticar(UsuarioAutenticarDto usuarioDto) {
		return "Logado com sucesso.";
	}
}
