package com.crudjava.crudspring.dto;

import com.crudjava.crudspring.model.Colaborador;
import lombok.Data;

import java.util.List;

@Data
public class ColaboradorDTO {
  private Long id;
  private String nome;
  private String observacao;
  private String cpf;

  public Colaborador toEntity() {
    Colaborador entity = new Colaborador();
    entity.setId(id);
    entity.setNome(nome);
    entity.setObservacao(observacao);
    entity.setCpf(cpf);
    return entity;
  }
}
