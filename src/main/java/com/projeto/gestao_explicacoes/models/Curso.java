package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Curso extends BaseModel{

  private String nome;

  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //@JsonBackReference
  @JsonIgnore
  private Faculdade faculdade; // adicionado em "Faculdade"

  @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Cadeira> cadeiras = new HashSet<>();

  @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Aluno>  alunos = new HashSet<>();

  // ****** METHODS ******

  public Curso(String nome) {
    this.nome = nome;
  }

  public void addCadeira(Cadeira cadeira){
    this.cadeiras.add(cadeira);
    cadeira.setCurso(this);
  }

  public void removeCadeira(Cadeira cadeira) {
    this.cadeiras.remove(cadeira);
    cadeira.setCurso(null);
  }

  public void addAluno(Aluno aluno){
    this.alunos.add(aluno);
    aluno.setCurso(this);
  }

  public void removeAluno(Aluno aluno){
    this.alunos.remove(aluno);
    aluno.setCurso(null);
  }

}