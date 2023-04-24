package com.example.notasmedia.services;

import com.example.notasmedia.entity.Professores;
import com.example.notasmedia.repository.AlunosRepository;
import com.example.notasmedia.repository.ProfessoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServices {

    @Autowired
    private ProfessoresRepository professoresRepository;

    @Autowired
    private AlunosRepository alunosRepository;

    public Professores insert(Professores obj){
        obj.setId(null);
        obj = professoresRepository.save(obj);
        alunosRepository.saveAll(obj.getAlunos());
        return obj;

    }


}
