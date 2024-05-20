package com.example.expericeIA.service;

import com.example.expericeIA.entity.Avaliacao;
import com.example.expericeIA.entity.Login;
import com.example.expericeIA.entity.Trend;
import com.example.expericeIA.repository.AvaliacaoRepository;
import com.example.expericeIA.repository.LoginRepository;
import com.example.expericeIA.repository.TrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TrendService {

    @Autowired
    private TrendRepository trendRepository;

    public List<Trend> listarTodos() {
        return trendRepository.findAll();
    }

    public Trend salvar(Trend trend) {
        return trendRepository.save(trend);
    }

    public void excluir(Long id) {
        trendRepository.deleteById(id);
    }

    public Optional<Trend> buscarPorId(Long id) {
        return trendRepository.findById(id);
    }

    public Trend atualizar(Trend trend, Long id) {
        return trendRepository.findById(id)
                .map(trendExistente -> {
                    trendExistente.setTopico(trend.getTopico());
                    trendExistente.setFrequencia(trend.getFrequencia());
                    trendExistente.setSentimentoPositivo(trend.getSentimentoPositivo());
                    trendExistente.setSentimentoNeutro(trend.getSentimentoNeutro());
                    trendExistente.setSentimentoNegativo(trend.getSentimentoNegativo());
                    trendExistente.setDataInicio(trend.getDataInicio());
                    trendExistente.setDataFim(trend.getDataFim());

                    return trendRepository.save(trendExistente);
                }).orElseThrow(() -> new IllegalStateException("Tendência com ID " + id + " não existe."));
    }


    public Trend atualizarParcialmente(Long id, Map<String, Object> updates) {
        return trendRepository.findById(id)
                .map(trendExistente -> {
                    updates.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(Trend.class, key);
                        if (field != null) {
                            field.setAccessible(true);
                            if (field.getType().equals(LocalDate.class) && value instanceof String) {
                                LocalDate dateValue = LocalDate.parse((String) value);
                                ReflectionUtils.setField(field, trendExistente, dateValue);
                            } else {
                                ReflectionUtils.setField(field, trendExistente, value);
                            }
                        }
                    });
                    return trendRepository.save(trendExistente);
                }).orElseThrow(() -> new IllegalStateException("Tendência com ID " + id + " não existe."));
    }

}
