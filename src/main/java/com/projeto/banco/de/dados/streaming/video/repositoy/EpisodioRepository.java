package com.projeto.banco.de.dados.streaming.video.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projeto.banco.de.dados.streaming.video.entity.Episodio;
import com.projeto.banco.de.dados.streaming.video.entity.Serie;

public interface EpisodioRepository extends JpaRepository<Episodio, Long> {

	boolean existsBySerie(Serie serie);

	@Modifying
	@Query(value = "DELETE FROM episodio WHERE id_titulo = :idSerie", nativeQuery = true)
	void deleteBySerie(@Param("idSerie") Long idSerie);
}
