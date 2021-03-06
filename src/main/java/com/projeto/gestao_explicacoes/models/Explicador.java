package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Explicador extends BaseModel{

  private String nome;
  private Integer numero;

  @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Horario> horarios = new HashSet<>();

  @ManyToMany(mappedBy = "explicadores" , cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Idioma> idiomas = new HashSet<>(); // tabela criada automaticamente

  @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Atendimento> atendimentos = new HashSet<>();

  @ManyToMany(mappedBy = "explicadores", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Cadeira> cadeiras = new HashSet<>();

  // ****** METHODS ******

  public Explicador(String nome){
    this.nome = nome;
  }

  public Explicador(String nome, Integer numero) {
    this.nome = nome;
    this.numero = numero;
  }

  public void addHorario(Horario horario){
    this.horarios.add(horario);
    horario.setExplicador(this);
  }

  public void removeHorario(Horario horario){
    this.horarios.remove(horario);
    horario.setExplicador(null);
  }

  /**
   * Verifica a existencia do horario na colecção
   * @param horario
   * @return true se existir e false se não
   */
  public boolean containsHorario(Horario horario) {
    for (Horario auxHorario: this.horarios) {
      if (auxHorario.getDiaSemana().equals(horario.getDiaSemana())) {
        if (auxHorario.getHoraInicio().equals(horario.getHoraInicio())) {
          if (auxHorario.getHoraFim().equals(horario.getHoraFim())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public void addIdioma(Idioma idioma){
    this.idiomas.add(idioma);
    idioma.getExplicadores().add(this);
  }

  public void removeIdioma(Idioma idioma){
    this.idiomas.remove(idioma);
    idioma.getExplicadores().remove(this);
  }

  /**
   * Verifica a existencia do {@code idioma} na colecção
   * @param idioma idioma a verificar
   * @return {@code true} se existir e {@code false} se não
   */
  public boolean containsIdioma(Idioma idioma) {
    for (Idioma auxIdioma: this.idiomas) {
      if (idioma.getNome() != null && idioma.getSigla() != null) {
        if (auxIdioma.getNome().equals(idioma.getNome().toUpperCase())) {
          if (auxIdioma.getSigla().equals(idioma.getSigla().toUpperCase())) {
            return true;
          }
        }
      } else {
        throw new FalhaCriarException("Tem que introduzir um Nome e uma Sigla no Idioma!!");
      }
    }
    return false;
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setExplicador(this);
  }

  public void removeAtendimento(Atendimento atendimento){
    this.atendimentos.remove(atendimento);
    atendimento.setExplicador(null);
  }

  /**
   * Verifica a existencia do atendimento na colecção
   * @param atendimento
   * @return true se existir e false se não
   */
  public boolean containsAtendimento(Atendimento atendimento) {
    for (Atendimento auxAtendimento : this.atendimentos) {
      if (auxAtendimento.getData().equals(atendimento.getData())) {
        return true;
      }
    }
    return false;
  }

  public void addCadeira(Cadeira cadeira) {
    this.cadeiras.add(cadeira);
    cadeira.getExplicadores().add(this);
  }

  public void removeCadeira(Cadeira cadeira) {
    this.cadeiras.remove(cadeira);
    cadeira.getExplicadores().remove(this);
  }

  /**
   * Verifica a existencia do cadeira na colecção
   * @param cadeira
   * @return true se existir e false se não
   */
  public boolean containsCadeira(Cadeira cadeira) {
    for (Cadeira auxCadeira : this.cadeiras) {
      if (auxCadeira.getNome().equals(cadeira.getNome())) {
        if (auxCadeira.getSigla().equals(cadeira.getSigla())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Permite copiar um objeto Explicador para um objeto ExplicadorDTO
   *
   * @return o objeto ExplicadorDTO criado
   */
  public ExplicadorDTO copyToExplicadorDTO() {
    return new ExplicadorDTO(
            this.getNome(),
            this.getNumero(),
            this.getHorarios(),
            this.getIdiomas(),
            this.getCadeiras());
  }

}