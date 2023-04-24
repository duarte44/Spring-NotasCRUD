package com.example.notasmedia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    private Double n1;

    private Double n2;

    private Double n3;

    public String getResultado(){
        Double m = (n1 + n2 + n3) / 3;
        if(m < 5){
            return "Media: " + String.format("%.2f", m) + ". Aluno Reprovado";
        }
        else{
            return "Media: " + String.format("%.2f", m) + ". Aluno Aprovado";
        }

    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "professor_id")
    private Professores professores;

}
