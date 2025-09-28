package cinthia.lindolfo.ecolete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cinthia.lindolfo.ecolete.model.Coleta;

@Repository
public interface ColetaRepository extends JpaRepository<Coleta, Long> {
   List<Coleta> findByUsuarioId(Long usuarioId);
}