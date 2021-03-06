package com.projeto.banco.de.dados.streaming.video.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarFilmeDto;
import com.projeto.banco.de.dados.streaming.video.dto.FilmeDto;
import com.projeto.banco.de.dados.streaming.video.dto.GeneroDto;
import com.projeto.banco.de.dados.streaming.video.dto.InsertFilmeDto;
import com.projeto.banco.de.dados.streaming.video.entity.Filme;
import com.projeto.banco.de.dados.streaming.video.entity.Genero;
import com.projeto.banco.de.dados.streaming.video.exception.ExcecaoNegocio;
import com.projeto.banco.de.dados.streaming.video.repositoy.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private GeneroService generoService;

	@Autowired
	private FilmeRepository filmeRepository;

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

	public Long insertFilme(InsertFilmeDto insertFilmeDto) {

		if (insertFilmeDto != null) {
			Filme filme = new Filme();

			filme.setTitulo(insertFilmeDto.getTitulo());

			filme.setSinopse(insertFilmeDto.getSinopse());

			filme.setAno(insertFilmeDto.getAno());

			filme.setDataLancamento(insertFilmeDto.getDataLancamento());

			List<String> listaGenero = Arrays.asList(insertFilmeDto.getListaGenero().split(","));

			filme.setGeneros(listaGenero.stream().map(genero -> {
				Genero generoNew = new Genero();
				generoNew.setIdGenero(generoService.persisteGenero(new GeneroDto(genero.trim())));
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

				List<String> listaGenero = Arrays.asList(atualizarFilmeDto.getListaGenero().split(","));

				filme.get().setGeneros(listaGenero.stream().map(genero -> {
					Genero generoNew = new Genero();
					generoNew.setIdGenero(generoService.persisteGenero(new GeneroDto(genero.trim())));
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

}
