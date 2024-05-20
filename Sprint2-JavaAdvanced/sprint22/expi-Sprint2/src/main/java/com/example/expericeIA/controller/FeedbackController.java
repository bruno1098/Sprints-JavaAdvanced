package com.example.expericeIA.controller;

import com.example.expericeIA.entity.Feedback;
import com.example.expericeIA.entity.Trend;
import com.example.expericeIA.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // GET ok
    @GetMapping
    public List<Feedback> listarFeedbacks() {
        return feedbackService.listarTodos();
    }

    // POST ok
    @PostMapping
    public ResponseEntity<Feedback> criarFeedback(@RequestBody Feedback feedback) {
        Feedback novoFeedback = feedbackService.salvar(feedback);
        return ResponseEntity.ok(novoFeedback);
    }

    // PUT ok
    @PutMapping("/{id}")
    public ResponseEntity<Feedback> atualizarFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Feedback feedbackAtualizado = feedbackService.atualizar(feedback, id);
        return ResponseEntity.ok(feedbackAtualizado);
    }

    // DELETE ok
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFeedback(@PathVariable Long id) {
        feedbackService.excluir(id);
        return ResponseEntity.ok().build();
    }

    // GET BY ID ok
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> consultarFeedbackPorId(@PathVariable Long id) {
        Feedback feedback = feedbackService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback não encontrado"));
        return ResponseEntity.ok(feedback);
    }

    // OPTIONS ok
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(@PathVariable Long id) {
        boolean tendenciaExiste = feedbackService.buscarPorId(id).isPresent();
        if (!tendenciaExiste) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
    }


    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Feedback> atualizarParcialmenteUsuario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Feedback feedbackAtualizado = feedbackService.atualizarParcialmente(id, updates);
        return ResponseEntity.ok(feedbackAtualizado);
    }

    // HEAD ok
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<?> consultarFeedbackHead(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.buscarPorId(id);
        if (!feedback.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
