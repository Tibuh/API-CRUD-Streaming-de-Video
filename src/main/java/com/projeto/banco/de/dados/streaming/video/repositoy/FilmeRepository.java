package com.projeto.banco.de.dados.streaming.video.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.banco.de.dados.streaming.video.entity.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
