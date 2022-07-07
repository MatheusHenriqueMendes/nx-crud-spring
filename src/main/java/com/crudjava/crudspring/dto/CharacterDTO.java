package com.crudjava.crudspring.dto;

import com.crudjava.crudspring.model.Character;

import lombok.Data;

@Data
public class CharacterDTO {
  private Long id;

  private String nome;

  public Character toEntity() {
    Character entity = new Character();
    entity.setNome(nome);
    return entity;
  }
}
