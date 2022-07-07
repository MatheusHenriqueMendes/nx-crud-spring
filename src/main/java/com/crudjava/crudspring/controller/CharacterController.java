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
  private final CharacterRepository estabelecimentoRepository;

  @GetMapping
  public List<Character> listar() {
    return estabelecimentoRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<CharacterDTO> criar(@RequestBody CharacterDTO estabelecimentoDTO) {
    Character estabelecimento = estabelecimentoDTO.toEntity();
    estabelecimentoRepository.save(estabelecimento);
    return new ResponseEntity<>(estabelecimento.toDTO(), HttpStatus.CREATED);
  }

  @DeleteMapping(path = { "/{id}" })
  public ResponseEntity<Long> excluir(@PathVariable long id) {
    Optional<Character> estabelecimento = estabelecimentoRepository.findById(id);
    if (estabelecimento.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    estabelecimentoRepository.deleteById(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
