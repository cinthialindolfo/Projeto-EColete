package cinthia.lindolfo.ecolete.controller;

import cinthia.lindolfo.ecolete.config.JwtUtil;
import cinthia.lindolfo.ecolete.dto.CadastroDTO;
import cinthia.lindolfo.ecolete.dto.LoginDTO;
import cinthia.lindolfo.ecolete.model.Usuario;
import cinthia.lindolfo.ecolete.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String, String>> cadastrar(@Valid @RequestBody CadastroDTO dto) {
        if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
            Map<String, String> erro = Map.of("erro", "As senhas não coincidem.");
            return ResponseEntity.badRequest().body(erro);
        }

        if (usuarioService.emailJaExiste(dto.getEmail())) {
            Map<String, String> erro = Map.of("erro", "Email já cadastrado.");
            return ResponseEntity.badRequest().body(erro);
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCelular(dto.getCelular());

        Usuario salvo = usuarioService.cadastrar(usuario);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Cadastro realizado com sucesso!");
        resposta.put("id", String.valueOf(salvo.getId()));
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        var usuarioOpt = usuarioService.buscarPorEmail(dto.getEmail());
        Map<String, String> resposta = new HashMap<>();

        if (usuarioOpt.isPresent() && passwordEncoder.matches(dto.getSenha(), usuarioOpt.get().getSenha())) {
            String token = jwtUtil.generateToken(dto.getEmail());
            resposta.put("token", token);
            resposta.put("mensagem", "Login realizado com sucesso!");
            return ResponseEntity.ok(resposta);
        }

        resposta.put("erro", "Credenciais inválidas.");
        return ResponseEntity.status(401).body(resposta);
    }
}