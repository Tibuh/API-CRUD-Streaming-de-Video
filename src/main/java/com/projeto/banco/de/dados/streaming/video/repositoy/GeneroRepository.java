package com.projeto.banco.de.dados.streaming.video.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.banco.de.dados.streaming.video.entity.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

	Optional<Genero> findByGenero(String genero);
}
