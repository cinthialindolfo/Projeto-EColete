package cinthia.lindolfo.ecolete.controller;

import cinthia.lindolfo.ecolete.dto.UsuarioDTO;
import cinthia.lindolfo.ecolete.model.Usuario;
import cinthia.lindolfo.ecolete.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<UsuarioDTO> perfil(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(u -> {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setId(u.getId());
                    dto.setNome(u.getNome());
                    dto.setEmail(u.getEmail());
                    dto.setCelular(u.getCelular());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/perfil/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return usuarioService.buscarPorId(id)
                .map(u -> {
                    u.setNome(dto.getNome());
                    u.setEmail(dto.getEmail());
                    u.setCelular(dto.getCelular());
                    Usuario atualizado = usuarioService.cadastrar(u); 
                    dto.setId(atualizado.getId());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UsuarioDTO>> ranking() {
        List<UsuarioDTO> ranking = usuarioService.listarTodos().stream()
                .map(u -> {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setId(u.getId());
                    dto.setNome(u.getNome());
                    
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(ranking);
    }
}