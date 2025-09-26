package cinthia.lindolfo.ecolete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cinthia.lindolfo.ecolete.model.Coleta;

@Repository
public interface ColetaRepository extends JpaRepository<Coleta, Long> {
    // Assim como o UsuarioRepository, esta interface já herda todos os
    // métodos básicos para manipular os dados de Coleta no banco de dados.
}