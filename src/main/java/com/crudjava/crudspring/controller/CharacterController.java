package com.crudjava.crudspring.controller;

import java.util.Optional;

import com.crudjava.crudspring.dto.CharacterDTO;
import com.crudjava.crudspring.model.Character;
import com.crudjava.crudspring.repository.CharacterRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/characters")
@AllArgsConstructor
public class CharacterController {
  private final CharacterRepository characterRepository;

  @GetMapping(params = "id")
  public Optional<Character> listar(@RequestParam long id) {
    return characterRepository.findById(id);
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
