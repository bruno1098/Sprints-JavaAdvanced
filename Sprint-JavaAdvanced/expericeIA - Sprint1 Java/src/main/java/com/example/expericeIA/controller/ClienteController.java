package com.example.expericeIA.controller;

import com.example.expericeIA.service.ClienteService;
import com.example.expericeIA.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarUsuarios() {
        return clienteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Cliente> criarUsuario(@RequestBody Cliente usuario) {
        Cliente novoUsuario = clienteService.salvar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarUsuario(@PathVariable Long id, @RequestBody Cliente usuario) {
        Cliente usuarioAtualizado = clienteService.atualizar(usuario, id);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarUsuarioPorId(@PathVariable Long id) {
        Cliente usuario = clienteService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return ResponseEntity.ok(usuario);
    }
}
