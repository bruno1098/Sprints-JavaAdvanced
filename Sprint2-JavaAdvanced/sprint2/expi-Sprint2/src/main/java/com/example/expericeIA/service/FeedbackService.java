package com.example.expericeIA.service;

import com.example.expericeIA.entity.Feedback;
import com.example.expericeIA.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> listarTodos() {
        return feedbackRepository.findAll();
    }

    public Feedback salvar(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public void excluir(Long id) {
        feedbackRepository.deleteById(id);
    }

    public Optional<Feedback> buscarPorId(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback atualizar(Feedback feedback, Long id) {
        return feedbackRepository.findById(id)
                .map(feedbackExistente -> {
                    feedbackExistente.setCliente(feedback.getCliente());
                    feedbackExistente.setDescricao(feedback.getDescricao());
                    feedbackExistente.setDataCriacao(feedback.getDataCriacao());
                    feedbackExistente.setSentimento(feedback.getSentimento());
                    return feedbackRepository.save(feedbackExistente);
                }).orElseThrow(() -> new IllegalStateException("Feedback com ID " + id + " não existe."));
    }

    public Feedback atualizarParcialmente(Long id, Map<String, Object> updates) {
        return feedbackRepository.findById(id)
                .map(feedbackExistente -> {
                    updates.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Feedback.class, key);
                        if (field != null) {
                            field.setAccessible(true);
                            ReflectionUtils.setField(field, feedbackExistente, value);
                        }
                    });
                    return feedbackRepository.save(feedbackExistente);
                }).orElseThrow(() -> new IllegalStateException("Feedback com ID " + id + " não existe."));
    }
}
