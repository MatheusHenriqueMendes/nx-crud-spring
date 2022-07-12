package com.crudjava.crudspring.controller;

import java.util.List;
import java.util.Optional;

import com.crudjava.crudspring.dto.CharacterDTO;
import com.crudjava.crudspring.model.Character;
import com.crudjava.crudspring.repository.CharacterRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/characters")
@AllArgsConstructor
public class CharacterController {
  private final CharacterRepository characterRepository;

  @GetMapping
  public List<Character> listar() {
    return characterRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<CharacterDTO> criar(@RequestBody CharacterDTO characterDTO) {
    Character character = characterDTO.toEntity();
    characterRepository.save(character);
    return new ResponseEntity<>(character.toDTO(), HttpStatus.CREATED);
  }

  @DeleteMapping(path = { "/{id}" })
  public ResponseEntity<Long> excluir(@PathVariable long id) {
    Optional<Character> character = characterRepository.findById(id);
    if (character.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    characterRepository.deleteById(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
