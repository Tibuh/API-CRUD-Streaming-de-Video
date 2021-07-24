package com.projeto.banco.de.dados.streaming.video.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarFilmeDto;
import com.projeto.banco.de.dados.streaming.video.dto.FilmeDto;
import com.projeto.banco.de.dados.streaming.video.dto.GeneroDto;
import com.projeto.banco.de.dados.streaming.video.entity.Filme;
import com.projeto.banco.de.dados.streaming.video.entity.Genero;
import com.projeto.banco.de.dados.streaming.video.exception.ExcecaoNegocio;
import com.projeto.banco.de.dados.streaming.video.repositoy.FilmeRepository;
import com.projeto.banco.de.dados.streaming.video.repositoy.GeneroRepository;

@Service
public class FilmeService {

	@Autowired
	private GeneroService generoService;

	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private GeneroRepository generoRepository;

	public List<FilmeDto> findAll() {
		return filmeRepository.findAll().stream().map(filme -> new FilmeDto(filme)).collect(Collectors.toList());
	}

	public FilmeDto findById(Long idFilme) {
		Optional<Filme> filme = filmeRepository.findById(idFilme);

		if (filme.isPresent()) {
			return new FilmeDto(filme.get());
		}

		throw new ExcecaoNegocio("Filme nao encontrado para o identificador passado.");
	}

	public Long insertFilme(FilmeDto filmeDto) {

		if (filmeDto != null) {
			Filme filme = new Filme();

			filme.setTitulo(filmeDto.getTitulo());

			filme.setSinopse(filmeDto.getSinopse());

			filme.setAno(filmeDto.getAno());

			filme.setDataLancamento(filmeDto.getDataLancamento());

			filme.setGeneros(filmeDto.getListaGenero().stream().map(genero -> {
				Genero generoNew = new Genero();
				generoNew.setIdGenero(persisteGenero(genero));
				return generoNew;
			}).collect(Collectors.toList()));
			return filmeRepository.save(filme).getIdTitulo();
		}

		throw new ExcecaoNegocio("E necessario informar um filme para o cadastro.");
	}

	public Long updateFilme(AtualizarFilmeDto atualizarFilmeDto) {

		if (atualizarFilmeDto != null) {
			Optional<Filme> filme = filmeRepository.findById(atualizarFilmeDto.getIdTitulo());

			if (filme.isPresent()) {
				filme.get().setTitulo(atualizarFilmeDto.getTitulo());

				filme.get().setSinopse(atualizarFilmeDto.getSinopse());

				filme.get().setAno(atualizarFilmeDto.getAno());

				filme.get().setDataLancamento(atualizarFilmeDto.getDataLancamento());

				filme.get().setGeneros(atualizarFilmeDto.getListaGenero().stream().map(genero -> {
					Genero generoNew = new Genero();
					generoNew.setIdGenero(persisteGenero(genero));
					return generoNew;
				}).collect(Collectors.toList()));

				return filmeRepository.save(filme.get()).getIdTitulo();
			}

			throw new ExcecaoNegocio("Filme nao encontrado para identificador passado.");
		}

		throw new ExcecaoNegocio("E necessario informar um filme para atualizar cadastro.");
	}

	public String deleteFilme(Long idTitulo) {

		Optional<Filme> filme = filmeRepository.findById(idTitulo);

		if (filme.isPresent()) {
			filmeRepository.deleteById(idTitulo);
			return "Filme deletado com sucesso.";
		}

		throw new ExcecaoNegocio("Filme nao encontrado para o identificador recebido.");
	}

	private Long persisteGenero(GeneroDto generoDto) {

		Optional<Genero> genero = generoRepository.findByGenero(generoDto.getGenero());

		return genero.isPresent() ? genero.get().getIdGenero() : generoService.insertGenero(generoDto);
	}
}
