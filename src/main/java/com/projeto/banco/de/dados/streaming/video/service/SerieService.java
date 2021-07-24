package com.projeto.banco.de.dados.streaming.video.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.banco.de.dados.streaming.video.dto.AtualizarEpisodioDto;
import com.projeto.banco.de.dados.streaming.video.dto.AtualizarSerieDto;
import com.projeto.banco.de.dados.streaming.video.dto.EpisodioDto;
import com.projeto.banco.de.dados.streaming.video.dto.InformacaoEpisodioDto;
import com.projeto.banco.de.dados.streaming.video.dto.SerieDto;
import com.projeto.banco.de.dados.streaming.video.entity.Episodio;
import com.projeto.banco.de.dados.streaming.video.entity.Genero;
import com.projeto.banco.de.dados.streaming.video.entity.Serie;
import com.projeto.banco.de.dados.streaming.video.exception.ExcecaoNegocio;
import com.projeto.banco.de.dados.streaming.video.repositoy.EpisodioRepository;
import com.projeto.banco.de.dados.streaming.video.repositoy.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private GeneroService generoService;

	@Autowired
	private SerieRepository serieRepository;

	@Autowired
	private EpisodioRepository episodioRepository;

	public List<SerieDto> findAll() {
		return serieRepository.findAll().stream().map(serie -> new SerieDto(serie)).collect(Collectors.toList());
	}

	public SerieDto findById(Long idSerie) {

		Optional<Serie> serie = serieRepository.findById(idSerie);

		if (serie.isPresent()) {
			return new SerieDto(serie.get());
		}

		throw new ExcecaoNegocio("Nao foi encontrada uma serie para o identificador passado.");
	}

	public Long insertSerie(SerieDto serieDto) {

		if (serieDto != null) {
			Serie serie = new Serie();

			serie.setTitulo(serieDto.getTitulo());

			serie.setSinopse(serieDto.getSinopse());

			serie.setAno(serieDto.getAno());

			serie.setNumeroTemporada(serieDto.getNumeroTemporada());

			serie.setAnoFim(serieDto.getAnoFim());

			serie.setGeneros(serieDto.getListaGenero().stream().map(genero -> {
				Genero generoNew = new Genero();
				generoNew.setIdGenero(generoService.persisteGenero(genero));
				return generoNew;
			}).collect(Collectors.toList()));

			serie = serieRepository.save(serie);

			for (EpisodioDto episodioDto : serieDto.getListaEpisodio()) {
				Episodio episodio = new Episodio();

				episodio.setNumero(episodioDto.getNumero());

				episodio.setTitulo(episodioDto.getTitulo());

				episodio.setSinopse(episodioDto.getSinopse());

				episodio.setTemporada(episodioDto.getTemporada());

				episodio.setSerie(serie);

				episodioRepository.save(episodio);
			}
			;

			return serie.getIdTitulo();

		}

		throw new ExcecaoNegocio("E necessario inforar a serie para o cadastro.");
	}

	public Long insertEpisodio(InformacaoEpisodioDto informacaoEpisodioDto) {

		if (informacaoEpisodioDto != null) {

			if (serieRepository.existsById(informacaoEpisodioDto.getIdSerie())) {
				Episodio episodio = new Episodio();

				episodio.setNumero(informacaoEpisodioDto.getNumero());

				episodio.setTitulo(informacaoEpisodioDto.getTitulo());

				episodio.setSinopse(informacaoEpisodioDto.getSinopse());

				episodio.setTemporada(informacaoEpisodioDto.getTemporada());

				Serie serie = new Serie();
				serie.setIdTitulo(informacaoEpisodioDto.getIdSerie());

				episodio.setSerie(serie);

				return episodioRepository.save(episodio).getIdEpisodio();
			}

			throw new ExcecaoNegocio("E necessario informar um identificador de serie valido.");
		}

		throw new ExcecaoNegocio("E necessario informar um episodio para o cadastro.");
	}

	public Long updateSerie(AtualizarSerieDto atualizarSerieDto) {

		if (atualizarSerieDto != null) {
			Optional<Serie> serie = serieRepository.findById(atualizarSerieDto.getIdSerie());

			if (serie.isPresent()) {

				serie.get().setTitulo(atualizarSerieDto.getTitulo());

				serie.get().setSinopse(atualizarSerieDto.getSinopse());

				serie.get().setAno(atualizarSerieDto.getAno());

				serie.get().setNumeroTemporada(atualizarSerieDto.getNumeroTemporada());

				serie.get().setAnoFim(atualizarSerieDto.getAnoFim());

				serie.get().setGeneros(atualizarSerieDto.getListaGenero().stream().map(genero -> {
					Genero generoNew = new Genero();
					generoNew.setIdGenero(generoService.persisteGenero(genero));
					return generoNew;
				}).collect(Collectors.toList()));

				return serieRepository.save(serie.get()).getIdTitulo();
			}

			throw new ExcecaoNegocio("Nao foi encontrado uma serie para o identificador passado.");
		}

		throw new ExcecaoNegocio("E necessario informar as informacoes da serie para atualizar.");
	}

	public Long updateEpisodio(AtualizarEpisodioDto atualizarEpisodioDto) {

		if (atualizarEpisodioDto != null) {

			Optional<Episodio> episodio = episodioRepository.findById(atualizarEpisodioDto.getIdEpisodio());

			if (episodio.isPresent()) {

				episodio.get().setNumero(atualizarEpisodioDto.getNumero());

				episodio.get().setTitulo(atualizarEpisodioDto.getTitulo());

				episodio.get().setSinopse(atualizarEpisodioDto.getSinopse());

				episodio.get().setTemporada(atualizarEpisodioDto.getTemporada());

				return episodioRepository.save(episodio.get()).getIdEpisodio();
			}

			throw new ExcecaoNegocio("E necessario informar um identificador de serie valido.");
		}

		throw new ExcecaoNegocio("E necessario informar um episodio para atualizar.");
	}

	@Transactional
	public String deleteSerie(Long idSerie) {

		if (serieRepository.existsById(idSerie)) {

			Serie serie = new Serie();
			serie.setIdTitulo(idSerie);

			if (episodioRepository.existsBySerie(serie)) {
				episodioRepository.deleteBySerie(idSerie);
			}

			serieRepository.deleteById(idSerie);

			return "Serie deletada com sucesso.";
		}

		throw new ExcecaoNegocio("Serie nao encontrada para o identificador passado.");
	}

	@Transactional
	public String deleteEpisodio(Long idEpisodio) {

		if (episodioRepository.existsById(idEpisodio)) {
			episodioRepository.deleteById(idEpisodio);

			return "Episodio deletado com sucesso.";
		}

		throw new ExcecaoNegocio("Episodio nao encontrado para o identificador passado.");
	}
}
