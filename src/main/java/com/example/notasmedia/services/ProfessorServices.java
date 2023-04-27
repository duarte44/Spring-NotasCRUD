package com.example.notasmedia.services;

import com.example.notasmedia.dto.AlunosDTO;
import com.example.notasmedia.dto.ProfessoresDTO;
import com.example.notasmedia.entity.Alunos;
import com.example.notasmedia.entity.Professores;
import com.example.notasmedia.exceptions.ResourceNotFoundException;
import com.example.notasmedia.repository.AlunosRepository;
import com.example.notasmedia.repository.ProfessoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServices {

    @Autowired
    private ProfessoresRepository professoresRepository;




    public List<Professores> findAll() {
        return professoresRepository.findAll();
    }

    public Professores findById(Long id){
        Optional<Professores> obj = professoresRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado, Id: " + id));

    }

    public Professores insert(Professores obj){
        obj.setId(null);
        obj = professoresRepository.save(obj);

        return obj;

    }

    public void delete(Long id){
        professoresRepository.deleteById(id);
    }

    public Professores update(Professores obj){
        Professores newObj = findById(obj.getId());
        updateData(newObj, obj);
        return professoresRepository.save(newObj);

    }

    private void updateData(Professores newObj, Professores obj){
        newObj.setNome(obj.getNome());
        newObj.setMateria(obj.getMateria());
        newObj.setAlunos(obj.getAlunos());


    }



    public Professores fromDTO(ProfessoresDTO objDto){
        return new Professores(objDto.getId(), objDto.getNome(), objDto.getMateria());
    }


}
