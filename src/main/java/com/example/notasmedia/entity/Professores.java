package com.example.notasmedia.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String materia;


    @OneToMany(mappedBy = "professores")
    private List<Alunos> alunos;

    public Professores(Long id, String nome, String materia) {
        this.id = id;
        this.nome = nome;
        this.materia = materia;
    }


}
