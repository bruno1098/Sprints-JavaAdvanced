package com.example.expericeIA.service;

import com.example.expericeIA.entity.Cliente;
import com.example.expericeIA.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente usuario) {
        return clienteRepository.save(usuario);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar(Cliente usuario, Long id) {
        return clienteRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuario.getNome());
                    usuarioExistente.setEmail(usuario.getEmail());
                    usuarioExistente.setTelefone(usuario.getTelefone());
                    usuarioExistente.setCnpj(usuario.getCnpj());
                    usuarioExistente.setTipoEmpresa(usuario.getTipoEmpresa());


                    return clienteRepository.save(usuarioExistente);
                }).orElseThrow(() -> new IllegalStateException("Usuário com ID " + id + " não existe."));
    }
}