package com.example.expericeIA.repository;

import com.example.expericeIA.entity.Login;
import com.example.expericeIA.entity.Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendRepository extends JpaRepository<Trend, Long> {
}
