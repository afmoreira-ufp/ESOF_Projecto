package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Cadeira extends BaseModel{

  private String nome;
  private String sigla;

  @ManyToOne
  private Curso curso; // adicionado em "Curso"

  @ManyToMany
  private Set<Explicador> explicadores = new HashSet<>(); // adicionado em "Explicador"

  @OneToMany(mappedBy = "cadeira")
  private Set<Atendimento> atendimentos = new HashSet<>();

  // ****** METHODS ******

  public Cadeira(String nome, String sigla) {
    this.nome = nome;
    this.sigla = sigla;
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setCadeira(this);
  }
}