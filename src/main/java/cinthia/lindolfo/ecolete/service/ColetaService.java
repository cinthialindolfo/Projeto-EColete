package cinthia.lindolfo.ecolete.service;

import cinthia.lindolfo.ecolete.model.Coleta;
import cinthia.lindolfo.ecolete.model.StatusColeta;
import cinthia.lindolfo.ecolete.model.Usuario;
import cinthia.lindolfo.ecolete.repository.ColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Coleta> listarPorUsuario(Long usuarioId) {
        return coletaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Coleta> buscarPorId(Long id) {
        return coletaRepository.findById(id);
    }

    @Transactional
    public Coleta agendar(Coleta coleta) {
        coleta.setStatus(StatusColeta.AGENDADA.name());
        return coletaRepository.save(coleta);
    }

    @Transactional
    public void cancelar(Long id) {
        Coleta coleta = coletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coleta não encontrada"));
        coleta.setStatus(StatusColeta.CANCELADA.name());
        coletaRepository.save(coleta);
    }

    @Transactional
    public void concluir(Long id) {
        Coleta coleta = coletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coleta não encontrada"));
        coleta.setStatus(StatusColeta.CONCLUIDA.name());
        coleta.setDataConclusao(java.time.LocalDateTime.now());
        
        Integer pontos = coleta.getQuantidadeMaterial() * 10;
        Usuario usuario = coleta.getUsuario();
        usuario.adicionarPontos(pontos);
        
        coletaRepository.save(coleta);
    }
}