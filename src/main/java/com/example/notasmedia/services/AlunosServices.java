package com.example.notasmedia.services;

import com.example.notasmedia.dto.AlunosDTO;
import com.example.notasmedia.entity.Alunos;


import com.example.notasmedia.exceptions.ResourceNotFoundException;
import com.example.notasmedia.repository.AlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunosServices {

    @Autowired
    private AlunosRepository alunosRepository;


    public Alunos findById(Long id){
        Optional<Alunos> obj = alunosRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, Id: " + id));

    }

    public List<Alunos> findAll() {
        return alunosRepository.findAll();
    }

    public Alunos insert(Alunos obj){
        obj.setId(null);
        return alunosRepository.save(obj);
    }

    public Alunos update(Alunos obj){
        Alunos newObj = findById(obj.getId());
        updateData(newObj, obj);
        return alunosRepository.save(newObj);

    }

    public void delete(Long id){
       alunosRepository.deleteById(id);
    }



    public Alunos fromDTO(AlunosDTO objDto){
        return new Alunos(objDto.getId(), objDto.getNome(), objDto.getN1(), objDto.getN2(), objDto.getN3());
    }


    private void updateData(Alunos newObj, Alunos obj){
        newObj.setNome(obj.getNome());
        newObj.setN1(obj.getN1());
        newObj.setN2(obj.getN2());
        newObj.setN3(obj.getN3());
        newObj.setProfessores(obj.getProfessores());

    }



}
