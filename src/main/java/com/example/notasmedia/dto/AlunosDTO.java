package com.example.notasmedia.dto;

import com.example.notasmedia.entity.Alunos;
import com.example.notasmedia.entity.Professores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunosDTO {

    private Long id;

    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Nota 1 não pode ser nula")
    @Max(value = 10, message = "As notas devem estar entre 0 e 10")
    @Min(value = 0, message = "As notas devem estar entre 0 e 10")
    private Double n1;

    @NotNull(message = "Nota 2 não pode ser nula")
    @Max(value = 10, message = "As notas devem estar entre 0 e 10")
    @Min(value = 0, message = "As notas devem estar entre 0 e 10")
    private Double n2;

    @NotNull(message = "Nota 3 não pode ser nula")
    @Max(value = 10, message = "As notas devem estar entre 0 e 10")
    @Min(value = 0, message = "As notas devem estar entre 0 e 10")
    private Double n3;


    private Professores professores;


    public AlunosDTO(Alunos obj){
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.n1 = obj.getN1();
        this.n2 = obj.getN2();
        this.n3 = obj.getN3();

    }
}
