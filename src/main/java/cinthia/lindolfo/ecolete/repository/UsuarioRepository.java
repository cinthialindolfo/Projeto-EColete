package cinthia.lindolfo.ecolete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cinthia.lindolfo.ecolete.model.Usuario;

@Repository // Anotação que indica ao Spring que esta é uma interface de repositório.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // A mágica acontece aqui!
    // Ao estender JpaRepository, nós automaticamente ganhamos métodos como:
    // save() - para salvar um usuário
    // findById() - para buscar um usuário pelo seu id
    // findAll() - para listar todos os usuários
    // deleteById() - para remover um usuário
    // E muitos outros! Não precisamos escrever nada aqui por enquanto.
}