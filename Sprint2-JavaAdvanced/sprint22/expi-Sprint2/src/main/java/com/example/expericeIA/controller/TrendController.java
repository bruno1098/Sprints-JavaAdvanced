package com.example.expericeIA.controller;

import com.example.expericeIA.entity.Trend;
import com.example.expericeIA.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/trends")
public class TrendController {

    @Autowired
    private TrendService trendService;

    // GET ok
    @GetMapping
    public List<Trend> listarUsuarios() {
        return trendService.listarTodos();
    }

    // POST ok
    @PostMapping
    public ResponseEntity<Trend> criarUsuario(@RequestBody Trend trend) {
        Trend novoUsuario = trendService.salvar(trend);
        return ResponseEntity.ok(novoUsuario);
    }

    // PUT ok
    @PutMapping("/{id}")
    public ResponseEntity<Trend> atualizarTendencia(@PathVariable Long id, @RequestBody Trend trend) {
        Trend tendenciaAtualizada = trendService.atualizar(trend, id);
        return ResponseEntity.ok(tendenciaAtualizada);
    }

    // DELETE ok
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        trendService.excluir(id);
        return ResponseEntity.ok().build();
    }

    // GET BY ID ok
    @GetMapping("/{id}")
    public ResponseEntity<Trend> consultarUsuarioPorId(@PathVariable Long id) {
        Trend trend = trendService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Tendência não encontrada"));
        return ResponseEntity.ok(trend);
    }

    // OPTIONS ok
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(@PathVariable Long id) {
        boolean tendenciaExiste = trendService.buscarPorId(id).isPresent();
        if (!tendenciaExiste) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
    }


    // PATCH ok
    @PatchMapping("/{id}")
    public ResponseEntity<Trend> atualizarParcialmenteUsuario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Trend tendenciaAtualizada = trendService.atualizarParcialmente(id, updates);
        return ResponseEntity.ok(tendenciaAtualizada);
    }

    // HEAD ok
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<?> consultarTendenciaHead(@PathVariable Long id) {
        Optional<Trend> trend = trendService.buscarPorId(id);
        if (!trend.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

}
