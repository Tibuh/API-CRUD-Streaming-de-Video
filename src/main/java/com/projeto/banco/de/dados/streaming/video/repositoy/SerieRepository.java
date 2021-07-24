package com.projeto.banco.de.dados.streaming.video.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.banco.de.dados.streaming.video.entity.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}
