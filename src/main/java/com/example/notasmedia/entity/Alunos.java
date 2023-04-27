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

    public Alunos(Long id, String nome, Double n1, Double n2, Double n3) {
        this.id = id;
        this.nome = nome;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "professor_id")
    private Professores professores;

}
