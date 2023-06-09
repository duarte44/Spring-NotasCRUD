package com.example.notasmedia.dto;

import com.example.notasmedia.entity.Alunos;
import com.example.notasmedia.entity.Professores;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessoresDTO {

    private Long id;

    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "Materia não pode ser nula")
    private String materia;


    public ProfessoresDTO(Professores obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.materia = obj.getMateria();
    }
}
