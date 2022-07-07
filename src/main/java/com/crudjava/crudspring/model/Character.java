package com.crudjava.crudspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.crudjava.crudspring.dto.CharacterDTO;

import lombok.Data;

@Data
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    public CharacterDTO toDTO() {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(id);
        dto.setNome(nome);
        return dto;
    }

}
