package com.projeto.banco.de.dados.streaming.video.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarGeneroDto;
import com.projeto.banco.de.dados.streaming.video.dto.GeneroDto;
import com.projeto.banco.de.dados.streaming.video.entity.Genero;
import com.projeto.banco.de.dados.streaming.video.exception.ExcecaoNegocio;
import com.projeto.banco.de.dados.streaming.video.repositoy.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	@Transactional(readOnly = true)
	public List<GeneroDto> findAll() {
		return generoRepository.findAll().stream().map(genero -> new GeneroDto(genero)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public GeneroDto findById(Long idGenero) {
		Optional<Genero> genero = generoRepository.findById(idGenero);

		if (genero.isPresent()) {
			return new GeneroDto(genero.get());
		}

		throw new ExcecaoNegocio("Nao foi encontrado nenhum genero para este identificador.");
	}

	public Long insertGenero(GeneroDto generoDto) {

		if (generoDto != null) {
			Genero genero = new Genero();

			genero.setGenero(generoDto.getGenero());

			return generoRepository.save(genero).getIdGenero();
		}

		throw new ExcecaoNegocio("E necessario informar um genero para ser cadastrado.");
	}

	public Long updateGenero(AtualizarGeneroDto atualizarGeneroDto) {

		if (atualizarGeneroDto != null) {
			Optional<Genero> genero = generoRepository.findById(atualizarGeneroDto.getIdGenero());

			if (genero.isPresent()) {
				genero.get().setGenero(atualizarGeneroDto.getGenero());
				return generoRepository.save(genero.get()).getIdGenero();
			}

			throw new ExcecaoNegocio("Nao foi encontrado um genero para este identificador.");
		}

		throw new ExcecaoNegocio("E necessario informar um genero para ser cadastrado.");
	}

	public String deleteGenero(Long idGenero) {
		Optional<Genero> genero = generoRepository.findById(idGenero);

		if (genero.isPresent()) {
			generoRepository.deleteById(idGenero);
			return "Genero deletado com sucesso.";
		}

		throw new ExcecaoNegocio("Nao foi encontrado um genero para este identificador.");
	}
}
