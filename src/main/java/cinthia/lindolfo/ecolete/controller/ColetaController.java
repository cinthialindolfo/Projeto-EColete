package cinthia.lindolfo.ecolete.controller;

import cinthia.lindolfo.ecolete.dto.ColetaDTO;
import cinthia.lindolfo.ecolete.model.Coleta;
import cinthia.lindolfo.ecolete.model.Usuario;
import cinthia.lindolfo.ecolete.service.ColetaService;
import cinthia.lindolfo.ecolete.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/coletas")
@Tag(name = "Coletas", description = "API para agendamento e gestão de coletas")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Agendar nova coleta")
    public ResponseEntity<ColetaDTO> agendar(@Valid @RequestBody ColetaDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Coleta coleta = new Coleta();
        coleta.setDescricaoMaterial(dto.getDescricaoMaterial());
        coleta.setQuantidadeMaterial(dto.getQuantidadeMaterial());
        coleta.setDataAgendamento(dto.getDataAgendamento());
        coleta.setUsuario(usuario);

        Coleta salva = coletaService.agendar(coleta);

        ColetaDTO resposta = toDTO(salva);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    @Operation(summary = "Listar coletas por usuário")
    public ResponseEntity<List<ColetaDTO>> historico(@RequestParam Long usuarioId) {
        List<ColetaDTO> coletas = coletaService.listarPorUsuario(usuarioId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(coletas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar coleta por ID")
    public ResponseEntity<ColetaDTO> buscarPorId(@PathVariable Long id) {
        return coletaService.buscarPorId(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar coleta")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        coletaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    private ColetaDTO toDTO(Coleta coleta) {
        ColetaDTO dto = new ColetaDTO();
        dto.setId(coleta.getId());
        dto.setDescricaoMaterial(coleta.getDescricaoMaterial());
        dto.setQuantidadeMaterial(coleta.getQuantidadeMaterial());
        dto.setDataAgendamento(coleta.getDataAgendamento());
        dto.setDataConclusao(coleta.getDataConclusao());
        dto.setStatus(coleta.getStatus());
        dto.setUsuarioId(coleta.getUsuario().getId());
        return dto;
    }
}