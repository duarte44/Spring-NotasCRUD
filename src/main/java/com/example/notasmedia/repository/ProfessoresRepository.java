package com.example.notasmedia.repository;

import com.example.notasmedia.entity.Alunos;
import com.example.notasmedia.entity.Professores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessoresRepository extends JpaRepository<Professores, Long> {
}
