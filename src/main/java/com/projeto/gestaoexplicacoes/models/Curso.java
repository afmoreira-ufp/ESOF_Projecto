package com.projeto.gestaoexplicacoes.models;

import java.util.Set;

public class Curso {

  private String nome;

  private Faculdade faculdade;
  private Set<Cadeira> cadeiras;
  private Set<Aluno>  alunos;
}